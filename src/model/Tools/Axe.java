package model.Tools;

import controller.MapController;
import model.App;
import model.Player;
import model.Result;
import model.Tool;
import model.enums.AllItems;
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
        boolean success = false;
        if(player.getCurrentfarm().GetCell(x , y).getName().equalsIgnoreCase("tree")){
            success = true;
        }
        if(!success){
            energy--;
            if(energy < 0)
                energy = 0;
        }
        if(energy > player.energy){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        if(!success){
            data.put("flg", false);
            data.put("message", "wrong cell");
            return new Result(data);
        }
        player.getBackpack().putItem(AllItems.Wood.getItemByType(), 10);
        player.getForaging().addXP(5);
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
