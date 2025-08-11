package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.GameAssetManager;

public class TradeMenuScreen extends AppMenu {

    private Stage stage;
    private Skin skin;
    private Texture background;
    private Window window;

    public TradeMenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background.png"));

        // Main window
        window = new Window("", skin);
        window.setSize(500, 300);
        updateWindowPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        window.padTop(42);

        // Title
        Label titleLabel = new Label("Trade Menu", skin, "title");
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(1.5f);

        Table titleTable = new Table();
        titleTable.add(titleLabel).growX().center().padBottom(20);
        window.add(titleTable).colspan(2).growX().row();

        // Buttons
        TextButton startTradeButton = new TextButton("Start Trade", skin);
        startTradeButton.getLabel().setFontScale(1.0f);

        TextButton tradeHistoryButton = new TextButton("Trade History", skin);
        tradeHistoryButton.getLabel().setFontScale(1.0f);

        window.add(startTradeButton).width(200).height(50).padBottom(15).colspan(2).row();
        window.add(tradeHistoryButton).width(200).height(50).colspan(2).row();

        stage.addActor(window);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.11f, 0.13f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw background
        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0,
            Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getBatch().end();

        // Draw stage
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
        updateWindowPosition(width, height);
    }

    private void updateWindowPosition(int width, int height) {
        window.setPosition((width - window.getWidth()) / 2f,
            (height - window.getHeight()) / 2f);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
