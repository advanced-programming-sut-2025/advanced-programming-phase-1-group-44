package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import controller.GameMenuController;
import main.Main;
import model.*;

import java.util.ArrayList;

public class gameplayScreen extends AppMenu {
    private Texture jangalbala, jangalchap, jangalpaeen, jangalrast;
    private Stage stage, uiStage;
    private Texture background;
    private Skin skin;
    private GameMenuController mc;

    public gameplayScreen() {
        stage = new Stage(new StretchViewport(90, 90));
        uiStage = new Stage();
        skin = GameAssetManager.getGameAssetManager().getSkin();
        background = new Texture(Gdx.files.internal("mapback.png"));
        mc = new GameMenuController();
        jangalbala = new Texture(Gdx.files.internal("jangalbala.png"));
        jangalchap = new Texture(Gdx.files.internal("jangalchap.png"));
        jangalpaeen = new Texture(Gdx.files.internal("jangalpaeen.png"));
        jangalrast = new Texture(Gdx.files.internal("jangalrast.png"));

        calcui();
    }

    void calcui() {
        ArrayList<Player> pls = App.getCurrentGame().getUsers();
        MapFarm mf = pls.get(0).getCurrentfarm();
        ArrayList<ArrayList<MapObj>> res = new ArrayList<>();

        // Initialize with Space objects
        for (int i = 0; i < mf.getWidth() * 3; i++) {
            res.add(new ArrayList<MapObj>());
            for (int j = 0; j < mf.getHigh() * 3; j++) {
                res.get(i).add(new Space());
            }
        }

        // Player farms
        int nowi = 0, nowj = 0;
        for (int i = 0; i < pls.get(0).getCurrentfarm().getWidth(); i++) {
            for (int j = 0; j < pls.get(0).getCurrentfarm().getHigh(); j++) {
                res.get(i + nowi).set(j + nowj, pls.get(0).getCurrentfarm().GetCell(i, j));
            }
        }

        nowi = 0;
        nowj = mf.getHigh() * 2;
        for (int i = 0; i < pls.get(1).getCurrentfarm().getWidth(); i++) {
            for (int j = 0; j < pls.get(1).getCurrentfarm().getHigh(); j++) {
                res.get(i + nowi).set(j + nowj, pls.get(1).getCurrentfarm().GetCell(i, j));
            }
        }

        nowi = mf.getWidth() * 2;
        nowj = 0;
        for (int i = 0; i < pls.get(2).getCurrentfarm().getWidth(); i++) {
            for (int j = 0; j < pls.get(2).getCurrentfarm().getHigh(); j++) {
                res.get(i + nowi).set(j + nowj, pls.get(2).getCurrentfarm().GetCell(i, j));
            }
        }

        nowi = mf.getWidth() * 2;
        nowj = mf.getHigh() * 2;
        for (int i = 0; i < pls.get(3).getCurrentfarm().getWidth(); i++) {
            for (int j = 0; j < pls.get(3).getCurrentfarm().getHigh(); j++) {
                res.get(i + nowi).set(j + nowj, pls.get(3).getCurrentfarm().GetCell(i, j));
            }
        }

        // Dehkade in center
        nowi = mf.getWidth();
        nowj = mf.getHigh();
        for (int i = 0; i < App.getCurrentGame().getDehkade().getWidth(); i++) {
            for (int j = 0; j < App.getCurrentGame().getDehkade().getHigh(); j++) {
                res.get(i + nowi).set(j + nowj, App.getCurrentGame().getDehkade().GetCell(i, j));
            }
        }

        // Add actors
        for (int i = 0; i < mf.getWidth() * 3; i++) {
            for (int j = 0; j < mf.getHigh() * 3; j++) {
                if (res.get(i).get(j).getName().equals("Space")) {
                    ((Space) res.get(i).get(j)).updcolor(i % mf.getWidth(), j % mf.getHigh());
                }
                res.get(i).get(j).setPosition((89 - i), (89 - j));
                res.get(i).get(j).setSize(res.get(i).get(j).getHigh(), res.get(i).get(j).getwidth());
                stage.addActor(res.get(i).get(j));
            }
        }
    }

    public void startVote(String description) {
        VoteDialog voteDialog = new VoteDialog(uiStage, skin);
        voteDialog.showVoteParticipationDialog(description);
    }

    public void showVoteResult(String description) {
        VoteDialog voteDialog = new VoteDialog(uiStage, skin);
        voteDialog.showVoteResultDialog(description);
    }

    public void notify(String title, String message) {
        new NotificationDialog(uiStage, skin, title, message);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float touchX = Gdx.input.getX();
            float touchY = Gdx.graphics.getHeight() - Gdx.input.getY();
            System.out.println(touchX + " " + touchY);

            if (touchX >= 970 && touchY >= 536) {
                if (App.getCurrentGame().getCurrentPlayer() == App.getCurrentGame().getUsers().get(0)) {
                    Main.setMenu(new FarmobjScreen(App.getCurrentGame().getCurrentPlayer().getCurrentfarm()));
                } else {
                    Main.setMenu(new ErrorScreen("We came here so I could tell you : I know you're planning to steal, but it's a bad thing to do."));
                }
            }
            if (touchX <= 441 && touchY >= 536) {
                if (App.getCurrentGame().getCurrentPlayer() == App.getCurrentGame().getUsers().get(2)) {
                    Main.setMenu(new FarmobjScreen(App.getCurrentGame().getCurrentPlayer().getCurrentfarm()));
                } else {
                    Main.setMenu(new ErrorScreen("We came here so I could tell you : I know you're planning to steal, but it's a bad thing to do."));
                }
            }
            if (touchX <= 460 && touchY <= 260) {
                if (App.getCurrentGame().getCurrentPlayer() == App.getCurrentGame().getUsers().get(3)) {
                    Main.setMenu(new FarmobjScreen(App.getCurrentGame().getCurrentPlayer().getCurrentfarm()));
                } else {
                    Main.setMenu(new ErrorScreen("We came here so I could tell you : I know you're planning to steal, but it's a bad thing to do."));
                }
            }
            if (touchX >= 970 && touchY <= 260) {
                if (App.getCurrentGame().getCurrentPlayer() == App.getCurrentGame().getUsers().get(1)) {
                    Main.setMenu(new FarmobjScreen(App.getCurrentGame().getCurrentPlayer().getCurrentfarm()));
                } else {
                    Main.setMenu(new ErrorScreen("We came here so I could tell you : I know you're planning to steal, but it's a bad thing to do."));
                }
            }
        }
    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0,
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight());
        Main.getBatch().end();

        stage.act(delta);
        stage.draw();
        uiStage.draw();

        Main.getBatch().begin();
        Main.getBatch().draw(jangalbala, 460, 525, 480, 300);
        Main.getBatch().draw(jangalchap, 0, 270, 460, 255);
        Main.getBatch().draw(jangalpaeen, 460, 0, 480, 270);
        Main.getBatch().draw(jangalrast, 940, 270, 500, 255);
        Main.getBatch().end();
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