package model.Tools;

import model.App;
import model.Player;
import model.Result;
import model.Tool;
import model.enums.FishingPoleType;
import model.enums.Tooltype;

import java.util.HashMap;
import java.util.Map;

public class FishingPole extends Tool {
    FishingPoleType fishingPoleType;

    public FishingPole(FishingPoleType fishingPoleType) {
        super(Tooltype.fishingPole);
        this.fishingPoleType = fishingPoleType;
    }

    public Result action(){
        int energy = this.fishingPoleType.cost;
        Map<String, Object> data = new HashMap<>();
        Player player = App.getAdmin();  //TODO set player to current player
        //TODO  check fishing max level
        if(player.getFishing().level == 4){
            energy--;
        }
        if(player.energy < energy){
            data.put("flg", false);
            data.put("message" , "not enough energy");
            return new Result(data);
        }
        //TODO  do fishing!
        data.put("flg" , true);
        data.put("message", "fishing is successful");
        return new Result(data);
    }
}
