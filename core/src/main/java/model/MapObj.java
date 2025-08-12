package model;

import com.badlogic.gdx.Gdx;
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
        Gdx.app.postRunnable(() -> {
            img = new Texture(Gdx.files.internal("Flooring/Flooring_50.png"));
            imgs = new TextureRegion(img);
        });
        width=1;
        high=1;
        setSize(width,high);
    }
    //ip<cpS>.?1T1dhoG=P;p^
    public void setpic(String path){
        Gdx.app.postRunnable(() -> {
                img = new Texture(path);
                imgs = new TextureRegion(img);
            });
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

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (imgs != null) {
            batch.draw(
                imgs,
                getX(), getY(),
                getOriginX(), getOriginY(),
                getWidth(), getHeight(),
                getScaleX(), getScaleY(),
                getRotation()
            );
        }
    }

}
