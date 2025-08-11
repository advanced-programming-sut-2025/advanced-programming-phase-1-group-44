package model;

import java.util.ArrayList;

public class Lobby {
    public String name;
    public String admin;
    public boolean isPrivate;
    public String password;
    public ArrayList<miniPlayer> players = new ArrayList<>();

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

    public void addPlayer(miniPlayer player){ players.add(player);}

    public void removePlayer(miniPlayer player){
        miniPlayer removedPlayer = null;
        for (miniPlayer miniPlayer : players) {
            if(miniPlayer.username.equals(player.username)){
                removedPlayer = miniPlayer;
            }
        }
        players.remove(removedPlayer);
    }
}
