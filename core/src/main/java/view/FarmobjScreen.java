package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import controller.MapController;
import controller.SignupMenuController;
import main.Main;
import model.*;

import java.util.ArrayList;

public class FarmobjScreen extends AppMenu{
    private Stage stage;
    private final Texture background;
    private final Skin skin;
    MapController mapController=new MapController();
    MapFarm mf;
    public FarmobjScreen(MapFarm mff) {
        mf=mff;
        stage = new Stage(new StretchViewport(30, 30));
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("background.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();
        calcui();
    }
    void calcui(){
        Player pl=App.getCurrentGame().getCurrentPlayer();
        ArrayList<ArrayList<MapObj>> res=new ArrayList<ArrayList<MapObj>>();
        for(int i=0;i<mf.getWidth();i++){
            res.add(new ArrayList<MapObj>());
            for (int j = 0; j < mf.getHigh(); j++) {
                res.get(i).add(mf.GetCell(i,j));
            }
        }
        for (int i = 0; i < mf.getWidth(); i++) {
            for (int j = 0; j < mf.getHigh(); j++) {
                res.get(i).get(j).setPosition((29-i),(29-j));
                //System.out.println(res.get(i).get(j).getWidth()+" "+res.get(i).get(j).getHigh());
                res.get(i).get(j).setSize(res.get(i).get(j).getHigh(),res.get(i).get(j).getwidth());
                stage.addActor(res.get(i).get(j));
            }
            System.out.print("\n");
        }
    }
    @Override public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Main.getBatch().begin();
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            Main.setMenu(new gameplayScreen());
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.D)){
            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation()-1,App.getCurrentGame().getCurrentPlayer().getYlocation());
            Main.setMenu(new FarmobjScreen(mf));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            System.out.println("wtf: "+App.getCurrentGame().getCurrentPlayer().getXlocation());
            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation()+1,App.getCurrentGame().getCurrentPlayer().getYlocation());
            Main.setMenu(new FarmobjScreen(mf));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            System.out.println("wtf: "+App.getCurrentGame().getCurrentPlayer().getXlocation());
            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation(),App.getCurrentGame().getCurrentPlayer().getYlocation()-1);
            Main.setMenu(new FarmobjScreen(mf));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            System.out.println("wtf: "+App.getCurrentGame().getCurrentPlayer().getXlocation());
            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation(),App.getCurrentGame().getCurrentPlayer().getYlocation()+1);
            Main.setMenu(new FarmobjScreen(mf));
        }
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
