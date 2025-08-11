package model.Animals;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.GamePlayController;
import model.*;
import model.Animations.*;
import model.enums.AnimalEnum.AnimalType;
import model.Animals.AnimalStrategy.AnimalStrategy;

import java.util.ArrayList;
import java.util.Random;

public class Animal extends MapObj {
    private AnimalHome home;
    private String name;
    private int friendship;
    private AnimalType type;
    private DateTime lastProduction;
    private boolean hasBeenPet;
    private AnimalStrategy strategy;
    private boolean hasBeenFed;
    private ArrayList<AnimalProduct> products = new ArrayList<>();
    private boolean isInHome;
    private final GamePlayController controller = new GamePlayController();

    private final Stage stage;
    private final Skin skin;

    // Animation fields
    private Animation<TextureRegion> walkDown;
    private Animation<TextureRegion> walkRight;
    private Animation<TextureRegion> walkLeft;
    private Animation<TextureRegion> walkUp;
    private Animation<TextureRegion> idle;
    private Animation<TextureRegion> pet;
    private Animation<TextureRegion> currentAnimation;

    private final int width = 32;
    private final int height = 32;
    private float stateTime = 0;

    public Animal(String name, AnimalType type, Stage stage) {
        this.stage = stage;
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.name = name;
        this.type = type;
//        this.lastProduction = App.getCurrentGame().getDateTime().clone();
        this.isInHome = true;
        super.setHigh(1);
        super.setWidth(1);


        setSize(width, height);

        addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                showMenu();
                return true;
            }

        });

        // Load graphics based on animal type name
        switch (type.name().toLowerCase()) {
            case "chicken":
                loadAnimations(chicken.loadAnimations());
                break;
            // In the future: add cases for duck, cow, etc.
            case "duck":
                loadAnimations(duck.loadAnimations());
                break;
            case "rabbit":
                loadAnimations(rabbit.loadAnimations());
                break;

            case "dinosaur":
                loadAnimations(dinosaur.loadAnimations());
                break;
            case "cow":
                loadAnimations(cow.loadAnimations());
                break;


            case "goat":
                loadAnimations(goat.loadAnimations());
                break;

            case "sheep":
                loadAnimations(sheep.loadAnimations());
                break;
            case "pig":
                loadAnimations(pig.loadAnimations());
                break;

            default:
                throw new IllegalArgumentException("Unsupported animal type: " + type.name());
        }

        this.currentAnimation = walkRight;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        TextureRegion frame = currentAnimation.getKeyFrame(stateTime, true);
        if (frame != null) {
            batch.draw(frame, getX(), getY(), getWidth(), getHeight());
        }
    }
    private void showMenu() {
        String animalName = this.name;

        Dialog dialog = new Dialog("Animal: " + animalName, skin);

        // Info display
        StringBuilder info = new StringBuilder();
        info.append("Friendship: ").append(friendship).append("\n")
            .append("Fed Today: ").append(hasBeenFed ? "Yes" : "No").append("\n")
            .append("Petted Today: ").append(hasBeenPet ? "Yes" : "No").append("\n")
            .append("Products: ").append(products.size()).append("\n")
            .append("Location: ").append(isInHome ? "In Home" : "Outside");

        dialog.text(info.toString()).pad(10);

        // Create buttons
        TextButton feedButton = new TextButton("Feed", skin);
        TextButton petButton = new TextButton("Pet", skin);
        TextButton sellButton = new TextButton("Sell", skin);
        TextButton getProductsButton = new TextButton("Get Products", skin);
        TextButton moveOutButton = new TextButton("Move Out", skin);
        TextButton closeButton = new TextButton("Close", skin);

        // Button logic (calls to controller)
        feedButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                Result result = controller.feedHay(animalName);
                dialog.hide();
            }
        });

        petButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                Result result = controller.pet(animalName);
                currentAnimation = pet;

                dialog.hide();
            }
        });

        sellButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                Result result = controller.sellAnimal(animalName);
                dialog.hide();
            }
        });

        getProductsButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
//                Result result = controller.collectProduce(animalName);
                dialog.hide();
            }
        });

        moveOutButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                // Default movement to (5,5) — you can make this dynamic
                Result result = controller.moveAnimal(animalName, "5", "5");
                dialog.hide();
            }
        });

        closeButton.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) {
                dialog.hide();
            }
        });

        // Vertical layout with spacing
        dialog.getButtonTable().defaults().padTop(10).width(200).center();
        dialog.getButtonTable().add(feedButton).row();
        dialog.getButtonTable().add(petButton).row();
        dialog.getButtonTable().add(sellButton).row();
        dialog.getButtonTable().add(getProductsButton).row();
        dialog.getButtonTable().add(moveOutButton).row();
        dialog.getButtonTable().add(closeButton).row();

        dialog.show(stage);
    }
    private void loadAnimations(Animation<TextureRegion>[] anims) {
        this.walkDown = anims[0];
        this.walkRight = anims[1];
        this.walkLeft = anims[2];
        this.walkUp = anims[3];
        this.idle = anims[4];
        this.pet = anims[5];
    }



    public void setHome(AnimalHome home) {
        this.home = home;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public AnimalHome getHome() {
        return home;
    }

    public ArrayList<AnimalProduct> getProducts() {
        return products;
    }

    public void addProduct(AnimalProduct product) {
        products.add(product);
    }

    public boolean isHome() {
        return isInHome;
    }

    public void moveOutSide() {
        isInHome = false;
    }

    public void moveInside() {
        isInHome = true;
    }

    public DateTime getLastProduction() {
        return lastProduction;
    }

    public void setLastProduction() {
        this.lastProduction = App.getCurrentGame().getDateTime().clone();
    }

    public void setStrategy(AnimalStrategy strategy) {
        this.strategy = strategy;
    }

    public AnimalType getType() {
        return type;
    }

    public void product() {
        strategy.produce(this);
    }

    public double getProductProbability() {
        Random rand = new Random();
        double randomVar = 0.5 + (1.0 * rand.nextDouble());
        return (friendship + 150. * randomVar) / 1500.;
    }

    public double getRandomQuality() {
        Random rand = new Random();
        double randomVar = 1.0 * rand.nextDouble();
        double p = (friendship / 1000.) * (0.5 + 0.5 * randomVar);
        if (p < 0.5) return 1;
        if (p < 0.7) return 1.25;
        if (p < 0.9) return 1.5;
        return 2;
    }

    public void feed() {
        friendship += 8;
        hasBeenFed = true;
    }

    public int getFriendship() {
        return friendship;
    }

    public void pet() {
        friendship += 15;
        hasBeenPet = true;
    }

    public void setFriendship(int amount) {
        friendship = amount;
    }

    public boolean hasBeenFed() {
        return hasBeenFed;
    }

    public void collectProduct() {
        strategy.collectProduct(this);
    }

    public void clearProduces() {
        products.clear();
    }

    public double getPrice() {
        return type.getPrice() * (friendship / 1000. + 0.3);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Friendship: " + friendship + ", Type: " + type +
            ", Last Production: " + lastProduction.toString() + ", Has Been Fed: " + hasBeenFed +
            ", Has Been Pet: " + hasBeenPet;
    }

    public void nextDay() {
        product();
        if (!hasBeenFed) friendship -= 20;
        if (!isInHome) friendship -= 20;
        if (!hasBeenPet) friendship = Math.max(0, friendship / 200 - 10);

        hasBeenFed = false;
        hasBeenPet = false;
    }
}
