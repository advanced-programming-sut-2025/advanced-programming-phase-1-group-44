package model.Animals;

import model.Eatable;
import model.Item;
import model.enums.AllItems;

public class AnimalProduct extends Item implements Eatable {
    private final AllItems product;
    private final double quality;

    public AnimalProduct(AllItems product, double quality) {
        super(product.getName(), product.getPrice());
        this.product = product;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return "Name: " + name + " ,Quality: " + quality + '\n';
    }
}
