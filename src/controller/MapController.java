package controller;

import model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Node implements Comparator<Node> {
    public int i,j,cost,akh;
    public Node(int i,int j, int cost,int akh)
    {
        this.i=i;
        this.j=j;
        this.akh=akh;
        this.cost = cost;
    }
    @Override public int compare(Node node1, Node node2)
    {

        if (node1.cost < node2.cost)
            return -1;

        if (node1.cost > node2.cost)
            return 1;

        return 0;
    }
}

public class MapController {
    //خونه بالا چپ یک شی و همینطور شی رو بده اگه فالس برگردوند یعنی همه اون خونه ها خالی نیستن در غیر اینصورت یعنی اینسرت شده است
    public boolean buildbuilding(MapObj mo,int x,int y){
        MapFarm mf= App.getCurrentGame().getCurrentPlayer().getMapFarm();
        for(int i=x;i<x+mo.getHigh();i++){
            for(int j=y;j<y+mo.getWidth();j++){
                if(i>mf.getHigh()||j>mf.getWidth()){
                    return false;
                }
                if(mf.GetCell(i,j).getName().equals("empty")){
                    return false;
                }
            }
        }
        for(int i=x;i<x+mo.getHigh();i++){
            for(int j=y;j<y+mo.getWidth();j++) {
                mf.setMapCell(i,j,mo);
            }
        }
        if(mo.getName().equals("Greenhouse")){
            mf.AddGreenhouse((Greenhouse) mo);
        }else if(mo.getName().equals("Lake")){
            mf.AddLakes((Lake) mo);
        }else if(mo.getName().equals("Cottage")){
            mf.AddCottages((Cottage) mo);
        }else if(mo.getName().equals("Quarry")){
            mf.AddQuarrys((Quarry) mo);
        }
        //todo age niaz shode va mikhast tree
        return true;
    }
    //نام اون شی رو بده و همینطور یک مختصات نزدیک ترینش رو میگم
    public int DistanceByName(int i,int j,String s){
        ArrayList<ArrayList<Integer>>vis=new ArrayList<ArrayList<Integer>>();
        PriorityQueue<Node> pq=new PriorityQueue<Node>();
        pq.add(new Node(i,j,0,-1));
        MapFarm mf=App.getCurrentGame().getCurrentPlayer().getMapFarm();
        while(!pq.isEmpty()){
            Node x=pq.peek();
            pq.remove();
            if(mf.GetCell(x.i,x.j).getName().equals(s)){
               return x.cost;
            }
            if(!mf.GetCell(x.i,x.j).getName().equals("empty")){
                continue;
            }
            if(x.akh==2||x.akh==4){
                pq.add(new Node(x.i+1,x.j,x.cost+1+10,3));
                pq.add(new Node(x.i-1,x.j,x.cost+1+10,1));
                pq.add(new Node(x.i,x.j+1,x.cost+1,2));
                pq.add(new Node(x.i,x.j-1,x.cost+1,4));
            }else{
                pq.add(new Node(x.i+1,x.j,x.cost+1,3));
                pq.add(new Node(x.i-1,x.j,x.cost+1,1));
                pq.add(new Node(x.i,x.j+1,x.cost+1+10,2));
                pq.add(new Node(x.i,x.j-1,x.cost+1+10,4));
            }
        }
        return App.inf;
    }
    //یک شی از اون بساز و مختصات نزدیک ترینش رو میگم
    public int DistanceByMapObj(int i,int j,MapObj mo){
        return DistanceByName(i,j,mo.getName());
    }
    //گلخانه را بازسازی میکند
    public boolean BuildGreenhouse(Greenhouse g){
        try {
            //todo check bokonam bebinam mishe
            g.isavailable=1;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //یک خونه بدیم که گلخونه باشه و نیاز بشه اویلبل بشه
    public boolean BuildGreenhouseByCell(int i,int j){
        try {
            MapFarm mf=App.getCurrentGame().getCurrentPlayer().getMapFarm();
            MapObj mo=mf.GetCell(i,j);
            if(!mo.getName().equals("Greenhouse")){
                return false;
            }
            BuildGreenhouse((Greenhouse) mo);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
