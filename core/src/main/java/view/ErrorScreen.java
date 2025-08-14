package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.Main;
import model.GameAssetManager;

public class ErrorScreen extends AppMenu {
    private final String errorMessage;

    private Stage stage;
    private Skin skin;

    public ErrorScreen( String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        skin = GameAssetManager.getGameAssetManager().getSkin();

        showErrorDialog();
    }

    private void showErrorDialog() {
        float maxWidth = stage.getViewport().getWorldWidth() * 0.9f;

        Label errorLabel = new Label(errorMessage, skin);
        errorLabel.setWrap(true);
        errorLabel.setAlignment(Align.center);

        Dialog dialog = new Dialog("Error", skin) {
            @Override
            protected void result(Object object) {
                // وقتی OK زده شد، برگرد به صفحه اصلی
                Main.setMenu(new gameplayScreen());
            }
        };

        dialog.getContentTable().add(errorLabel).width(maxWidth).pad(10).row();
        dialog.button("OK");

        dialog.show(stage);

        // مرکز کردن دیالوگ
        dialog.setPosition(
            (stage.getViewport().getWorldWidth() - dialog.getWidth()) / 2,
            (stage.getViewport().getWorldHeight() - dialog.getHeight()) / 2
        );
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
    @Override public void pause() { }
    @Override public void resume() { }
    @Override public void hide() { }
    @Override public void dispose() {
        stage.dispose();
    }
}
