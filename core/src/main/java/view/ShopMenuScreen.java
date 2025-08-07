package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import model.GameAssetManager;
import model.Stores.Shop;
import model.Stores.ShopItem;
import java.util.*;
import java.util.List;

public class ShopMenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private List<ShopItem> items;
    private Shop shop;

    private static final int ITEMS_PER_ROW = 5;

    // Cart: maps ShopItem to quantity in cart
    private final Map<ShopItem, Integer> cart = new LinkedHashMap<>();

    public ShopMenuScreen(Shop shop) {
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.shop = shop;
        this.items = shop.getItems();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // --- Cart items table must be created before item listeners
        final Table cartItemsTable = new Table();

        // --- Shop Title
        Label shopTitle = new Label(shop.getName(), skin, "title");
        shopTitle.setFontScale(1.2f);

        // --- Tooltip label
        final Label tooltipLabel = new Label("", skin, "default");
        tooltipLabel.setColor(1, 1, 1, 0.92f);
        tooltipLabel.setVisible(false);
        Label.LabelStyle tooltipStyle = new Label.LabelStyle(tooltipLabel.getStyle().font, tooltipLabel.getStyle().fontColor);
        tooltipStyle.background = skin.newDrawable("white", new Color(0, 0, 0, 0.85f));
        tooltipLabel.setStyle(tooltipStyle);

        // --- Shop Item Table
        final Table itemTable = new Table();

        for (int i = 0; i < items.size(); i++) {
            final ShopItem item = items.get(i);
            final Stack stack = new Stack();

            Image slotBg = new Image(new Texture(Gdx.files.internal("inventory/itemBackground.png")));
            stack.add(slotBg);

            Image icon = new Image(new Texture(Gdx.files.internal(item.imagePath)));
            if (item.getDailyLimit() == 0) {
                icon.setColor(0.3f, 0.3f, 0.3f, 1f); // dim unavailable
            } else {
                icon.setColor(Color.WHITE);
            }
            stack.add(icon);

            // Tooltip logic
            stack.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltipLabel.setText("name : " + item.getName() + '\n' +
                        "remaining : " + (item.getDailyLimit() > 2000000 ? "infinity" : item.getDailyLimit()));
                    tooltipLabel.pack();
                    Vector2 coord = new Vector2(x, y);
                    stack.localToStageCoordinates(coord);
                    tooltipLabel.setPosition(coord.x + 20, coord.y + 20);
                    tooltipLabel.setVisible(true);
                }
                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltipLabel.setVisible(false);
                }
            });

            // Add-to-cart on click
            stack.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (item.getDailyLimit() == 0) return; // Do not add unavailable
                    cart.put(item, cart.getOrDefault(item, 0) + 1);
                    updateCartTable(cartItemsTable, cart, skin);
                }
            });

            itemTable.add(stack).size(64, 64).pad(8);
            if ((i + 1) % ITEMS_PER_ROW == 0) itemTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(itemTable, skin);
        scrollPane.setFadeScrollBars(false);

        // --- CART UI
        final Table cartTable = new Table();
        cartTable.top().left().pad(10);
        cartTable.setBackground(skin.newDrawable("white", new Color(0.12f, 0.12f, 0.12f, 0.94f)));
        Label cartLabel = new Label("Cart", skin, "title");
        cartTable.add(cartLabel).left();
        cartTable.row();
        cartTable.add(cartItemsTable).left();
        cartTable.row();
        TextButton finalBuyButton = new TextButton("Final Buy", skin);
        cartTable.add(finalBuyButton).padTop(15).left();

        // --- LAYOUT: Shop & Cart side by side
        Table mainTable = new Table();
        mainTable.setFillParent(true);

        Table shopArea = new Table();
        shopArea.top().padTop(30);
        shopArea.add(shopTitle).colspan(ITEMS_PER_ROW).padBottom(20);
        shopArea.row();
        shopArea.add(scrollPane).expand().fill().colspan(ITEMS_PER_ROW);

        mainTable.add(shopArea).expand().fill();
        mainTable.add(cartTable).width(250).top().padLeft(30);

        stage.addActor(mainTable);
        stage.addActor(tooltipLabel);
    }

    // -- Updates the visible contents of the cart!
    private void updateCartTable(Table cartItemsTable, Map<ShopItem, Integer> cart, Skin skin) {
        cartItemsTable.clear();
        if (cart.isEmpty()) {
            cartItemsTable.add(new Label("Your cart is empty.", skin)).left().row();
            return;
        }
        for (ShopItem cartItem : cart.keySet()) {
            int count = cart.get(cartItem);
            Label nameLabel = new Label(cartItem.getName() + " x" + count, skin);
            TextButton minusButton = new TextButton("-", skin);

            minusButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    int oldCount = cart.get(cartItem);
                    if (oldCount <= 1) {
                        cart.remove(cartItem);
                    } else {
                        cart.put(cartItem, oldCount - 1);
                    }
                    updateCartTable(cartItemsTable, cart, skin);
                }
            });

            Table rowTable = new Table();
            rowTable.add(nameLabel).left().padRight(12);
            rowTable.add(minusButton).right();

            cartItemsTable.add(rowTable).left().row();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}
