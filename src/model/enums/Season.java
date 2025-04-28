package model.enums;

public enum Season {
    SPRING(31*3),
    SUMMER(31*3),
    FALL(30*3),
    WINTER(30*3);

    private int numberOfDays;

    Season(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    
}
