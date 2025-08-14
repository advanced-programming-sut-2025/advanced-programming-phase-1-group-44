package view;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import controller.GamePlayController;
import model.App;
import model.Item;
import model.Result;

import java.util.ArrayList;
import java.util.List;

public class FriendsDialog extends Dialog {

    public FriendsDialog(Stage stage, Skin skin) {
        super("Friends", skin);

        // Fetch friendship data
        List<String[]> friends = App.getCurrentGame().getFriendshipsData();

        Table contentTable = new Table(skin);
        contentTable.top().left().pad(10);
        ScrollPane scrollPane = new ScrollPane(contentTable, skin);

        GamePlayController controller = new GamePlayController();

        // Get items from backpack
        ArrayList<Item> items = App.getCurrentGame().getAdmin().getBackpack().getItems();
        Item[] itemArray = items.toArray(new Item[0]);

        for (String[] friend : friends) {
            String name = friend[0];
            String level = friend[1];
            String points = friend[2];

            Label friendLabel = new Label(name + " - Level: " + level + " (" + points + " pts)", skin);

            // SelectBox for choosing gift item
            SelectBox<Item> itemSelect = new SelectBox<>(skin);
            itemSelect.setItems(itemArray);

            // TextField for amount
            TextField amountField = new TextField("1", skin);
            amountField.setMessageText("Amount");
            amountField.setTextFieldFilter(new TextField.TextFieldFilter.DigitsOnlyFilter());

            // Gift button
            TextButton giftButton = new TextButton("Gift", skin);
            giftButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    Item selectedItem = itemSelect.getSelected();
                    String amountStr = amountField.getText().trim();

                    if (selectedItem == null || amountStr.isEmpty()) {
                        return;
                    }

                    Result result = controller.gift(name, selectedItem.getName(), amountStr);

                    // Optionally show in-game notification dialog instead of console log
                    new NotificationDialog(stage, skin, "Gift Result",
                            (String) result.getData().get("message"));
                }
            });

            // Add UI elements for each friend
            contentTable.add(friendLabel).pad(5).left().expandX().fillX();
            contentTable.add(itemSelect).pad(5).width(150);
            contentTable.add(amountField).pad(5).width(60);
            contentTable.add(giftButton).pad(5).right();
            contentTable.row();
        }

        getContentTable().add(scrollPane).width(600).height(350).row();
        button("Close");

        // Show the dialog on the provided stage immediately
        show(stage);
    }
}