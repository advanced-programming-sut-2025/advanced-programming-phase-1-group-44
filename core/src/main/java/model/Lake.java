package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Lake extends MapObj{
    public Lake(){
        img=new Texture("—Pngtree—peaceful lake in mountain valley_21596382.png");
        imgs=new TextureRegion(img);
        this.Name="Lake";
        this.width=4;
        this.high=4;
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
    public Lake(int width,int high){
        Gdx.app.postRunnable(() -> {
            img=new Texture("—Pngtree—peaceful lake in mountain valley_21596382.png");
            imgs=new TextureRegion(img);
        });

        this.Name="Lake";
        this.width=width;
        this.high=high;
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
