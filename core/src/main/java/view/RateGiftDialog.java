package view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import controller.GamePlayController;

public class RateGiftDialog extends Dialog {

    public RateGiftDialog(Stage stage, Skin skin,
                          String senderName, String itemName, int amount) {
        super("Rate Gift", skin);

        GamePlayController controller = new GamePlayController();

        // Message about the gift
        Label giftInfo = new Label(
                "You received " + amount + "x " + itemName +
                        " from " + senderName + "!", skin);
        giftInfo.setWrap(true);

        // Rating selector (1 to 5)
        SelectBox<Integer> ratingSelect = new SelectBox<>(skin);
        ratingSelect.setItems(1, 2, 3, 4, 5);
        ratingSelect.setSelected(5); // Default max rating

        // Submit button
        TextButton okButton = new TextButton("OK", skin);
        okButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                int rating = ratingSelect.getSelected();


                // Send the rating to game logic
//                controller.rateGift(senderName, itemName, rating);

                hide(); // Close dialog
            }
        });

        // Layout
        getContentTable().add(giftInfo).width(400).pad(15).row();
        getContentTable().add(new Label("Rate this gift (1-5):", skin)).pad(5);
        getContentTable().add(ratingSelect).pad(5).row();
        getContentTable().add(okButton).pad(10);

        show(stage);
    }
}