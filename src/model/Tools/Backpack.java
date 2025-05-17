package model.Tools;

import model.*;
import model.enums.BackpackType;
import model.enums.Tooltype;

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

    
    //TODO upgrade!
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
        player.money += money; //TODO  check
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
        player.money += money; //TODO  check
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
        //TODO;
        return null;
    }
}
