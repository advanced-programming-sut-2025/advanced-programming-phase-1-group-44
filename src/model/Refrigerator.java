package model;

import java.util.HashMap;
import java.util.Map;

public class Refrigerator {
    private Map<Eatable, Integer> items = new HashMap<>();
    public void putItem(Eatable item , int cnt){
        if(!items.containsKey(item)){
            items.put(item , cnt);
        } else{
            int x = items.get(item);
            items.put(item , x + cnt);
        }
    }
    public boolean contain(Eatable item){
        return items.containsKey(item);
    }
    public Integer pickItem(Eatable item){
        return items.remove(item);
    }
}
