package model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class GameAssetManager {
    private static GameAssetManager gameAssetManager;
    private final Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));

    private GameAssetManager() {
    }


    public static GameAssetManager getGameAssetManager() {
        if (gameAssetManager == null) gameAssetManager = new GameAssetManager();
        return gameAssetManager;
    }

    public Skin getSkin() {
        return skin;
    }

    public ArrayList<Image> getWeather() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image(new Texture(Gdx.files.internal("weather/Rain.png"))));
        images.add(new Image(new Texture(Gdx.files.internal("weather/Snow.png"))));
        images.add(new Image(new Texture(Gdx.files.internal("weather/Storm.png"))));
        images.add(new Image(new Texture(Gdx.files.internal("weather/Sunny.png"))));
        return images;
    }

    public ArrayList<Image> getSeasons() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(new Image(new Texture(Gdx.files.internal("season/autumn.png"))));
        images.add(new Image(new Texture(Gdx.files.internal("season/spring.png"))));
        images.add(new Image(new Texture(Gdx.files.internal("season/summer.png"))));
        images.add(new Image(new Texture(Gdx.files.internal("season/winter.png"))));
        return images;
    }



}
