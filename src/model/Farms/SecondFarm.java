package model.Farms;

import controller.MapController;
import model.*;

import java.util.Random;

public class SecondFarm extends MapFarm {
    MapController mc=new MapController();
    public SecondFarm(){
        //keep main farm
        this.setName("secondfarm");
        mc.buildbuilding(this,new Greenhouse(),1,1);
        mc.buildbuilding(this,new Cottage(),getWidth()/2,1);
        mc.buildbuilding(this,new Quarry(3,5),1,getHigh()/3);
        mc.buildbuilding(this,new Lake(4,6),getWidth()/2,getHigh()/2);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(1,20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
        //todo fill some random value
    }
    public SecondFarm(Player pl){
        //keep main farm
        this.setName("secondfarm");
        this.setMapCell(0,0,pl);
        mc.buildbuilding(this,new Greenhouse(),1,1);
        mc.buildbuilding(this,new Cottage(),getWidth()/2,1);
        mc.buildbuilding(this,new Quarry(3,5),1,getHigh()/3);
        mc.buildbuilding(this,new Lake(4,6),getWidth()/2,getHigh()/2);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(1,20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
        //todo fill some random value
    }
}
