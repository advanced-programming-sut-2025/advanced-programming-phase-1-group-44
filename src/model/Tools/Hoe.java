package model.Tools;

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
        this.hoeType = Material.normal;
        this.level = 0;
        this.type = Tooltype.hoe;
    }
    @Override
    public Result action(int x , int y) {
        int energy = 5 - hoeType.hardness;
        Map<String, Object> data = new HashMap<>();
        Player player;//TODO change player to current player and check energy
        //TODO check position x , y is available for shokhm!
        //TODO shokhm
        data.put("flg", true);
        data.put("message", "position x , y shokhmed!");
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
