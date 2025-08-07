package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import model.GameAssetManager;

public class InventoryScreen implements Screen {

    private Stage stage;
    private Skin skin;

    private static final int INVENTORY_COLUMNS = 4;
    private static final int INVENTORY_ROWS = 20;

    private Table contentTable;
    private ScrollPane scrollPane;
    private Table inventoryTable;
    private Label skillsPlaceholder;

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        skin = GameAssetManager.getGameAssetManager().getSkin();

        // --- Inventory Item Grid ---
        inventoryTable = new Table(skin);
        for (int i = 0; i < INVENTORY_ROWS; i++) {
            for (int j = 0; j < INVENTORY_COLUMNS; j++) {
                Texture background = new Texture(Gdx.files.internal("inventory/itemBackground.png"));
                Image itemBackground = new Image(background);
                inventoryTable.add(itemBackground).size(64, 64).pad(6);
            }
            inventoryTable.row();
        }

        scrollPane = new ScrollPane(inventoryTable, skin);
        scrollPane.setScrollingDisabled(true, false);
        scrollPane.setFadeScrollBars(false);

        skillsPlaceholder = new Label("skill menu", skin);

        // --- Tab Buttons ---
        TextButton inventoryBtn = new TextButton("Inventory", skin, "toggle");
        TextButton skillsBtn = new TextButton("Skills", skin, "toggle");
        inventoryBtn.setChecked(true);

        // --- Tab Button Bar ---
        Table tabBar = new Table();
        tabBar.add(inventoryBtn).padRight(10).height(40).width(120);
        tabBar.add(skillsBtn).height(40).width(120);

        // --- Content Area ---
        contentTable = new Table(skin);
        contentTable.setFillParent(false); // it will be filled inside root
        showInventory(); // Show inventory grid by default

        // --- Button Actions ---
        inventoryBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                inventoryBtn.setChecked(true); skillsBtn.setChecked(false);
                showInventory();
            }
        });
        skillsBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                inventoryBtn.setChecked(false); skillsBtn.setChecked(true);
                showSkills();
            }
        });

        // --- Root Table: Stacks tab bar above content area ---
        Table root = new Table();
        root.setFillParent(true);
        root.top();
        root.add(tabBar).expandX().fillX().padTop(10);
        root.row();
        root.add(contentTable).expand().fill();

        stage.addActor(root);
    }

    private void showInventory() {
        contentTable.clear();
        contentTable.add(scrollPane).expand().fill();
    }

    private void showSkills() {
        contentTable.clear();
        contentTable.add(skillsPlaceholder).center().expand();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
