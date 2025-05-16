package model.Animals;

import model.Eatable;
import model.Item;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class AnimalProduct extends Item implements Eatable {
    private final AnimalProductsEnum product;
    private final double quality;

    public AnimalProduct(AnimalProductsEnum product, double quality) {
        name = product.getName();
        price = product.getPrice();
        this.product = product;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Name: " + name + " ,Quality: " + quality + '\n';
    }
}
