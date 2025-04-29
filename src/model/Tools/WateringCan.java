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

    public WateringCan() {
        this.wateringCanType = Material.normal;
        this.level = 0;
        this.type = Tooltype.wateringCan;
    }

    @Override
    public Result action(int x, int y) {
        int energy = 5 - this.wateringCanType.hardness;
        int capacity = this.wateringCanType.hardness * 15 + 40;
        Map<String, Object> data = new HashMap<>();
        Player player = App.getAdmin();  //TODO set player to current player
        if(player.getFarming().level == 4){
            energy--;
        }
        //TODO check success and fix energy
        if(energy < player.energy){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        //TODO get map item and decide what to do
        data.put("flg", true);
        data.put("message", "action Done!");
        return new Result(data);
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
