package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import model.App;
import model.DateTime;
import model.GameAssetManager;
import model.enums.Season;
import model.enums.Weather;

public class testScreen extends AppMenu {

    private Stage stage;
    private Skin skin;
    private Texture background;

    private Image weatherImage, seasonImage;
    private Label clockLabel, dateLabel;

    private float timeAccumulator = 0;

    private DateTime date;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("background.png"));

        createInfoWidget();
    }

    private void createInfoWidget() {
        final String[] nameDay = {"-", "Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};

        if (App.getCurrentGame() == null) return;

        Weather weather = App.getCurrentGame().getWeather();
        date = App.getCurrentGame().getDateTime();
        Season season = date.getSeason();

        int dayOfWeek = date.getDayOfWeek();
        int dayOfMonth = date.getDay();

        weatherImage = weather.getImage();
        seasonImage = season.getImage();

        dateLabel = new Label(nameDay[dayOfWeek] + " " + dayOfMonth, skin);
        dateLabel.setFontScale(1.1f);

        clockLabel = new Label(String.valueOf(date.getTime()), skin);
        clockLabel.setFontScale(1.1f);

        Table infoTable = new Table(skin);
        infoTable.top().right();
        infoTable.setFillParent(true);
        infoTable.padTop(20).padRight(20);

        // First row: icons
        infoTable.add(weatherImage).size(40).padRight(10);
        infoTable.add(seasonImage).size(40).row();

        // Second row: date and clock
        infoTable.add(dateLabel).padTop(5).padRight(10).right();
        infoTable.add(clockLabel).padTop(5).right().row();

        stage.addActor(infoTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.getBatch().end();

        // Update clock label every second using in-game time
        timeAccumulator += delta;
        if (timeAccumulator >= 1f) {
            timeAccumulator = 0;
            if (date != null) {
                clockLabel.setText(String.valueOf(date.getTime()));
            }
        }

        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
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
