package main;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import model.enums.Menu;
import view.AppMenu;
import view.SignupScreen;
import view.animalTestScreen;
import view.testScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    private static SpriteBatch batch;
    private Texture image;
    private static Main main;
    public static Label.LabelStyle font; // Static style for global use


    @Override
    public void create() {

// Initialize the TTF font
//        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("myfont.ttf"));
//        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
//        parameter.size = 24; // Font size in pixels
//        parameter.color = Color.WHITE; // Font color
//        parameter.borderWidth = 1; // Optional: Add a border
//        parameter.borderColor = Color.BLACK; // Optional: Border color
//        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS; // Default character set
//
//        // Generate the BitmapFont
//        BitmapFont customFont = generator.generateFont(parameter);
//
//        // Dispose of the generator to free memory
//        generator.dispose();
//
//        // Create and store the global LabelStyle
//        font = new Label.LabelStyle();
//        font.font = customFont;
//        font.fontColor = Color.WHITE;

        main = this;
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        AppMenu menu = new SignupScreen();
//        AppMenu menu = new animalTestScreen();
        main.setScreen(menu);
    }

    @Override
    public void render() {
        super.render();
//        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
//        batch.begin();
//        batch.draw(image, 140, 210);
//        batch.end();
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
