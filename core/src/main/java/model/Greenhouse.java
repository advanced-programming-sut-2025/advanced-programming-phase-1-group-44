package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Greenhouse extends MapObj {
    public Greenhouse(){
        img=new Texture("greenhouse.jpeg");
        imgs=new TextureRegion(img);
        this.Name="Greenhouse";
        width=5;
        high=6;
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
    public int isavailable=0;
}
