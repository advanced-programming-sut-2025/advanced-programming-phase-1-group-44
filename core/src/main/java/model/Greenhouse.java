package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Greenhouse extends MapObj {
    public Greenhouse(){
        this.Name="Greenhouse";
        width=5;
        high=6;
        setpic("greenhouse.jpeg");
    }
    public int isavailable=0;
}
