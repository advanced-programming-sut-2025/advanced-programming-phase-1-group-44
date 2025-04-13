package model;

import java.util.ArrayList;

import model.enums.Trees;
import model.enums.ForagingMaterial.ForagingCrops;
import model.enums.ForagingMaterial.ForagingMinerals;
import model.enums.ForagingMaterial.ForagingSeeds;

public class Map {
    private final ArrayList<Greenhouse> Greenhouses=new ArrayList<Greenhouse>();
    private final ArrayList<Lake> Lakes=new ArrayList<Lake>();
    private final ArrayList<Cottage> Cottages=new ArrayList<Cottage>();
    private final ArrayList<Quarry> Quarrys=new ArrayList<Quarry>();
    private final ArrayList<Trees> Trees=new ArrayList<Trees>();
    private final ArrayList<ForagingCrops> ForagingCrops=new ArrayList<ForagingCrops>();
    private final ArrayList<ForagingMinerals> ForagingMinerals=new ArrayList<ForagingMinerals>();
    private final ArrayList<ForagingSeeds> ForagingSeeds=new ArrayList<ForagingSeeds>();
    private ArrayList<ArrayList<Character>> Map_cells=new ArrayList<ArrayList<Character>>(); 
    public Map(){

    }
    public char GetCell(int i,int j){
        return Map_cells.get(i).get(j);
    }
}
