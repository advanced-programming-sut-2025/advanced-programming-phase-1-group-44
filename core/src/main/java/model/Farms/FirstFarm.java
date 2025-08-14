package model.Farms;
import controller.MapController;
import model.*;

import java.util.Random;

public class FirstFarm extends MapFarm {
    MapController mc=new MapController();
    public FirstFarm(){
        this.setName("firstfarm");
        mc.buildbuilding(this,new Greenhouse(),16,21);
        mc.buildbuilding(this,new Cottage(),11,16);
        mc.buildbuilding(this,new Quarry(4,4),16,11);
        mc.buildbuilding(this,new Lake(4,4),11,6);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
        //todo fill some random value
    }
    public FirstFarm(Player pl){
        this.setName("firstfarm");
        mc.buildbuilding(this,pl,0,0);
        mc.buildbuilding(this,new Greenhouse(),16,21);
        mc.buildbuilding(this,new Cottage(),11,16);
        mc.buildbuilding(this,new Quarry(4,4),16,11);
        mc.buildbuilding(this,new Lake(4,4),11,6);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
        //todo fill some random value
    }
}
