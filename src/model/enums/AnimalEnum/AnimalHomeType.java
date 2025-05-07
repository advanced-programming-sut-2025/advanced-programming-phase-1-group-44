package model.enums.AnimalEnum;

public enum AnimalHomeType {
    coop(4),
    bigCoop(8),
    deluxeCoop(12),
    barn(4),
    bigBarn(8),
    deluxeBarn(12);

    private final int size;
    AnimalHomeType(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
