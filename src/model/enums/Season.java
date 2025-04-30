package model.enums;

public enum Season {
    SPRING(28, "Spring"),
    SUMMER(28, "Summer"),
    FALL(28, "Fall"),
    WINTER(28, "Winter");

    private final int numberOfDays;
    private final String name;

    Season(int numberOfDays, String name) {
        this.numberOfDays = numberOfDays;
        this.name = name;
    }

    public int getNumberOfDays() {
        return numberOfDays;
    }

    
    @Override
    public String toString() {
        return name;
    }
}
