package model;

import model.Stores.Blacksmith;
import model.Stores.Shop;
import model.Stores.StardropSaloon;
import model.enums.ShopEnum;
import model.enums.Weather;

import java.util.ArrayList;

public class Game {
    public DateTime dateTime;
    private ArrayList<Player> users , loggedInUsers;
    public Weather weather , nextDayWeather;
    private Boolean fixedWeather;
    private Player currentPlayer;
    private ArrayList<Shop> shops = new ArrayList<>();

    void buildShops(){
        shops.add(new Blacksmith(ShopEnum.blacksmith));
        shops.add(new StardropSaloon(ShopEnum.stardropSaloon));

        //TODO add all shops;
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
    public Shop getShop(String name){
        for (Shop shop : this.shops) {
            if(shop.getShopType().name().equalsIgnoreCase(name)){
                return shop;
            }
        }
        return null;
    }
}
