package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import model.GameAssetManager;

public class FridgeMenuScreen implements Screen {

    private Stage stage;
    private Skin skin;

    private static final int FRIDGE_COLUMNS = 4;
    private static final int FRIDGE_ROWS = 20;

    private Table contentTable;
    private ScrollPane scrollPane;
    private Table fridgeTable;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();

        // --- Fridge Item Grid ---
        fridgeTable = new Table(skin);
        for (int i = 0; i < FRIDGE_ROWS; i++) {
            for (int j = 0; j < FRIDGE_COLUMNS; j++) {
                Texture background = new Texture(Gdx.files.internal("inventory/itemBackground.png"));
                Image itemBackground = new Image(background);

                // Tint the slot for a chilly look
                itemBackground.setColor(new Color(0.8f, 0.92f, 1f, 1f)); // light blue

                fridgeTable.add(itemBackground).size(64, 64).pad(6);
            }
            fridgeTable.row();
        }

        scrollPane = new ScrollPane(fridgeTable, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        // --- Fridge title (can use same style as tabs, adjust as you wish) ---
        Label fridgeTitle = new Label("Fridge", skin, "title");
        fridgeTitle.setFontScale(1.15f);

        Table tabBar = new Table();
        tabBar.add(fridgeTitle).padTop(10).padBottom(10).left();

        // --- Content Area ---
        contentTable = new Table(skin);
        contentTable.setFillParent(false);
        showFridge();

        // --- Root Table: Title above content area ---
        Table root = new Table();
        root.setFillParent(true);
        root.top();
        root.add(tabBar).expandX().fillX();
        root.row();
        root.add(contentTable).expand().fill();

        stage.addActor(root);
    }

    private void showFridge() {
        contentTable.clear();
        contentTable.add(scrollPane).expand().fill();
    }

    @Override
    public void render(float delta) {
        // Optional: Slight blue fridge background
        Gdx.gl.glClearColor(0.93f, 0.97f, 1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
