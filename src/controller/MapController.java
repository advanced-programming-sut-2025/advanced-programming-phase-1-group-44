package controller;

import model.*;
import model.Animals.AnimalHome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

import static java.lang.Math.max;
import static java.lang.Math.min;

class Node implements Comparator<Node> {
    public int i,j,cost,akh;
    public Node() {}
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
        if(node1.i<node2.i){
            return 1;
        }
        if(node2.i<node1.i){
            return -1;
        }
        if(node1.akh<node2.akh){
            return 1;
        }
        if(node2.akh<node1.akh){
            return -1;
        }
        return Integer.compare(node2.j, node1.j);
    }
}

public class MapController {
    public int walk1(int i,int j){
        try {
            int ni=App.getCurrentGame().getCurrentPlayer().getXlocation();
            int nj=App.getCurrentGame().getCurrentPlayer().getYlocation();
            System.out.println("ha:");
            System.out.println(App.getCurrentGame().getCurrentPlayer().getMapFarm().GetCell(i,j).getName());
            int dis=DistanceByMapObj(ni,nj,App.getCurrentGame().getCurrentPlayer().getMapFarm().GetCell(i,j));
            return dis;
        } catch (Exception e) {
            return App.inf;
        }
    }
    public boolean walk2(int i,int j){
        try {
            int ni=App.getCurrentGame().getCurrentPlayer().getXlocation();
            int nj=App.getCurrentGame().getCurrentPlayer().getYlocation();
            int dis=DistanceByMapObj(ni,nj,App.getCurrentGame().getCurrentPlayer().getMapFarm().GetCell(i,j));
            App.getCurrentGame().getCurrentPlayer().getCurrentfarm().setMapCell(i,j,App.getCurrentGame().getCurrentPlayer());
            App.getCurrentGame().getCurrentPlayer().getCurrentfarm().setMapCell(ni,nj,new Space());
            App.getCurrentGame().getCurrentPlayer().setXlocation(i);
            App.getCurrentGame().getCurrentPlayer().setYlocation(j);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean buildbuilding(MapFarm mf,MapObj mo,int x,int y){
        for(int i=x;i<x+mo.getHigh();i++){
            for(int j=y;j<y+mo.getWidth();j++){
                if(i>mf.getHigh()||j>mf.getWidth()||i<0||j<0){
                    return false;
                }
                if(!mf.GetCell(i,j).getName().equals("Space")){
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
        }else if(mo.getName().equals("Tree")) {
            mf.AddTrees((Tree) mo);
        }
        //todo age niaz shode va mikhast tree
        mo.setXlocation(x);
        mo.setYlocation(y);
        return true;
    }
    //خونه بالا چپ یک شی و همینطور شی رو بده اگه فالس برگردوند یعنی همه اون خونه ها خالی نیستن در غیر اینصورت یعنی اینسرت شده است
    public boolean buildbuilding(MapObj mo,int x,int y){
        MapFarm mf=App.getCurrentGame().getCurrentPlayer().getMapFarm();
        for(int i=x;i<x+mo.getHigh();i++){
            for(int j=y;j<y+mo.getWidth();j++){
                if(i>mf.getHigh()||j>mf.getWidth()||i<0||j<0){
                    return false;
                }
                if(!mf.GetCell(i,j).getName().equals("Space")){
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
        }else if(mo.getName().equals("Tree")) {
            mf.AddTrees((Tree) mo);
        }
        else if (mo.getName().equals("AnimalHome")) {
            mf.AddAnimalHome((AnimalHome) mo);
        }
        //todo age niaz shode va mikhast tree
        mo.setXlocation(x);
        mo.setYlocation(y);
        return true;
    }
    
    //نام اون شی رو بده و همینطور یک مختصات نزدیک ترینش رو میگم
    public int DistanceByName(int i,int j,String s){
        try {
            ArrayList<ArrayList<Integer>>vis=new ArrayList<>();
            for(int ni=0;ni<150;ni++){
                vis.add(new ArrayList<Integer>());
                for(int nj=0;nj<150;nj++){
                    vis.get(i).add(0);
                }
            }
            PriorityQueue<Node> pq=new PriorityQueue<Node>(150,new Node());
            pq.add(new Node(i,j,0,-1));
            MapFarm mf=App.getCurrentGame().getCurrentPlayer().getMapFarm();
            while(!pq.isEmpty()){
                Node x=pq.remove();
                System.out.println("ha:s "+" "+x.i+" "+x.j+" ");
                System.out.println(mf.GetCell(x.i,x.j).getName());
                if(x.i<0||x.j<0||x.i>=mf.getWidth()||x.j>=mf.getHigh()){
                    continue;
                }
                if(mf.GetCell(x.i,x.j).getName().equals(s)){
                    return x.cost;
                }
                if(vis.get(x.i).get(x.j)==1){
                    continue;
                }
                if(!mf.GetCell(x.i,x.j).getName().equals("empty")&&!mf.GetCell(x.i,x.j).getName().equals("Space")&&!(x.i==i&&x.j==j)){
                    continue;
                }
                vis.get(x.i).set(x.j,1);
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
        } catch (Exception e) {
            System.out.println("pasinjast");
            return App.inf;
        }
    }
    //یک شی از اون بساز و مختصات نزدیک ترینش رو میگم
    public int DistanceByMapObj(int i,int j,MapObj mo){
        try {
            ArrayList<ArrayList<Integer>>vis=new ArrayList<>();
            for(int ni=0;ni<150;ni++){
                vis.add(new ArrayList<Integer>());
                for(int nj=0;nj<150;nj++){
                    vis.get(ni).add(0);
                }
            }
            PriorityQueue<Node> pq=new PriorityQueue<Node>(150,new Node());
            pq.add(new Node(i,j,0,-1));
            MapFarm mf=App.getCurrentGame().getCurrentPlayer().getMapFarm();
            while(!pq.isEmpty()){
 //               System.out.println("wtttttf");
                Node x=pq.remove();
  //              System.out.println("ha:s "+" "+x.i+" "+x.j+" ");
//                System.out.println(mf.GetCell(x.i,x.j).getName());
                if(x.i<0||x.j<0||x.i>=mf.getWidth()||x.j>=mf.getHigh()){
                    continue;
                }
  //              System.out.println("ha: "+" "+x.i+" "+x.j+" ");
                if(mf.GetCell(x.i,x.j)==mo){
                    return x.cost;
                }
  //              System.out.println("ha: "+" "+x.i+" "+x.j+" ");
                if(vis.get(x.i).get(x.j)==1){
                    continue;
                }
    //            System.out.println("ha: "+" "+x.i+" "+x.j+" ");
                if(!mf.GetCell(x.i,x.j).getName().equals("empty")&&!mf.GetCell(x.i,x.j).getName().equals("Space")&&!(x.i==i&&x.j==j)){
                    continue;
                }
    //            System.out.println("ha: "+" "+x.i+" "+x.j+" ");
                vis.get(x.i).set(x.j,1);
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
        } catch (Exception e) {
            System.out.println("pasinjast");
            return App.inf;
        }
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
    public boolean Isadj(int i,int j,MapObj mo){
        MapFarm f=App.getCurrentGame().getCurrentPlayer().getCurrentfarm();
        for(int ii=max(i-1,0);ii<=min(i+1,f.getHigh());ii++){
            for(int jj=max(j-1,0);jj<=min(j+1,f.getWidth());jj++){
                if(i==ii&&j==jj){
                    continue;
                }
                if(f.GetCell(ii,jj).equals(mo)){
                    return  true;
                }
            }
        }
        return false;
    }
    public boolean Isadj(MapFarm f, int i,int j,MapObj mo){
        for(int ii=max(i-1,0);ii<=min(i+1,f.getHigh());ii++){
            for(int jj=max(j-1,0);jj<=min(j+1,f.getWidth());jj++){
                if(i==ii&&j==jj){
                    continue;
                }
                if(f.GetCell(ii,jj).equals(mo)){
                    return  true;
                }
            }
        }
        return false;
    }
    public boolean removeObj(MapObj mo){
        try {
            MapFarm mf=App.getCurrentGame().getCurrentPlayer().getMapFarm();
            for(int i=0;i<mf.getWidth()+1;i++){
                for(int j=0;j<mf.getHigh()+1;j++){
                    if(mf.GetCell(i,j)==mo){
                        mf.setMapCell(i,j,new Space());
                    }
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
