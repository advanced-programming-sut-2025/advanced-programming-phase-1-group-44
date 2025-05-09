package model.enums;

import java.util.Scanner;

import view.*;
import view.SignupLoginMenu;

public enum Menu {
    ExitMenu(new ExitMenu()),
    SignupLoginMenu(new SignupLoginMenu()),
    GameMenu(new GameMenu()),
    ProfileMenu(new ProfileMenu()),
    MainMenu(new MainMenu());

    AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }
    public void processInput(Scanner IOScanner) {
        menu.process(IOScanner);
    }

}
