package model;

import model.enums.Recipe;

public class Eatable extends Item{
    Recipe recipe;

    public Eatable(Recipe recipe) {
        this.recipe = recipe;
        this.name = this.recipe.getName();
        this.price = this.recipe.getSellPrice();
    }
    public Eatable(String name){
        this.name = name;
        this.recipe = Recipe.getRecipe(name);
        if(this.recipe != null){
            this.price = this.recipe.getSellPrice();
        }
    }
    public Eatable(String name, int price){
        this.name = name;
        this.price = price;
    }
}
