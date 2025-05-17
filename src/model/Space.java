package model;

public class Space extends MapObj{
    boolean shokhmzadeshode=false;
    public Space (){
        this.Name="Space";
        this.width=1;
        this.high=1;
    }
    public Space(int width,int high){
        this.Name="Space";
        this.width=width;
        this.high=high;
    }

    public boolean isShokhmzadeshode() {
        return shokhmzadeshode;
    }

    public void setShokhmzadeshode(boolean shokhmzadeshode) {
        this.shokhmzadeshode = shokhmzadeshode;
    }
}
