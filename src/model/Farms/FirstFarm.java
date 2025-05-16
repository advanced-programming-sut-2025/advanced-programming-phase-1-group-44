package model.Farms;
import controller.MapController;
import model.*;

import java.util.Random;

public class FirstFarm extends MapFarm {
    MapController mc=new MapController();
    public FirstFarm(){
        mc.buildbuilding(this,new Greenhouse(),35,1);
        mc.buildbuilding(this,new Cottage(),1,1);
        mc.buildbuilding(this,new Quarry(3,5),20,35);
        mc.buildbuilding(this,new Lake(4,6),1,15);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(20)),r.nextInt(35),r.nextInt(35));
        }
        //todo fill some random value
    }
}
