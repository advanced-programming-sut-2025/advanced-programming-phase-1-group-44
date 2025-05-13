package model;

import java.util.ArrayList;

import model.Animals.Animal;
import model.enums.AnimalEnum.AnimalHomeType;

public class AnimalHome {
    private AnimalHomeType homeType;
    private int remainingCapacity;
    private ArrayList<Animal> animals;


    AnimalHome(AnimalHomeType homeType) {
        this.homeType = homeType;

        this.remainingCapacity = homeType.getSize();
    }

}
