package controller;

import model.NPC.NPC;
import model.NPC.Quest;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Crop;
import model.*;
import model.Animals.Animal;
import model.Animals.AnimalHome;
import model.Animals.AnimalProduct;
import model.Animals.AnimalStrategy.AnimalStrategy;
import model.Animals.AnimalStrategy.ChickenStrategy;
import model.Animals.AnimalStrategy.CowStrategy;
import model.Animals.AnimalStrategy.DinosaurStrategy;
import model.Animals.AnimalStrategy.DuckStrategy;
import model.Animals.AnimalStrategy.GoatStrategy;
import model.Animals.AnimalStrategy.PigStrategy;
import model.Animals.AnimalStrategy.RabbitStrategy;
import model.Animals.AnimalStrategy.SheepStrategy;
import model.enums.Recipe;
import model.Stores.Shop;
import model.Stores.ShopItem;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Crop;
import model.*;
import model.enums.Season;
import model.enums.Weather;
import model.enums.AnimalEnum.AnimalType;

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
        String name = args.get("name");
        Map<String , Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        Recipe recipe = player.canCook(name);
        if(recipe == null){
            data.put("flg" , false);
            data.put("message", "you can't cook this");
            return new Result(data);
        }
        Map<String , Integer> ingredients = recipe.getIngredients();
        boolean contain = true;
        for (String s : ingredients.keySet()) {
            int x = player.getRefrigerator().contain(s) + player.getBackpack().contain(s);
            if(x < ingredients.get(s))
                contain = false;
        }
        if(!contain){
            data.put("flg", false);
            data.put("message", "you don't have required items!");
            return new Result(data);
        }
        if(player.getEnergy() < 3){
            data.put("flg" , false);
            data.put("message", "not enough energy");
            return new Result(data);
        }
        if(player.getBackpack().isFull()){
            data.put("flg" , false);
            data.put("message", "backpack is full");
            return new Result(data);
        }
        player.decreaseEnergy(3);
        for (String s : ingredients.keySet()) {
            int x = ingredients.get(s);
            if(player.getRefrigerator().contain(s) < x)
            {
                x = player.getRefrigerator().contain(s);
            }
            if(x != 0)
                player.getRefrigerator().removeItem(player.getRefrigerator().getItem(s) , x);
            if(ingredients.get(s) - x > 0){
                player.getBackpack().removeItem(player.getBackpack().getItem(s) , ingredients.get(s) - x);
            }
        }
        Eatable newFood = new Eatable(recipe);
        player.getBackpack().putItem(newFood, 1);
        data.put("flg" , true);
        data.put("message", "item cooked successfully");
        return new Result(data);
    }

    public Result eat(HashMap<String, String> args) {
        return null;
    }
    public Result buildBuilding(String name, String strX, String strY) {
        // TODO  MAP
        return null;
    }

    public Result buyAnimal(String animalName, String name) {
        // TODO decrease money

        if (App.getCurrentGame().getDateTime().getTime() > 4) return new Result(Map.of("message", "Marnie's store is closed"));
        AnimalType animalType = null;
        for (AnimalType type : AnimalType.values()) {
            if (type.getName().equals(animalName)) {
                animalType = type;
            }
        }
        if (animalType == null) {
            return new Result(Map.of("message", "invalid name given"));
        }
        if (App.getCurrentGame().getCurrentPlayer().getAnimalsBoughtToday().get(animalType) == 2) {
            return new Result(Map.of("message", "you can't buy more than 2 animals of the same type"));
        }
        Animal animal = new Animal(name, animalType);
        // TODO
        AnimalStrategy strategy = null;
        switch (animalName) {
            case "chicken":
                strategy = new ChickenStrategy();
                break;
            case "dinosaur":
                strategy = new DinosaurStrategy();
                break;
            case "duck":
                strategy = new DuckStrategy();
                break;
            case "rabbit":
                strategy = new RabbitStrategy();
                break;
            case "cow":
                strategy = new CowStrategy();
                break;
            case "sheep":
                strategy = new SheepStrategy();
                break;
            case "goat":
                strategy = new GoatStrategy();
                break;
            case "pig":
                strategy = new PigStrategy();
                break;
            
        }
        animal.setStrategy(strategy);

        AnimalHome home = null;

        for (AnimalHome availableHome : App.getCurrentGame().getCurrentPlayer().getMapFarm().getAnimlaHomes()) {
            if (availableHome.getRemainingCapacity() > 0 && animalCanGoToHome(animal, availableHome)) {
                home = availableHome;
                break;
            }
        }
        if (home == null) {
            return new Result(Map.of("message", "no available home for this animal"));
        }


        App.getCurrentGame().getCurrentPlayer().addAnimal(animal);
        home.addAnimal(animal);
        App.getCurrentGame().getCurrentPlayer().spendMoney(animal.getPrice());
        return new Result(Map.of("message", "animal created successfully"));
    }

    private boolean animalCanGoToHome(Animal animal, AnimalHome home) {
        return animal.getType().canGoTo(home.getHomeType());
    }
    private Animal findAnimalByName(String name) {
        for (AnimalHome animalHome : App.getCurrentGame().getCurrentPlayer().getMapFarm().getAnimlaHomes()) {
            for (Animal animal : animalHome.getAnimals()) {
                if (animal.getName().equals(name)) {
                    return animal;
                }
            }
        }
        return null;
    }

    public Result pet(String name) {
        // TODO Map

        Animal animal = findAnimalByName(name);
        if (animal == null) return new Result(Map.of("message", "no animal with given name exist"));

        animal.pet();
        return new Result(Map.of("message", "animal petted successfully"));
    }

    public Result cheatSetFriendship(String name, String amount) {
        // TODO
        Animal animal = findAnimalByName(name);
        if (animal == null) return new Result(Map.of("message", "no animal with given name exist"));

        // finding the animal is to do

        int val = Integer.valueOf(amount);
        animal.setFriendship(Integer.valueOf(amount));
    }

    public Result showAnimals() {
        String message = "";
        // TODO map
        for (Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            message += animal.toString() + "\n";
        }
        return new Result(Map.of("message", message));
    }
    public Result moveAnimal(String name, String xStr, String yStr) {
        int x = Integer.valueOf(xStr), y = Integer.valueOf(yStr);

        Animal animal = findAnimalByName(name);
        if (animal == null) return new Result(Map.of("message", "no animal with given name exist"));
        // TODO check if the given (x,y) exist in the map
        // TODO check if ther's a path
        // TODO set an object in the given position
        

        return null;
    }
    
    public Result feedHay(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) return new Result(Map.of("message", "no animal with given name exist"));
        animal.feed();
        return new Result(Map.of("message", "animal fed successfully"));
    }
    
    public Result showProduces() {
        String message = "";
        for (AnimalHome home : App.getCurrentGame().getCurrentPlayer().getMapFarm().getAnimlaHomes()) {
            for (Animal animal : home.getAnimals()) {
                message += animal.getName() + ":\n";
                for (AnimalProduct product : animal.getProducts()) {
                    message += product.toString() + "\n";
                }
            }
        }
        return new Result(Map.of("message", message));
    }
    public Result collectProduce(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) return new Result(Map.of("message", "no animal with given name exist"));
        animal.collectProduct();
        return new Result(Map.of("message", "animal product collected successfully"));
    }
    public Result sellAnimal(String name) {
        Animal animal = findAnimalByName(name);
        if (animal == null) return new Result(Map.of("message", "no animal with given name exist"));
        // TODO
        double price = animal.getPrice();
        App.getCurrentGame().getCurrentPlayer().removeAnimal(animal);
        return new Result(Map.of("message", "animal sold successfully"));
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
        Shop shop = App.getCurrentGame().getShop(args.get("shop"));
        Map<String , Object> data = new HashMap<>();
        if(shop == null){
            data.put("flg" , false);
            data.put("message" , "what the Shop?!");
            return new Result(data);
        }
        ArrayList<ShopItem> items = shop.getItems();
        ShopItem currentItem = null;
        for (ShopItem item : items) {
            if(item.name.equalsIgnoreCase(args.get("name"))){
                currentItem = item;
            }
        }
        if(currentItem == null){
            data.put("flg" , false);
            data.put("message" , "this shop don't have this item");
            return new Result(data);
        }
        int cnt = Integer.parseInt(args.get("cnt"));
        if(currentItem.getDailyLimit() >= 0 && currentItem.getDailyLimit() < Integer.parseInt(args.get("cnt"))){
            data.put("flg", false);
            data.put("message" , "daily limit reached!");
            return new Result(data);
        }
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(player.money < cnt * currentItem.price){
            data.put("flg" , false);
            data.put("message", "not enough money");
            return new Result(data);
        }
        if(player.getBackpack().isFull() && player.getBackpack().contain(currentItem) != 0){
            data.put("flg", false);
            data.put("message", "inventory is full");
            return new Result(data);
        }
        currentItem.decreaseDailyLimit(Integer.parseInt(args.get("cnt")));
        player.decreaseMoney(cnt * currentItem.price);
        player.getBackpack().putItem(currentItem, cnt);
        data.put("flg" , true);
        data.put("message" , "item bought successfully");
        return new Result(data);
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
    public Result meetNpc(HashMap<String , String> args){
        NPC currentNpc = App.getCurrentGame().getNPC(args.get("NPC name"));
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map<String, Object> data = new HashMap<>();
        if(currentNpc == null){
            data.put("flg" , false);
            data.put("message" , "invalid NPC");
            return new Result(data);
        }
        //TODO check adj
        data.put("flg" , true);
        data.put("message" , currentNpc.talk());
        if(player.isFirstMeet(args.get("NPC name"))){
            player.addNpcFriendShip(args.get("NPC name") , 20);
        }
        return new Result(data);
    }
    public Result giftNpc(HashMap<String , String> args){
        NPC currentNPC = App.getCurrentGame().getNPC(args.get("NPC name"));
        Map<String , Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(currentNPC == null){
            data.put("flg" , false);
            data.put("message" , "invalid NPC");
            return new Result(data);
        }
        if(player.getBackpack().contain(args.get("item name")) == 0){
            data.put("flg" , false);
            data.put("message" , "you don't have this item");
            return new Result(data);
        }
        data.put("flg" , true);
        data.put("message" , "gift gifted successfully");
        if(currentNPC.isFavorite(args.get("item name"))){
            player.addNpcFriendShip(args.get("NPC name") , 200);
        }
        if(player.isFirstGiftNpc(args.get("NPC name"))){
            player.addNpcFriendShip(args.get("NPC name") , 50);
        }
        return new Result(data);
    }
    public Result friendShipNpc(){
        Map<String , Object> data = new HashMap<>();
        Map<String , Integer> friendships = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        for (NPC gameNPC : App.getCurrentGame().getGameNPCs()) {
            friendships.put(gameNPC.getName(), player.getNpcFriendship(gameNPC.getName()) / 200);
        }
        data.put("friendships", friendships);
        return new Result(data);
    }
    public Result questsList(){
        //TODO remove done quests;
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(App.getCurrentGame().getDateTime().getSeason() != Season.SPRING){
            App.getCurrentGame().activeThirdQuest();
        }
        Map<String , Object> data = new HashMap<>();
        ArrayList<Quest> quests = new ArrayList<>();
        for (NPC gameNPC : App.getCurrentGame().getGameNPCs()) {
            quests.add(gameNPC.getQuests().get(0));
            if(player.getNpcFriendship(gameNPC.getName()) >= 200){
                quests.add(gameNPC.getQuests().get(1));
            }
            if(App.getCurrentGame().isThirdQuest()){
                quests.add(gameNPC.getQuests().get(2));
            }
        }
        data.put("quest list" , quests);
        return new Result(data);
    }
    public Result finishQuest(HashMap<String , String> args){
        //TODO check adj;
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(App.getCurrentGame().getDateTime().getSeason() != Season.SPRING){
            App.getCurrentGame().activeThirdQuest();
        }
        Map<String , Object> data = new HashMap<>();
        ArrayList<Quest> quests = new ArrayList<>();
        for (NPC gameNPC : App.getCurrentGame().getGameNPCs()) {
            quests.add(gameNPC.getQuests().get(0));
            if(player.getNpcFriendship(gameNPC.getName()) >= 200){
                quests.add(gameNPC.getQuests().get(1));
            }
            if(App.getCurrentGame().isThirdQuest()){
                quests.add(gameNPC.getQuests().get(2));
            }
        }
        int id = Integer.parseInt(args.get("quest id"));
        if(id >= quests.size())
        {
            data.put("flg" , false);
            data.put("message" , "invalid quest id (note that quests are 0 base)");
            return new Result(data);
        }
        if(!quests.get(id).canDoQuest(player)) {
            data.put("flg", false);
            data.put("message", "you don't have required items");
            return new Result(data);
        }
        Quest nowQuest = quests.get(id);
        if(!nowQuest.checkBackpack(player)){
            data.put("flg" , false);
            data.put("message" , "your backpack is full");
            return new Result(data);
        }
        NPC owner = App.getCurrentGame().getQuestOwner(nowQuest);
        nowQuest.doQuest(player, owner);
        data.put("flg" , true);
        data.put("message" , "quest done!");
        return new Result(data);
    }
}
