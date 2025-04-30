package model;

public abstract class MapObj {
    //top-left corner
    int Xlocation,Ylocation;
    int width,high;
    String Name="empty";

    public void setHigh(int high) {
        this.high = high;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHigh() {
        return high;
    }

    public int getWidth() {
        return width;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setXlocation(int xlocation) {
        Xlocation = xlocation;
    }

    public void setYlocation(int ylocation) {
        Ylocation = ylocation;
    }

    public String getName() {
        return Name;
    }

    public int getXlocation() {
        return Xlocation;
    }

    public int getYlocation() {
        return Ylocation;
    }
}