package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controller.SignupMenuController;
import main.Main;
import model.GameAssetManager;
import model.Result;

public class LoginScreen extends AppMenu {

    private final SignupMenuController controller;
    private Stage stage;
    private final Texture background;
    private final Skin skin;

    private TextField usernameField, passwordField;
    private TextButton loginButton, goToSignupButton;

    public LoginScreen() {
        controller = new SignupMenuController();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("background.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();

        createUI();
    }

    private void createUI() {
        // Title label (absolute position at top)
        Label title = new Label("Login Menu", skin);
        title.setFontScale(3f);
        title.setAlignment(Align.center);

        // Set position manually: center horizontally, near top vertically
        float titleWidth = 600;
        float titleHeight = 60;
        title.setSize(titleWidth, titleHeight);
        title.setPosition(
            (Gdx.graphics.getWidth() - titleWidth) / 2f,  // Center horizontally
            Gdx.graphics.getHeight() - titleHeight - 70   // 20px from top
        );
        stage.addActor(title);

        // Form table
        Table table = new Table(skin);
        table.setFillParent(true);
        table.top().center().padTop(100); // Pad down so it doesn't overlap title
        stage.addActor(table);

        // --- rest of your form code remains unchanged ---

        // Fields
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        table.defaults().pad(10).width(300).left();
        table.add(createLargeLabel("Username:"));
        table.add(usernameField).row();

        table.add(createLargeLabel("Password:"));
        table.add(passwordField).row();

        // Buttons
        loginButton = new TextButton("Login", skin);
        loginButton.getLabel().setFontScale(1.5f);
        loginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.login(
                    usernameField.getText(),
                    passwordField.getText(),
                    "na"
                );

                boolean isValid = (boolean) result.getData().get("isValid");
                String message = (String) result.getData().get("message");

                if (!isValid) {
                    showErrorDialog(message);
                } else {
                    Main.setMenu(new MainScreen());
                    // Navigate to next screen if needed
                    // Main.getInstance().setScreen(new MainMenuScreen());
                }
            }
        });

        goToSignupButton = new TextButton("Go to Signup", skin);
        goToSignupButton.getLabel().setFontScale(1.2f);
        goToSignupButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.setMenu(new SignupScreen());
            }
        });

        // Button Row
        Table buttonRow = new Table();
        buttonRow.add(loginButton).width(200).padRight(10);
        buttonRow.add(goToSignupButton).width(200);
        table.add(buttonRow).colspan(2).center().padTop(20);
    }

    private Label createLargeLabel(String text) {
        Label label = new Label(text, skin);
        label.setFontScale(2f);
        return label;
    }

    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog("Error", skin) {
            @Override
            protected void result(Object object) {
                this.hide();
            }
        };

        Label messageLabel = new Label(message, skin);
        messageLabel.setFontScale(1.5f);
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.center);

        dialog.getContentTable().clear();
        dialog.getContentTable().pad(20);
        dialog.getContentTable().add(messageLabel).width(440).center().pad(10).row();

        dialog.getButtonTable().padTop(20);
        dialog.button("OK", true);

        dialog.show(stage);
        dialog.setSize(500, 250);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
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
        background.dispose();
    }
}
