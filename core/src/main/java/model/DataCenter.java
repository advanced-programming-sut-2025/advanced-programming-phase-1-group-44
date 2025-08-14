package model;

import com.esotericsoftware.kryonet.Connection;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class DataCenter {
    private static ArrayList<miniPlayer> playerList = new ArrayList<>();
    private static ArrayList<miniPlayer> connectedPlayers = new ArrayList<>();
    private static ArrayList<Lobby> lobbies = new ArrayList<>();


    public static ArrayList<miniPlayer> getRoommates(String username) {
        miniPlayer player = getUserByUsername(username);
        Lobby lobby = findLobbyByUser(player);
        return lobby.getPlayers();
    }
    // Changed to miniPlayer → Connection
    private static Map<String, Connection> playerConnections = new HashMap<>();

    public static void addPlayer(miniPlayer player) {
        playerList.add(player);
    }

    public static ArrayList<miniPlayer> getPlayerList() {
        return playerList;
    }

    public static void setPlayerList(ArrayList<miniPlayer> playerList) {
        DataCenter.playerList = playerList;
    }

    public static miniPlayer getUserByUsername(String username) {
        for (miniPlayer player : playerList) {
            if (player.getUsername().equals(username)) return player;
        }
        return null;
    }

    public static void connectPlayer(miniPlayer player) {
        connectedPlayers.add(player);
    }

    public static void connectPlayer(String player, Connection connection) {
        playerConnections.put(player, connection);
    }



    public static void removePlayerConnectionMap(String username) {
        playerList.remove(username);
    }

    public static void disconnectPlayer(miniPlayer player) {
        connectedPlayers.remove(player);
    }

    public static void disconnectPlayerConnection(miniPlayer player) {
        playerConnections.remove(player);
    }

    public static Connection getConnection(miniPlayer player) {
        return playerConnections.get(player.getUsername());
    }

    public static ArrayList<miniPlayer> getConnectedPlayers() {
        return connectedPlayers;
    }

    public static void setConnectedPlayers(ArrayList<miniPlayer> connectedPlayers) {
        DataCenter.connectedPlayers = connectedPlayers;
    }

    public static void addLobby(Lobby lobby) {
        lobbies.add(lobby);
    }

    public static ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public static Lobby getLobby(Lobby lobby) {
        for (Lobby lobby1 : lobbies) {
            if (lobby1.getName().equals(lobby.getName())) {
                return lobby1;
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

    public static ArrayList<String> getPublicChats(miniPlayer player) {
        for (Lobby lobby : lobbies) {
            if (lobby.hasPlayer(player)) {
                return lobby.getPublicChats();
            }
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
        System.out.println(lobbies.size());
        Lobby lobby = findLobbyByUser(player);
        if (lobby != null) {
            lobby.addPublicChat(message);
        }
    }

    public static void sendPrivateMessage(miniPlayer sender, miniPlayer receiver, String message) {
        Lobby lobby = findLobbyByUser(sender);
        if (lobby != null) {
            lobby.addPrivateChat(sender, receiver, message);
        }
    }
}