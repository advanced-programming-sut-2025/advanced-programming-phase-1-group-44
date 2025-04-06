package controller;

import model.Result;

import java.util.HashMap;

public class GamePlayController implements MenuController{
    @Override
    public Result exit() {
        return null;
    }

    @Override
    public Result showCurrentMenu() {
        return null;
    }

    public Result exitGame(){
        return null;
    }

    public Result nextTurn(){
        return null;
    }
    public Result getTime(){
        return null;
    }
    public Result getDate(){
        return null;
    }
    public Result getDateTime(){
        return null;
    }
    public Result getDayOfTheWeek(){
        return null;
    }
    public Result cheatTime(HashMap<String, String> args){
        return null;
    }
    public Result cheatDate(HashMap<String, String> args){
        return null;
    }
    public Result getSeason(){
        return null;
    }
}
