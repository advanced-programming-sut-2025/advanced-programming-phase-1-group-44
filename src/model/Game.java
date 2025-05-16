package model;

import model.NPC.NPC;
import model.NPC.NPCBuilder;
import model.NPC.NPCDirector;
import model.NPC.Quest;
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
    private ArrayList<NPC> gameNPCs;
    private boolean thirdQuest;
    private void buildShops(){
        shops = new ArrayList<>();
        shops.add(new Shop(ShopEnum.blacksmith, BlackSmithItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.stardropSaloon, StardropSaloonItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.fishShop, FishShopItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.carpenter, CarpenterItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.jojaMart, JojaMartItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.marniesRanch, MarineRanchItems.getItems(this.getDateTime().getSeason().getID())));
        shops.add(new Shop(ShopEnum.pierresGeneralStore, PierreStoreItems.getItems(this.getDateTime().getSeason().getID())));
    }

    private void buildNPC(){
        NPCDirector director = new NPCDirector();
        gameNPCs.add(director.constructAbigail(new NPCBuilder()));
        gameNPCs.add(director.constructHarvey(new NPCBuilder()));
        gameNPCs.add(director.constructLia(new NPCBuilder()));
        gameNPCs.add(director.constructRobin(new NPCBuilder()));
        gameNPCs.add(director.constructSebastian(new NPCBuilder()));
    }

    Game() {
        dateTime = new DateTime();
        this.buildShops();
        this.buildNPC();
        thirdQuest = false;
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
    public NPC getNPC(String name){
        for (NPC gameNPC : gameNPCs) {
            if (gameNPC.getName().equalsIgnoreCase(name)){
                return gameNPC;
            }
        }
        return null;
    }

    public ArrayList<NPC> getGameNPCs() {
        return gameNPCs;
    }
    public void activeThirdQuest(){
        this.thirdQuest = true;
    }

    public boolean isThirdQuest() {
        return thirdQuest;
    }
    public NPC getQuestOwner(Quest quest){
        for (NPC gameNPC : gameNPCs) {
            for (Quest npcQuest : gameNPC.getQuests()) {
                if (npcQuest.equals(quest))
                    return gameNPC;
            }
        }
        return null;
    }
}
