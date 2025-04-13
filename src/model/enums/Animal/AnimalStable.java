package model.enums.Animal;

public enum AnimalStable {
    chicken(800),
    duck(1200),
    rabbit(8000),
    dinasour(14000);

    private final int price;
    AnimalStable(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
