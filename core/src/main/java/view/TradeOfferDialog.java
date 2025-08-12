package view;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import main.Main;
import model.GameAssetManager;

import java.util.HashMap;
import java.util.Map;

public class TradeOfferDialog extends Dialog {

    private Label messageLabel;
    private Map<String, Object> data = new HashMap<>();

    public TradeOfferDialog(Map <String , Object> data) {
        super("", GameAssetManager.getGameAssetManager().getSkin());
        this.data = data;

        Skin skin = GameAssetManager.getGameAssetManager().getSkin();

        padTop(40);
        setModal(true);
        setMovable(false);

        // Default message
        messageLabel = new Label("PlayerX wants to trade with you", skin);
        messageLabel.setWrap(true);
        messageLabel.setAlignment(Align.center);

        getContentTable().add(messageLabel)
            .width(300)
            .pad(15)
            .row();

        // Accept button
        TextButton acceptButton = new TextButton("Accept", skin);
        acceptButton.addListener(e -> {
            if (!acceptButton.isPressed()) return false;
            data.put("command", "acceptTrade");
            TradeCenterScreen tradeCenterScreen = new TradeCenterScreen(data);
            Main.setMenu(tradeCenterScreen);
            return true;
        });

        // Reject button
        TextButton rejectButton = new TextButton("Reject", skin);
        rejectButton.addListener(e -> {
            if (!rejectButton.isPressed()) return false;
            hide();
            return true;
        });

        getButtonTable().add(acceptButton).pad(10);
        getButtonTable().add(rejectButton).pad(10);
    }

    /** Allows setting the message dynamically */
    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}
