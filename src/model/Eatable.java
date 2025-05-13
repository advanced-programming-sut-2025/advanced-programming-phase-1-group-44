package model;

<<<<<<< HEAD
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
=======
public interface Eatable {
>>>>>>> f6b1b30491ff470a1d66e13726fc3b22268876b5
}
