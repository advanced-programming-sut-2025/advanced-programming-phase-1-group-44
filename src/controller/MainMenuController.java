package controller;

import java.util.Map;

import model.App;
import model.Result;
import model.enums.Menu;

public class MainMenuController extends MenuController{
    @Override
    public Result exit() {
        App.enterMenu(Menu.SignupLoginMenu);
        return new Result(Map.of("message", "exit main menu, entering signup/login menu"))
    }
    @Override
    public Result enterMenu() {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }


    public Result logout(){
        App.logout();
        return new Result(Map.of("message", "User logged out successfully, you're now in signup/login menu"));
    }


    public Result enterMenu(String menuName) {
        if (menuName.equals("game")) {
            App.enterMenu(Menu.GameMenu);
            return new Result(Map.of("message", "entered game menu"));
        }
        else if (menuName.equals("profile")) {
            App.enterMenu((Menu.ProfileMenu));
            return new Result(Map.of("message", "enterd profile menu"));
        }
        return new Result(Map.of("message", "given menu doesn't exist"));
    }

}
