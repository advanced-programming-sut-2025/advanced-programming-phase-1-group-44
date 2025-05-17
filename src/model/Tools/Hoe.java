package model.Tools;

import controller.MapController;
import model.App;
import model.Player;
import model.Result;
import model.Tool;
import model.enums.Material;
import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public class Hoe extends Tool {
    Material hoeType;

    public Hoe() {
        super(Tooltype.hoe);
        this.hoeType = Material.normal;
        this.level = 0;
    }
    @Override
    public Result action(int x , int y) {
        int energy = 5 - hoeType.hardness;
        Map<String, Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(player.energy < energy){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        MapController controller = new MapController();
        player.energy -= energy;
        /*if(!controller.shokhm(x , y)){
            data.put("flg" , false);
            data.put("message", "you can't shokhm here");
            return new Result(data);
        }*/
        data.put("flg", true);
        data.put("message", "position" + x + " , " + y + "shokhmed!");
        return new Result(data);
    }

    @Override
    public Result upgrade() {
        Result result = super.upgrade();
        Map<String, Object> data = result.getData();
        if(data.get("flg").equals(true)){
            hoeType = Material.getMaterial(this.level);
        }
        return result;
    }
}
