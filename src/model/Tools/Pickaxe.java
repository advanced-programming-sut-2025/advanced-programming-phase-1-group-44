package model.Tools;

import model.App;
import model.Player;
import model.Result;
import model.Tool;
import model.enums.Material;
import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public class Pickaxe extends Tool {
    Material pickaxeType;
    //TODO  give player normal items at first
    public Pickaxe() {
        this.pickaxeType = Material.normal;
        this.level = 0;
        this.type = Tooltype.pickaxe;
    }

    @Override
    public Result action(int x, int y) {
        int energy = 5 - this.pickaxeType.hardness;
        Map<String, Object> data = new HashMap<>();
        Player player = App.getAdmin();  //TODO set player to current player
        if(player.getExtracing().level == 4){
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
            pickaxeType = Material.getMaterial(this.level);
        }
        return result;
    }
}
