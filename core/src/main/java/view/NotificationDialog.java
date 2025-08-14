package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class NotificationDialog extends Dialog {

    public NotificationDialog(Stage stage, Skin skin, String title, String message) {
        super(title, skin);

        // Content
        Label messageLabel = new Label(message, skin);
        messageLabel.setWrap(true);
        getContentTable().defaults().pad(16);
        getContentTable().add(messageLabel).width(420).row();

        // OK button that explicitly closes the dialog
        TextButton ok = new TextButton("OK", skin);
        button(ok);
        ok.addListener(new ChangeListener() {
            @Override public void changed(ChangeEvent event, Actor actor) {
                hide();
            }
        });

        // Also allow Enter/Escape keys to close
        key(Input.Keys.ENTER, true);
        key(Input.Keys.ESCAPE, false);

        // Show and center
        show(stage);
        pack();
        setPosition(
                Math.round((stage.getWidth()  - getWidth())  / 2f),
                Math.round((stage.getHeight() - getHeight()) / 2f)
        );
    }

    @Override
    protected void result(Object object) {
        // Close for any key-bound result too
        hide();
    }
}