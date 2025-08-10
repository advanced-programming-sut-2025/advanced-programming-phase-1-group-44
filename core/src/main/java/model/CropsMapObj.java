package model;

import model.enums.Crops;
import model.enums.ForagingSeeds;

import java.util.Locale;

public class CropsMapObj extends MapObj{
    private Crops MainCrop=Crops.AMARANTH;
    private boolean Isbig=false,isprotect=false;
    private boolean cood1=false,cood2=false;
    private int cnt=0;
    private int countrooz=0;

    public void setCountrooz(int countrooz) {
        this.countrooz = countrooz;
    }

    public int getCountrooz() {
        return countrooz;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getCnt() {
        return cnt;
    }

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

    public CropsMapObj(){
        this.setName("Crops");
        this.setHigh(1);
        this.setHigh(1);
    }

    public void setIsprotect(boolean isprotect) {
        this.isprotect = isprotect;
    }

    public boolean isprotect() {
        return isprotect;
    }

    public CropsMapObj(ForagingSeeds FS){
        this.setName("Crops");
        for(Crops cr:Crops.values()){
            if(cr.getSource().equals(FS.getName())){
                setMainCrop(cr);
            }
        }
        this.setHigh(1);
        this.setHigh(1);
    }
    public CropsMapObj(Crops FS){
        this.setName("Crops");
        setMainCrop(FS);
        this.setHigh(1);
        this.setHigh(1);
    }
    public boolean isIsbig() {
        return Isbig;
    }

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
