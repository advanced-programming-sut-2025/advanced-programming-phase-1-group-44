package model.enums;

public enum ForagingSeeds {
        JAZZ_SEEDS("Jazz Seeds", "Spring"),
        CARROT_SEEDS("Carrot Seeds", "Spring"),
        CAULIFLOWER_SEEDS("Cauliflower Seeds", "Spring"),
        COFFEE_BEAN("Coffee Bean", "Spring"),
        GARLIC_SEEDS("Garlic Seeds", "Spring"),
        BEAN_STARTER("Bean Starter", "Spring"),
        KALE_SEEDS("Kale Seeds", "Spring"),
        PARSNIP_SEEDS("Parsnip Seeds", "Spring"),
        POTATO_SEEDS("Potato Seeds", "Spring"),
        RHUBARB_SEEDS("Rhubarb Seeds", "Spring"),
        STRAWBERRY_SEEDS("Strawberry Seeds", "Spring"),
        TULIP_BULB("Tulip Bulb", "Spring"),
        RICE_SHOOT("Rice Shoot", "Spring"),
        BLUEBERRY_SEEDS("Blueberry Seeds", "Summer"),
        CORN_SEEDS("Corn Seeds", "Summer"),
        HOPS_STARTER("Hops Starter", "Summer"),
        PEPPER_SEEDS("Pepper Seeds", "Summer"),
        MELON_SEEDS("Melon Seeds", "Summer"),
        POPPY_SEEDS("Poppy Seeds", "Summer"),
        RADISH_SEEDS("Radish Seeds", "Summer"),
        RED_CABBAGE_SEEDS("Red Cabbage Seeds", "Summer"),
        STARFRUIT_SEEDS("Starfruit Seeds", "Summer"),
        SPANGLE_SEEDS("Spangle Seeds", "Summer"),
        SUMMER_SQUASH_SEEDS("Summer Squash Seeds", "Summer"),
        SUNFLOWER_SEEDS("Sunflower Seeds", "Summer"),
        TOMATO_SEEDS("Tomato Seeds", "Summer"),
        WHEAT_SEEDS("Wheat Seeds", "Summer"),
        AMARANTH_SEEDS("Amaranth Seeds", "Fall"),
        ARTICHOKE_SEEDS("Artichoke Seeds", "Fall"),
        BEET_SEEDS("Beet Seeds", "Fall"),
        BOK_CHOY_SEEDS("Bok Choy Seeds", "Fall"),
        BROCCOLI_SEEDS("Broccoli Seeds", "Fall"),
        CRANBERRY_SEEDS("Cranberry Seeds", "Fall"),
        EGGPLANT_SEEDS("Eggplant Seeds", "Fall"),
        FAIRY_SEEDS("Fairy Seeds", "Fall"),
        GRAPE_STARTER("Grape Starter", "Fall"),
        PUMPKIN_SEEDS("Pumpkin Seeds", "Fall"),
        YAM_SEEDS("Yam Seeds", "Fall"),
        RARE_SEED("Rare Seed", "Fall"),
        POWDERMELON_SEEDS("Powdermelon Seeds", "Winter"),
        ANCIENT_SEEDS("Ancient Seeds", "Special"),
        MIXED_SEEDS("Mixed Seeds", "Special");

        private final String name;
        private final String season;

        ForagingSeeds(String name, String season) {
            this.name = name;
            this.season = season;
        }

        public String getName() { return name; }
        public String getSeason() { return season; }
}
