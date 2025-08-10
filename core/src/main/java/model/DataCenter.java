package model;


import java.lang.reflect.Array;
import java.util.ArrayList;

public final class DataCenter {
    private static ArrayList<miniPlayer> playerList = new ArrayList<>();

    private static ArrayList<miniPlayer> connectedPlayers = new ArrayList<>();

    public static void addPlayer(miniPlayer player) {
        playerList.add(player);
    }

    public static ArrayList<miniPlayer> getPlayerList() {
        return playerList;
    }

    public static miniPlayer getUserByUsername(String username) {
        for (miniPlayer player : playerList) {
            if (player.getUsername().equals(username)) return player;
        }
        return null;
    }

    public static void setPlayerList(ArrayList<miniPlayer> playerList) {
        DataCenter.playerList = playerList;
    }

    public static void connectPlayer(miniPlayer player) {
        connectedPlayers.add(player);
    }
    public static void disconnectPlayer(miniPlayer player) {
        connectedPlayers.remove(player);
    }


    public static ArrayList<miniPlayer> getConnectedPlayers() {
        return connectedPlayers;
    }

    public static void setConnectedPlayers(ArrayList<miniPlayer> connectedPlayers) {
        DataCenter.connectedPlayers = connectedPlayers;
    }
}
