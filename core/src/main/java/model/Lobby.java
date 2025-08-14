package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Lobby {
    public String name;
    public String admin;
    public boolean isPrivate;
    public String password;
    public ArrayList<miniPlayer> players = new ArrayList<>();
    private ArrayList<String> publicChats = new ArrayList<>();
    private Map<miniPlayer, Map<miniPlayer, ArrayList<String>>> privateChats = new HashMap<>();
    private int yesVote = 0, noVote = 0;
    private boolean voteType; // 0 -> terminate
    private miniPlayer playerVote; // which player to kick out

    public Lobby() {
    }

    public Lobby(String name, String admin, boolean isPrivate, String password) {
        this.name = name;
        this.admin = admin;
        this.isPrivate = isPrivate;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getAdmin() {
        return admin;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getPassword() {
        return password;
    }

    public ArrayList<miniPlayer> getPlayers() {
        return players;
    }

    public void addPlayer(miniPlayer player) {
        players.add(player);
    }

    public void removePlayer(miniPlayer player) {
        miniPlayer removedPlayer = null;
        for (miniPlayer miniPlayer : players) {
            if (miniPlayer.username.equals(player.username)) {
                removedPlayer = miniPlayer;
            }
        }
        players.remove(removedPlayer);
    }

    public boolean hasPlayer(miniPlayer player) {
        for (miniPlayer player1 : players) {
            if (player.getUsername().equals(player1.getUsername())) return true;
        }
        return false;
    }

    public boolean getVoteType() {
        return voteType;
    }

    public miniPlayer getPlayerVote() {
        return playerVote;
    }

    public void startKickVote(String playerVoteName) {
        yesVote = 0;
        noVote = 0;
        this.voteType = true;
        this.playerVote = DataCenter.getUserByUsername(playerVoteName);
    }

    public void startTerminateVote() {
        yesVote = 0;
        noVote = 0;
        this.voteType = false; // 0 is terminate
    }


    public int addVote(boolean vote) { ///  2 is yes, 1 is no, 0 is not finished
        if (vote) yesVote++;
        else noVote++;
        if ((yesVote + noVote) < getNumberOfPlayers()) return 0;

        return (yesVote > noVote ? 2 : 1);
    }

    public int getNumberOfPlayers() {
        return players.size();
    }

    public Map<miniPlayer, ArrayList<String>> getPrivateChats(miniPlayer player) {
        return privateChats.get(player);
    }

    public ArrayList<String> getPublicChats() {
        return publicChats;
    }

    public void addPublicChat(String message) {
        publicChats.add(message);
    }

    public void addPrivateChat(miniPlayer sender, miniPlayer receiver, String message) {
        privateChats
                .computeIfAbsent(sender, k -> new HashMap<>())
                .computeIfAbsent(receiver, k -> new ArrayList<>())
                .add(sender.getUsername() + ": " + message);

        privateChats
                .computeIfAbsent(receiver, k -> new HashMap<>())
                .computeIfAbsent(sender, k -> new ArrayList<>())
                .add(sender.getUsername() + ": " + message);
    }
}