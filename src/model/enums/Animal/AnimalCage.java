package model.enums.Animal;

public enum AnimalCage {
    cow(1500),
    goat(4000),
    sheep(8000),
    sus(16000);

    private final int price;

    AnimalCage(int price) {
        this.price = price;
        
    }

    public int getPrice() {
        return price;
    }
}
