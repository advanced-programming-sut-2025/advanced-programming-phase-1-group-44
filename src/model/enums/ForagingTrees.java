package model.enums;

public enum ForagingTrees {
        ACORNS("Acorns", "Special"),
        MAPLE_SEEDS("Maple Seeds", "Special"),
        PINE_CONES("Pine Cones", "Special"),
        MAHOGANY_SEEDS("Mahogany Seeds", "Special"),
        MUSHROOM_TREE_SEEDS("Mushroom Tree Seeds", "Special");

        private final String name;
        private final String season;

        ForagingTrees(String name, String season) {
            this.name = name;
            this.season = season;
        }

        public String getName() { return name; }
        public String getSeason() { return season; }
}
