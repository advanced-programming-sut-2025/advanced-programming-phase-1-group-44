package model.enums.AnimalEnum;

public enum AnimalHomeType {
    coop(4, "coop"),
    bigCoop(8, "big coop"),
    deluxeCoop(12, "deluxe coop"),
    barn(4, "barn"),
    bigBarn(8, "big barn"),
    deluxeBarn(12, "deluxe barn");
    private final String name;

    private final int size;

   
    AnimalHomeType(int size, String name) {
        this.size = size;
        this.name = name;
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
