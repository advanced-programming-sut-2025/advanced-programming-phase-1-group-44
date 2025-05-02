package controller;

import model.App;
import model.Game;
import model.Result;
import model.enums.Crop;
import model.*;

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
    public Result getWeather(){
        return null;
    }
    public Result predictWeather(){
        return null;
    }
    public Result cheatThor(HashMap<String, String> args){
        return null;
    }
    public Result cheatWeatherSet(HashMap<String, String> args){
        return null;
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
        return null;
    }

    public Result placeItem(HashMap<String, String> args) {
        return null;
    }

    public Result cheatAddItem(String itemName, int number) {
        return null;
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
        return null;
    }
    
    public Result showAllAvailableProduct(HashMap<String, String> args) {
        return null;
    }
    
    public Result purchase(HashMap<String, String> args) {
        return null;
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
