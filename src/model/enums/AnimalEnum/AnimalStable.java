package model.enums.AnimalEnum;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public enum AnimalStable {
    cow(1500, new ArrayList<>(List.of(AnimalProducts.milkCow, AnimalProducts.largeMilkCow)), new ArrayList<>(List.of(AnimalHomeType.barn, AnimalHomeType.bigBarn, AnimalHomeType.deluxeBarn))),
    goat(4000, new ArrayList<>(List.of(AnimalProducts.milkGoat, AnimalProducts.largeMilkGoat)), new ArrayList<>(List.of(AnimalHomeType.bigBarn, AnimalHomeType.deluxeBarn))),
    sheep(8000, new ArrayList<>(List.of(AnimalProducts.sheetWool)), new ArrayList<>(List.of(AnimalHomeType.deluxeBarn))),
    sus(16000, new ArrayList<>(List.of(AnimalProducts.truffle)), new ArrayList<>(List.of(AnimalHomeType.deluxeBarn)));

    private final int price;
    private final ArrayList<AnimalProducts> products;
    private final ArrayList<AnimalHomeType> homes;

    AnimalStable(int price, ArrayList<AnimalProducts> products, ArrayList<AnimalHomeType> homes) {
        this.price = price;
        this.homes = homes;
        this.products = products;
    }

    public int getPrice() {
        return price;
    }
}