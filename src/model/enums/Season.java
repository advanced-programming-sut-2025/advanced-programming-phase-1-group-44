package model.enums;

public enum Season {
    SPRING(28, "Spring", 0),
    SUMMER(28, "Summer", 1),
    FALL(28, "Fall", 2),
    WINTER(28, "Winter", 3);

    private final int numberOfDays, id;
    private final String name;

    Season(int numberOfDays, String name, int id) {
        this.id = id;
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
