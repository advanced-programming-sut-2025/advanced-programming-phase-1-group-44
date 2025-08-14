package view;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import main.Main;
import model.App;
import model.miniPlayer;

import java.util.ArrayList;
import java.util.List;

public class VoteDialog {

    private final Stage stage;
    private final Skin skin;

    public VoteDialog(Stage stage, Skin skin) {
        this.stage = stage;
        this.skin = skin;
    }

    /**
     * Shown when the initiator presses "v".
     */
    public void showVoteSetupDialog() {
        Dialog setupDialog = new Dialog("Start Vote", skin);

        // Vote type selector
        SelectBox<String> voteTypeSelector = new SelectBox<>(skin);
        voteTypeSelector.setItems("Terminate Game", "Kick Player");

        // Player selector for "Kick Player"
        SelectBox<String> playerSelector = new SelectBox<>(skin);
        playerSelector.setDisabled(true);

        List<String> playerNames = new ArrayList<>();
        for (miniPlayer p : Main.lobby.getPlayers()) {
            if (!p.getUsername().equals(App.getAdmin().getUsername())) {
                playerNames.add(p.getUsername());
            }
        }
        playerSelector.setItems(playerNames.toArray(new String[0]));

        // Enable/disable player selector depending on vote type
        voteTypeSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean kick = voteTypeSelector.getSelected().equals("Kick Player");
                playerSelector.setDisabled(!kick);
            }
        });

        // Buttons
        TextButton startButton = new TextButton("Start Vote", skin);
        TextButton cancelButton = new TextButton("Cancel", skin);

        startButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                String selectedType = voteTypeSelector.getSelected();
                String targetPlayer = playerSelector.isDisabled() ? null : playerSelector.getSelected();

                if (selectedType.equals("Terminate Game"))
                    Main.getNetworkClient().startTerminateVote();
                else
                    Main.getNetworkClient().startKickVote(targetPlayer);

                // TODO: Send start vote request to server
                // Example:
                // Main.getNetworkClient().startVote(selectedType, targetPlayer);

                setupDialog.hide();
            }
        });

        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                setupDialog.hide();
            }
        });

        // Layout
        setupDialog.getContentTable().add(new Label("Vote Type:", skin)).pad(5);
        setupDialog.getContentTable().add(voteTypeSelector).pad(5).row();
        setupDialog.getContentTable().add(new Label("Player:", skin)).pad(5);
        setupDialog.getContentTable().add(playerSelector).pad(5).row();

        setupDialog.getButtonTable().add(startButton).pad(10);
        setupDialog.getButtonTable().add(cancelButton).pad(10);

        setupDialog.show(stage);
    }

    /**
     * Shown for everyone when the server broadcasts a "startVote" event.
     */
    public void showVoteParticipationDialog(String voteDescription) {
        Dialog voteDialog = new Dialog("Vote", skin);

        voteDialog.getContentTable().add(new Label(voteDescription, skin)).pad(10).row();

        SelectBox<String> voteChoice = new SelectBox<>(skin);
        voteChoice.setItems("Yes", "No");
        voteDialog.getContentTable().add(voteChoice).pad(10).row();

        TextButton submitButton = new TextButton("Submit", skin);
        submitButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean voteYes = voteChoice.getSelected().equals("Yes");

                Main.getNetworkClient().sendVote(voteYes);
                // TODO: Send vote to server
                // Example:
                // Main.getNetworkClient().sendVote(voteYes);

                voteDialog.hide();
            }
        });

        voteDialog.getButtonTable().add(submitButton).pad(10);

        voteDialog.show(stage);
    }

    /**
     * Called when server sends final vote result.
     */
    public void showVoteResultDialog(String resultMessage) {
        Dialog resultDialog = new Dialog("Vote Result", skin);
        resultDialog.getContentTable().add(new Label(resultMessage, skin)).pad(10).row();

        TextButton okButton = new TextButton("OK", skin);
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                resultDialog.hide();
            }
        });
        resultDialog.getButtonTable().add(okButton).pad(10);

        resultDialog.show(stage);
    }
}