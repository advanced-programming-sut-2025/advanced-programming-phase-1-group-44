package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.App;
import model.GameAssetManager;
import model.miniPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatScreen extends AppMenu {

    private Stage stage;
    private Skin skin;

    private Table chatTable;       // To hold chat messages
    private ScrollPane scrollPane; // Scrollable chat area
    private TextField inputField;
    private TextButton sendButton;
    private TextButton exitButton;

    private SelectBox<String> chatSelector;  // Dropdown for selecting Public or private chats

    private String currentChatKey = "Public";  // Tracks currently selected chat

    // Local caches of messages
    private List<String> publicMessages = new ArrayList<>();
    private Map<miniPlayer, ArrayList<String>> privateMessage = new HashMap<>();

    public void initializeChats(Map<String, Object> data) {
        publicMessages = (List<String>) data.get("publicMessages");
        privateMessage = (Map<miniPlayer, ArrayList<String>>) data.get("privateMessages");

        // Update chatSelector options
        ArrayList<String> chatOptions = new ArrayList<>();
        chatOptions.add("Public");
        for (miniPlayer p : privateMessage.keySet()) {
            chatOptions.add(p.getUsername());
        }

        chatSelector.setItems(chatOptions.toArray(new String[0]));
        chatSelector.setSelected("Public");

        updateChatMessages();
    }

    public ChatScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();

        createUI();

        Main.getNetworkClient().getChats();
    }

    private void createUI() {
        Table root = new Table(skin);
        root.setFillParent(true);
        stage.addActor(root);

        // Title
        Label title = new Label("Chat", skin);
        title.setFontScale(2f);
        title.setAlignment(Align.center);
        root.add(title).colspan(2).pad(20).center();
        root.row();

        // Chat selector dropdown (public or private chats)
        chatSelector = new SelectBox<>(skin);
        chatSelector.setItems("Public");
        chatSelector.setSelected("Public");
        chatSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                currentChatKey = chatSelector.getSelected();
                updateChatMessages();
            }
        });
        root.add(chatSelector).colspan(2).fillX().pad(10);
        root.row();

        // Chat messages area (scrollable)
        chatTable = new Table(skin);
        scrollPane = new ScrollPane(chatTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false); // vertical scroll only
        scrollPane.setScrollBarPositions(false, true);

        root.add(scrollPane).colspan(2).grow().pad(10);
        root.row();

        // Input and send button row
        inputField = new TextField("", skin);
        sendButton = new TextButton("Send", skin);

        Table inputRow = new Table(skin);
        inputRow.add(inputField).growX().minWidth(300).pad(10);
        inputRow.add(sendButton).width(120).pad(10);
        root.add(inputRow).colspan(2).growX();
        root.row();

        // Exit button
        exitButton = new TextButton("Exit Chat", skin);
        root.add(exitButton).colspan(2).center().pad(20);

        // Send message listener
        sendButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String text = inputField.getText().trim();
                if (!text.isEmpty()) {
                    sendMessage(text);
                    inputField.setText("");
                }
            }
        });

        // Exit button listener
        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.setMenu(new SignupScreen());  // Change this to your target screen
            }
        });
    }

    private void sendMessage(String message) {
        if (message.isEmpty()) return;

        if (currentChatKey.equals("Public")) {
            publicMessages.add(App.getAdmin().getUsername() + ": " + message);
            // TODO: send public chat message to server
            Main.getNetworkClient().sendPublicMessage(App.getAdmin().getUsername(), message);


        } else {
            miniPlayer selectedPlayer = null;
            for (miniPlayer p : privateMessage.keySet()) {
                if (p.getUsername().equals(currentChatKey)) {
                    selectedPlayer = p;
                    break;
                }
            }
            if (selectedPlayer != null) {
                ArrayList<String> msgs = privateMessage.get(selectedPlayer);
                if (msgs == null) {
                    msgs = new ArrayList<>();
                    privateMessage.put(selectedPlayer, msgs);
                }
                msgs.add(App.getAdmin().getUsername() + ": "  + message);
                // TODO: send private chat message to server with selectedPlayer info

                Main.getNetworkClient().sendPrivateMessage(App.getAdmin().getUsername(), selectedPlayer.getUsername(), message);

            }
        }

        updateChatMessages();
    }

    public void updateChatMessages() {
        chatTable.clear();

        List<String> messagesToShow;

        if (currentChatKey.equals("Public")) {
            messagesToShow = publicMessages;
        } else {
            miniPlayer selectedPlayer = null;
            for (miniPlayer p : privateMessage.keySet()) {
                if (p.getUsername().equals(currentChatKey)) {
                    selectedPlayer = p;
                    break;
                }
            }
            if (selectedPlayer != null) {
                messagesToShow = privateMessage.get(selectedPlayer);
            } else {
                messagesToShow = new ArrayList<>();
            }
        }

        for (String msg : messagesToShow) {
            Label msgLabel = new Label(msg, skin);
            msgLabel.setWrap(true);
            msgLabel.setAlignment(Align.left);
            chatTable.add(msgLabel).width(Gdx.graphics.getWidth() * 0.8f).left().row();
        }

        scrollPane.layout();
        scrollPane.setScrollPercentY(1f);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }
}
