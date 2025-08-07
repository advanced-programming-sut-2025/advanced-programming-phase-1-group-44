package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controller.SignupMenuController;
import main.Main;
import model.GameAssetManager;
import model.Result;
import model.enums.Menu;

public class SignupScreen extends AppMenu {

    private final SignupMenuController controller;
    private Stage stage;
    private final Texture background;
    private final Skin skin;

    private TextField usernameField, passwordField, confirmPasswordField, nicknameField, emailField;
    private SelectBox<String> genderBox;
    private TextButton signupButton, randomPasswordButton, goToLoginButton;

    public SignupScreen() {
        controller = new SignupMenuController();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("background.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();

        createUI();
    }

    private Label createLargeLabel(String text) {
        Label label = new Label(text, skin);
        label.setFontScale(2f);  // Scale label font size
        return label;
    }

    private void createUI() {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.top().center();
        stage.addActor(table);

        // Title
        Label name = new Label("Signup Menu", skin);
        name.setFontScale(3f);
        name.setAlignment(Align.center);
        table.add(name).colspan(2).center().padTop(20).padBottom(30);
        table.row();

        // Fields
        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        confirmPasswordField = new TextField("", skin);
        confirmPasswordField.setPasswordMode(true);
        confirmPasswordField.setPasswordCharacter('*');

        nicknameField = new TextField("", skin);
        emailField = new TextField("", skin);

        genderBox = new SelectBox<>(skin);
        genderBox.setItems("Male", "Female");

        // Buttons
        signupButton = new TextButton("Sign Up", skin);
        signupButton.getLabel().setFontScale(1.5f);
        signupButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Result result = controller.register(
                    usernameField.getText(),
                    passwordField.getText(),
                    confirmPasswordField.getText(),
                    nicknameField.getText(),
                    emailField.getText(),
                    genderBox.getSelected()
                );

                boolean isValid = (boolean) result.getData().get("isValid");
                String message = (String) result.getData().get("message");

                if (!isValid) {
                    showErrorDialog(message);
                } else {
                    Main.setMenu(new LoginScreen());
                    // Navigate to next screen
                    // Main.getInstance().setScreen(new NextScreen());
                }
            }
        });

        randomPasswordButton = new TextButton("Random Password", skin);
        randomPasswordButton.getLabel().setFontScale(1.2f);
        randomPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String pass = controller.generatePass();
                passwordField.setText(pass);
                confirmPasswordField.setText(pass);
                showTemporaryPasswordDialog(pass);
            }
        });

        goToLoginButton = new TextButton("Go to Login", skin);
        goToLoginButton.getLabel().setFontScale(1.2f);
        goToLoginButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                // Replace with your login screen class
                Main.setMenu(new LoginScreen());
//                Menu.LoginMenu.setMenu();
//                Main.getInstance().setScreen(new LoginScreen());
            }
        });

        // Layout
        table.defaults().pad(10).width(300).left();
        table.add(createLargeLabel("Username:"));
        table.add(usernameField).row();

        table.add(createLargeLabel("Password:"));
        table.add(passwordField).row();

        table.add(createLargeLabel("Confirm Password:"));
        table.add(confirmPasswordField).row();

        table.add(createLargeLabel("Nickname:"));
        table.add(nicknameField).row();

        table.add(createLargeLabel("Email:"));
        table.add(emailField).row();

        table.add(createLargeLabel("Gender:"));
        table.add(genderBox).row();

        // Button Row
        Table buttonRow = new Table();
        buttonRow.add(signupButton).width(200).padRight(10);
        buttonRow.add(randomPasswordButton).width(250).padRight(10);
        buttonRow.add(goToLoginButton).width(200);
        table.add(buttonRow).colspan(2).center().padTop(20);
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

    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog("Error", skin) {
            @Override
            protected void result(Object object) {
                this.hide();
            }
        };

        // Create large text label
        Label messageLabel = new Label(message, skin);
        messageLabel.setFontScale(1.5f);
        messageLabel.setWrap(true);

        // Apply padding and layout to content
        dialog.getContentTable().pad(20).defaults().width(400).pad(10);
        dialog.getContentTable().add(messageLabel).width(400).row();

        // Add button normally
        dialog.button("OK", true);

        dialog.show(stage);

        // Resize and center dialog
        dialog.setSize(500, 250);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2,
            (stage.getHeight() - dialog.getHeight()) / 2
        );

        // After showing, find the button and resize it
        TextButton okButton = (TextButton) dialog.getButtonTable().getCells().first().getActor();
        okButton.getLabel().setFontScale(1.2f);
        okButton.setWidth(200); // Set the desired width
        dialog.getButtonTable().invalidateHierarchy(); // Force relayout
    }

    private void showTemporaryPasswordDialog(String password) {
        final Dialog dialog = new Dialog("Generated Password", skin);

        Label passwordLabel = new Label(password, skin);
        passwordLabel.setFontScale(1.5f);
        passwordLabel.setWrap(true);
        passwordLabel.setAlignment(Align.center);

        dialog.getContentTable().pad(20);
        dialog.getContentTable().add(passwordLabel).width(350).center().row();

        TextButton copyButton = new TextButton("Copy", skin);
        copyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.getClipboard().setContents(password);
                dialog.hide();
            }
        });

        dialog.getButtonTable().padTop(20);
        dialog.button(copyButton);
        dialog.setModal(true);
        dialog.setMovable(false);

        dialog.show(stage);
        dialog.setSize(400, 200);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
    }
}

