package model.Tools;

import model.App;
import model.Player;
import model.Result;
import model.Tool;
import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public class MilkPail extends Tool {
    //TODO give it to player
    public MilkPail(){
        super(Tooltype.milkPail);
    }
    @Override
    public Result action(int x, int y) {
        int energy = 4;
        Player player = App.getAdmin(); //TODO
        Map<String , Object> data = new HashMap<>();
        if(player.energy < energy){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        //TODO action
        data.put("flg", true);
        data.put("message" , "DONE!");
        return new Result(data);
    }
}
