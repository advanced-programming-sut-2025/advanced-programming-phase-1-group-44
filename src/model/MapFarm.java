package model;

import model.enums.ForagingMaterial.ForagingCrops;
import model.enums.ForagingMaterial.ForagingMinerals;
import model.enums.ForagingMaterial.ForagingSeeds;
import model.Animals.Animal;
import model.enums.Trees;
import model.Animals.AnimalHome;

import java.util.ArrayList;

public abstract class MapFarm {
    private final ArrayList<Greenhouse> Greenhouses=new ArrayList<Greenhouse>();
    private final ArrayList<Lake> Lakes=new ArrayList<Lake>();
    private final ArrayList<Cottage> Cottages=new ArrayList<Cottage>();
    private final ArrayList<Quarry> Quarrys=new ArrayList<Quarry>();
    private final ArrayList<Tree> Trees=new ArrayList<Tree>();
    private final ArrayList<ForagingCrops> ForagingCrops=new ArrayList<ForagingCrops>();
    private final ArrayList<ForagingMinerals> ForagingMinerals=new ArrayList<ForagingMinerals>();
    private final ArrayList<ForagingSeeds> ForagingSeeds=new ArrayList<ForagingSeeds>();
    private ArrayList<ArrayList<MapObj>> MapCells=new ArrayList<ArrayList<MapObj>>();
    private final ArrayList<AnimalHome> animalHomes = new ArrayList<>();

    int width=50,high=50;


    public void removeAnimal(Animal animal) {
        for (AnimalHome animalHome : animalHomes) {
            if (animalHome.getAnimals().contains(animal)) {
                animalHome.removeAnimal(animal);
                break;
            }   
        }
    }
    public ArrayList<AnimalHome> getAnimlaHomes() {
        return animalHomes;
    }

    public MapObj GetCell(int i,int j){
        return MapCells.get(i).get(j);
    }

    public void setMapCells(ArrayList<ArrayList<MapObj>> mapCells) {
        MapCells = mapCells;
    }
    public void setMapCell(int i,int j,MapObj mo){
        MapCells.get(i).set(j,mo);
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
    public boolean AddTrees(Tree t){
        try {
            Trees.add(t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean AddQuarrys(Quarry q){
        try {
            Quarrys.add(q);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean AddGreenhouse(Greenhouse g){
        try {
            Greenhouses.add(g);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean AddLakes(Lake l){
        try {
            Lakes.add(l);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean AddCottages(Cottage c){
        try {
            Cottages.add(c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean AddAnimalHome(AnimalHome h) {
        try {
            animalHomes.add(h);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
