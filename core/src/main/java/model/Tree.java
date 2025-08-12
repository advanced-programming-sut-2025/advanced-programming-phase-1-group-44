package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Random;

public class Tree extends MapObj{
    private int color;
    public Tree(){
        Random rand=new Random();
        img=new Texture(App.getAlltreepath().get(rand.nextInt(App.getAlltreepath().size())));
        imgs=new TextureRegion(img);
        this.setName("Tree");
        color=1;
        this.width=3;
        this.high=1;
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
    public Tree(int r){
        Random rand=new Random();
        img=new Texture(App.getAlltreepath().get(rand.nextInt(App.getAlltreepath().size())));
        imgs=new TextureRegion(img);
        this.setName("Tree");
        color=r;
        this.width=3;
        this.high=1;
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
