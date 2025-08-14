package model;

public class Gift {
    private final Item item;
    private final int amount;
    private final int id;
    private boolean isRated = false;
    private final Player sender, receiver;
    private int rating = 0;
    
    Gift(Item item, int amount, int id, Player sender, Player receiver) {
        this.item = item;
        this.amount = amount;
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
    }
    public Player getReceiver() {
        return receiver;
    }
    public Player getSender() {
        return sender;
    }
    public int getId() {
        return id;
    }
    public Item getItem() {
        return item;
    }
    public int getAmount() {
        return amount;
    }
    public void rate(int rating) {
        this.rating = rating;
        isRated = true;
    }
    public boolean isRated() {
        return isRated;
    }
    
    @Override
    public String toString() {
        return "sent from: " + sender.getUsername() + ", received by: " + receiver.getUsername() + ", item: " + item.toString() + ", amount: " + amount + ", id: " + id;
    }
}
