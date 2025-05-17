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
    public boolean collectProduct(Animal animal) {
                // TODO check if the pig is outside of the stable
        if (animal.isHome() == true) return false;
                for (AnimalProduct product : animal.getProducts()) {
            App.getCurrentGame().getCurrentPlayer().getBackpack().putItem(product, 1);
        }
        animal.clearProduces();

        return true;


    }
}
