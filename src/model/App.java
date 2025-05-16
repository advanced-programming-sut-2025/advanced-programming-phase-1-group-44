package model;

import java.util.ArrayList;

import model.enums.*;
// all the data for the app are stored here. the main data center

public class App {
    static private Menu currentMenu = Menu.SignupLoginMenu;
    static private ArrayList<Player> players = new ArrayList<>();
    static private Player admin;
    static private ArrayList<Game> games = new ArrayList<>();
    static private Game currentGame;
    public static int inf=1000000000+10;
    public static boolean AddGame(){
        try{
            games.add(new Game());
            return true;
        } catch (Exception e) {
           return false;
        }
    }

    public static void setCurrentGame(Game currentGame) {
        App.currentGame = currentGame;
    }

    static public Menu getGetCurrentMenu() {
        return currentMenu;
    }

    public static void setPlayers(ArrayList<Player> players) {
        App.players = players;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static Game getCurrentGame() {
        return currentGame;
    }

    static public void enterMenu(Menu menu) {
        currentMenu = menu;
    }

    public static Player getAdmin() {
        return admin;
    }
    
    public static ArrayList<Player> getPlayers() {
        return players;
    }

    public static void addPlayer(Player player) {
        players.add(player);
    }

    public static Player findUserByUsername(String usernam) {
        for (Player player : players) {
            if (player.getUsername().equals(usernam)) return player;
        }
        return null;
    }

    public static void login(Player user) {
        admin = user;
    }
    public static void logout() {
        admin = null;
    }
}
