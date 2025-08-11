package model;

public class Space extends MapObj{
    boolean shokhmzadeshode=false;
    private boolean coodi=false;
    private boolean cood1=false,cood2=false;

    public void setCood2(boolean cood2) {
        this.cood2 = cood2;
    }

    public void setCood1(boolean cood1) {
        this.cood1 = cood1;
    }

    public boolean isCood1() {
        return cood1;
    }

    public boolean isCood2() {
        return cood2;
    }

    public void setCoodi(boolean coodi) {
        this.coodi = coodi;
    }

    public boolean isCoodi() {
        return coodi;
    }
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
