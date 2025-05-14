package model.Animals;

import java.util.ArrayList;
import java.util.Random;

import model.App;
import model.DateTime;
import model.MapObj;
import model.Animals.AnimalStrategy.AnimalStrategy;
import model.enums.AnimalEnum.AnimalType;

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

    public Animal(String name, AnimalType type) {
        this.name = name;
        this.type = type;
        this.lastProduction = App.getCurrentGame().getDateTime().clone();
        isInHome = true;
        super.setHigh(1);
        super.setWidth(1);
    }

    public void setHome(AnimalHome home) {
        this.home = home;
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
        
        // Range: [0.5, 1.5)
        double randomVar = 0.5 + (1.0 * rand.nextDouble());
        return (friendship + 150.*randomVar) / 1500.;
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

    public double getPrice() {
        return type.getPrice() * (friendship / 1000. + 0.3);
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Friendship: " + friendship + ", Type: " + type +
                ", Last Production: " + lastProduction.toString() + ", Has Been Fed: " + hasBeenFed +
                ", Has Been Pet: " + hasBeenPet;
    }
    
    
}