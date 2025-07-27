package model;

public class Message {
    private String message;
    private Player player;

    Message(String message, Player player) {
        this.message = message;
        this.player = player;
    }

    public String getMessage() {
        return message;
    }
    public Player getPlayer() {
        return player;
    }
}
