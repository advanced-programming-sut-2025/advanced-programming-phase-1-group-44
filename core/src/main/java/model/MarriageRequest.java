package model;

public class MarriageRequest {
    private Player sender;
    private Player receiver;
    private String ring;

    MarriageRequest(Player sender, Player receiver, String ring) {
        this.sender = sender;
        this.receiver = receiver;
        this.ring = ring;
    }
    public Player getReceiver() {
        return receiver;
    }
    public String getRing() {
        return ring;
    }
    public Player getSender() {
        return sender;
    }
}
