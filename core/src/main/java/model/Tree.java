package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class Tree extends MapObj{
    private int color;
    public Tree(){
        Random rand=new Random();
        setpic(App.getAlltreepath().get(rand.nextInt(App.getAlltreepath().size())));
        this.setName("Tree");
        color=1;
        this.width=3;
        this.high=1;
    }
    public Tree(int r){
        Random rand=new Random();
        setpic(App.getAlltreepath().get(rand.nextInt(App.getAlltreepath().size())));
        this.setName("Tree");
        color=r;
        this.width=3;
        this.high=1;
    }
}
