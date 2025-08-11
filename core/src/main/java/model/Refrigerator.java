package model;

import java.util.HashMap;
import java.util.Map;

public class Refrigerator {
    private Map<Item, Integer> items = new HashMap<>();
    public void putItem(Item item , int cnt){
        if(!items.containsKey(item)){
            items.put(item , cnt);
        } else{
            int x = items.get(item);
            items.put(item , x + cnt);
        }
    }
    public boolean contain(Item item){
        return contain(item.name);
    }
    public Integer pickItem(Item item){
        return items.remove(item);
    }
    public boolean contain(String name){
        for (Item item : items.keySet()) {
            if(item.name.equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
    public Item getItem(String name){
        for (Item item : items.keySet()) {
            if(item.name.equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }
    public void removeItem(Item item , int cnt){
        int currentCnt = items.get(item);
        currentCnt -= cnt;
        items.put(item , currentCnt);
    }
    public int getCnt(String name){
        Item item = getItem(name);
        if(item == null)
            return 0;
        return items.get(item);
    }
}
