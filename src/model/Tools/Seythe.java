package model.Tools;

import model.App;
import model.Player;
import model.Result;
import model.Tool;

import java.util.HashMap;
import java.util.Map;

public class Seythe extends Tool {
    //TODO  give player
    @Override
    public Result action(int x, int y) {
        int energy = 2;
        Player player = App.getAdmin();  //TODO
        Map<String, Object> data = new HashMap<>();
        if(player.energy < energy){
            data.put("flg", false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        //TODO if you can do your action do that!;
        data.put("flg" , true);
        data.put("message", "Done!");
        return new Result(data);
    }
}
