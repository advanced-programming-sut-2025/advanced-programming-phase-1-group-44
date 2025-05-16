package model.Tools;

import model.*;
import model.enums.BackpackType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backpack extends Tool {
    private BackpackType backpackType;
    private Map<Item, Integer> items = new HashMap<>();
    private Integer cnt = 0;
    public Backpack() {
        backpackType = BackpackType.initial;
    }

    //TODO upgrade!
    public Result putItem(Item item , int cnt){
        Map<String , Object> data = new HashMap<>();
        if(items.containsKey(item)) {
            int val = items.get(item);
            items.put(item, val + 1);
            data.put("flg", true);
            data.put("message", "item added successfully");
            return new Result(data);
        }
        if(backpackType.isLimited() && backpackType.getCapacity() == this.cnt){
            data.put("flg" , false);
            data.put("message", "backpack is full!");
            return new Result(data);
        }
        items.put(item, 1);
        data.put("flg", true);
        data.put("message", "item added successfully");
        return new Result(data);
    }
    public int contain(Item item){
        int itemCnt = -1;
        if(items.containsKey(item)){
            itemCnt = items.get(item);
        }
        return itemCnt;
    }
    public int contain(String name){
        int itemCnt = -1;
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
    public Result removeItem(Item item, int cnt){
        Result result = contain(item);
        Map<String , Object> data = new HashMap<>();
        if(result.getData().get("flg").equals(false) || (Integer)result.getData().get("cnt") < cnt){
            data.put("flg", false);
            data.put("message", "you don't have enough from this item");
            return new Result(data);
        }
        int x = items.get(item);
        x -= cnt;
        if(x == 0){
            items.remove(item);
        }
        else {
            items.put(item , x);
        }
        Player player = App.getAdmin(); //TODO Fix this
        TrashCan trashCan = player.getTrashCan();
        Result result1 = trashCan.remove(item , cnt);
        player.money += (Integer)result1.getData().get("money"); //TODO  check
        return result1;
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
        if(this.backpackType.isLimited() && this.cnt == this.backpackType.getCapacity()){
            return true;
        }
        return false;
    }
    public Item getMaxPlant(){
        //TODO;
        return null;
    }
}
