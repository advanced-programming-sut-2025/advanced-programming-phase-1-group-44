package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;
import java.util.Random;

public class Space extends MapObj{
    public ArrayList<String>floorsnumber=new ArrayList<String>();
    boolean shokhmzadeshode=false;
    private boolean coodi=false;
    private boolean cood1=false,cood2=false;

    public Texture getImg() {
        return img;
    }

    public void setCood2(boolean cood2) {
        this.cood2 = cood2;
    }

    public void setCood1(boolean cood1) {
        this.cood1 = cood1;
    }

    public boolean isCood1() {
        return cood1;
    }

    public boolean isCood2() {
        return cood2;
    }

    public void setCoodi(boolean coodi) {
        this.coodi = coodi;
    }

    public boolean isCoodi() {
        return coodi;
    }
    public Space (){
        floorsnumber.add("29");
        floorsnumber.add("04");
        floorsnumber.add("28");
        floorsnumber.add("44");
        floorsnumber.add("50");
        Random rnd=new Random();
        img=new Texture("Flooring/Flooring_"+floorsnumber.get(rnd.nextInt(floorsnumber.size()-1))+".png");
        imgs=new TextureRegion(img);
        this.Name="Space";
        this.width=1;
        this.high=1;
        setSize(this.high, this.width);
    }
    public Space(int width,int high){
        floorsnumber.add("29");
        floorsnumber.add("04");
        floorsnumber.add("28");
        floorsnumber.add("44");
        floorsnumber.add("50");
        Random rnd=new Random();
        img=new Texture("Flooring/Flooring_"+floorsnumber.get(rnd.nextInt(floorsnumber.size()-1))+".png");
        imgs=new TextureRegion(img);
        this.Name="Space";
        this.width=width;
        this.high=high;
        setSize(width, high);
    }
    public boolean isShokhmzadeshode() {
        return shokhmzadeshode;
    }

    public void setShokhmzadeshode(boolean shokhmzadeshode) {
        this.shokhmzadeshode = shokhmzadeshode;
    }
}
