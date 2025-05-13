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
<<<<<<< HEAD
    public int contain(Eatable item){
        if(items.containsKey(item)){
            return items.get(item);
        }
        return 0;
    }
    public int contain(String name){
        int itemCnt = 0;
        for (Eatable eatable : items.keySet()) {
            if(eatable.name.equalsIgnoreCase(name)){
                itemCnt = items.get(eatable);
            }
        }
        return itemCnt;
=======
    public boolean contain(Eatable item){
        return items.containsKey(item);
>>>>>>> f6b1b30491ff470a1d66e13726fc3b22268876b5
    }
    public Integer pickItem(Eatable item){
        return items.remove(item);
    }
<<<<<<< HEAD
    public void removeItem(Eatable item , int cnt){
        int nowCnt = items.get(item);
        nowCnt -= cnt;
        items.put(item , nowCnt);
    }
    public Eatable getItem(String name){
        for (Eatable eatable : items.keySet()) {
            if(eatable.name.equalsIgnoreCase(name))
                return eatable;
        }
        return null;
    }
=======
>>>>>>> f6b1b30491ff470a1d66e13726fc3b22268876b5
}
