package model.enums;

import java.util.Scanner;

import view.AppMenu;

public enum Menu {
    ExitMenu(new ExitMenuView());

    AppMenu menu;

    Menu(AppMenu menu) {
        this.menu = menu;
    }
    public void processInput(Scanner IOScanner) {
        menu.process(IOScanner);
    }

}
