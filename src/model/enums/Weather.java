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
}
