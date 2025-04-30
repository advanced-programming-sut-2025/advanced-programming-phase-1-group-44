package controller;

import model.Result;

import java.util.HashMap;
import java.util.Map;

public class LoginMenuController extends MenuController {
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

    public Result login(HashMap<String, String> args){

    }

    public Result forgetPassword(HashMap<String, String> args){

    }
}
