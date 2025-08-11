package model;


import com.esotericsoftware.kryonet.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class DataCenter {
    private static ArrayList<miniPlayer> playerList = new ArrayList<>();

    private static ArrayList<miniPlayer> connectedPlayers = new ArrayList<>();

    private static ArrayList<Lobby> lobbies = new ArrayList<>();

    private static Map<String, Connection> playerConnections = new HashMap<>();

    public static void connectPlayer(miniPlayer player, Connection connection) {
        playerConnections.put(player.getUsername(), connection);
    }

    public static void disconnectPlayerConnection(miniPlayer player) {
        playerConnections.remove(player.getUsername());
    }

    public static Connection getConnection(miniPlayer player) {
        return playerConnections.get(player.getUsername());
    }

    public static void addPlayer(miniPlayer player) {
        playerList.add(player);
    }


    public static ArrayList<miniPlayer> getPlayerList() {
        return playerList;
    }

    public static ArrayList<String> getPublicChats(miniPlayer player) {
        for (Lobby lobby : lobbies) {
            if (lobby.hasPlayer(player)) {
                return lobby.getPublicChats();
            }
        }
        return null;
    }

    public static Lobby findLobbyByUser(miniPlayer player) {
        for (Lobby lobby : lobbies) {
            if (lobby.hasPlayer(player)) return lobby;
        }
        return null;
    }
    public static Map<miniPlayer, ArrayList<String>> getPrivateChats(miniPlayer player) {
        for (Lobby lobby : lobbies) {
            if (lobby.hasPlayer(player)) {
                return lobby.getPrivateChats(player);
            }
        }
        return null;

    }

    public static void sendPublicMessage(miniPlayer player, String message) {
        Lobby lobby = findLobbyByUser(player);
        assert lobby != null;
        lobby.addPublicChat(message);
    }
    public static void sendPrivateMessage(miniPlayer sender, miniPlayer receiver, String message) {
        Lobby lobby = findLobbyByUser(sender);
        assert lobby != null;
        lobby.addPrivateChat(sender, receiver, message);
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

    public static void addLobby(Lobby lobby){ lobbies.add(lobby);}

    public static ArrayList<Lobby> getLobbies(){ return  lobbies;}

    public static Lobby getLobby(Lobby lobby){
        for (Lobby lobby1 : lobbies) {
            if(lobby1.getName().equals(lobby.getName())){
                return lobby1;
            }
        }
        return null;
    }
}
