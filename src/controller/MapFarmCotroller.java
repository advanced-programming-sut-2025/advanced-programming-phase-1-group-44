package controller;

import model.MapFarm;

public class MapFarmCotroller {
    public boolean Isnearlake(int x, int y, MapFarm MF){
        for(int i=-1;i<=1;i++){
            for(int j=-1;j<=1;j++){
                if(Isongrid(i+x,j+y,MF)&&MF.GetCell(x+i,j+y).getName().equals("Lake")){
                    return true;
                }
            }
        }
        return false;
    }
    public boolean Isongrid(int x,int y,MapFarm MF){
        if(x<=0||y<=0||x>MF.getWidth()||y>MF.getHigh()){
            return false;
        }
        return true;
    }
}
