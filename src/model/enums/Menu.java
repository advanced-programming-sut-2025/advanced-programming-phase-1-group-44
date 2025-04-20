package model.enums;

import java.util.Scanner;

import view.AppMenu;
import view.ExitMenu;
import view.SignupLoginMenu;

public enum Menu {
    ExitMenu(new ExitMenu()),
    SignupLoginMenu(new SignupLoginMenu());

    AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }
    public void processInput(Scanner IOScanner) {
        menu.process(IOScanner);
    }

}
