package model.Animals.AnimalStrategy;

import model.App;
import model.DateTime;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class SheepStrategy implements AnimalStrategy {
    @Override
    public void produce(Animal animal) {
        if (animal.getFriendship() < 70 || DateTime.DateDiff(animal.getLastProduction(), App.getCurrentGame().getDateTime()) < 3) return;
        double quality = animal.getRandomQuality();
        animal.addProduct(new AnimalProduct(AnimalProductsEnum.sheepWool, quality));
    }
        @Override
    public void collectProduct(Animal animal) {
        // TODO check if the player has scissors

    }

    
}
