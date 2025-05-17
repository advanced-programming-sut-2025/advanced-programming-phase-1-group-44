package model.Animals.AnimalStrategy;

import java.util.Random;

import model.App;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class ChickenStrategy implements AnimalStrategy{
    public void produce(Animal animal) {
        if (!animal.hasBeenFed()) return;
        double p = 0;
        if (animal.getFriendship() >= 100) {
            p = animal.getProductProbability();
        }
        double quality = animal.getRandomQuality();
        if (p < 0.5) {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.egg, quality));
        }
        else {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.largeEgg, quality));
        }
    }
    @Override
    public boolean collectProduct(Animal animal) {
        for (AnimalProduct product : animal.getProducts()) {
            App.getCurrentGame().getCurrentPlayer().getBackpack().putItem(product, 1);
        }
        return true;
    }
}
