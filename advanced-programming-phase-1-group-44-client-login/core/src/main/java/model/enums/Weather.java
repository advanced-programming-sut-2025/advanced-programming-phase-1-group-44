package model.enums;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public enum Weather {
    Sunny(1.5),
    Rain(1.2),
    Storm(0.5),
    Snow(1);
    public final double fishing;

    Weather(double fishing) {
        this.fishing = fishing;
    }
    public static Weather getWeather(String name){
        for (Weather value : Weather.values()) {
            if(value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        return null;
    }

    public Image getImage() {
        return new Image(new Texture(Gdx.files.internal("weather/" + name() + ".png")));
    }
}
