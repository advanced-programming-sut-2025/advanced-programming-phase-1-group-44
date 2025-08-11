package model;

public class Tree extends MapObj{
    private int color;
    public Tree(){
        this.setName("Tree");
        color=1;
        this.width=1;
        this.high=1;
    }
    public Tree(int r){
        this.setName("Tree");
        color=r;
        this.width=1;
        this.high=1;
    }
}
