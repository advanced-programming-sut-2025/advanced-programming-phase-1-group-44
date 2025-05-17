package model;

import model.NPC.NPC;
import model.NPC.NPCBuilder;
import model.NPC.NPCDirector;
import model.NPC.Quest;
import model.Stores.Shop;
import model.enums.Season;
import model.enums.ShopEnum;
import model.enums.StoreItems.*;
import model.enums.Weather;
import model.enums.AnimalEnum.AnimalHomeType;
import model.Animals.Animal;
import model.Animals.AnimalHome;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class Game {
    private final int[] friendshipMAX = {100, 200, 300, 400};
    public DateTime dateTime;
    private ArrayList<Player> users , loggedInUsers;
    private int countuser=0;
    public Weather weather , nextDayWeather;
    private Boolean fixedWeather;
    private Player currentPlayer;
    private Player admin=null;
    private ArrayList<Shop> shops = new ArrayList<>();
    private ArrayList<Trade> trades = new ArrayList<>();
    private HashSet<AnimalHomeType> buildingBuiltToday = new HashSet<>();

    public void setAdmin(Player admin) {
        this.admin = admin;
    }

    public Player getAdmin() {
        return admin;
    }

    public void setCountuser(int countuser) {
        this.countuser = countuser;
    }

    public int getCountuser() {
        return countuser;
    }

    private ArrayList<AnimalHome> animalHomes = new ArrayList<>();

    public void setUsers(ArrayList<Player> users) {
        this.users = users;
    }

    public ArrayList<Player> getUsers() {
        return users;
    }

    public ArrayList<AnimalHome> getAnimalHomes() {
        return animalHomes;
    }

    public Boolean getFixedWeather() {
        return fixedWeather;
    }

    public void setAnimalHomes(ArrayList<AnimalHome> animalHomes) {
        this.animalHomes = animalHomes;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
    private int[][] friendship = new int[4][4];
    private int[][] friendshipLevel = new int[4][4];
    private boolean[][] hadCommunication = new boolean[4][4];
    private ArrayList<Message>[] talkHistory = new ArrayList[4];
    private boolean[][] married = new boolean[4][4];


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
        for (int i = 0; i < 4; i++) talkHistory[i] = new ArrayList<>();
        this.currentPlayer = App.getAdmin();
        weather = Weather.Sunny;
    }

    public void marry(Player reciever, Player sender) {
        MarriageRequest marReq = reciever.getMarriageRequest(sender);

        Item ring = sender.getBackpack().getItem(marReq.getRing());
        sender.getBackpack().removeItem(ring, 1);
        reciever.getBackpack().putItem(ring, 1);
        int i = getId(reciever), j = getId(sender);
        married[i][j] = true;
        married[j][i] = true;
    }
    public boolean BuildingBuiltToday(AnimalHomeType home) {
        return buildingBuiltToday.contains(home);
    }

    public String showFriendships() {
        int i = getId(currentPlayer);
        String out = "";
        for (Player user : users) {
            if (user == currentPlayer) continue;
            out += user.getName() + " " + ", level: " + friendshipLevel[i][getId(user)] + ", friendship: " + friendship[i][getId(user)] + '\n';
        }
        return out;
    }

    public void rejectMarriageRequest(Player reciever, Player sender) {
        MarriageRequest marReq = reciever.getMarriageRequest(sender);
        int i = getId(reciever), j = getId(sender);
        friendship[i][j] = 0;
        friendship[j][i] =0 ;
        friendshipLevel[i][j] = 0;
        friendshipLevel[j][i] = 0;
        // TODO
        // half the energy
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
        if (friendship[i][j] > friendshipMAX[friendshipLevel[i][j]] && friendshipLevel[i][j] != 2) {
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
        updateFriendship(i, j, married[i][j] ? 20 : 50);

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
        this.buildNPC();
        thirdQuest = false;
    }

    public void addAnimalHome(AnimalHomeType type) {
        animalHomes.add(new AnimalHome(type));

    }

    public Weather getWeather() {
        return weather;
    }

    public void buildHome(AnimalHome home, AnimalHomeType type) {
        animalHomes.add(home);
        buildingBuiltToday.add(type);
    }



    public DateTime getDateTime() {
        return dateTime;
    }

    public void setClock(int clock) {
        dateTime.setClock(clock);
    }

    public void nextDay() {
        buildingBuiltToday.clear();;

        // TODO

                // Animals:
        // if(fixedWeather){
        //     weather = nextDayWeather;
        // }
        // else {
        //     ArrayList<Weather> weathers = new ArrayList<>();
        //     if(this.getDateTime().getSeason().equals(Season.WINTER)){
        //         weathers.add(Weather.Snow);
        //     }
        //     else{
        //         weathers.add(Weather.Rain);
        //         weathers.add(Weather.Storm);
        //     }
        //     weathers.add(Weather.Sunny);
        //     Random random = new Random();
        //     int randomInt = random.nextInt(weathers.size());
        //     weather = weathers.get(randomInt);
        // }
        // fixedWeather = false;

        for (AnimalHome home : animalHomes) {
            for (Animal animal : home.getAnimals()) {
                animal.nextDay();
            }
        }
        for (int i = 0; i < users.size(); i++) {
            Player player = users.get(i);
            player.unlimitedEnergy = false;
            if(player.isCollapsed){
                player.energy = 150;
            }
            else{
                player.energy = 200;
            }
            player.isCollapsed = false;
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
        dateTime.nextDay();

    }

    public boolean giveFlower(Player p1, Player p2) {
        // TODO   flower item???
        if (p1.getBackpack().contain("flower") == 0) return false;
        Item flower = p1.getBackpack().getItem("flower");
        p1.getBackpack().removeItem(flower);
        p2.getBackpack().putItem(flower, 1);

        int i = getId(p1);
        int j = getId(p2);

        if (friendshipLevel[i][j] == 2 && friendship[i][j] > friendshipMAX[2]) {
            friendship[i][j] = 0;
            friendship[j][i] = 0;
            friendshipLevel[i][j]++;
            friendshipLevel[j][i]++;
        }

    }

    public void hug(Player p1, Player p2) {
        int i = getId(p1);
        int j = getId(p1);

        updateFriendship(i, j, 60);
    }

    public int getFriendshipLevel(Player p1, Player p2) {
        int i = getId(p1);
        int j = getId(p2);
        return friendshipLevel[i][j];
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

    public ArrayList<Trade> getTrades() {
        return trades;
    }
    public void addTrade(Trade trade){
        trades.add(trade);
    }
}
