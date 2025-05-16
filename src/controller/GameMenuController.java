package controller;

import model.App;
import model.Farms.FirstFarm;
import model.Farms.SecondFarm;
import model.Player;
import model.Result;
import service.SignupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMenuController extends MenuController{
    SignupService service = new SignupService();
    @Override
    public Result exit() {

    }
    @Override
    public Result enterMenu() {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }


    @Override
    public Result showCurrentMenu() {
        return new 
    }

    public boolean createNewGame(ArrayList<String> AllNames){
        try {
            //todo motmaen besham App.player ha hamin han
            ArrayList<Player>Players=new ArrayList<Player>();
            for(String s:AllNames){
                Player pl=service.FindUserByUsername(s);
                if(pl == null){
                    return false;
                }
                Players.add(pl);
            }
            App.setPlayers(Players);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean chooseGameMap(int no){
        try {
            if(no==1){
                App.getCurrentGame().getCurrentPlayer().setMapFarm(new FirstFarm());
            }else if(no==2){
                App.getCurrentGame().getCurrentPlayer().setMapFarm(new SecondFarm());
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Result loadGame(){
        //todo nafahmiadm
    }
}
