package view;

import com.badlogic.gdx.Gdx;
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
        label.setFontScale(2f);
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
                    showSecurityQuestionDialog(); // Show dialog on success
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
                Main.setMenu(new LoginScreen());
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

        Label messageLabel = new Label(message, skin);
        messageLabel.setFontScale(1.5f);
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.center);

        dialog.getContentTable().pad(20).defaults().width(400).pad(10);
        dialog.getContentTable().add(messageLabel).width(400).row();

        dialog.button("OK", true);
        dialog.show(stage);
        dialog.setSize(500, 250);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2,
            (stage.getHeight() - dialog.getHeight()) / 2
        );

        TextButton okButton = (TextButton) dialog.getButtonTable().getCells().first().getActor();
        okButton.getLabel().setFontScale(1.2f);
        okButton.setWidth(200);
        dialog.getButtonTable().invalidateHierarchy();
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

    private void showSecurityQuestionDialog() {
        final Dialog dialog = new Dialog("Set Security Question", skin);

        final SelectBox<String> questionBox = new SelectBox<>(skin);
        questionBox.setItems(
            "What is your favorite movie?",
            "What is your childhood nickname?",
            "What is the name of your first pet?",
            "What city were you born in?"
        );

        final TextField answerField = new TextField("", skin);
        final TextField confirmAnswerField = new TextField("", skin);
        answerField.setMessageText("Answer");
        confirmAnswerField.setMessageText("Confirm Answer");

        Label questionLabel = new Label("Select a security question:", skin);
        questionLabel.setFontScale(1.2f);

        Label answerLabel = new Label("Answer:", skin);
        answerLabel.setFontScale(1.2f);

        Label confirmLabel = new Label("Confirm Answer:", skin);
        confirmLabel.setFontScale(1.2f);

        Table content = dialog.getContentTable();
        content.pad(20).defaults().width(400).pad(10);
        content.add(questionLabel).left().row();
        content.add(questionBox).row();
        content.add(answerLabel).left().row();
        content.add(answerField).row();
        content.add(confirmLabel).left().row();
        content.add(confirmAnswerField).row();

        TextButton submitButton = new TextButton("Submit", skin);
        submitButton.getLabel().setFontScale(1.2f);
        submitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String answer = answerField.getText().trim();
                String confirm = confirmAnswerField.getText().trim();

                if (answer.isEmpty() || confirm.isEmpty()) {
                    showErrorDialog("Please fill in both answer fields.");
                } else if (!answer.equals(confirm)) {
                    showErrorDialog("Answers do not match.");
                } else {
                    dialog.hide();
                    Main.setMenu(new LoginScreen());
                }
            }
        });

        dialog.getButtonTable().padTop(20);
        dialog.button(submitButton);
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.show(stage);
        dialog.setSize(500, 500);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
    }
}
