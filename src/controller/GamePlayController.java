package controller;

import model.App;
import model.Game;
import model.Result;
import model.Stores.Shop;
import model.Stores.ShopItem;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Crop;
import model.*;
import model.enums.Season;
import model.enums.Weather;

import javax.print.attribute.standard.JobKOctets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GamePlayController extends MenuController{
    @Override
    public Result exit() {
        return new Result(Map.of("message", "you should go to signup/login menu first"));
    }
    @Override
    public Result enterMenu() {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }

    public Result showCurrentMenu() {
        return new Result(Map.of("message", "current menu is: Game Play"));
    }

    public Result exitGame(){
        return null;
    }

    public Result nextTurn(){
        return null;
    }
    public Result getTime() {
        Game game = App.getCurrentGame();
        return new Result(Map.of("message", game.getDateTime().getTime() + "o clock"));
    }
    public Result getDate(){
        Game game = App.getCurrentGame();
        return new Result(Map.of("message", game.getDateTime().getDate()));
    }
    public Result getDateTime(){
        Game game = App.getCurrentGame();
        return new Result(Map.of("message", game.getDateTime().getTime() + "o clock - " + game.getDateTime().getDate()));
    }
    public Result getDayOfTheWeek(){
        Game game = App.getCurrentGame();
        return new Result(Map.of("message", game.getDateTime().getDayOfWeek()));
    }
    public Result cheatTime(String time){
        int intTime = Integer.valueOf(time);
        Game game = App.getCurrentGame();
        int newClock = intTime + game.getDateTime().getTime();
        if (newClock >= 24 || intTime < 0) return new Result(Map.of("message", "invalid time given"));
        game.setClock(newClock);
        return new Result(Map.of("message", "time changed successfully"));
    }
    public Result cheatDate(String date){
        int intDate = Integer.valueOf(date);
        if (intDate < 0) return new Result(Map.of("message", "invalid day given"));
        while (intDate > 0) {
            App.getCurrentGame().nextDay();
            intDate--;
        }
        return makeResult("date updated successfully");
    }
    public Result getSeason(){
        return makeResult("current season: " + App.getCurrentGame().getDateTime().getSeason().toString());
    }
    public Result getWeather() {
        Game game = App.getCurrentGame();
        Map<String, Object> data = new HashMap<>();
        data.put("message", game.weather.name());
        return new Result(data);
    }
    public Result predictWeather(){
        ArrayList<Weather> weathers = new ArrayList<>();
        Game game = App.getCurrentGame();
        if(game.getDateTime().getSeason().equals(Season.WINTER)){
            weathers.add(Weather.Snow);
        }
        else{
            weathers.add(Weather.Rain);
            weathers.add(Weather.Storm);
        }
        weathers.add(Weather.Sunny);
        Map<String, Object> data = new HashMap<>();
        data.put("weathers", weathers);
        return new Result(data);
    }
    public Result thor(HashMap<String, String> args){
        //TODO  kishkishhhhkishkish
        return null;
    }
    public Result cheatWeatherSet(HashMap<String, String> args){
        Map<String, Object> data = new HashMap<>();
        Weather weather = Weather.getWeather(args.get("weather"));
        Game game = App.getCurrentGame();
        if(weather == null){
            data.put("message" , "invalid weather");
        }
        else {
            data.put("message", "done");
            game.setNextDayWeather(weather);
        }
        return new Result(data);
    }
    public Result enterNextDay(HashMap<String, String> args){
        return null;
    }
    public Result showEnergy(){
        Player player = App.getAdmin(); //TODO fix player
        Map<String, Object> data = new HashMap<>();
        data.put("message", player.energy);
        return new Result(data);
    }
    public Result cheatSetEnergy(HashMap<String, String> args){
        Player player = App.getAdmin(); //TODO
        Integer value = Integer.parseInt(args.get("vaue"));
        Map<String, Object> data = new HashMap<>();
        data.put("message","energy set successfully");
        player.energy = value;
        return new Result(data);
    }
    public Result cheatInfiniteEnergy(){
        Player player = App.getAdmin(); //TODO;
        player.energy = 1000000000; //TODO  check max value
        player.unlimitedEnergy = true;
        Map<String, Object> data = new HashMap<>();
        data.put("message", "energy is now infinite");
        return new Result(data);
    }
    public Result collapse(){
        return null;
    }
    public Result equipTool(HashMap<String, String> args){
        return null;
    }
    public Result showCurrentTool(){
        return null;
    }
    public Result showAvailable(){
        return null;
    }
    public Result upgradeTool(HashMap<String, String> args){
        return null;
    }
    public Result useTool(HashMap<String, String> args){
        return null;
    }



    public Result buildGreenhouse() {
        return null;
    }
    public Result showInventory() {
        Player player = App.getAdmin();  //TODO
        ArrayList<Item> items = player.getBackpack().getItems();
        Map<String , Object> data = new HashMap<>();
        data.put("items", items);
        return new Result(data);
    }
    public Result removeFromInventory(HashMap<String, String> args) {

        return null;
    }

    public Result craftInfo(String name) {
        for (Crop crop : Crop.values()) {
            if (crop.getName().equals(name)) {
                return new Result(Map.of("message", crop.toString()));
            }
        }
        return null;
    }

    public Result plant(HashMap<String, String> args) {
        
        return null;
    }

    public Result showPlant(int x, int y) {
        return null;
    }

    public Result fertilize(HashMap<String, String> args) {
        return null;
    }

    public Result showWaterRemaining() {
        return null;
    }


    public Result showRecipes() {
        return null;
    }

    public Result craft(String itemName) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        CraftableItem craftableItem = player.CanCraft(itemName);
        Map<String , Object> data = new HashMap<>();
        if(craftableItem == null){
            data.put("flg", false);
            data.put("message", "you can't Craft this");
            return new Result(data);
        }
        if(player.getBackpack().isFull()){
            data.put("flg" , false);
            data.put("message", "your backpack is full!");
            return new Result(data);
        }
        boolean contain = true;
        Map<String, Integer> ingredients = craftableItem.getIngredients();
        for (String s : ingredients.keySet()) {
            if(player.getBackpack().contain(s) < ingredients.get(s)){
                contain = false;
            }
        }
        if(!contain){
            data.put("flg", false);
            data.put("message", "you don't have items you need!");
            return new Result(data);
        }
        if(player.energy < 2){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        CraftedItem craftedItem = new CraftedItem(craftableItem);
        for (String s : ingredients.keySet()) {
            Item item = player.getBackpack().getItem(s);
            player.getBackpack().removeItem(item, ingredients.get(s));
        }
        player.getBackpack().putItem(craftedItem, 1);
        data.put("flg", true);
        data.put("message", "item crafted successfully");
        player.decreaseEnergy(2);
        return new Result(data);
    }

    public Result placeItem(HashMap<String, String> args) {
        return null;
    }

    public Result cheatAddItem(String itemName, int number) {
        //TODO check all items!!
        Item item = null;
        for (CraftableItem value : CraftableItem.values()) {
            if(value.getName().equalsIgnoreCase(itemName)){
                item = (Item) new CraftedItem(value);
            }
        }
        Map<String, Object> data = new HashMap<>();
        if(item == null){
            data.put("flg" , false);
            data.put("message", "item doesn't exist");
            return new Result(data);
        }
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(player.getBackpack().contain(item) == -1 && player.getBackpack().isFull()){
            data.put("flg" , false);
            data.put("message", "your backpack is full");
            return new Result(data);
        }
        player.getBackpack().putItem(item, number);
        data.put("flg" , true);
        data.put("message", "item added to inventory!");
        return new Result(data);
    }

    public Result cookingRefigerator(HashMap<String, String> args) {
        return null;
    }

    public Result cookingShowRecipes() {
        return null;
    }
    public Result cookingPrepair(HashMap<String, String> args) {
        return null;
    }

    public Result eat(HashMap<String, String> args) {
        return null;
    }
    public Result buildBuilding(HashMap<String, String> args) {
        return null;
    }

    public Result buyAnimal(HashMap<String, String> args) {
        return null;
    }

    public Result pet(HashMap<String, String> args) {
        return null;
    }

    public Result cheatSetFriendship(HashMap<String, String> args) {
        return null;
    }

    public Result showAnimals(HashMap<String, String> args) {
        return null;
    }
    public Result shepherdAnimals(HashMap<String, String> args) {
        return null;
    }
    
    public Result feedHay(HashMap<String, String> args) {
        return null;
    }
    
    public Result showProduces(HashMap<String, String> args) {
        return null;
    }
    public Result collectProduct(HashMap<String, String> args) {
        return null;
    }

    public Result fishing(HashMap<String, String> args) {
        return null;
    }

    public Result useArtisan(HashMap<String, String> args) {
        return null;
    }
    
    public Result getArtisan(HashMap<String, String> args) {
        return null;
    }
    public Result showAllProducts(HashMap<String, String> args) {
        Shop shop = App.getCurrentGame().getShop(args.get("shop"));
        Map<String , Object> data = new HashMap<>();
        if(shop == null){
            data.put("flg" , false);
            data.put("message" , "what the Shop?!");
            return new Result(data);
        }
        ArrayList<ShopItem> items = shop.getItems();
        data.put("flg", true);
        data.put("items", items);
        return new Result(data);
    }
    
    public Result showAllAvailableProduct(HashMap<String, String> args) {
        Shop shop = App.getCurrentGame().getShop(args.get("shop"));
        Map<String , Object> data = new HashMap<>();
        if(shop == null){
            data.put("flg" , false);
            data.put("message" , "what the Shop?!");
            return new Result(data);
        }
        ArrayList<ShopItem> items = shop.getItems();
        ArrayList<ShopItem> availableItems = new ArrayList<>();
        for (ShopItem item : items) {
            if(item.getDailyLimit() != 0){
                availableItems.add(item);
            }
        }
        data.put("flg", true);
        data.put("items", availableItems);
        return new Result(data);
    }
    
    public Result purchase(HashMap<String, String> args) {
        return null;
    }

    public Result cheatAddMoney(HashMap<String , String> args){
        int x = Integer.parseInt(args.get("count"));
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.money += x;
        Map<String, Object> data = new HashMap<>();
        data.put("flg" , true);
        data.put("message" , "money added successfully");
        return new Result(data);
    }
    public Result cheatAddProduct(HashMap<String, String> args) {
        return null;
    }
    public Result sell(HashMap<String, String> args) {
        return null;
    }

    public Result talk(HashMap<String, String> args) {
        return null;
    }
    
    public Result talkHistory(HashMap<String, String> args) {
        return null;
    }
    
    public Result gift(HashMap<String, String> args) {
        return null;
    }
    
    public Result getGiftList() {
        return null;
    }
    
    public Result rateGift(HashMap<String, String> args) {
        return null;
    }
    
    public Result getGiftHistory(HashMap<String, String> args) {
        return null;
    }
    
    public Result hug(HashMap<String, String> args) {
        return null;
    }
    
    public Result giveFlower(HashMap<String, String> args) {
        return null;
    }
    
    public Result askMarriage(HashMap<String, String> args) {
        return null;
    }
    
    public Result respondMarriageAsk(HashMap<String, String> args) {
        return null;
    }
    public Result startTrade() {
        return null;
    }
    
    public Result trade(HashMap<String, String> args) {
        return null;
    }
    
    public Result getTradeList() {
        return null;
    }
    public Result tradeResponse(HashMap<String, String> args) {
        return null;
    }

    public Result getTradeHistory() {
        return null;
    }

}
