package model.Animals.AnimalStrategy;

import model.App;
import model.DateTime;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class SheepStrategy implements AnimalStrategy {
    @Override
    public void produce(Animal animal) {
        if (!animal.hasBeenFed()) return;
        if (animal.getFriendship() < 70 || DateTime.DateDiff(animal.getLastProduction(), App.getCurrentGame().getDateTime()) < 3) return;
        double quality = animal.getRandomQuality();
        animal.addProduct(new AnimalProduct(AnimalProductsEnum.sheepWool, quality));
    }
    @Override
    public boolean collectProduct(Animal animal) {
        // TODO check if the player has scissors
        if (!(App.getCurrentGame().getCurrentPlayer().currenttool instanceof scissor)) return false;

        for (AnimalProduct product : animal.getProducts()) {
            App.getCurrentGame().getCurrentPlayer().getBackpack().putItem(product, 1);
        }
        return true;

    }

    
}
