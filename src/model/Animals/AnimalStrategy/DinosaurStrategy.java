package model.Animals.AnimalStrategy;

import model.App;
import model.DateTime;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class DinosaurStrategy implements AnimalStrategy {
    public void produce(Animal animal) {
        if (!animal.hasBeenFed()) return;
        if (DateTime.DateDiff(animal.getLastProduction(), App.getCurrentGame().getDateTime()) < 7) return;
        double quality = animal.getRandomQuality();
        animal.addProduct(new AnimalProduct(AnimalProductsEnum.dinasourEgg, quality));
    }
    @Override
    public boolean collectProduct(Animal animal) {
        for (AnimalProduct product : animal.getProducts()) {
            App.getCurrentGame().getCurrentPlayer().getBackpack().putItem(product, 1);
        }
        return true;
    }

}
