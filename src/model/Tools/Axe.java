package model.Tools;

import model.App;
import model.Player;
import model.Result;
import model.Tool;
import model.enums.Material;
import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public class Axe extends Tool {
    Material axeType;

    public Axe() {
        super(Tooltype.axe);
        this.axeType = Material.normal;
        this.level = 0;
    }

    @Override
    public Result action(int x, int y) {
        int energy = 5 - this.axeType.hardness;
        Map<String, Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(player.getForaging().level == 4){
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
            axeType = Material.getMaterial(this.level);
        }
        return result;
    }
}
