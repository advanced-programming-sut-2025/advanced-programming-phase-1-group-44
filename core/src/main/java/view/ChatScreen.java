package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ChatScreen extends AppMenu {

    private Stage stage;
    private Skin skin;

    private Table chatTable;
    private ScrollPane scrollPane;
    private TextField inputField;
    private TextButton sendButton;
    private TextButton exitButton;

    // Now storing miniPlayer in the SelectBox
    private SelectBox<Object> chatSelector;
    private Object currentChatKey = "Public";

    private List<String> publicMessages = new ArrayList<>();
    private Map<miniPlayer, ArrayList<String>> privateMessage = new HashMap<>();

    private final Texture background;

    public void addPublicMessage(String st) {
        publicMessages.add(st);
    }

    public void addPrivateMessage(miniPlayer player, String message) {

        System.out.println("add private message " + player.username + " " + message);
        privateMessage.computeIfAbsent(player, k -> new ArrayList<>()).add(message);
    }

    public void initializeChats(Map<String, Object> data) {
        publicMessages = (List<String>) data.get("publicMessages");
        privateMessage = (Map<miniPlayer, ArrayList<String>>) data.get("privateMessages");

        System.out.println("!!!!!" + privateMessage);
        System.out.println("??????" + publicMessages);

        ArrayList<Object> chatOptions = new ArrayList<>();
        chatOptions.add("Public");
        System.out.println("key set is " + privateMessage.keySet());
        System.out.println("private mess " + privateMessage);
        chatOptions.addAll(privateMessage.keySet());

        chatSelector.setItems(chatOptions.toArray());
        chatSelector.setSelected("Public");

        updateChatMessages();
    }

    public ChatScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = GameAssetManager.getGameAssetManager().getSkin();

        background = new Texture(Gdx.files.internal("background.png"));

        createUI();
    }

    private AppMenu returnScreen;

    public void setReturnScreen(AppMenu returnScreen) {
        this.returnScreen = returnScreen;
    }

    public void init() {
        Main.getNetworkClient().getChats();

        CountDownLatch latch = new CountDownLatch(1);

        try {
            latch.await(1, TimeUnit.SECONDS); // wait up to 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void createUI() {
        Table root = new Table(skin);
        root.setFillParent(true);
        stage.addActor(root);

        Label title = new Label("Chat", skin);
        title.setFontScale(2f);
        title.setAlignment(Align.center);
        root.add(title).colspan(2).pad(20).center();
        root.row();

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

        chatTable = new Table(skin);
        scrollPane = new ScrollPane(chatTable, skin);
        scrollPane.setFadeScrollBars(false);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setScrollBarPositions(false, true);

        root.add(scrollPane).colspan(2).grow().pad(10);
        root.row();

        inputField = new TextField("", skin);
        sendButton = new TextButton("Send", skin);

        Table inputRow = new Table(skin);
        inputRow.add(inputField).growX().minWidth(300).pad(10);
        inputRow.add(sendButton).width(120).pad(10);
        root.add(inputRow).colspan(2).growX();
        root.row();

        exitButton = new TextButton("Exit Chat", skin);
        root.add(exitButton).colspan(2).center().pad(20);

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

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (returnScreen != null) {
                    Main.setMenu(returnScreen);
                } else {
                    Main.setMenu(new SignupScreen()); // fallback
                }
            }
        });

    }

    private void sendMessage(String message) {
        if (message.isEmpty()) return;

        if (currentChatKey instanceof String && currentChatKey.equals("Public")) {
            publicMessages.add(App.getAdmin().getUsername() + ": " + message);
            Main.getNetworkClient().sendPublicMessage(App.getAdmin().getUsername(), message);

        } else if (currentChatKey instanceof miniPlayer) {
            miniPlayer selectedPlayer = (miniPlayer) currentChatKey;
            privateMessage.computeIfAbsent(selectedPlayer, k -> new ArrayList<>())
                    .add(App.getAdmin().getUsername() + ": " + message);
            Main.getNetworkClient().sendPrivateMessage(App.getAdmin().getUsername(), selectedPlayer.getUsername(), message);
        }

        updateChatMessages();
    }

    public void updateChatMessages() {
        chatTable.clear();
        List<String> messagesToShow;

        if (currentChatKey instanceof String && currentChatKey.equals("Public")) {
            messagesToShow = publicMessages;
        } else if (currentChatKey instanceof miniPlayer) {
            messagesToShow = privateMessage.getOrDefault((miniPlayer) currentChatKey, new ArrayList<>());
        } else {
            messagesToShow = new ArrayList<>();
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

        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getBatch().end();

        updateChatMessages();
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