package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controller.GameMenuController;
import controller.GamePlayController;
import main.Main;
import model.App;
import model.GameAssetManager;

import java.util.ArrayList;

public class gamemenuScreen extends AppMenu {
    private Stage stage;
    private Texture background;
    private Skin skin;
    private GameMenuController mc;

    public gamemenuScreen() {
        stage = new Stage(new ScreenViewport());
        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background.png"));
        mc = new GameMenuController();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        initUI();
    }

    private void initUI() {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.center().pad(20);

        TextButton newGameBtn = new TextButton("New Game", skin);
        newGameBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                showNewGameDialog();
            }
        });

        TextButton loadGameBtn = new TextButton("Load Game", skin);
        loadGameBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean ok = mc.load();
                if (!ok) {
                    showMessage("خطا در لود بازی");
                } else {
                    Main.setMenu(new GamePlay());
                }
            }
        });

        TextButton viewGameBtn = new TextButton("View Current Save", skin);
        viewGameBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                //mc.viewCurrent();
            }
        });

        TextButton exitBtn = new TextButton("Exit Game", skin);
        exitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                mc.exitgame();
                Main.setMenu(new MainScreen());
            }
        });

        table.add(newGameBtn).width(200).height(50).pad(10).row();
        table.add(loadGameBtn).width(200).height(50).pad(10).row();
        table.add(viewGameBtn).width(200).height(50).pad(10).row();
        table.add(exitBtn).width(200).height(50).pad(10);

        stage.clear();
        stage.addActor(table);
    }

    private void showNewGameDialog() {
        final ArrayList<String> players = new ArrayList<>();
        final TextField nameField = new TextField("", skin);
        final Label errorLabel = new Label("", skin);
        errorLabel.setColor(Color.RED);

        // New label to display the full list of players
        final Label playersLabel = new Label("Players: (none)", skin);

        final Dialog dialog = new Dialog("Create New Game", skin) {
            @Override
            protected void result(Object object) {
                String action = (String) object;
                if ("start".equals(action)) {
                    if (players.isEmpty()) {
                        errorLabel.setText("Please add at least one player.");
                    } else {
                        boolean ok = mc.createNewGame(players);
                        System.out.println(ok);
                        for (String s:players){
                            System.out.println(s);
                        }
                        if (!ok) {
                            errorLabel.setText("Error creating game!");
                        } else {
                            this.hide();
                            showMapSelectionDialog();
                        }
                    }
                } else {
                    this.hide();  // Cancel
                }
            }
        };

        Table content = dialog.getContentTable();
        content.add(new Label("Enter username:", skin)).pad(5).row();
        content.add(nameField).width(250).pad(5).row();
        content.add(errorLabel).pad(5).row();

        // Show players list below the errorLabel
        content.add(playersLabel).pad(5).row();

        // Add Player button: updates both errorLabel and playersLabel
        TextButton addBtn = new TextButton("Add Player", skin);
        addBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String username = nameField.getText().trim();
                if (username.isEmpty()) {
                    errorLabel.setText("Username is empty!");
                } else if (players.contains(username)) {
                    errorLabel.setText("This user has already been added.");
                } else if (players.size() >= 3) {
                    errorLabel.setText("You cannot add more than 3 players.");
                } else {
                    players.add(username);
                    errorLabel.setText("Player added: " + username);
                    nameField.setText("");

                    // Rebuild the list text
                    StringBuilder sb = new StringBuilder("Players:");
                    for (String p : players) {
                        sb.append("\n - ").append(p);
                    }
                    playersLabel.setText(sb.toString());
                }
            }
        });
        dialog.getButtonTable().add(addBtn).pad(5);

        // Start & Cancel: these auto-close via result()
        dialog.button("Start Game", "start");
        dialog.button("Cancel", "cancel");

        dialog.show(stage);
    }


    private void showMapSelectionDialog() {
        final Dialog dlg = new Dialog("Select Map", skin) {
            @Override
            protected void result(Object object) {
                int mapIndex = (Integer) object;
                mc.chooseGameMap(mapIndex);
                GamePlayController gmcf=new GamePlayController();
                if (!App.getCurrentGame().getCurrentPlayer().getUsername().equals(App.getCurrentGame().getUsers().get(App.getCurrentGame().getCountuser()-1).getUsername())) {
                    this.hide();
                    gmcf.nextTurn();
                    showMapSelectionDialog();
                } else {
                    this.hide();
                    Main.setMenu(new gameplayScreen());
                }
            }
        };

        String username = App.getCurrentGame().getCurrentPlayer().getUsername();
        dlg.getContentTable()
            .add(new Label("Choose map for " + username, skin))
            .pad(10)
            .row();
        dlg.button("Map 1", 1);
        dlg.button("Map 2", 2);
        dlg.show(stage);
    }

    private void showMessage(String msg) {
        Dialog d = new Dialog("Message", skin);
        d.text(msg);
        d.button("OK");
        d.show(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0,
            Gdx.graphics.getWidth(),
            Gdx.graphics.getHeight());
        Main.getBatch().end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
