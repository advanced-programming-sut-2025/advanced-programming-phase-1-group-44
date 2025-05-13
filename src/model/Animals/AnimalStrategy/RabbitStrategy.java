package model.Animals.AnimalStrategy;

import model.App;
import model.DateTime;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class RabbitStrategy implements AnimalStrategy{
    public void produce(Animal animal) {
        if (DateTime.DateDiff(animal.getLastProduction(), App.getCurrentGame().getDateTime()) < 4) return;
        double p = 0;
        if (animal.getFriendship() >= 100) {
            p = animal.getProductProbability();
        }
        double quality = animal.getRandomQuality();
        if (p < 0.5) {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.rabbitWool, quality));
        }
        else {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.rabbitLeg, quality));
        }    
    }
        @Override
    public void collectProduct(Animal animal) {

    }


}
