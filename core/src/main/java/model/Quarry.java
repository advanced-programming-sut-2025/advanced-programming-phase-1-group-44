package model;

public class Quarry extends MapObj {
    public Quarry(){
        this.Name="Quarry";
        this.width=4;
        this.high=4;
        setpic("quarry.png");
    }
    public Quarry(int width, int high){
        this.Name="Quarry";
        this.width=width;
        this.high=high;
        setpic("quarry.png");
    }
}
