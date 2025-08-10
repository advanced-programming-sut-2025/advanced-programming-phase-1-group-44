package model.Tools;

import model.*;
import model.enums.Tooltype;
import model.enums.Weather;

import java.util.HashMap;
import java.util.Map;

public class Seythe extends Tool {
    //TODO  give player
    public Seythe(){
        super(Tooltype.seythe);
    }
    @Override
    public Result action(int x, int y) {
        int energy = 2;
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map<String, Object> data = new HashMap<>();
        if(App.getCurrentGame().getWeather().equals(Weather.Snow)){
            energy *= 2;
        }
        if(player.energy < energy){
            data.put("flg", false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        boolean success = false;
        if(player.getCurrentfarm().GetCell(x , y).getName().equalsIgnoreCase("alafharz")){
            success = true;
            player.getCurrentfarm().setMapCell(x, y, new Space());
        }
        if(!success){
            data.put("flg", false);
            data.put("message", "wrong cell!");
            return new Result(data);
        }
        data.put("flg" , true);
        data.put("message", "Done!");
        return new Result(data);
    }
}
