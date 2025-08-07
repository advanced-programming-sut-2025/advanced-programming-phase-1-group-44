package view;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import model.GameAssetManager;
import model.enums.CraftingItems.CraftableItem;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import java.util.*;

public class CraftingMenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private static final int ITEMS_PER_ROW = 5;

    public CraftingMenuScreen() {
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Crafting Title
        Label title = new Label("Crafting", skin, "title");
        title.setFontScale(1.2f);

        // Tooltip label (black background, hidden by default)
        final Label tooltipLabel = new Label("", skin, "default");
        tooltipLabel.setColor(1f, 1f, 1f, 0.92f);
        tooltipLabel.setVisible(false);
        Label.LabelStyle tooltipStyle = new Label.LabelStyle(tooltipLabel.getStyle().font, tooltipLabel.getStyle().fontColor);
        tooltipStyle.background = skin.newDrawable("white", new Color(0f, 0f, 0f, 0.87f));
        tooltipLabel.setStyle(tooltipStyle);

        // --- Crafting Grid Table
        Table itemTable = new Table();
        CraftableItem[] craftableItems = CraftableItem.values();
        for (int i = 0; i < craftableItems.length; i++) {
            final CraftableItem item = craftableItems[i];
            final Stack stack = new Stack();

            // background slot (optional, adjust path to your art)
            Image slotBg = new Image(new Texture(Gdx.files.internal("inventory/itemBackground.png")));
            stack.add(slotBg);

            // icon for this craftable item
            Image icon;
            try {
                icon = new Image(new Texture(Gdx.files.internal(item.imagePath)));
            } catch (Exception e) {
                icon = new Image(new Texture(Gdx.files.internal("inventory/itemBackground.png")));
            }

            // Lock/dim if player hasn't unlocked (replace isUnlocked logic as needed!)
            if (!isUnlocked(item)) {
                icon.setColor(0.38f, 0.38f, 0.38f, 1f); // darken locked
            } else {
                icon.setColor(Color.WHITE);
            }
            stack.add(icon);

            // Tooltip: show info/ingredients on hover
            stack.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltipLabel.setText(getTooltipString(item));
                    tooltipLabel.pack();
                    Vector2 coord = new Vector2(x, y);
                    stack.localToStageCoordinates(coord);
                    tooltipLabel.setPosition(coord.x + 20, coord.y + 20);
                    tooltipLabel.setVisible(true);
                }
                @Override
                public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                    tooltipLabel.setVisible(false);
                }
            });

            itemTable.add(stack).size(64, 64).pad(8);
            if ((i + 1) % ITEMS_PER_ROW == 0) itemTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(itemTable, skin);
        scrollPane.setFadeScrollBars(false);

        // --- Layout
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(32);
        mainTable.add(title).colspan(ITEMS_PER_ROW).padBottom(25).row();
        mainTable.add(scrollPane).expand().fill().colspan(ITEMS_PER_ROW).row();

        stage.addActor(mainTable);
        stage.addActor(tooltipLabel);
    }

    // -- Unlock logic: replace with your real progression --
    private boolean isUnlocked(CraftableItem item) {
        // For demo, everything unlocked
        // Replace with actual game logic using item.getSource(), levels, quest flags, etc
        return true;
    }

    // -- Tooltip string for a CraftableItem --
    private String getTooltipString(CraftableItem item) {
        StringBuilder sb = new StringBuilder();
        sb.append(item.getName()).append("\n");
        if (item.getSource() != null)
            sb.append("Source: ").append(item.getSource()).append("\n");
        if (item.getSellPrice() != null && item.getSellPrice() > 0)
            sb.append("Sell: ").append(item.getSellPrice()).append("g\n");
        sb.append("Ingredients:\n");
        for (Map.Entry<String, Integer> entry : item.getIngredients().entrySet()) {
            sb.append(" - ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.12f, 0.13f, 0.14f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
    }
}
