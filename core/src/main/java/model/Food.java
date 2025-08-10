package model;

import model.enums.Recipe;

public class Food extends Item implements Eatable{
    int quality = 1;
    public Food(Recipe recipe){
        super(recipe.getName(), recipe.getSellPrice());
    }
    public Food(String name, int price){
        super(name, price);
    }
    public Food(String name, int price, int quality){
        super(name, price);
        this.quality = quality;
    }
}
