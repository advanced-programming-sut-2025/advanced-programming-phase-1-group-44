package model;

import model.enums.*;
// all the data for the app are stored here. the main data center

public class App {
    static private Menu currentMenu = Menu.SignupLoginMenu;

    static public Menu getGetCurrentMenu() {
        return currentMenu;
    }

    static public void enterMenu(Menu menu) {
        currentMenu = menu;
    }
}
