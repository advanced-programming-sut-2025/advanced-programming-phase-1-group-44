package model;

import model.enums.Crops;

import java.util.Locale;

public class CropsMapObj extends MapObj{
    private Crops MainCrop=Crops.AMARANTH;
    boolean Isbig=false;
    public void setMainCrop(Crops mainCrop) {
        MainCrop = mainCrop;
        this.setName(mainCrop.getName());
        if(Isbig){
            this.setName(this.getName().toUpperCase());
        }else{
            this.setName(this.getName().toLowerCase());
        }
    }

    public void setIsbig(boolean isbig) {
        Isbig = isbig;
        this.setName(MainCrop.getName());
        if(Isbig){
            this.setName(this.getName().toUpperCase());
        }else{
            this.setName(this.getName().toLowerCase());
        }
    }

    public Crops getMainCrop() {
        return MainCrop;
    }
}
