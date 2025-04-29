package model.enums;

public enum Material {
    normal(0),
    copper(1),
    iron(2),
    gold(3),
    iridium(4),
    ;

    public final int hardness;

    Material(int hardness) {
        this.hardness = hardness;
    }
    public static Material getMaterial(int hardness){
        for (Material value : Material.values()) {
            if(value.hardness == hardness){
                return value;
            }
        }
        return null;
    }
}
