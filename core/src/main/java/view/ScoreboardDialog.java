package view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Timer;
import main.Main;
import model.Lobby;
import model.miniPlayer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScoreboardDialog extends Dialog {

    private final Skin skin;
    private final Stage stage;
    private final SelectBox<String> sortTypeBox;
    private final Table scoreboardTable;
    private Timer.Task updateTask;

    public ScoreboardDialog(Stage stage, Skin skin) {
        super("Scoreboard", skin);
        this.stage = stage;
        this.skin = skin;

        sortTypeBox = new SelectBox<>(skin);
        sortTypeBox.setItems("Money", "Quests Done", "Total Ability Power");

        sortTypeBox.addListener(new com.badlogic.gdx.scenes.scene2d.utils.ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                updateTable();
            }
        });

        scoreboardTable = new Table(skin);
        scoreboardTable.defaults().pad(5);

        getContentTable().add(new Label("Sort by:", skin)).padRight(10);
        getContentTable().add(sortTypeBox).row();
        getContentTable().add(scoreboardTable).colspan(2).row();

        button("Close", false);
    }

    public void open() {
        show(stage);
        setSize(getWidth() * 1.5f, getHeight() * 2.5f);

        startAutoUpdate();
    }

    private void startAutoUpdate() {
        if (updateTask != null) {
            updateTask.cancel();
        }

        updateTask = Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                updateTable();
            }
        }, 0, 0.5f); // update every 0.5 seconds
    }

    @Override
    protected void result(Object object) {
        if (updateTask != null) {
            updateTask.cancel();
        }
    }

    private void updateTable() {
        Lobby lobby = Main.lobby;
        if (lobby == null) return;

        List<miniPlayer> players = new ArrayList<>(lobby.getPlayers());

        String sortType = sortTypeBox.getSelected();
        switch (sortType) {
            case "Money":
                players.sort(Comparator.comparingInt(miniPlayer::getMoney).reversed());
                break;
            case "Quests Done":
                players.sort(Comparator.comparingInt(miniPlayer::getQuestsDone).reversed());
                break;
            case "Total Ability Power":
                players.sort(Comparator.comparingInt(miniPlayer::getTotalAbilityPower).reversed());
                break;
        }

        scoreboardTable.clear();
        scoreboardTable.add(new Label("Player", skin)).padRight(20);
        scoreboardTable.add(new Label(sortType, skin)).row();

        for (miniPlayer p : players) {
            scoreboardTable.add(new Label(p.getUsername(), skin)).padRight(20);
            switch (sortType) {
                case "Money":
                    scoreboardTable.add(new Label(String.valueOf(p.getMoney()), skin));
                    break;
                case "Quests Done":
                    scoreboardTable.add(new Label(String.valueOf(p.getQuestsDone()), skin));
                    break;
                case "Total Ability Power":
                    scoreboardTable.add(new Label(String.valueOf(p.getTotalAbilityPower()), skin));
                    break;
            }
            scoreboardTable.row();
        }
    }
}