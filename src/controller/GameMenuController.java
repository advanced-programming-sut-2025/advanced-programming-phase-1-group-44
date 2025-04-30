package controller;

import model.Result;

import java.util.HashMap;
import java.util.Map;

public class GameMenuController extends MenuController{
    @Override
    public Result exit() {

    }
    @Override
    public Result enterMenu() {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }


    @Override
    public Result showCurrentMenu() {

    }

    public Result createNewGame(HashMap<String, String> args){
        return null;
    }
    public Result chooseGameMap(HashMap<String, String> args){
        return null;
    }
    public Result loadGame(){
         return null;
    }
}
