package model.enums.AnimalEnum;

public enum AnimalHomeType {
    coop(4, "coop", 6, 3, 4000, 300, 100),
    bigCoop(8, "big coop", 6, 3, 10000, 400, 150),
    deluxeCoop(12, "deluxe coop", 6, 3, 20000, 500, 200),
    barn(4, "barn", 7, 4, 6000, 350, 150),
    bigBarn(8, "big barn", 7, 4, 12000, 450, 200),
    deluxeBarn(12, "deluxe barn", 7, 4, 25000, 550, 300);
    private final String name;

    private final int size;
    private final int price, wood, stone, width, high;

   
    AnimalHomeType(int size, String name, int width, int high, int price, int wood, int stone) {
        this.size = size;
        this.name = name;
        this.width = width;
        this.high = high;
        this.price = price;
        this.wood = wood;
        this.stone = stone;
    }

    public int getHigh() {
        return high;
    }
    public int getPrice() {
        return price;
    }
    public int getStone() {
        return stone;
    }
    public int getWidth() {
        return width;
    }
    public int getWood() {
        return wood;
    }

    public int getSize() {
        return size;
    }
    public String getName() {
        return name;
    }

    static public AnimalHomeType getHomeTypeByName(String name) {
        for (AnimalHomeType homeType : AnimalHomeType.values()) {
            if (homeType.getName().equals(name)) return homeType;
        }
        return null;
    }

}
