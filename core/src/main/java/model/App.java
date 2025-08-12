package model;

import java.util.ArrayList;

import model.enums.*;
// all the data for the app are stored here. the main data center

public class App {
    static public ArrayList<String>Alltreepath=new ArrayList<String>();
    static {
        Alltreepath.add("Trees/AppleTreeLightning.png");
        Alltreepath.add("Trees/Apricot_Stage_5_Fruit.png");
        Alltreepath.add("Trees/Banana_Stage_5_Fruit.png");
        Alltreepath.add("Trees/Cherry_Stage_5_Fruit.png");
    }
    static public ArrayList<String>Allnpcpath=new ArrayList<String>();
    static {
        Allnpcpath.add("Villagers/Abigail.png");
        Allnpcpath.add("Villagers/Alex.png");
        Allnpcpath.add("Villagers/Birdie.png");
        Allnpcpath.add("Villagers/Bouncer.png");
        Allnpcpath.add("Villagers/Caroline.png");
        Allnpcpath.add("Villagers/Clint.png");
        Allnpcpath.add("Villagers/Demetrius.png");
    }

    public static ArrayList<String> getAllnpcpath() {
        return Allnpcpath;
    }

    public static ArrayList<String> getAlltreepath() {
        return Alltreepath;
    }

    static private ArrayList<Player> players = new ArrayList<>();
    static {
        players.add(new Player("asd1","asd1","asd1","asd1","men"));
        players.add(new Player("asd2","asd2","asd2","asd2","men"));
    }
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


    public static void setPlayers(ArrayList<Player> players) {
        App.players = players;
    }

    public static ArrayList<Game> getGames() {
        return games;
    }

    public static Game getCurrentGame() {
        return currentGame;
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
