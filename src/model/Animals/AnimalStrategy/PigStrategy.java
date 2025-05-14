package model.Animals.AnimalStrategy;

import model.App;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class PigStrategy implements AnimalStrategy {
    @Override
    public void produce(Animal animal) {
        if (!animal.hasBeenFed()) return;
        if (animal.isHome()) return;
        double quality = animal.getRandomQuality();
        animal.addProduct(new AnimalProduct(AnimalProductsEnum.truffle, quality));
    }

    @Override
    public void collectProduct(Animal animal) {
                // TODO check if the pig is outside of the stable

    }
}
