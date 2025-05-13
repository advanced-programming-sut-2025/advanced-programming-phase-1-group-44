package model;

import model.Stores.Shop;
import model.enums.ShopEnum;
import model.enums.StoreItems.*;
import model.enums.Weather;

import java.util.ArrayList;

public class Game {
    public DateTime dateTime;
    private ArrayList<Player> users , loggedInUsers;
<<<<<<< HEAD
    public Weather weather , nextDayWeather;
    private Boolean fixedWeather;
    private Player currentPlayer;

    Game() {
        dateTime = new DateTime();
=======

    public Weather weather , nextDayWeather;
    private Boolean fixedWeather;
    private Player currentPlayer;
    private ArrayList<Shop> shops = new ArrayList<>();

    void buildShops(){
        shops = new ArrayList<>();
        shops.add(new Shop(ShopEnum.blacksmith, BlackSmithItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.stardropSaloon, StardropSaloonItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.fishShop, FishShopItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.carpenter, CarpenterItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.jojaMart, JojaMartItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.marniesRanch, MarineRanchItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.pierresGeneralStore, PierreStoreItems.getItems(this.getDateTime().getSeason().getID())));
>>>>>>> f6b1b30491ff470a1d66e13726fc3b22268876b5
    }

    Game() {
        dateTime = new DateTime();
        this.buildShops();
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public void setClock(int clock) {
        dateTime.setClock(clock);
    }

    public void nextDay() {
        // TODO
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
<<<<<<< HEAD
=======
    public Shop getShop(String name){
        for (Shop shop : this.shops) {
            if(shop.getName().equalsIgnoreCase(name)){
                return shop;
            }
        }
        return null;
    }
>>>>>>> f6b1b30491ff470a1d66e13726fc3b22268876b5
}
