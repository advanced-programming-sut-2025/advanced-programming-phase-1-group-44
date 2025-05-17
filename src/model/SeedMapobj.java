package model;

import model.enums.ForagingSeeds;

public class SeedMapobj extends MapObj{
    ForagingSeeds Fs=ForagingSeeds.AMARANTH_SEEDS;
    public SeedMapobj(){
        this.Name="seed";
        setHigh(1);
        setWidth(1);
    }
    public SeedMapobj(ForagingSeeds FS){
        this.Name="seed";
        setHigh(1);
        setWidth(1);
        this.Fs=FS;
    }
}
