package model.Farms;
import controller.MapController;
import model.*;

import java.util.Random;

public class FirstFarm extends MapFarm {
    MapController mc=new MapController();
    public FirstFarm(){
        this.setName("firstfarm");
        mc.buildbuilding(this,new Greenhouse(),5,5);
        mc.buildbuilding(this,new Cottage(),15,10);
        mc.buildbuilding(this,new Quarry(3,5),10,20);
        mc.buildbuilding(this,new Lake(4,6),20,20);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
        //todo fill some random value
    }
    public FirstFarm(Player pl){
        this.setName("firstfarm");
        mc.buildbuilding(this,pl,0,0);
        mc.buildbuilding(this,new Greenhouse(),5,1);
        mc.buildbuilding(this,new Cottage(),getWidth()/4,1);
        mc.buildbuilding(this,new Quarry(3,5),1,getHigh()/2);
        mc.buildbuilding(this,new Lake(4,6),getWidth()/3,getHigh()/2);
        Random r= new Random();
        for(int i=0;i<20;i++){
            mc.buildbuilding(this,new Tree(r.nextInt(20)),r.nextInt(this.getWidth()),r.nextInt(this.getHigh()));
        }
        //todo fill some random value
    }
}
