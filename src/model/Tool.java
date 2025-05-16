package model;


import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public abstract class Tool { 
    public Tooltype type;
    public int level;
    public int price;

    public Tool(Tooltype type) {
        super(type.name(), 0);
        this.level = 0;
        this.type = type;
    }

    public Result action(int x , int y) {
        return null;
    };
    public Result upgrade(){
        Map<String, Object> data = new HashMap<>();
        if(this.level == this.type.maxLevel){
            data.put("message",this.type.name() + " reached max level");
            data.put("flg" , false);
        }
        else {
            this.level++;
            data.put("message", this.type.name() + " upgraded to level " + this.level);
            data.put("flg" , true);
        }
        return new Result(data);
    };
}
