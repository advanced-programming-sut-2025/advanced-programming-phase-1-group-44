package view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    Texture spriteSheet = new Texture(Gdx.files.internal("hameframe.png"));
    private Stage stage;
    private final Texture background;
    private final Skin skin;
    MapController mapController=new MapController();
    MapFarm mf;
    public FarmobjScreen(MapFarm mff) {
        TextureRegion[][] frames = TextureRegion.split(spriteSheet, 32, 32);
        mf=mff;
        stage = new Stage(new StretchViewport(30, 30));
        Gdx.input.setInputProcessor(stage);

        background = new Texture(Gdx.files.internal("farmback.png"));
        skin = GameAssetManager.getGameAssetManager().getSkin();
        calcui();
    }
    void calcui(){
        stage.clear();
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
                if(res.get(i).get(j).getName().equals("Space")){
                    ((Space) res.get(i).get(j)).updcolor(i,j);
                }
                res.get(i).get(j).setPosition((29-i),(29-j));
                //System.out.println(res.get(i).get(j).getWidth()+" "+res.get(i).get(j).getHigh());
                res.get(i).get(j).setSize(res.get(i).get(j).getHigh(),res.get(i).get(j).getwidth());
                stage.addActor(res.get(i).get(j));
            }
        }
    }
    @Override public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        int ni=App.getCurrentGame().getCurrentPlayer().getXlocation();
        int nj=App.getCurrentGame().getCurrentPlayer().getYlocation();
        Main.getBatch().begin();
        if (Gdx.input.isKeyPressed(Input.Keys.M)) {
            Main.setMenu(new gameplayScreen());
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.D)){

            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation()-1,App.getCurrentGame().getCurrentPlayer().getYlocation());
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            if( mf.GetCell(ni,nj).getName().equals("Space")){
                ((Space)  mf.GetCell(ni,nj)).updcolor(ni,nj);
            }
            stage.addActor(mf.GetCell(ni,nj));
            ni=App.getCurrentGame().getCurrentPlayer().getXlocation();
            nj=App.getCurrentGame().getCurrentPlayer().getYlocation();
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            mf.GetCell(ni,nj).toFront();
            //stage.addActor(mf.GetCell(ni,nj));
            //calcui();
            //Main.setMenu(new FarmobjScreen(mf));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.A)){
            System.out.println("wtf: "+App.getCurrentGame().getCurrentPlayer().getXlocation());
            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation()+1,App.getCurrentGame().getCurrentPlayer().getYlocation());
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            if( mf.GetCell(ni,nj).getName().equals("Space")){
                ((Space)  mf.GetCell(ni,nj)).updcolor(ni,nj);
            }
            stage.addActor(mf.GetCell(ni,nj));
            ni=App.getCurrentGame().getCurrentPlayer().getXlocation();
            nj=App.getCurrentGame().getCurrentPlayer().getYlocation();
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            mf.GetCell(ni,nj).toFront();
            //stage.addActor(mf.GetCell(ni,nj));
            //calcui();
            //Main.setMenu(new FarmobjScreen(mf));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
            System.out.println("wtf: "+App.getCurrentGame().getCurrentPlayer().getXlocation());
            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation(),App.getCurrentGame().getCurrentPlayer().getYlocation()-1);
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            if( mf.GetCell(ni,nj).getName().equals("Space")){
                ((Space)  mf.GetCell(ni,nj)).updcolor(ni,nj);
            }
            stage.addActor(mf.GetCell(ni,nj));
            ni=App.getCurrentGame().getCurrentPlayer().getXlocation();
            nj=App.getCurrentGame().getCurrentPlayer().getYlocation();
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            mf.GetCell(ni,nj).toFront();
            //stage.addActor(mf.GetCell(ni,nj));
            //calcui();
            //Main.setMenu(new FarmobjScreen(mf));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.S)){
            System.out.println("wtf: "+App.getCurrentGame().getCurrentPlayer().getXlocation());
            mapController.walk2(App.getCurrentGame().getCurrentPlayer().getXlocation(),App.getCurrentGame().getCurrentPlayer().getYlocation()+1);
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            if( mf.GetCell(ni,nj).getName().equals("Space")){
                ((Space)  mf.GetCell(ni,nj)).updcolor(ni,nj);
            }
            stage.addActor(mf.GetCell(ni,nj));
            ni=App.getCurrentGame().getCurrentPlayer().getXlocation();
            nj=App.getCurrentGame().getCurrentPlayer().getYlocation();
            mf.GetCell(ni,nj).setPosition((29-ni),(29-nj));
            mf.GetCell(ni,nj).toFront();
            //stage.addActor(mf.GetCell(ni,nj));
            //calcui();
            //Main.setMenu(new FarmobjScreen(mf));
        }else if(Gdx.input.isKeyJustPressed(Input.Keys.Z)){
            mapController.shokhm(ni+1,nj);
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
