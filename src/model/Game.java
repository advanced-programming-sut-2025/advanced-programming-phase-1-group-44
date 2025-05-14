package model;

import model.Stores.Shop;
import model.enums.ShopEnum;
import model.enums.StoreItems.*;
import model.enums.Weather;
import model.enums.AnimalEnum.AnimalHomeType;
import model.Animals.AnimalHome;

import java.util.ArrayList;

public class Game {
    public DateTime dateTime;
    private ArrayList<Player> users , loggedInUsers;

    public Weather weather , nextDayWeather;
    private Boolean fixedWeather;
    private Player currentPlayer;
    private ArrayList<Shop> shops = new ArrayList<>();

    private ArrayList<AnimalHome> animalHomes = new ArrayList<>();

    private int[][] friendship = new int[4][4];
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

    public void talk(String message, Player player1, Player player2) {
        int i = getId(player1);
        int j = getId(player2);
        if (hadCommunication[i][j]) return;
        hadCommunication[i][j] = hadCommunication[j][i] = true;
        friendship[i][j] += 20;

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
