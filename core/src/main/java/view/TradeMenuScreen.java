package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.App;
import model.GameAssetManager;
import model.miniPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TradeMenuScreen extends AppMenu {

    private Skin skin;
    private Texture background;

    // UI Parts
    private Table contentTable; // This changes dynamically
    private Window mainWindow;
    private Map<String, Object> data = new HashMap<>();

    public TradeMenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background.png"));

        // Main window
        mainWindow = new Window("", skin);
        mainWindow.setSize(500, 350);
        updateWindowPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainWindow.padTop(42);

        // Title
        Label titleLabel = new Label("Trade Menu", skin, "title");
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(1.5f);

        Table titleTable = new Table();
        titleTable.add(titleLabel).growX().center().padBottom(20);
        mainWindow.add(titleTable).colspan(2).growX().row();

        // Content area (changes)
        contentTable = new Table();
        showMainMenu(); // start with main buttons
        mainWindow.add(contentTable).expand().fill().colspan(2).row();

        stage.addActor(mainWindow);
    }

    public TradeMenuScreen(Map<String, Object> data) {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background.png"));

        // Main window
        mainWindow = new Window("", skin);
        mainWindow.setSize(500, 350);
        updateWindowPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainWindow.padTop(42);

        // Title
        Label titleLabel = new Label("Trade Menu", skin, "title");
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(1.5f);

        Table titleTable = new Table();
        titleTable.add(titleLabel).growX().center().padBottom(20);
        mainWindow.add(titleTable).colspan(2).growX().row();

        // Content area (changes)
        contentTable = new Table();
        showTradeSession(); // start with main buttons
        mainWindow.add(contentTable).expand().fill().colspan(2).row();

        stage.addActor(mainWindow);
        this.data = data;
    }

    private void showMainMenu() {
        contentTable.clear();

        TextButton startTradeButton = new TextButton("Start Trade", skin);
        TextButton tradeHistoryButton = new TextButton("Trade History", skin);

        startTradeButton.addListener(e -> {
            if (!startTradeButton.isPressed()) return false;
            showTradeSession();
            return true;
        });

        tradeHistoryButton.addListener(e -> {
            if (!tradeHistoryButton.isPressed()) return false;
            showTradeHistory();
            return true;
        });

        contentTable.add(startTradeButton).width(200).height(50).padBottom(15).row();
        contentTable.add(tradeHistoryButton).width(200).height(50).row();
    }

    private void showTradeSession() {
        contentTable.clear();

        // Label
        Label label = new Label("Trade Session View", skin);
        label.setFontScale(1.2f);

        // SelectBox with test options
        SelectBox<String> selectBox = new SelectBox<>(skin);
        Array<String> playerNames = new Array<>();
        for (miniPlayer player : Main.lobby.getPlayers()) {
            if(!player.username.equals(App.getAdmin().getUsername())){
                playerNames.add(player.username);
            }
        }
        selectBox.setItems(playerNames);
        TextButton offerTradeButton = new TextButton("Offer Trade", skin);
        offerTradeButton.addListener(e -> {
            if (!offerTradeButton.isPressed()) return false;
            Main.getNetworkClient().offerTrade(App.getAdmin(), selectBox.getSelected());
            return true;
        });

        // Back button
        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(e -> {
            if (!backButton.isPressed()) return false;
            showMainMenu();
            return true;
        });

        // Layout
        contentTable.add(label).padBottom(20).row();
        contentTable.add(selectBox).width(200).height(40).padBottom(15).row();
        contentTable.add(offerTradeButton).width(200).height(50).padBottom(15).row();
        contentTable.add(backButton).width(200).height(50).row();
    }


    private void showTradeHistory() {
        contentTable.clear();

        Label label = new Label("Trade History View", skin);
        label.setFontScale(1.2f);

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(e -> {
            if (!backButton.isPressed()) return false;
            showMainMenu();
            return true;
        });

        contentTable.add(label).padBottom(20).row();
        contentTable.add(backButton).width(200).height(50).row();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.11f, 0.13f, 1);
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
        updateWindowPosition(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    private void updateWindowPosition(int width, int height) {
        mainWindow.setPosition((width - mainWindow.getWidth()) / 2f,
            (height - mainWindow.getHeight()) / 2f);
    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }

    @Override
    public Stage getStage() {
        return stage;
    }
}
