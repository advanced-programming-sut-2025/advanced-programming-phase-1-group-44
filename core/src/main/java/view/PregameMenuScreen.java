package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.App;
import model.GameAssetManager;
import model.Lobby;
import model.miniPlayer;

import java.util.ArrayList;

public class PregameMenuScreen extends AppMenu {

    private final Stage stage;
    private final Skin skin;
    private final Texture background;
    private final Window window;
    private final Table playersTable;
    private Lobby lobby;

    // Static list of players in current lobby — updated from network listener
    public static ArrayList<miniPlayer> currentPlayers = new ArrayList<>();

    // Static lobby name (set when joining)
    public static String currentLobbyName = "";

    public PregameMenuScreen(Lobby lobby) {
        this.lobby = lobby;
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background.png"));

        // MAIN WINDOW
        window = new Window("", skin);
        window.setSize(600, 500);
        updateWindowPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        window.padTop(42);

        // Title
        Label titleLabel = new Label("Lobby: " + lobby.getName(), skin, "title");
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(2.0f);

        Table titleTable = new Table();
        titleTable.add(titleLabel).growX().center().padBottom(22);
        window.add(titleTable).growX().colspan(2).row();

        // --- PLAYERS SECTION ---
        Label playersLabel = new Label("Players in Lobby", skin);
        playersLabel.setFontScale(1.1f);
        window.add(playersLabel).left().padBottom(10).colspan(2).row();

        playersTable = new Table(skin);
        playersTable.defaults().pad(4).left();
        updatePlayersDisplay();

        ScrollPane playersScrollPane = new ScrollPane(playersTable, skin);
        playersScrollPane.setFadeScrollBars(false);
        playersScrollPane.setScrollingDisabled(true, false);
        window.add(playersScrollPane).height(250).width(400).center().colspan(2).padBottom(20).row();

        // BUTTONS ROW
        TextButton leaveButton = new TextButton("Leave Lobby", skin);
        leaveButton.getLabel().setFontScale(1.0f);
        leaveButton.addListener(e -> {
            if (e.toString().equals("touchDown")) {
                miniPlayer player = new miniPlayer();
                player.username = App.getAdmin().getUsername();
                Main.getNetworkClient().removePlayer(player);
                Main.setMenu(new LobbyMenuScreen());
                return true;
            }
            return false;
        });

        TextButton startButton = new TextButton("Start Game", skin);
        startButton.getLabel().setFontScale(1.0f);
        startButton.addListener(e -> {
            if (e.toString().equals("touchDown")) {
                if(App.getAdmin().getUsername().equals(lobby.getAdmin())) {
                    miniPlayer player = new miniPlayer();
                    player.username = App.getAdmin().getUsername();
                    Main.getNetworkClient().createGame(player);
                }
                else{
                    Dialog dialog = new Dialog("only admin can start the game", skin);
                    dialog.button("OK");
                    dialog.show(stage);
                }
                return true;
            }
            return false;
        });

        window.add(leaveButton).width(180).height(48).padRight(15);
        window.add(startButton).width(180).height(48).padLeft(15).row();

        stage.addActor(window);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        System.out.println("Salam");
        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                miniPlayer player = new miniPlayer();
                player.username = App.getAdmin().getUsername();
                Main.getNetworkClient().getLobby(player);
            }
        }, 2, 2);
        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {
                lobby = Main.lobby;
                if(lobby != null) {
                    System.out.println("Lobby is not empty!");
                    System.out.println(lobby.name);
                    for (miniPlayer player : lobby.getPlayers()) {
                        System.out.println(player.username);
                    }
                    updatePlayersDisplay();
                }
            }
        }, 2, 2);
    }

    // Updates the table UI with currentPlayers list
    public void updatePlayersDisplay() {
        playersTable.clear();
        // Header row
        Label nameHeader = new Label("Player Name", skin);
        nameHeader.setFontScale(1.05f);
        playersTable.add(nameHeader).left();
        playersTable.row();
        currentPlayers = lobby.getPlayers();
        // Actual players
        for (miniPlayer player: currentPlayers) {
            playersTable.add(new Label(player.getUsername(), skin)).left();
            playersTable.row();
        }
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

    private void updateWindowPosition(int width, int height) {
        window.setPosition(
            (width - window.getWidth()) / 2f,
            (height - window.getHeight()) / 2f
        );
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
