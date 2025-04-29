package model.Tools;

import model.Result;
import model.Tool;
import model.enums.BackpackType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Backpack extends Tool {
    private BackpackType backpackType;
    private ArrayList<Object> items = new ArrayList<>();
    //TODO upgrade!
    public Result putItem(Object item){
        Map<String , Object> data = new HashMap<>();
        if(backpackType.isLimited() && backpackType.getCapacity() == items.size()){
            data.put("flg" , false);
            data.put("message", "backpack is full!");
            return new Result(data);
        }
        items.add(item);
        data.put("flg", true);
        data.put("message", "item added successfully");
        return new Result(data);
    }
    public Result contain(Object item){
        Map<String, Object> data = new HashMap<>();
        data.put("flg", false);
        data.put("message", "you don't have this item");
        for (Object o : items) {
            if(o.equals(item)){
                data.put("flg" , true);
                data.put("item", o);
                break;
            }
        }
        return new Result(data);
    }
}
