package model.Farms;

import controller.MapController;
import model.*;

import java.util.Random;

public class SecondFarm extends MapFarm {
    MapController mc=new MapController();
    public SecondFarm(){
        //keep main farm
        this.setName("secondfarm");
        mc.buildbuilding(this,new Greenhouse(),21,16);
        mc.buildbuilding(this,new Cottage(),16,11);
        mc.buildbuilding(this,new Quarry(4,4),11,16);
        mc.buildbuilding(this,new Lake(4,4),6,11);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(1,20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
    }
    public SecondFarm(Player pl){
        //keep main farm
        this.setName("secondfarm");
        mc.buildbuilding(this,pl,0,0);
        mc.buildbuilding(this,new Greenhouse(),21,16);
        mc.buildbuilding(this,new Cottage(),16,11);
        mc.buildbuilding(this,new Quarry(4,4),11,16);
        mc.buildbuilding(this,new Lake(4,4),6,11);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(1,20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
    }
}
