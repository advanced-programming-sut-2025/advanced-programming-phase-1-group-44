package view;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import main.Main;
import model.enums.Menu;

import java.util.Scanner;

public abstract class AppMenu implements Screen {
    protected Stage stage;
    public Stage getStage() {
        return stage;
    }
}
