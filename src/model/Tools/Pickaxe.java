package model.Tools;

import model.*;
import model.enums.Material;
import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public class Pickaxe extends Tool {
    Material pickaxeType;
    public Pickaxe() {
        super(Tooltype.pickaxe);
        this.pickaxeType = Material.normal;
        this.level = 0;
    }

    @Override
    public Result action(int x, int y) {
        int energy = 5 - this.pickaxeType.hardness;
        Map<String, Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(player.getMining().level == 4){
            energy--;
        }
        boolean success = false;
        if(App.getCurrentGame().getCurrentPlayer().getCurrentfarm().GetCell(x , y).getName().equalsIgnoreCase("quarry")){
            success = true;
        }
        if(!success) {
            energy--;
            if(energy < 0)
                energy = 0;
        }
        if(energy < player.energy){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        if(!success){
            data.put("flg" , false);
            data.put("message", "can't break this cell");
            return new Result(data);
        }
        App.getCurrentGame().getCurrentPlayer().getCurrentfarm().setMapCell(x, y, new Space());
        player.getMining().addXP(5);
        data.put("flg", true);
        data.put("message", "action Done!");
        return new Result(data);
    }

    @Override
    public Result upgrade() {
        Result result = super.upgrade();
        Map<String, Object> data = result.getData();
        if(data.get("flg").equals(true)){
            pickaxeType = Material.getMaterial(this.level);
        }
        return result;
    }
}
