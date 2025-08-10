package main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import model.Lobby;
import model.enums.Menu;
import view.AppMenu;
import view.SignupScreen;
import view.animalTestScreen;
import view.testScreen;

import com.esotericsoftware.kryonet.Connection;


/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static SpriteBatch batch;
    private Texture image;
    private static Main main;
    public static Label.LabelStyle font; // Static style for global use
    public static Lobby lobby;

    private static NetworkClient networkClient;


    public static NetworkClient getNetworkClient() {
        return networkClient;
    }

    @Override
    public void create() {

        main = this;
        networkClient = new NetworkClient();

        new Thread(() -> {
            networkClient.start();
        }, "NetworkClientThread").start();
        // initializing - syncing with server

        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        AppMenu menu = new SignupScreen();
//        AppMenu menu = new animalTestScreen();
        main.setScreen(menu);



    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    public static Main getMain() {
        return main;
    }

    public static SpriteBatch getBatch() {
        return batch;
    }

    public static void setMenu(AppMenu menu) {
        main.getScreen().dispose();
        main.setScreen(menu);
    }

}
