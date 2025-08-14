package model;


import java.util.ArrayList;

public final class DataCenter {
    private static ArrayList<miniPlayer> playerList;

    public void addPlayer(miniPlayer player) {
        playerList.add(player);
    }

    public static ArrayList<miniPlayer> getPlayerList() {
        return playerList;
    }

    public static miniPlayer getUserByUsername(String a){return null;};


}
