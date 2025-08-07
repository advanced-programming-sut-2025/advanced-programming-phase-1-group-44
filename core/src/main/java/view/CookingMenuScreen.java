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
import model.enums.Recipe;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import java.util.*;
import java.util.List;

public class CookingMenuScreen implements Screen {

    private Stage stage;
    private Skin skin;
    private List<Recipe> recipes;

    private static final int RECIPES_PER_ROW = 5;

    public CookingMenuScreen(List<Recipe> recipes) {
        this.skin = GameAssetManager.getGameAssetManager().getSkin();
        this.recipes = recipes;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        // Cooking Title
        Label title = new Label("Cooking", skin, "title");
        title.setFontScale(1.2f);

        // Tooltip label (black background, hidden by default)
        final Label tooltipLabel = new Label("", skin, "default");
        tooltipLabel.setColor(1f, 1f, 1f, 0.92f);
        tooltipLabel.setVisible(false);
        Label.LabelStyle tooltipStyle = new Label.LabelStyle(tooltipLabel.getStyle().font, tooltipLabel.getStyle().fontColor);
        tooltipStyle.background = skin.newDrawable("white", new Color(0f, 0f, 0f, 0.87f));
        tooltipLabel.setStyle(tooltipStyle);

        // --- Recipe Grid Table
        Table recipeTable = new Table();
        for (int i = 0; i < recipes.size(); i++) {
            final Recipe recipe = recipes.get(i);
            final Stack stack = new Stack();

            // background (optional, use any tile image you want)
            Image slotBg = new Image(new Texture(Gdx.files.internal("inventory/itemBackground.png")));
            stack.add(slotBg);

            Image icon = new Image(new Texture(Gdx.files.internal(recipe.getImagePath())));
            //TODO: fix is Locked
            if (recipe.isLocked()) {
                icon.setColor(0.38f, 0.38f, 0.38f, 1f); // darken locked
            } else {
                icon.setColor(Color.WHITE);
            }
            stack.add(icon);

            // Tooltip: list ingredients on hover
            stack.addListener(new InputListener() {
                @Override
                public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                    tooltipLabel.setText(getIngredientsString(recipe));
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

            recipeTable.add(stack).size(64,64).pad(8);
            if ((i + 1) % RECIPES_PER_ROW == 0) recipeTable.row();
        }

        ScrollPane scrollPane = new ScrollPane(recipeTable, skin);
        scrollPane.setFadeScrollBars(false);

        // --- Layout
        Table mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.top().padTop(32);
        mainTable.add(title).colspan(RECIPES_PER_ROW).padBottom(25).row();
        mainTable.add(scrollPane).expand().fill().colspan(RECIPES_PER_ROW).row();

        stage.addActor(mainTable);
        stage.addActor(tooltipLabel);
    }

    // Creates black tooltip text for a recipe's ingredients
    private String getIngredientsString(Recipe recipe) {
        StringBuilder sb = new StringBuilder();
        sb.append(recipe.getName()).append("\n");
        sb.append("Ingredients:\n");
        for (Map.Entry<String, Integer> entry : recipe.getIngredients().entrySet()) {
            sb.append(" - ").append(entry.getKey())
                .append(": ").append(entry.getValue()).append('\n');
        }
        return sb.toString();
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

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
    }

}
