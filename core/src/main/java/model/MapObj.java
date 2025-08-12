package model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class MapObj extends Actor {
    //top-left corner
    public TextureRegion imgs;
    Texture img;
    int Xlocation,Ylocation;
    int width,high;
    public MapObj(){
        img=new Texture("Flooring/Flooring_50.png");
        imgs=new TextureRegion(img);
        width=1;
        high=1;
        setSize(width,high);
    }
    public void setpic(String path){
        img=new Texture(path);
        imgs=new TextureRegion(img);
        setSize(this.width,this.high);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }
    protected String Name="Space";

    public Texture getImg() {
        return img;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigh() {
        return high;
    }
    public int getwidth() {
        return width;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setXlocation(int xlocation) {
        Xlocation = xlocation;
    }

    public void setYlocation(int ylocation) {
        Ylocation = ylocation;
    }

    public String getName() {
        return Name;
    }

    public int getXlocation() {
        return Xlocation;
    }

    public int getYlocation() {
        return Ylocation;
    }

    public void setSize(float width) {
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(
            imgs,
            getX(), getY(), // موقعیت X,Y
            getOriginX(), getOriginY(),
            getWidth(), getHeight(),
            getScaleX(), getScaleY(),
            getRotation() // تنظیمات تبدیل‌ها
        );
    }
}
