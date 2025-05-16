package model.enums.AnimalEnum;

import java.util.ArrayList;
import java.util.List;

public enum AnimalType {   
    chicken("chicken", 800, new ArrayList<>(List.of(AnimalProductsEnum.egg, AnimalProductsEnum.largeEgg)), new ArrayList<>(List.of(AnimalHomeType.coop, AnimalHomeType.bigCoop, AnimalHomeType.deluxeCoop))),
    duck("duck", 1200, new ArrayList<>(List.of(AnimalProductsEnum.duckEgg, AnimalProductsEnum.duckFeather)), new ArrayList<>(List.of(AnimalHomeType.bigCoop, AnimalHomeType.deluxeCoop))),
    rabbit("rabbit", 8000, new ArrayList<>(List.of(AnimalProductsEnum.rabbitLeg, AnimalProductsEnum.rabbitWool)), new ArrayList<>(List.of(AnimalHomeType.deluxeCoop))),
    dinasour("dinasour", 14000, new ArrayList<>(List.of(AnimalProductsEnum.dinasourEgg)), new ArrayList<>(List.of(AnimalHomeType.bigCoop))),
    cow("cow", 1500, new ArrayList<>(List.of(AnimalProductsEnum.milkCow, AnimalProductsEnum.largeMilkCow)), new ArrayList<>(List.of(AnimalHomeType.barn, AnimalHomeType.bigBarn, AnimalHomeType.deluxeBarn))),
    goat("goat", 4000, new ArrayList<>(List.of(AnimalProductsEnum.milkGoat, AnimalProductsEnum.largeMilkGoat)), new ArrayList<>(List.of(AnimalHomeType.bigBarn, AnimalHomeType.deluxeBarn))),
    sheep("sheep", 8000, new ArrayList<>(List.of(AnimalProductsEnum.sheepWool)), new ArrayList<>(List.of(AnimalHomeType.deluxeBarn))),
    pig("pig", 16000, new ArrayList<>(List.of(AnimalProductsEnum.truffle)), new ArrayList<>(List.of(AnimalHomeType.deluxeBarn)));


    private final String name;
    private final int price;
    private final List<AnimalProductsEnum> products;
    private final List<AnimalHomeType> homes;
    AnimalType(String name, int price, List<AnimalProductsEnum> products, List<AnimalHomeType> homes) {
        this.name = name;
        this.price = price;
        this.products = products;
        this.homes = homes;
    }

    public int getPrice() {
        return price;
    }

    public List<AnimalProductsEnum> getProducts() {
        return products;
    }

    public boolean canGoTo(AnimalHomeType home) {
        return homes.contains(home);
    }
    public String getName() {
        return name;
    }
 
}