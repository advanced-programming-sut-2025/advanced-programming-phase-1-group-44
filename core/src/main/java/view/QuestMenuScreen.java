package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.App;
import model.GameAssetManager;
import model.GroupQuest;

import java.util.ArrayList;
import java.util.List;

public class QuestMenuScreen extends AppMenu {

    private Skin skin;
    private Texture background;
    private Window mainWindow;
    private Table contentTable;

    private List<GroupQuest> availableQuests = new ArrayList<>();
    private List<GroupQuest> activeQuests = new ArrayList<>();

    public QuestMenuScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background.png"));

        // Window setup
        mainWindow = new Window("", skin);
        mainWindow.setSize(700, 500);
        updateWindowPosition(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        mainWindow.padTop(42);

        Label titleLabel = new Label("Quest Menu", skin, "title");
        titleLabel.setFontScale(1.3f);
        titleLabel.setAlignment(Align.center);

        Table titleTable = new Table();
        titleTable.add(titleLabel).growX().center().padBottom(15);
        mainWindow.add(titleTable).growX().row();

        // Content area with ScrollPane
        contentTable = new Table();
        contentTable.defaults().pad(5).top().left();
        buildQuestLists();

        ScrollPane scrollPane = new ScrollPane(contentTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        mainWindow.add(scrollPane).expand().fill().row();

        stage.addActor(mainWindow);
    }

    public void buildQuestLists() {
        contentTable.clear();

        // -------- Available Quests --------
        Table availableTable = new Table(skin);
        availableTable.add(new Label("Available Quests", skin)).colspan(6).padBottom(10).row();

        availableTable.add("Quest");
        availableTable.add("Players");
        availableTable.add("Item");
        availableTable.add("Amount");
        availableTable.add("Reward");
        availableTable.add("").row();

        for (GroupQuest quest : Main.quests) {
            if(!quest.isFull()){
                availableQuests.add(quest);
            }
            else{
                for (String player : quest.players) {
                    if(player.equals(App.getAdmin().getUsername())){
                        activeQuests.add(quest);
                    }
                }
            }
        }

        for (GroupQuest q : new ArrayList<>(availableQuests)) {
            availableTable.add(q.itemName);
            availableTable.add(q.players.size() + "/" + q.capacity);
            availableTable.add(q.itemName);
            availableTable.add(String.valueOf(q.reqCnt));
            availableTable.add("$" + q.reward);

            TextButton joinBtn = new TextButton("Join", skin);
            joinBtn.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    for (String player : q.players) {
                        if(player.equals(App.getAdmin().getUsername())){
                            showDialog("you have already joined the quest!");
                            return;
                        }
                        if(activeQuests.size() >= 3){
                            showDialog("you can't have more than 3 active queries!");
                            return;
                        }
                    }
                    Main.getNetworkClient().joinQuest(q.itemName, App.getAdmin().getUsername());
                }
            });

            availableTable.add(joinBtn).row();
        }

        // -------- Active Quests --------
        Table activeTable = new Table(skin);
        activeTable.add(new Label("Active Quests", skin)).colspan(3).padBottom(10).row();

        for (GroupQuest a : activeQuests) {
            activeTable.add(new Label(a.itemName, skin)).colspan(3).left().row();

            ProgressBar questBar = new ProgressBar(0, a.reqCnt, 1, false, skin);
            questBar.setValue(a.players.size()); // placeholder
            activeTable.add(questBar).colspan(3).width(400).row();

            for (String player : a.players) {
                activeTable.add(new Label(player, skin)).left();
                ProgressBar pb = new ProgressBar(0, a.reqCnt, 1, false, skin);
                pb.setValue(0); // placeholder
                activeTable.add(pb).width(300).colspan(2).row();
            }

            activeTable.add(new Label("Time left: " + a.getRemainingTime() + " days", skin))
                .colspan(3).padBottom(10).row();
        }

        contentTable.add(availableTable).expandX().fillX().row();
        contentTable.add(activeTable).expandX().fillX().padTop(20).row();
    }

    private void showDialog(String message) {
        Dialog dialog = new Dialog("", skin) {
            @Override
            protected void result(Object object) {
                this.hide();
            }
        };

        Label label = new Label(message, skin);
        label.setAlignment(Align.center);
        label.setWrap(true);

        dialog.getContentTable().add(label)
            .width(300f) // Keep some fixed width for wrapping
            .pad(20f)
            .center();

        dialog.button("OK", true);

        dialog.setModal(true);
        dialog.setMovable(false);

        dialog.show(stage);
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
