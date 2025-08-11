package controller;

import java.util.Map;

import main.Main;
import model.App;
import model.Result;
import model.enums.Menu;

public class MainMenuController extends MenuController{
    @Override
    public Result exit() {
        Menu.SignupMenu.setMenu();
        return new Result(Map.of("message", "exit main menu, entering signup/login menu"));
    }



    public Result logout(){


        Main.getNetworkClient().logout();
        App.logout();

        return new Result(Map.of("message", "User logged out successfully, you're now in signup/login menu"));
    }

    @Override
    public Result enterMenu(String menuName) {
        if (menuName.equals("signup")) {
            Menu.SignupMenu.setMenu();
            return new Result(Map.of("message", "entered signup/login menu"));
        }
        if (menuName.equals("game")) {
            Menu.GameView.setMenu();
            return new Result(Map.of("message", "entered game menu"));
        }
        else if (menuName.equals("profile")) {
            Menu.ProfileMenu.setMenu();
            return new Result(Map.of("message", "enterd profile menu"));
        }else if(menuName.equals("play")){
            Menu.Gameplay.setMenu();
            return new Result(Map.of("message","enterd Game play"));
        }
        return new Result(Map.of("message", "given menu doesn't exist"));
    }

}
