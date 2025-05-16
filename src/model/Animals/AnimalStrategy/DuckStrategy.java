package model.Animals.AnimalStrategy;

import model.App;
import model.DateTime;
import model.Animals.Animal;
import model.Animals.AnimalProduct;
import model.enums.AnimalEnum.AnimalProductsEnum;

public class DuckStrategy implements AnimalStrategy {
    public void produce(Animal animal) {
        if (!animal.hasBeenFed()) return;
        if (DateTime.DateDiff(animal.getLastProduction(), App.getCurrentGame().getDateTime()) < 2) return;
        double p = 0;
        if (animal.getFriendship() >= 100) {
            p = animal.getProductProbability();
        }
        double quality = animal.getRandomQuality();
        if (p < 0.5) {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.duckEgg, quality));
        }
        else {
            animal.addProduct(new AnimalProduct(AnimalProductsEnum.duckFeather, quality));
        }    
    }
    @Override
    public void collectProduct(Animal animal) {
        
    }


}
