package model.enums;

import main.Main;
import view.*;
import view.SignupLoginMenu;

public enum Menu {
    ExitMenu(new ExitMenu()),
    SignupMenu(new SignupScreen()),
    LoginMenu(new LoginScreen()),
    ProfileMenu(new ProfileMenu()),
    MainMenu(new MainMenu()),
    Gameplay(new GamePlay()),
    GameView(new GameView()),
    TradeView(new TradeView()),
    ;
    AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }
    public void setMenu() {
        Main.getMain().getScreen().dispose();
        Main.getMain().setScreen(menu);
    }

}
