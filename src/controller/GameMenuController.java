package controller;

import model.App;
import model.Farms.FirstFarm;
import model.Farms.SecondFarm;
import model.Game;
import model.Player;
import model.Result;
import service.SignupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GameMenuController extends MenuController{
    SignupService service = new SignupService();
    GamePlayController gpc=new GamePlayController();
    @Override
    public Result exit() {
        return new Result(Map.of("message", "you should go to signup/login menu first"));
    }
    @Override
    public Result enterMenu(String menuName) {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }

    public Result showCurrentMenu() {
        return new Result(Map.of("message", "current menu is: Game Play"));
    }
    Game FindGameByUserName(Player pl){
        for(Game gm:App.getGames()){
            for(Player p:gm.getUsers()){
                if(p==pl){
                    return gm;
                }
            }
        }
        return null;
    }
    public boolean load(){
        try {
            Player pl=App.getAdmin();
            Game gm=FindGameByUserName(pl);
            if(gm==null){
                return false;
            }
            App.setCurrentGame(gm);
        } catch (Exception e) {
            return false;
        }
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
            App.AddGame();
            App.setCurrentGame(App.getGames().getLast());
            App.setCurrentGame(App.getGames().getLast());
            App.getCurrentGame().setCountuser(Players.size());
            for(Player pl:Players){
                pl.getCurrentfarm().setMapCell(0,0,pl);
                pl.setEnergy(200);
            }
            int ted=1;
            while (Players.size()<4){
                Players.add(new Player("Ai"+ted,"qazwsxecd","khafan"+ted,ted+"khafan@gmail.com","male"));
                ted++;
            }
            for(Player pl:Players){
                pl.getCurrentfarm().setMapCell(0,0,pl);
                pl.setEnergy(200);
            }
            App.getCurrentGame().setUsers(Players);
            App.getCurrentGame().setAdmin(Players.get(0));
            App.getCurrentGame().setCurrentPlayer(Players.get(0));
            gpc.enterNextDay();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean chooseGameMap(int no){
        try {
            if(no==1){
                App.getCurrentGame().getCurrentPlayer().setCurrentfarm(new FirstFarm(App.getCurrentGame().getCurrentPlayer()));
            }else if(no==2){
                App.getCurrentGame().getCurrentPlayer().setCurrentfarm(new SecondFarm(App.getCurrentGame().getCurrentPlayer()));
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void exitgame(){
        App.setCurrentGame(null);
    }
}
