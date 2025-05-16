package model;

import model.enums.Recipe;

public class Food extends Item implements Eatable{
    public Food(Recipe recipe){
        super(recipe.getName(), recipe.getSellPrice());
    }
}
