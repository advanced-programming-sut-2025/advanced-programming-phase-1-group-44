package model;

import model.enums.ForagingMaterial.ForagingCrops;
import model.enums.ForagingMaterial.ForagingMinerals;
import model.enums.ForagingMaterial.ForagingSeeds;
import model.Animals.Animal;
import model.Animals.AnimalHome;
import model.enums.Trees;

import java.util.ArrayList;

public abstract class MapFarm {
    private final ArrayList<Greenhouse> Greenhouses=new ArrayList<Greenhouse>();
    private final ArrayList<Lake> Lakes=new ArrayList<Lake>();
    private final ArrayList<Cottage> Cottages=new ArrayList<Cottage>();
    private final ArrayList<Quarry> Quarrys=new ArrayList<Quarry>();
    private final ArrayList<Trees> Trees=new ArrayList<Trees>();
    private final ArrayList<ForagingCrops> ForagingCrops=new ArrayList<ForagingCrops>();
    private final ArrayList<ForagingMinerals> ForagingMinerals=new ArrayList<ForagingMinerals>();
    private final ArrayList<ForagingSeeds> ForagingSeeds=new ArrayList<ForagingSeeds>();
    private ArrayList<ArrayList<MapObj>> MapCells=new ArrayList<ArrayList<MapObj>>();
    private final ArrayList<AnimalHome> animalHomes = new ArrayList<>();
    
    int width,high;
    public MapObj GetCell(int i,int j){
        return MapCells.get(i).get(j);
    }

    public void removeAnimal(Animal animal) {
        for (AnimalHome animalHome : animalHomes) {
            if (animalHome.getAnimals().contains(animal)) {
                animalHome.removeAnimal(animal);
                break;
            }   
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHigh() {
        return high;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public ArrayList<AnimalHome> getAnimlaHomes() {
        return animalHomes;
    }

}
