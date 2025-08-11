package model.Animals.AnimalStrategy;

import model.Animals.Animal;

public interface AnimalStrategy {
    public void produce(Animal animal); 
    public boolean collectProduct(Animal animal);   
}
