package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Greenhouse extends MapObj {
    public Greenhouse(){
        img=new Texture("greenhouse.png");
        imgs=new TextureRegion(img);
        this.Name="Greenhouse";
        width=4;
        high=4;
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
    public int isavailable=0;
}
