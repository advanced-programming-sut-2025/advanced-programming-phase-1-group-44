package model;

import model.Stores.Shop;
import model.enums.ShopEnum;
import model.enums.StoreItems.*;
import model.enums.Weather;
import model.enums.AnimalEnum.AnimalHomeType;
import model.Animals.AnimalHome;

import java.util.ArrayList;

public class Game {
    private final int[] friendshipMAX = {100, 200, 300, 400};
    public DateTime dateTime;
    private ArrayList<Player> users , loggedInUsers;

    public Weather weather , nextDayWeather;
    private Boolean fixedWeather;
    private Player currentPlayer;
    private ArrayList<Shop> shops = new ArrayList<>();

    private ArrayList<AnimalHome> animalHomes = new ArrayList<>();

    private int[][] friendship = new int[4][4];
    private int[][] friendshipLevel = new int[4][4];
    private boolean[][] hadCommunication = new boolean[4][4];
    private ArrayList<Message>[] talkHistory = new ArrayList[4];

    void buildShops(){
        shops = new ArrayList<>();
        shops.add(new Shop(ShopEnum.blacksmith, BlackSmithItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.stardropSaloon, StardropSaloonItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.fishShop, FishShopItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.carpenter, CarpenterItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.jojaMart, JojaMartItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.marniesRanch, MarineRanchItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.pierresGeneralStore, PierreStoreItems.getItems(this.getDateTime().getSeason().getID())));
    }

    Game() {
        dateTime = new DateTime();
        this.buildShops();

        for (int i = 0; i < 4; i++) talkHistory[i] = new ArrayList<>();
    }

    public Player findPlayerByUsername(String username) {
        for (Player user : users) {
            if (user.getUsername().equals(username)) return user;
        }
        return null;
    }

    public ArrayList<Message> getTalkHistoryByUsername(String username) {
        int i = getId(currentPlayer), j = 0;
        ArrayList<Message> result = new ArrayList<>();
        for (Message message : talkHistory[i]) {
            if (message.getPlayer().getUsername().equals(username)) result.add(message);
        }
        return result;
    }
    private void updateFriendship(int i, int j, int amount) {
        
        friendship[i][j] += amount;
        friendship[j][i] += amount;
        if (friendshipLevel[i][j] >= 3) return;
        if (friendship[i][j] > friendshipMAX[friendshipLevel[i][j]]) {
            friendshipLevel[i][j]++;
            friendshipLevel[j][i]++;

            friendship[i][j] = 0;
            friendship[j][i] = 0;
        }
    }
    

    public void talk(String message, Player player1, Player player2) {
        int i = getId(player1);
        int j = getId(player2);
        if (hadCommunication[i][j]) return;
        hadCommunication[i][j] = hadCommunication[j][i] = true;
        updateFriendship(i, j, 20);

        talkHistory[j].add(new Message(message, player1));
    }
    private int getId(Player p) {
        int i = 0;
        for (Player user : users) {
            if (user == p) return i;
            i++;
        }
        return -1;
    }

    public void giftFriendship(Player player1, Player player2, int amount) {
        int i = getId(player1);
        int j = getId(player2);
        updateFriendship(i, j, amount);
    }

    public void addAnimalHome(AnimalHomeType type) {
        animalHomes.add(new AnimalHome(type));

    }


    public DateTime getDateTime() {
        return dateTime;
    }

    public void setClock(int clock) {
        dateTime.setClock(clock);
    }

    public void nextDay() {
        // TODO

                // Animals:


        for (int i = 0; i < users.size(); i++) {
            Player player = users.get(i);
            for (int j = 0; j < users.size(); j++) {
                if (i == j) continue;
                Player otherPlayer = users.get(j);
                if (!hadCommunication[i][j]) {
                    friendship[i][j] -= 10;
                    friendship[j][i] -= 10;
                    if (friendshipLevel[i][j] == 0) {
                        friendship[i][j] = friendship[i][j] < 0 ? 0 : friendship[i][j];
                        friendship[j][i] = friendship[j][i] < 0 ? 0 : friendship[j][i];
                    }
                    if (friendship[i][j] < 0) {
                        friendship[i][j] = friendshipMAX[friendshipLevel[i][j]];
                        friendship[j][i] = friendshipMAX[friendshipLevel[i][j]];
                    }
                }
                hadCommunication[i][j] = false;
                hadCommunication[j][i] = false;
                
                    
            }
        }
    }

    public void setNextDayWeather(Weather nextDayWeather) {
        this.nextDayWeather = nextDayWeather;
        this.fixedWeather = true;
    }
    public void resetFixedWeather(){
        this.fixedWeather = false;
    }
    public Player getCurrentPlayer(){
        //TODO fix this
        return currentPlayer;
    }
    public Shop getShop(String name){
        for (Shop shop : this.shops) {
            if(shop.getName().equalsIgnoreCase(name)){
                return shop;
            }
        }
        return null;
    }
}
