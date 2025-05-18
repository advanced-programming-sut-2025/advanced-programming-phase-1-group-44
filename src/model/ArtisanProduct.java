package model;

import java.sql.Date;

public class ArtisanProduct extends Item {
    
    private final Player player;
    private final Item item;
    private final DateTime finishTime;
    private final Item builder;

    public ArtisanProduct(Player player, Item item, DateTime finishTime, Item builder, double price, String name) {
        super(name, (int)price);

        this.player = player;
        this.item = item;
        this.finishTime = finishTime;
        this.builder = builder;
    }
    public void setReady() {
        player.getBackpack().putItem(this, 1);
        
    }
    public Player getPlayer() {
        return player;
    }
    public Item getItem() {
        return item;
    }
    public DateTime getFinishTime() {
        return finishTime;
    }
}
