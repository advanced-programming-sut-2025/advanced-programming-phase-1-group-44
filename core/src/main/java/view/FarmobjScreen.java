package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import controller.MapController;
import main.Main;
import model.*;
import model.enums.Season;
import model.enums.Weather;

import java.util.ArrayList;

public class FarmobjScreen extends AppMenu {
    private Stage worldStage;  // Game map
    private Stage uiStage;     // HUD/UI
    private final Texture background;
    private final Skin skin;
    private MapController mapController = new MapController();
    private MapFarm mf;

    private ScoreboardDialog scoreboardDialog;

    // Clock widget
    private Image weatherImage, seasonImage;
    private Label clockLabel, dateLabel;
    private DateTime date;
    private float timeAccumulator = 0;

    public FarmobjScreen(MapFarm mff) {
        mf = mff;

        // World stage (tiles, actors)
        worldStage = new Stage(new StretchViewport(30, 30));

        // UI stage
        uiStage = new Stage(new ScreenViewport());

        background = new Texture(Gdx.files.internal("background.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();

        calcui();               // populate map
        createClockWidget();    // top-right clock/date
        createButtonsColumn();  // left-side buttons
    }

    // Build initial map
    void calcui() {
        Player pl = App.getCurrentGame().getCurrentPlayer();
        ArrayList<ArrayList<MapObj>> res = new ArrayList<>();
        for (int i = 0; i < mf.getWidth(); i++) {
            res.add(new ArrayList<>());
            for (int j = 0; j < mf.getHigh(); j++) {
                res.get(i).add(mf.GetCell(i, j));
            }
        }
        for (int i = 0; i < mf.getWidth(); i++) {
            for (int j = 0; j < mf.getHigh(); j++) {
                if (res.get(i).get(j).getName().equals("Space")) {
                    ((Space) res.get(i).get(j)).updcolor(i, j);
                }
                res.get(i).get(j).setPosition((29 - i), (29 - j));
                res.get(i).get(j).setSize(res.get(i).get(j).getHigh(), res.get(i).get(j).getwidth());
                worldStage.addActor(res.get(i).get(j));
            }
        }
    }

    private void createClockWidget() {
        final String[] nameDay = {"-", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        if (App.getCurrentGame() == null) return;

        Weather weather = App.getCurrentGame().getWeather();
        date = App.getCurrentGame().getDateTime();
        Season season = date.getSeason();

        weatherImage = weather.getImage();
        seasonImage = season.getImage();

        weatherImage.setSize(24, 24);
        seasonImage.setSize(24, 24);

        dateLabel = new Label(nameDay[date.getDayOfWeek()] + " " + date.getDay(), skin);
        dateLabel.setFontScale(0.8f);
        clockLabel = new Label(String.valueOf(date.getTime()), skin);
        clockLabel.setFontScale(0.8f);

        Table table = new Table();
        table.top().right();
        table.setFillParent(true);
        table.padTop(10).padRight(10);

        table.add(weatherImage).size(24).padBottom(2).row();
        table.add(seasonImage).size(24).padBottom(4).row();
        table.add(dateLabel).padBottom(2).right().row();
        table.add(clockLabel).right();

        uiStage.addActor(table);
    }

    private void createButtonsColumn() {
        Texture scoreboardTexture = new Texture(Gdx.files.internal("scoreboard.png"));
        TextureRegionDrawable scoreboardDrawable = new TextureRegionDrawable(new TextureRegion(scoreboardTexture));

        ImageButton scoreboardBtn = new ImageButton(scoreboardDrawable);
        scoreboardBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                new ScoreboardDialog(uiStage, skin).open();
            }
        });

        Texture voteTexture = new Texture(Gdx.files.internal("vote.png"));
        TextureRegionDrawable voteDrawable = new TextureRegionDrawable(new TextureRegion(voteTexture));

        ImageButton voteBtn = new ImageButton(voteDrawable);
        voteBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                new VoteDialog(uiStage, skin).showVoteSetupDialog();
            }
        });

        Texture friendsTexture = new Texture(Gdx.files.internal("friends.png"));
        TextureRegionDrawable friendsDrawable = new TextureRegionDrawable(new TextureRegion(friendsTexture));

        ImageButton friendsBtn = new ImageButton(friendsDrawable);
        friendsBtn.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ClickListener() {
            @Override
            public void clicked(com.badlogic.gdx.scenes.scene2d.InputEvent event, float x, float y) {
                new FriendsDialog(uiStage, skin);
            }
        });

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
        table.padTop(10).padLeft(10);

        table.add(scoreboardBtn).size(32).padBottom(10).row();
        table.add(voteBtn).size(32).padBottom(10).row();
        table.add(friendsBtn).size(32);

        uiStage.addActor(table);
    }

    public void startVote(String description) {
        new VoteDialog(uiStage, skin).showVoteParticipationDialog(description);
    }

    public void showVoteResult(String description) {
        new VoteDialog(uiStage, skin).showVoteResultDialog(description);
    }

    @Override
    public void show() {
        InputMultiplexer mux = new InputMultiplexer();
        mux.addProcessor(uiStage);
        mux.addProcessor(worldStage);
        Gdx.input.setInputProcessor(mux);
    }

    private void refreshDateTimeWidget() {
        if (App.getCurrentGame() == null) return;
        DateTime date = App.getCurrentGame().getDateTime();
        Weather weather = App.getCurrentGame().getWeather();
        Season season = date.getSeason();
        String[] nameDay = {"-", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        dateLabel.setText(nameDay[date.getDayOfWeek()] + " " + date.getDay());
        clockLabel.setText(String.valueOf(date.getTime()));
        weatherImage.setDrawable(weather.getImage().getDrawable());
        seasonImage.setDrawable(season.getImage().getDrawable());
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        timeAccumulator += delta;
        if (timeAccumulator >= 10f) {
            refreshDateTimeWidget();
            timeAccumulator = 0f;
        }

        int ni = App.getCurrentGame().getCurrentPlayer().getXlocation();
        int nj = App.getCurrentGame().getCurrentPlayer().getYlocation();

        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            Main.setMenu(new gameplayScreen());
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            movePlayer(ni - 1, nj);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.A)) {
            movePlayer(ni + 1, nj);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.W)) {
            movePlayer(ni, nj - 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.S)) {
            movePlayer(ni, nj + 1);
        } else if (Gdx.input.isKeyJustPressed(Input.Keys.Z)) {
            mapController.shokhm(ni + 1, nj);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) && Gdx.input.isKeyJustPressed(Input.Keys.C)) {
            ChatScreen chatScreen = new ChatScreen();
            chatScreen.setReturnScreen(this);
            Main.getMain().setScreen(chatScreen);
            chatScreen.init();
        }

        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getBatch().end();

        worldStage.act(delta);
        uiStage.act(delta);

        worldStage.draw();
        uiStage.draw();
    }

    private void movePlayer(int newX, int newY) {
        int oldX = App.getCurrentGame().getCurrentPlayer().getXlocation();
        int oldY = App.getCurrentGame().getCurrentPlayer().getYlocation();

        mapController.walk2(newX, newY);

        // Update old cell
        MapObj oldCell = mf.GetCell(oldX, oldY);
        oldCell.setPosition((29 - oldX), (29 - oldY));
        if (oldCell.getName().equals("Space")) {
            ((Space) oldCell).updcolor(oldX, oldY);
        }
        worldStage.addActor(oldCell);

        // Update new cell
        MapObj newCell = mf.GetCell(newX, newY);
        newCell.setPosition((29 - newX), (29 - newY));
        newCell.toFront();
    }

    @Override
    public void resize(int width, int height) {
        worldStage.getViewport().update(width, height, true);
        uiStage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        worldStage.dispose();
        uiStage.dispose();
        background.dispose();
    }

    public void notify(String title, String message) {
        new NotificationDialog(uiStage, skin, title, message);
    }
}