package model.Tools;

import model.*;
import model.enums.*;
import model.enums.AnimalEnum.Fish;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backpack extends Tool {
    private BackpackType backpackType;
    private Map<Item, Integer> items = new HashMap<>();
    private Integer cnt = 0;
    public Backpack() {
        super(Tooltype.backpack);
        backpackType = BackpackType.initial;
    }

    @Override
    public Result upgrade() {
        this.level++;
        this.backpackType = BackpackType.values()[this.level];
        Map<String, Object> data = new HashMap<>();
        return new Result(data);
    }

    public void putItem(Item item , int cnt){
        if(contain(item) > 0) {
            int val = contain(item);
            Item existItem = getItem(item.name);
            items.put(existItem, val + cnt);
        }
        else {
            items.put(item, cnt);
            this.cnt++;
        }
    }
    public void removeItemByName(String name, int amount) {
        for (Item item : items.keySet()) {
            if (item.name.equalsIgnoreCase(name)) {
                items.put(item, items.get(item) - amount);
            }
        }
    }
    public int contain(Item item){
        return contain(item.name);
    }
    public int contain(String name){
        int itemCnt = 0;
        for (Item item : items.keySet()) {
            if(item.name.equalsIgnoreCase(name)){
                itemCnt = items.get(item);
            }
        }
        return itemCnt;
    }
    public ArrayList<Item> getItems(){
        ArrayList<Item> itemsList = new ArrayList<>(items.keySet());
        return itemsList;
    }
    public void removeItem(Item item){
        items.remove(item);
        Player player = App.getCurrentGame().getCurrentPlayer();
        TrashCan trashCan = player.getTrashCan();
        int money = trashCan.remove(item , cnt);
        player.money += money;
    }
    public void removeItem(Item itemm, int cnt){
        Item item = getItem(itemm.name);
        int x = items.get(item);
        x -= cnt;
        if(x == 0){
            items.remove(item);
        }
        else {
            items.put(item , x);
        }
        Player player = App.getCurrentGame().getCurrentPlayer();
        TrashCan trashCan = player.getTrashCan();
        int money = trashCan.remove(item , cnt);
        player.money += money;
    }
    public Item getItem(String name){
        for (Item item : items.keySet()) {
            if(item.name.equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }
    public boolean isFull(){
        if(this.backpackType.isLimited() && this.items.keySet().size() == this.backpackType.getCapacity()){
            return true;
        }
        return false;
    }
    public Item getMaxPlant(){
        Item res = null;
        int now = 0;
        for (Plants value : Plants.values()) {
            if(contain(value.getName()) > now){
                now = contain(value.getName());
                res = getItem(value.getName());
            }
        }
        return res;
    }
    public Item getMaxMushroom(){
        Item res = null;
        int now = 0;
        for (Mushrooms value : Mushrooms.values()) {
            if(contain(value.getName()) > now){
                now = contain(value.getName());
                res = getItem(value.getName());
            }
        }
        return res;
    }
    public Item getMaxFish(){
        Item res = null;
        int now = 0;
        for (Fish fish : Fish.values()) {
            Item item = fish.getItem();
            if(contain(item) > now){
                res = item;
                now = contain(item);
            }
        }
        return res;
    }
    public Item getMaxFruit(){
        Item res = null;
        int now = 0;
        for (Fruits value : Fruits.values()) {
            if(contain(value.getName()) > now){
                now = contain(value.getName());
                res = getItem(value.getName());
            }
        }
        return  res;
    }

}
