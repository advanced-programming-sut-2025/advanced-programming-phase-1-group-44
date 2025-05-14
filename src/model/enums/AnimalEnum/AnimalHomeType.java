package model.enums.AnimalEnum;

public enum AnimalHomeType {
    coop(4, "coop", 6, 3),
    bigCoop(8, "big coop", ),
    deluxeCoop(12, "deluxe coop"),
    barn(4, "barn"),
    bigBarn(8, "big barn"),
    deluxeBarn(12, "deluxe barn");

    private final int size;
    private final String name;
    AnimalHomeType(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public int getSize() {
        return size;
    }
}
