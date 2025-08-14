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
import model.enums.Menu;

public class LoginScreen extends AppMenu {

    private final SignupMenuController controller;
    private Stage stage;
    private final Texture background;
    private final Skin skin;

    private TextField usernameField, passwordField;
    private TextButton loginButton, goToSignupButton, forgetPasswordButton;

    public LoginScreen() {
        controller = new SignupMenuController();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("background.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();

        createUI();
    }

    private void createUI() {
        Label title = new Label("Login Menu", skin);
        title.setFontScale(2.2f);
        title.setAlignment(Align.center);
        float titleWidth = 600;
        float titleHeight = 60;
        title.setSize(titleWidth, titleHeight);
        title.setPosition(
            (Gdx.graphics.getWidth() - titleWidth) / 2f,
            Gdx.graphics.getHeight() - titleHeight - 70
        );
        stage.addActor(title);

        Table table = new Table(skin);
        table.setFillParent(true);
        table.top().center().padTop(100);
        stage.addActor(table);

        usernameField = new TextField("", skin);
        passwordField = new TextField("", skin);
        passwordField.setPasswordMode(true);
        passwordField.setPasswordCharacter('*');

        table.defaults().pad(10).width(300).left();
        table.add(createLargeLabel("Username:")).right();
        table.add(usernameField).row();

        table.add(createLargeLabel("Password:")).right();
        table.add(passwordField).row();

        loginButton = new TextButton("Login", skin);
        loginButton.getLabel().setFontScale(1.3f);
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
                    Gdx.app.postRunnable(() -> {
                        Main.getMain().setScreen(new LobbyMenuScreen());
                    });
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

        forgetPasswordButton = new TextButton("Forget Password", skin);
        forgetPasswordButton.getLabel().setFontScale(1.1f);
        forgetPasswordButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                showSecurityVerificationDialog();
            }
        });

        Table buttonRow = new Table();
        buttonRow.add(loginButton).width(180).padRight(10);
        buttonRow.add(goToSignupButton).width(180).padRight(10);
        buttonRow.add(forgetPasswordButton).width(180);
        table.add(buttonRow).colspan(2).center().padTop(20);
    }

    private Label createLargeLabel(String text) {
        Label label = new Label(text, skin);
        label.setFontScale(1.4f);
        return label;
    }

    private void showSecurityVerificationDialog() {
        final Dialog dialog = new Dialog("Verify Identity", skin);

        final TextField username = new TextField("", skin);
        final SelectBox<String> questionBox = new SelectBox<>(skin);
        final TextField answer = new TextField("", skin);

        username.setMessageText("Username");
        questionBox.setItems(
            "What is your favorite movie?",
            "What is your childhood nickname?",
            "What is the name of your first pet?",
            "What city were you born in?"
        );
        answer.setMessageText("Answer");

        username.getStyle().font.getData().setScale(1.2f);
        answer.getStyle().font.getData().setScale(1.2f);
        questionBox.getStyle().font.getData().setScale(1.1f);

        Table content = dialog.getContentTable();
        content.pad(20).defaults().width(400).pad(10);
        content.add(new Label("Username", skin)).left().row();
        content.add(username).row();
        content.add(new Label("Security Question", skin)).left().row();
        content.add(questionBox).row();
        content.add(new Label("Answer", skin)).left().row();
        content.add(answer).row();

        TextButton verifyButton = new TextButton("Verify", skin);
        verifyButton.getLabel().setFontScale(1.1f);
        verifyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String uname = username.getText().trim();
                String question = questionBox.getSelected();
                String ans = answer.getText().trim();

                boolean correct = (boolean)controller.forgetPassword(uname, question + "$" + ans).getData().get("isValid");


                if (correct) {
                    dialog.hide();
                    showPasswordResetDialog(uname);
                } else {
                    showErrorDialog("Incorrect answer or username.");
                }
            }
        });

        dialog.getButtonTable().padTop(20);
        dialog.button(verifyButton);
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.show(stage);
        dialog.setSize(500, 500);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
    }

    private void showPasswordResetDialog(String username) {
        final Dialog dialog = new Dialog("Reset Password", skin);

        final TextField newPassword = new TextField("", skin);
        newPassword.setPasswordMode(true);
        newPassword.setPasswordCharacter('*');
        newPassword.setMessageText("New Password");
        newPassword.getStyle().font.getData().setScale(1.2f);

        Table content = dialog.getContentTable();
        content.pad(20);
        content.add(newPassword).width(400).pad(10).row();

        TextButton randomPassButton = new TextButton("Random Password", skin);
        randomPassButton.getLabel().setFontScale(1.1f);
        randomPassButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String pass = controller.generatePass();
                newPassword.setText(pass);
                showGeneratedPassDialog(pass);
            }
        });

        TextButton changePassButton = new TextButton("Change Password", skin);
        changePassButton.getLabel().setFontScale(1.1f);
        changePassButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                String pass = newPassword.getText().trim();

                if (pass.isEmpty()) {
                    showErrorDialog("Please enter a new password.");
                    return;
                }

                controller.changePassword(username, pass);

                dialog.hide();
                showErrorDialog("Password successfully changed.");
            }
        });

        Table buttonTable = new Table();
        buttonTable.add(randomPassButton).width(200).padRight(10);
        buttonTable.add(changePassButton).width(200);

        dialog.getButtonTable().padTop(20);
        dialog.getButtonTable().add(buttonTable);
        dialog.setModal(true);
        dialog.setMovable(false);
        dialog.show(stage);
        dialog.setSize(500, 300);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
    }

    private void showGeneratedPassDialog(String password) {
        final Dialog dialog = new Dialog("Generated Password", skin);

        Label passLabel = new Label(password, skin);
        passLabel.setFontScale(1.3f);
        passLabel.setWrap(true);
        passLabel.setAlignment(Align.center);

        dialog.getContentTable().pad(30);
        dialog.getContentTable().add(passLabel).width(450).center().row();

        TextButton copyButton = new TextButton("Copy", skin);
        copyButton.getLabel().setFontScale(1f);
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
        dialog.setSize(500, 250);
        dialog.setPosition(
            (stage.getWidth() - dialog.getWidth()) / 2f,
            (stage.getHeight() - dialog.getHeight()) / 2f
        );
    }

    private void showErrorDialog(String message) {
        Dialog dialog = new Dialog("Message", skin) {
            @Override
            protected void result(Object object) {
                this.hide();
            }
        };

        Label messageLabel = new Label(message, skin);
        messageLabel.setFontScale(1.2f);
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

    @Override public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.getBatch().begin();
        Main.getBatch().draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Main.getBatch().end();
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
