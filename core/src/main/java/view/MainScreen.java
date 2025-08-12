package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.App;
import model.GameAssetManager;

public class MainScreen extends AppMenu {

    private Stage stage;
    private final Texture background;
    private final Skin skin;

    public MainScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("background.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();

        createUI();
    }

    private void createUI() {
        // Title
        Label title = new Label("Main Menu", skin);
        title.setFontScale(3f);
        title.setAlignment(Align.center);
        title.setSize(600, 60);
        title.setPosition(
            (Gdx.graphics.getWidth() - 600) / 2f,
            Gdx.graphics.getHeight() - 80
        );
        stage.addActor(title);

        // Menu buttons
        Table table = new Table(skin);
        table.setFillParent(true);
        table.top().padTop(150); // Push buttons below the title
        table.defaults().pad(15).width(300).height(60);

        // Profile Menu Button
        TextButton profileButton = new TextButton("Go to Profile Menu", skin);
        profileButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: Navigate to ProfileMenu
                Main.setMenu(new ProfileScreen());
                // Main.setMenu(new ProfileMenuScreen());
            }
        });
        table.add(profileButton).row();

        // Game Menu Button
        TextButton gameButton = new TextButton("Go to Game Menu", skin);
        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: Navigate to GameMenu
                Main.setMenu(new gamemenuScreen());
                // Main.setMenu(new GameMenuScreen());
            }
        });
        table.add(gameButton).row();

        // Logout Button
        TextButton logoutButton = new TextButton("Logout", skin);
        logoutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Go back to SignupScreen
                App.logout();
                Main.setMenu(new SignupScreen());
            }
        });
        table.add(logoutButton).row();

        stage.addActor(table);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
