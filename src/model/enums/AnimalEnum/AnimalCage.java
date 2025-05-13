package model.enums.AnimalEnum;
import java.util.List;
import java.util.ArrayList;

public enum AnimalCage implements AnimalType {
    chicken(800, new ArrayList<>(List.of(AnimalProducts.egg, AnimalProducts.largeEgg)), new ArrayList<>(List.of(AnimalHomeType.coop, AnimalHomeType.bigCoop, AnimalHomeType.deluxeCoop))),
    duck(1200, new ArrayList<>(List.of(AnimalProducts.dockEgg, AnimalProducts.dockFeather)), new ArrayList<>(List.of(AnimalHomeType.bigCoop, AnimalHomeType.deluxeCoop))),
    rabbit(8000, new ArrayList<>(List.of(AnimalProducts.rabbitLeg, AnimalProducts.rabbitWool)), new ArrayList<>(List.of(AnimalHomeType.deluxeCoop))),
    dinasour(14000, new ArrayList<>(List.of(AnimalProducts.dinasourEgg)), new ArrayList<>(List.of(AnimalHomeType.bigCoop)));

    private final int price;
    private final List<AnimalProducts> products;
    private final List<AnimalHomeType> homes;
    AnimalCage(int price, List<AnimalProducts> products, List<AnimalHomeType> homes) {
        this.price = price;
        this.products = products;
        this.homes = homes;
    }

    public int getPrice() {
        return price;
    }

    public List<AnimalProducts> getProducts() {
        return products;
    }

}


