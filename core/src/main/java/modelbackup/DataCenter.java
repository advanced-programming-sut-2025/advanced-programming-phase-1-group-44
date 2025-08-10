package modelbackup;


import java.util.ArrayList;

public final class DataCenter {
    private static ArrayList<miniPlayer> playerList = new ArrayList<>();

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


}
