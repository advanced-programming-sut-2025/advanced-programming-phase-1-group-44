package model.enums;

import java.util.ArrayList;
import java.util.Arrays;

public enum Season {
    SPRING(28, "Spring", 0),
    SUMMER(28, "Summer", 1),
    FALL(28, "Fall", 2),
    WINTER(28, "Winter", 3);
    ArrayList<String>Crops=new ArrayList<String>();
    private final int numberOfDays, id;
    private final String name;

    Season(int numberOfDays, String name, int id) {
        this.id = id;
        this.numberOfDays = numberOfDays;
        this.name = name;
        if(name.equals("Spring")){
            String[] s={"Cauliflower","Parsnip","Potato","Blue Jazz","Tulip"};
            Crops.addAll(Arrays.asList(s));
        }else if(name.equals("Summer")){
            String[] s={"Corn","Hot Pepper","Radish","Wheat","Poppy","Sunflower","Summer Spangle"};
            Crops.addAll(Arrays.asList(s));
        }else if(name.equals("Fall")){
            String[] s={"Artichoke","Corn","Eggplant","Pumpkin","Sunflower","Fairy Rose"};
            Crops.addAll(Arrays.asList(s));
        }else{
            String[] s={"Powdermelon"};
            Crops.addAll(Arrays.asList(s));
        }
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    public int getID(){return id;}
    @Override
    public String toString() {
        return name;
    }
}
