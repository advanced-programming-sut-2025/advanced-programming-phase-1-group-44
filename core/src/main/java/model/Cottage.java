package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Cottage extends MapObj{
    public Cottage(){
        img=new Texture("Cottage.png");
        imgs=new TextureRegion(img);
        Name="Cottage";
        width=4;
        high=4;
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
}
