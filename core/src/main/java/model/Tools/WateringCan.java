package model.Tools;

import model.App;
import model.Player;
import model.Result;
import model.Tool;
import model.enums.Material;
import model.enums.Tooltype;
import model.enums.WateringCanType;

import java.util.HashMap;
import java.util.Map;

public class WateringCan extends Tool {
    Material wateringCanType;
    int water;
    public WateringCan() {
        super(Tooltype.wateringCan);
        this.wateringCanType = Material.normal;
        this.level = 0;
        this.water = 0;
    }

    @Override
    public Result action(int x, int y) {
        int energy = 5 - this.wateringCanType.hardness;
        int capacity = this.wateringCanType.hardness * 15 + 40;
        Map<String, Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(player.getFarming().level == 4){
            energy--;
        }
        boolean success = false;
        if(player.getCurrentfarm().GetCell(x,y).getName().equalsIgnoreCase("lake")){
            success = true;
        }
        if(energy < player.energy){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        if(!success){
            data.put("flg" , false);
            data.put("message", "wrong cell");
            return new Result(data);
        }
        this.water = capacity;
        data.put("flg", true);
        data.put("message", "action Done!");
        return new Result(data);
    }

    public int getWater(){
        return this.water;
    }
    public void decreaseWater(int x){
        this.water -= x;
    }

    @Override
    public Result upgrade() {
        Result result = super.upgrade();
        Map<String, Object> data = result.getData();
        if(data.get("flg").equals(true)){
            this.wateringCanType = Material.getMaterial(this.level);
        }
        return result;
    }
}
