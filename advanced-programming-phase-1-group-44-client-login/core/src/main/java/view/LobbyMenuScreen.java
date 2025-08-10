package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.App;
import model.GameAssetManager;
import model.Lobby;
import model.miniPlayer;

import java.util.ArrayList;

public class LobbyMenuScreen extends AppMenu {

    private final Stage stage;
    private final Skin skin;
    private final Texture background;
    private final Window window;
    private final Table publicLobbiesTable;

    public static ArrayList<Lobby> lobbies = new ArrayList<>();

    public LobbyMenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();

        // Use your main background
        background = new Texture(Gdx.files.internal("background.png"));

        // MAIN WINDOW
        window = new Window("", skin);
        window.setSize(700, 650);
        updateWindowPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        window.padTop(42);

        // Title
        Label titleLabel = new Label("Lobby Menu", skin, "title");
        titleLabel.setAlignment(Align.center);
        titleLabel.setFontScale(2.2f);

        Table titleTable = new Table();
        titleTable.add(titleLabel).growX().center().padBottom(22);
        window.add(titleTable).growX().colspan(3).row();

        // --- CREATE LOBBY SECTION ---
        Label createLabel = new Label("Create New Lobby:", skin);
        createLabel.setFontScale(1.15f);

        final TextField createLobbyNameField = new TextField("", skin);
        createLobbyNameField.setMessageText("Enter lobby name...");

        // Public/Private toggle
        final CheckBox privacyCheckBox = new CheckBox(" Private", skin);
        privacyCheckBox.getLabel().setFontScale(1.0f);

        // Password field (hidden initially)
        final TextField passwordField = new TextField("", skin);
        passwordField.setMessageText("Enter password...");
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');
        passwordField.setVisible(false);

        privacyCheckBox.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                boolean isPrivate = privacyCheckBox.isChecked();
                passwordField.setVisible(isPrivate);
            }
        });

        final TextButton createLobbyButton = new TextButton("Create", skin);
        createLobbyButton.getLabel().setFontScale(1.1f);

        createLobbyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String name = createLobbyNameField.getText();
                boolean isPrivate = privacyCheckBox.isChecked();
                String password = passwordField.getText();

                if (name.isEmpty()) {
                    Dialog dialog = new Dialog("Error", skin);
                    dialog.text("The name can't be empty!");
                    dialog.button("OK");
                    dialog.show(stage);
                    return;
                }

                if (isPrivate && password.isEmpty()) {
                    Dialog dialog = new Dialog("Error", skin);
                    dialog.text("Private lobbies must have a password!");
                    dialog.button("OK");
                    dialog.show(stage);
                    return;
                }

                Dialog dialog = new Dialog("Lobby Created", skin) {
                    @Override
                    protected void result(Object object) {
                        Lobby lobby = new Lobby(createLobbyNameField.getText(), "admin", privacyCheckBox.isChecked(), passwordField.getText());
                        Main.getNetworkClient().buildLobby(lobby);
                        createLobbyNameField.setText("");
                        passwordField.setText("");
                        privacyCheckBox.setChecked(false);
                        passwordField.setVisible(false);
                    }
                };
                dialog.text(
                    "Lobby name: " + name + " \n Privacy: " + (isPrivate ? "Private" : "Public") +
                (isPrivate ? " \n Password: " + password : "")
                );
                dialog.button("OK");
                dialog.show(stage);
            }
        });

        // Create lobby layout
        Table createTable = new Table();
        createTable.add(createLabel).left().padRight(14);
        createTable.add(createLobbyNameField).width(250).padRight(12);
        createTable.add(createLobbyButton).width(95).height(40).row();

        // Privacy row
        createTable.add(privacyCheckBox).left().padTop(8).padRight(8);
        createTable.add(passwordField).width(200).colspan(2).left().padTop(8).row();

        window.add(createTable).expandX().center().colspan(3).padBottom(28).row();

        // --- JOIN BY ID SECTION ---
        Label joinIdLabel = new Label("Or Join by Lobby ID:", skin);
        joinIdLabel.setFontScale(1.09f);
        final TextField joinLobbyIdField = new TextField("", skin);
        joinLobbyIdField.setMessageText("Lobby ID...");
        final TextButton joinLobbyIdButton = new TextButton("Join with ID", skin);
        joinLobbyIdButton.getLabel().setFontScale(1.0f);

        Table joinIdTable = new Table();
        joinIdTable.add(joinIdLabel).padRight(12);
        joinIdTable.add(joinLobbyIdField).width(120).padRight(10);
        joinIdTable.add(joinLobbyIdButton).width(125);
        window.add(joinIdTable).expandX().center().colspan(3).padBottom(25).row();

        // --- PUBLIC LOBBY LIST ---
        Label publicLabel = new Label("Public Lobbies", skin);
        publicLabel.setFontScale(1.12f);
        window.add(publicLabel).padBottom(14).colspan(3).left().row();

        publicLobbiesTable = new Table(skin);
        publicLobbiesTable.defaults().pad(4).left();

        // Initial table population
        updateLobbiesDisplay();

        ScrollPane lobbyScroll = new ScrollPane(publicLobbiesTable, skin);
        lobbyScroll.setFadeScrollBars(false);
        lobbyScroll.setScrollingDisabled(true, false);
        window.add(lobbyScroll).height(140).width(450).center().colspan(3).padBottom(18).row();

        // BACK BUTTON
        TextButton backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1.1f);
        backButton.addListener(e -> {
            if (e.toString().equals("touchDown")) {
                Main.setMenu(new MainScreen());
                return true;
            }
            return false;
        });
        window.add().colspan(1);
        window.add(backButton).width(140).height(48).colspan(1).center().padTop(10);
        window.add().colspan(1);
        window.row();

        stage.addActor(window);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        // Send initial lobby list request
        Main.getNetworkClient().getLobbies();

        // Schedule auto-refresh every 2 seconds
        com.badlogic.gdx.utils.Timer.schedule(new com.badlogic.gdx.utils.Timer.Task() {
            @Override
            public void run() {

                Main.getNetworkClient().getLobbies();
            }
        }, 2, 2);
    }

    // Updates the table based on the current lobbies list
    public void updateLobbiesDisplay() {
        publicLobbiesTable.clear();

        // Header row
        Label header1 = new Label("Lobby Name", skin);
        header1.setFontScale(1.06f);
        Label header2 = new Label("Players", skin);
        header2.setFontScale(1.06f);

        publicLobbiesTable.add(header1).expandX().left();
        publicLobbiesTable.add(header2).width(80).left().padLeft(24);
        publicLobbiesTable.add().width(80);
        publicLobbiesTable.row();

        // Populate actual lobbies
        for (Lobby l : lobbies) {
            publicLobbiesTable.add(new Label(l.getName(), skin)).expandX().left();
            int playerCount = (l.getPlayers() != null) ? l.getPlayers().size() : 0;
            publicLobbiesTable.add(new Label(playerCount + " / 4", skin)).width(80).left().padLeft(24);

            TextButton joinBtn = new TextButton("Join", skin);
            joinBtn.getLabel().setFontScale(0.98f);
            joinBtn.addListener(new ClickListener(){
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Main.getMain().setScreen(new PregameMenuScreen(l));
                    miniPlayer player = new miniPlayer();
                    player.username = App.getAdmin().getUsername();
                    Main.getNetworkClient().addPlayer(l, player);
                }
            });
            publicLobbiesTable.add(joinBtn).width(70);
            publicLobbiesTable.row();
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
