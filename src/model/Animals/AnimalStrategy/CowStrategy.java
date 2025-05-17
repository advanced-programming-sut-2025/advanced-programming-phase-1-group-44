package model.Animals.AnimalStrategy;

import model.App;
import model.DateTime;
import model.Player;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class CowStrategy implements AnimalStrategy {
    @Override
    public void produce(Animal animal) {
        if (!animal.hasBeenFed()) return;

        double p = 0;
        if (animal.getFriendship() >= 100) {
    
            p = animal.getProductProbability();
        }
        double quality = animal.getRandomQuality();
        if (p < 0.5) {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.milkCow, quality));
        }
        else {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.largeMilkCow, quality));
        }    
    }
    @Override
    public boolean collectProduct(Animal animal) {
        // TODO check if the player has satl shir
        if (!(App.getCurrentGame().getCurrentPlayer().currenttool instanceof milkpail)) return false;
        for (AnimalProduct product : animal.getProducts()) {
            App.getCurrentGame().getCurrentPlayer().getBackpack().putItem(product, 1);
        }
        return false;
    }
}
