package model.Tools;

import model.Item;
import model.Result;
import model.Tool;
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
    public Result contain(Item item){
        Map<String, Object> data = new HashMap<>();
        data.put("flg", false);
        data.put("message", "you don't have this item");
        if(items.containsKey(item)){
            data.put("flg" , true);
            data.put("message", "you have this item");
        }
        return new Result(data);
    }
}
