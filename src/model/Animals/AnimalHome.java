package model.Animals;

import java.util.ArrayList;

import model.MapObj;
import model.enums.AnimalEnum.AnimalHomeType;

public class AnimalHome extends MapObj {
    private final AnimalHomeType homeType;
    private int remainingCapacity;
    private final ArrayList<Animal> animals;


    public AnimalHome(AnimalHomeType homeType) {
        this.homeType = homeType;
        this.animals = new ArrayList<>();
        this.remainingCapacity = homeType.getSize();
    }

    public int getRemainingCapacity() {
        return remainingCapacity;
    }
    public ArrayList<Animal> getAnimals() {
        return animals;
    }
    public AnimalHomeType getHomeType() {
        return homeType;
    }
    public void addAnimal(Animal animal) {
        animals.add(animal);
        remainingCapacity--;
    }
    public void removeAnimal(Animal animal) {
        animals.remove(animal);
        remainingCapacity++;
    }

    

}
