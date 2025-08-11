package model.enums;

public enum Plants {
    CBLUE_JAZZ("Blue Jazz"),
    CARROT("Carrot"),
    CAULIFLOWER("Cauliflower"),
    GARLIC("Garlic"),
    GREEN_BEAN("Green Bean"),
    KALE("Kale"),
    PARSNIP("Parsnip"),
    POTATO("Potato"),
    RHUBARB("Rhubarb"),
    TULIP("Tulip"),
    UNMILLED_RICE("Unmilled Rice"),
    CORN("Corn"),
    HOPS("Hops"),
    POPPY("Poppy"),
    RADISH("Radish"),
    RED_CABBAGE("Red Cabbage"),
    SUMMER_SPANGLE("Summer Spangle"),
    SUNFLOWER("Sunflower"),
    WHEAT("Wheat"),
    AMARANTH("Amaranth"),
    ARTICHOKE("Artichoke"),
    BEET("Beet"),
    BOK_CHOY("Bok Choy"),
    BROCCOLI("Broccoli"),
    EGGPLANT("Eggplant"),
    FAIRY_ROSE("Fairy Rose"),
    PUMPKIN("Pumpkin"),
    YAM("Yam"),
    POWDERMELON("Powdermelon");

    private final String name;

    Plants(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
