package controller;

import model.App;
import model.Result;
import model.enums.Menu;

public class MainMenuController implements MenuController{
    @Override
    public Result exit() {

    }

    public Result logout(){

    }


    public Result enterMenu(String menuName) {
        if (menuName.equals("game")) {
            App.enterMenu(Menu.GameMenu);
            return new Result("entered game menu", true);
        }
        else if (menuName.equals("profile")) {
            App.enterMenu((Menu.ProfileMenu));
            return new Result("enterd profile menu", true);
        }
        return new Result("given menu not available", false);
    }

}
