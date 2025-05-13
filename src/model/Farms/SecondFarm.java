package model.Farms;

import controller.MapController;
import model.*;

import java.util.Random;

public class SecondFarm extends MapFarm {
    MapController mc=new MapController();
    public SecondFarm(){
        //keep main farm
        MapFarm FakeFarm= App.getCurrentGame().getCurrentPlayer().getMapFarm();
        App.getCurrentGame().getCurrentPlayer().setMapFarm(this);
        mc.buildbuilding(new Greenhouse(),1,1);
        mc.buildbuilding(new Cottage(),35,1);
        mc.buildbuilding(new Quarry(3,5),1,35);
        mc.buildbuilding(new Lake(4,6),1,15);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(new Tree(r.nextInt(20)),r.nextInt(35),r.nextInt(35));
        }
        App.getCurrentGame().getCurrentPlayer().setMapFarm(FakeFarm);
        //todo fill some random value
    }
}
