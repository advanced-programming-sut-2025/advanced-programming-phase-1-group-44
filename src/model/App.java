package model;

import java.util.ArrayList;

import model.enums.*;
// all the data for the app are stored here. the main data center

public class App {
    static private Menu currentMenu = Menu.SignupLoginMenu;
    static private ArrayList<Player> players = new ArrayList<>();

    static public Menu getGetCurrentMenu() {
        return currentMenu;
    }

    static public void enterMenu(Menu menu) {
        currentMenu = menu;
    }

    public static ArrayList<Player> getPlayers() {
        return players;
    }
}
