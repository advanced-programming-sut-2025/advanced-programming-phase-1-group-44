package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import controller.ProfileMenuController;
import main.Main;
import model.GameAssetManager;
import model.Result;

public class ProfileScreen extends AppMenu {

    private final ProfileMenuController controller;
    private Stage stage;
    private final Texture background;
    private final Skin skin;

    private SelectBox<String> fieldSelector;
    private TextField newValueField, confirmField, oldPasswordField;
    private TextButton updateButton, backButton;

    public ProfileScreen() {
        controller = new ProfileMenuController();
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("background.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();

        createUI();
    }

    private void createUI() {
        Table table = new Table(skin);
        table.setFillParent(true);
        table.top().center();
        stage.addActor(table);

        // Title
        Label title = new Label("Profile Menu", skin);
        title.setFontScale(3f);
        title.setAlignment(Align.center);
        table.add(title).colspan(2).center().padTop(30).padBottom(40);
        table.row();

        // Field selector
        fieldSelector = new SelectBox<>(skin);
        fieldSelector.setItems("Username", "Password", "Nickname", "Email");

        newValueField = new TextField("", skin);
        newValueField.setMessageText("New Value");

        confirmField = new TextField("", skin);
        confirmField.setMessageText("Confirm New Value");

        oldPasswordField = new TextField("", skin);
        oldPasswordField.setMessageText("Old Password");
        oldPasswordField.setPasswordMode(true);
        oldPasswordField.setPasswordCharacter('*');
        oldPasswordField.setVisible(false); // only shown when editing password

        // Change visibility of old password input depending on selection
        fieldSelector.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                oldPasswordField.setVisible(fieldSelector.getSelected().equals("Password"));
            }
        });

        updateButton = new TextButton("Change", skin);
        updateButton.getLabel().setFontScale(1.4f);
        updateButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                handleUpdate();
            }
        });

        backButton = new TextButton("Back", skin);
        backButton.getLabel().setFontScale(1.2f);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Main.setMenu(new MainScreen());
            }
        });

        // Layout
        table.defaults().pad(15).width(400).left();

        Label selectFieldLabel = new Label("Select Field to Change:", skin);
        selectFieldLabel.setFontScale(2.5f);
        table.add(selectFieldLabel).left().row();
        table.add(fieldSelector).row();

        Label newValueLabel = new Label("New Value:", skin);
        newValueLabel.setFontScale(2.5f);
        table.add(newValueLabel).left().row();
        table.add(newValueField).row();

        Label confirmLabel = new Label("Confirm Value:", skin);
        confirmLabel.setFontScale(2.5f);
        table.add(confirmLabel).left().row();
        table.add(confirmField).row();
        table.add(oldPasswordField).row(); // will only show when needed
        table.add(updateButton).colspan(2).center().padTop(10).row();
        table.add(backButton).colspan(2).center().padTop(10);
    }

    private void handleUpdate() {
        String field = fieldSelector.getSelected();
        String value = newValueField.getText().trim();
        String confirm = confirmField.getText().trim();

        if (value.isEmpty() || confirm.isEmpty()) {
            showErrorDialog("Please fill in all fields.");
            return;
        }

        if (!value.equals(confirm)) {
            showErrorDialog("Values do not match.");
            return;
        }

        Result result;

        switch (field) {
            case "Username":
                result = controller.changeUsername(value);
                break;
            case "Password":
                String oldPass = oldPasswordField.getText().trim();
                if (oldPass.isEmpty()) {
                    showErrorDialog("Please enter your old password.");
                    return;
                }
                result = controller.changePassword(value, oldPass);
                break;
            case "Nickname":
                result = controller.changeNickname(value);
                break;
            case "Email":
                result = controller.changeEmail(value);
                break;
            default:
                showErrorDialog("Unsupported field.");
                return;
        }

        String message = (String) result.getData().get("message");

        // Assume success unless message contains error keywords
        if (message.contains("invalid") || message.contains("exist") || message.contains("same") || message.contains("incorrect")) {
            showErrorDialog(message);
        } else {
            showInfoDialog(message);
        }
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
    }

    private void showInfoDialog(String message) {
        Dialog dialog = new Dialog("Success", skin) {
            @Override
            protected void result(Object object) {
                this.hide();
            }
        };

        Label messageLabel = new Label(message, skin);
        messageLabel.setFontScale(1.4f);
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
