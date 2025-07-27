package model.enums;

public enum Weather {
    Sunny(1.5),
    Rain(1.2),
    Storm(0.5),
    Snow(1);
    public final double fishing;

    Weather(double fishing) {
        this.fishing = fishing;
    }
    public static Weather getWeather(String name){
        for (Weather value : Weather.values()) {
            if(value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        return null;
    }
}
