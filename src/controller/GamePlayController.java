package controller;

import model.NPC.NPC;
import model.NPC.Quest;
import model.enums.*;
import model.enums.CraftingItems.CraftableItem;
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
import model.Stores.Shop;
import model.Stores.ShopItem;
import model.enums.AnimalEnum.AnimalHomeType;
import model.enums.AnimalEnum.AnimalType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamePlayController extends MenuController{
    private MapController mapController = new MapController();
    @Override
    public Result exit() {
        return new Result(Map.of("message", "you should go to signup/login menu first"));
    }
    @Override
    public Result enterMenu(String menuName) {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }

    public Result showCurrentMenu() {
        return new Result(Map.of("message", "current menu is: Game Play"));
    }

    public Result exitGame(){
        return null;
    }

    public Result nextTurn() {
        try {
            Player pl = App.getCurrentGame().getCurrentPlayer();
            int i = 0;
            ArrayList<Player> pls = App.getCurrentGame().getUsers();
            int co=App.getCurrentGame().getCountuser();
            if (pls.isEmpty()) {
                return new Result(Map.of("message", "oh no nobody is here"));
            }
            for (; i < co; i++) {
                if (pls.get(i) == pl) {
                    break;
                }
            }
            i++;
            if (i >= co) App.getCurrentGame().getDateTime().nextHour();
            i %= co;
            App.getCurrentGame().setCurrentPlayer(pls.get(i));
            pl.checkBuff();
            return new Result(Map.of("message", "haha next turn is done!"));
        } catch (Exception e) {
            return new Result(Map.of("message", "oh no nobody is here"));
        }
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
    public Result enterNextDay(){
        return null;
    }
    public Result showEnergy(){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map<String, Object> data = new HashMap<>();
        data.put("message", player.energy);
        return new Result(data);
    }
    public Result cheatSetEnergy(HashMap<String, String> args){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Integer value = Integer.parseInt(args.get("value"));
        Map<String, Object> data = new HashMap<>();
        data.put("message","energy set successfully");
        player.energy = value;
        return new Result(data);
    }
    public Result cheatInfiniteEnergy(){
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.energy = 2000000000;
        player.unlimitedEnergy = true;
        Map<String, Object> data = new HashMap<>();
        data.put("message", "energy is now infinite");
        return new Result(data);
    }
    public void collapse(){
        Player player = App.getCurrentGame().getCurrentPlayer();
        player.collapse();
        Map<String, Object> data = new HashMap<>();
    }
    public Result equipTool(HashMap<String, String> args){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map<String , Object> data = new HashMap<>();
        Item item = player.getBackpack().getItem(args.get("name"));
        if(item == null){
            data.put("flg" , false);
            data.put("message" , "you don't have this tool");
            return new Result(data);
        }
        if(!(item instanceof Tool)){
            data.put("flg" , false);
            data.put("message" , "this item is not a tool!!");
            return new Result(data);
        }
        player.currentTool = (Tool) item;
        data.put("flg" , true);
        data.put("message" , "tool equipped successfully!");
        return new Result(data);
    }
    public Result showCurrentTool(){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map<String , Object> data = new HashMap<>();
        if(player.currentTool == null){
            data.put("message" , "your hand is empty!");
            return new Result(data);
        }
        data.put("message" , player.currentTool.name);
        return new Result(data);
    }
    public Result showAvailableTools(){
        ArrayList<Tool> tools = new ArrayList<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        ArrayList<Item> items = player.getBackpack().getItems();
        for (Item item : items) {
            if(item instanceof Tool){
                tools.add((Tool) item);
            }
        }
        Map<String , Object> data = new HashMap<>();
        data.put("tools" , tools);
        return new Result(data);
    }
    public Result upgradeTool(HashMap<String, String> args){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Item item = player.getBackpack().getItem(args.get("name"));
        Map<String , Object> data = new HashMap<>();
        if(item == null){
            data.put("flg" , false);
            data.put("message" , "you don't have this item");
            return new Result(data);
        }
        if(!(item instanceof Tool)){
            data.put("flg", false);
            data.put("message", "this item is not a tool!");
            return new Result(data);
        }
        Tool tool = (Tool) item;
        if(tool.level == tool.getMaxLevel()){
            data.put("flg" , false);
            data.put("message", "tool reached final level!!");
            return new Result(data);
        }
        if(tool.tooltype.equals(Tooltype.backpack)){
            //TODO check to be in Pierre
            //TODO
            return null;
        }
        else{
            //TODO  check to be in ahangari
            Material nextMaterial = Material.getMaterial(tool.level + 1);
            Shop shop = App.getCurrentGame().getShop("blacksmith");
            ShopItem shopItem = shop.getItem(nextMaterial.name() + " tool");
            int price = shopItem.price;
            if(player.money < price){
                data.put("flg" , false);
                data.put("message" , "you don't have enough money");
                return new Result(data);
            }
            if(shopItem.getDailyLimit() == 0){
                data.put("flg" , false);
                data.put("message", "daily limit reached!");
                return new Result(data);
            }
            player.money -= price;
            tool.upgrade();
            shopItem.decreaseDailyLimit(1);
            data.put("flg" , true);
            data.put("message" , "tool upgraded successfully");
            return new Result(data);
        }
    }
    public Result useTool(HashMap<String, String> args){
        Player player = App.getCurrentGame().getCurrentPlayer();
        int x = player.getXlocation(), y = player.getYlocation();
        String direction = args.get("direction");
        if(direction.startsWith("right")){
            x++;
        }
        else if(direction.startsWith("left")){
            x--;
        }
        if(direction.endsWith("up")){
            y--;
        }
        else if(direction.endsWith("down")){
            y++;
        }
        Map<String, Object> data = new HashMap<>();
        if(player.currentTool == null){
            data.put("flg" , false);
            data.put("message", "your hand is full of empty!");
            return new Result(data);
        }
        Result result = player.currentTool.action(x , y);
        return result;
    }

    public Result showInventory() {
        Player player = App.getCurrentGame().getCurrentPlayer();  //TODO
        ArrayList<Item> items = player.getBackpack().getItems();
        Map<String , Object> data = new HashMap<>();
        data.put("items", items);
        return new Result(data);
    }
    public Result disappearFromInventory(HashMap<String , String> args){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Item item = player.getBackpack().getItem(args.get("item"));
        Map<String , Object> data = new HashMap<>();
        if(item == null){
            data.put("flg" , false);
            data.put("message" , "you don't have this item");
            return new Result(data);
        }
        player.getBackpack().removeItem(item);
        data.put("flg" , true);
        data.put("message" , "item removed successfully");
        return new Result(data);
    }
    public Result removeFromInventory(HashMap<String, String> args) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Item item = player.getBackpack().getItem(args.get("item"));
        Map<String , Object> data = new HashMap<>();
        if(item == null){
            data.put("flg" , false);
            data.put("message" , "you don't have this item");
            return new Result(data);
        }
        int cnt = Integer.parseInt(args.get("cnt"));
        if(player.getBackpack().contain(item) < cnt){
            data.put("flg" , false);
            data.put("message" , "you don't have enough amount of this item");
            return new Result(data);
        }
        player.getBackpack().removeItem(item, cnt);
        data.put("flg" , true);
        data.put("message" , "item removed successfully");
        return new Result(data);
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

    public Result craft(HashMap<String, String> args) {
        String itemName = args.get("name");
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

    public Result allCraftableItems(){
        Map<String, Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        data.put("items", player.getCraftableItems());
        return new Result(data);
    }

    public Result placeItem(HashMap<String, String> args) {
        return null;
    }

    public Result cheatAddItem(HashMap<String , String> args) {
        String itemName = args.get("name");
        int number = Integer.parseInt(args.get("number"));
        //TODO check all items!!
        Item item = null;
        for (AllItems value : AllItems.values()) {
            if(value.getName().equalsIgnoreCase(itemName)){
                item = value.getItemByType();
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

    public Result cookingRefrigerator(HashMap<String, String> args) {
        Map<String, Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        if(args.get("type").equalsIgnoreCase("put")){
            if(player.getBackpack().contain(args.get("item")) == 0){
                data.put("flg" , false);
                data.put("message" , "you don't have this item in your backpack");
                return new Result(data);
            }
            Item item = player.getBackpack().getItem(args.get("item"));
            if(!(item instanceof Eatable)) {
                data.put("flg" , false);
                data.put("message" , "you can't put this in refrigerator");
                return new Result(data);
            }
            int cnt = player.getBackpack().contain(item);
            player.getBackpack().removeItem(item , cnt);
            player.getRefrigerator().putItem(item, cnt);
            data.put("flg" , true);
            data.put("message" , "item put in refrigerator successfully");
            return new Result(data);
        }
        else{
            if(!player.getRefrigerator().contain(args.get("item"))){
                data.put("flg" , false);
                data.put("message", "you don't have this item");
                return new Result(data);
            }
            if(player.getBackpack().isFull()){
                data.put("flg" , false);
                data.put("message" , "backpack is full!");
                return new Result(data);
            }
            Item item = player.getRefrigerator().getItem(args.get("item"));
            int cnt = player.getRefrigerator().pickItem(item);
            player.getBackpack().putItem(item , cnt);
            data.put("flg" , true);
            data.put("message" , "item put in backpack successfully!");
            return new Result(data);
        }
    }

    public Result cookingShowRecipes() {
        Player player = App.getCurrentGame().getCurrentPlayer();
        ArrayList<Recipe> recipes = player.getRecipes();
        Map<String , Object> data = new HashMap<>();
        data.put("recipes" , recipes);
        return new Result(data);
    }

    public Result cookingPrepare(HashMap<String, String> args) {
        String name = args.get("name");
        Map<String , Object> data = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        Recipe recipe = player.getRecipe(name);
        if(recipe == null){
            data.put("flg" , false);
            data.put("message", "you can't cook this");
            return new Result(data);
        }
        Map<String , Integer> ingredients = recipe.getIngredients();
        boolean contain = true;
        for (String s : ingredients.keySet()) {
            int x = player.getRefrigerator().getCnt(s) + player.getBackpack().contain(s);
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
            if(player.getRefrigerator().getCnt(s) < x)
            {
                x = player.getRefrigerator().getCnt(s);
            }
            if(x != 0)
                player.getRefrigerator().removeItem(player.getRefrigerator().getItem(s) , x);
            if(ingredients.get(s) - x > 0){
                player.getBackpack().removeItem(player.getBackpack().getItem(s) , ingredients.get(s) - x);
            }
        }
        Food newFood = new Food(recipe);
        player.getBackpack().putItem(newFood, 1);
        data.put("flg" , true);
        data.put("message", "item cooked successfully");
        return new Result(data);
    }

    public Result eat(HashMap<String, String> args) {
        Player player = App.getCurrentGame().getCurrentPlayer();
        Item item = player.getRefrigerator().getItem(args.get("name"));
        if(item == null){
            item = player.getBackpack().getItem(args.get("name"));
        }
        Map<String , Object> data = new HashMap<>();
        if(item == null){
            data.put("flg" , false);
            data.put("message", "you don't have this item");
            return new Result(data);
        }
        Recipe recipe = Recipe.getRecipe(item.name);
        if(recipe == null){
            data.put("flg" , false);
            data.put("message", "you can't put anything in your mouth!");
            return new Result(data);
        }
        data.put("flg" , true);
        data.put("message", "yam yam!");
        player.addEnergy(recipe.getEnergy());
        player.addBuff(recipe.getBuff(), recipe.getBuffTime());
        if(player.getRefrigerator().contain(item)){
            player.getRefrigerator().removeItem(item, 1);
        }
        else{
            player.getBackpack().removeItem(item , 1);
        }
        return new Result(data);
    }
    public Result buildBuilding(String name, String strX, String strY) {
        // TODO  MAP
        int x = Integer.valueOf(strX);
        int y = Integer.valueOf(strY);

        if (App.getCurrentGame().getDateTime().getTime() > 20) return new Result(Map.of("message", "Carpenter's shop is closed"));

        AnimalHomeType homeType = AnimalHomeType.getHomeTypeByName(name);

        if (homeType == null) return new Result(Map.of("message", "given name doesn't exist"));
        if (App.getCurrentGame().getCurrentPlayer().getMoney() < homeType.getPrice()) {
            // return new Result(Map.of("message", "not enough money"));
        }

        System.err.println(App.getCurrentGame().BuildingBuiltToday(homeType));
        if (App.getCurrentGame().BuildingBuiltToday(homeType)) return new Result(Map.of("message", "reached limit"));

        AnimalHome home = new AnimalHome(homeType);

        if (!mapController.buildbuilding(home, x, y)) return new Result(Map.of("message", "not enough space"));

        // TODO check there are enough materials
        App.getCurrentGame().buildHome(home, homeType);
        
        return new Result(Map.of("message", "building built successfully"));
    }

    public Result buyAnimal(String animalName, String name) {
        // TODO decrease money

        if (App.getCurrentGame().getDateTime().getTime() > 16) return new Result(Map.of("message", "Marnie's store is closed"));
        AnimalType animalType = null;
        for (AnimalType type : AnimalType.values()) {
            if (type.getName().equals(animalName)) {
                animalType = type;
            }
        }
        
        if (animalType == null) {
            return new Result(Map.of("message", "invalid name given"));
        }
        if (App.getCurrentGame().getCurrentPlayer().getAnimalsBoughtTodayByType(animalType) == 2) {
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


        animal.setHome(home);

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

        MapObj place = animal;

        if (animal.isHome()) place = animal.getHome();
        if (!mapController.Isadj(App.getCurrentGame().getCurrentPlayer().getXlocation(), App.getCurrentGame().getCurrentPlayer().getYlocation(), place)) {
            return new Result(Map.of("message", "you should be near the animal"));
        }

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
        return new Result(Map.of("message", "cheated!"));
    }

    public Result showAnimals() {
        String message = "";
        for (Animal animal : App.getCurrentGame().getCurrentPlayer().getAnimals()) {
            message += animal.toString() + "\n";
        }
        return new Result(Map.of("message", message));
    }
    public Result moveAnimal(String name, String xStr, String yStr) {
        // if (App.getCurrentGame().getWeather() != Weather.Sunny) return new Result(Map.of("message", "not sunny :("));
        int x = Integer.valueOf(xStr), y = Integer.valueOf(yStr);

        Animal animal = findAnimalByName(name);
        if (animal == null) return new Result(Map.of("message", "no animal with given name exist"));
        MapObj res = App.getCurrentGame().getCurrentPlayer().getMapFarm().GetCell(x, y);

        if (!animal.isHome()) mapController.removeObj(animal);
        if (res == animal.getHome()) {
            animal.moveInside();;
        }
        else if (res.getName().equals("Space")) {
            animal.moveOutSide();
            App.getCurrentGame().getCurrentPlayer().getMapFarm().setMapCell(x, y, animal);
            //TODO 0base??
        }

        else return new Result(Map.of("message", "can't move animal"));
    

        return new Result(Map.of("message", "animal moved!"));
    }
    
    public Result feedHay(String name) {
        // TODO yonje
        
        if (App.getCurrentGame().getCurrentPlayer().getBackpack().contain("hay") == 0) {
            return new Result(Map.of("message", "you don't have any hay"));
        }
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
        App.getCurrentGame().getCurrentPlayer().sellAnimal(animal);
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
    public Result goToShop(HashMap<String, String> args){
        Shop shop = App.getCurrentGame().getShop(args.get("name"));
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map<String , Object> data = new HashMap<>();
        if(shop == null){
            data.put("flg" , false);
            data.put("message", "what the shop?!");
            return new Result(data);
        }
        player.goToShop(shop);
        data.put("flg" , true);
        data.put("message", "you move to the shop successfully!");
        return new Result(data);
    }
    public Result showAllProducts() {
        Shop shop = App.getCurrentGame().getCurrentPlayer().getCurrentShop();
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
    
    public Result showAllAvailableProduct() {
        Shop shop = App.getCurrentGame().getCurrentPlayer().getCurrentShop();
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
        Shop shop = App.getCurrentGame().getCurrentPlayer().getCurrentShop();
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
        String name = args.get("name");
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
        if(currentItem.name.endsWith("Recipe")){
            player.decreaseMoney(cnt * currentItem.price);
            name = name.substring(0, name.length() - 7);
            System.out.println("just Debug : " + name + "!");
            player.addRecipe(Recipe.getRecipe(name));
            currentItem.decreaseDailyLimit(1);
            data.put("flg" , true);
            data.put("message", "recipe bought successfully!");
            return new Result(data);
        }
        if(player.getBackpack().isFull() && player.getBackpack().contain(currentItem) == 0){
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
        //TODO check adj,
        Player player = App.getCurrentGame().getCurrentPlayer();
        Item item = player.getBackpack().getItem(args.get("name"));
        Map<String, Object> data = new HashMap<>();
        if(item == null){
            data.put("flg" , false);
            data.put("message", "you don't have this item");
            return new Result(data);
        }
        int cnt;
        if(args.get("count") == null){
            cnt = player.getBackpack().contain(item);
        }
        else{
            cnt = Integer.parseInt(args.get("count"));
        }
        if(cnt > player.getBackpack().contain(item)){
            data.put("flg" , false);
            data.put("message", "you don't have enough item!");
            return new Result(data);
        }
        if(item.price == 0){
            data.put("flg" , false);
            data.put("message", "you can't sell this item!");
            return new Result(data);
        }
        int price = cnt * item.price * (4 + item.type.recycle()) / 4;
        player.addPaya(price);
        player.getBackpack().removeItem(item, cnt);
        data.put("flg", true);
        data.put("message", "item sold successfully!");
        return new Result(data);
    }

    public Result showFriendships() {
        return new Result(Map.of("message", App.getCurrentGame().showFriendships()));
    }

    public Result talk(String username, String message) {
        // check the two players are adjacent!
        Player player = App.getCurrentGame().findPlayerByUsername(username);
        if (player == null) return new Result(Map.of("message", "user with given username doesn't exist!"));
        App.getCurrentGame().talk(message, App.getCurrentGame().getCurrentPlayer(), player);
        return new Result(Map.of("message", "message sent!"));
    }
    
    public Result getTalkHistory(String username) {
        ArrayList<Message> history = App.getCurrentGame().getTalkHistoryByUsername(username);
        String message = "Your history with " + username + ": \n";
        for (Message mesg : history) {
            message += mesg.getMessage() + ",  ";
        }
        return new Result(Map.of("message", message));
    }

    // public Result cheatFriendship(String username, String amountStr) {
    //     int amount = Integer.valueOf(amountStr);
    //     Player player = App.getCurrentGame().findPlayerByUsername(username);
    //     if (player == null) return new Result(Map.of("message", "invalid username"));
    //     while (amount != 0) {
    //         App.getCurrentGame().addFriendship(player);
    //         amount--;
    //     }
    // }
    

    public Result gift(String username, String itemName, String amountStr) {
        int amount = Integer.valueOf(amountStr);
        Player player = App.getCurrentGame().findPlayerByUsername(username);
        if (player == null) return new Result(Map.of("message", "user with given username doesn't exist!"));
        Item item = App.getCurrentGame().getCurrentPlayer().getBackpack().getItem(itemName);
        System.out.println(item + " " + App.getCurrentGame().getCurrentPlayer().getBackpack().contain(itemName));
        boolean ok = App.getCurrentGame().getCurrentPlayer().sendGift(item, amount, player);

        if (ok == false) {
            return new Result(Map.of("message", "you don't have enough items"));
        }

        if (App.getCurrentGame().getFriendshipLevel(App.getCurrentGame().getCurrentPlayer(), player) < 1) {
            return new Result(Map.of("message", "you should have friendship level at least 1 with the player to send a gift"));
        }

        player.getGift(item, amount, App.getCurrentGame().getCurrentPlayer());
        return new Result(Map.of("message", "gift sent successfully"));
    }
    
    public Result showReceivedGiftList() {
        String message = "";
        for (Gift gift : App.getCurrentGame().getCurrentPlayer().getReceivedGiftList()) {
            message += gift.toString() + "\n";
        }
        return new Result(Map.of("message", message));
    }

    public Result showSentGiftList() {
        String message = "";
        for (Gift gift : App.getCurrentGame().getCurrentPlayer().getSentGiftList()) {
            message += gift.toString() + "\n";
        }
        return new Result(Map.of("message", message));
    }
    
    public Result rateGift(String giftIdStr, String rateStr) {
        int giftId = Integer.valueOf(giftIdStr);
        int rate = Integer.valueOf(rateStr);

        if (rate < 1 || rate > 5) {
            return new Result(Map.of("message", "invalid rate"));
        }
        Gift gift = App.getCurrentGame().getCurrentPlayer().getReceivedGiftList().get(giftId);
        if (gift == null) {
            return new Result(Map.of("message", "gift with given id doesn't exist"));
        }
        if (gift.isRated()) {
            return new Result(Map.of("message", "you have already rated this gift"));
        }
        int friendshipDelta = App.getCurrentGame().getCurrentPlayer().rateGift(giftId, rate);
        App.getCurrentGame().giftFriendship(gift.getSender(), gift.getReceiver(), friendshipDelta);
        return new Result(Map.of("message", "gift rated successfully"));
    }
    
    public Result getGiftHistory(String username) {
        Player player = App.getCurrentGame().findPlayerByUsername(username);
        if (player == null) return new Result(Map.of("message", "user with given username doesn't exist!"));
        String message = "Your history with " + username + ": \n";
        for (Gift gift : player.getSentGiftList()) {
            message += gift.toString() + "\n";
        }
        return new Result(Map.of("message", message));
    }
    
    public Result hug(String username) {
        Player player = App.getCurrentGame().findPlayerByUsername(username);
        if (player == null) return new Result(Map.of("message", "user with given username doesn't exist!"));
        // if (!mapController.Isadj(App.getCurrentGame().getCurrentPlayer().getXlocation(), App.getCurrentGame().getCurrentPlayer().getYlocation(), player))
        //     return new Result(Map.of("message", "you should be adjacent to the player"));
        if (App.getCurrentGame().getFriendshipLevel(App.getCurrentGame().getCurrentPlayer(), player) < 2)
            return new Result(Map.of("message", "you should have friendship level at least 2 with the player to hug"));
        App.getCurrentGame().hug(player, App.getCurrentGame().getCurrentPlayer());

        return new Result(Map.of("message", "hugged successfully"));
    }
    
    public Result giveFlower(String username) {
        Player player = App.getCurrentGame().findPlayerByUsername(username);
        System.out.println(username);
        if (player == null) return new Result(Map.of("message", "user with given username doesn't exist!"));

        
        boolean ok = App.getCurrentGame().giveFlower(App.getCurrentGame().getCurrentPlayer(), player);
        if (!ok) return new Result(Map.of("message", "you don't any flower"));

        return new Result(Map.of("message", "flower given"));
    }
    
    public Result askMarriage(String username, String ringName) {
        Player player = App.getCurrentGame().findPlayerByUsername(username);
        if (player == null) return new Result(Map.of("message", "user with given username doesn't exist!"));
        // check if the player has the ring
        if (App.getCurrentGame().getCurrentPlayer().getBackpack().contain(ringName) == 0) {
            return new Result(Map.of("message", "you don't have any ring"));
        }
        // TODO

        if (App.getCurrentGame().getCurrentPlayer().getGender() != Gender.MALE) {
            return new Result(Map.of("message", "you should be a male "));
        }
        if (player.getGender() == App.getCurrentGame().getCurrentPlayer().getGender()) {
            return new Result(Map.of("message", "are you okay?"));
        }
        if (App.getCurrentGame().getFriendshipLevel(App.getCurrentGame().getCurrentPlayer(), player) < 3) {
            return new Result(Map.of("message", "you should have friendship level at least 3 with the player to ask for marriage"));
        }

        player.addMarriageRequest(App.getCurrentGame().getCurrentPlayer(), ringName);

        return new Result(Map.of("message", "marriage request sent successfully"));
    }
    
    public Result respondProposal(String response, String username) {
        Player player = App.getCurrentGame().findPlayerByUsername(username);
        if (player == null) return new Result(Map.of("message", "user with given username doesn't exist!"));
        if (!App.getCurrentGame().getCurrentPlayer().hasMarriageRequest(player)) {
            return new Result(Map.of("message", "you don't have any marriage request from this player"));
        }

        if (response.equals("accept")) {
            App.getCurrentGame().marry(App.getCurrentGame().getCurrentPlayer(), player);
            return new Result(Map.of("message", "married successfully"));
        }
        if (response.equals("reject")) {
            App.getCurrentGame().rejectMarriageRequest(App.getCurrentGame().getCurrentPlayer(), player);
            return new Result(Map.of("message", "marriage request rejected"));
        }
        return null;
    }
    public Result startTrade() {
        App.enterMenu(Menu.TradeView);
        return new Result(Map.of("message", "you entered trade menu"));
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
        /*MapController mapController = new MapController();
        if(!mapController.Isadj(player.getXlocation(), player.getYlocation(), currentNpc)){
            data.put("flg" , false);
            data.put("message", "you are not close to this NPC");
            return new Result(data);
        }*/
        data.put("flg" , true);
        data.put("message" , currentNpc.talk());
        if(player.isFirstMeet(args.get("NPC name"))){
            player.addNpcFriendShip(currentNpc.getName(), 20);
        }
        player.meetNPC(currentNpc.getName());
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
        Item item = player.getBackpack().getItem(args.get("item name"));
        player.getBackpack().removeItem(item, 1);
        data.put("flg" , true);
        data.put("message" , "gift gifted successfully");
        if(currentNPC.isFavorite(args.get("item name"))){
            player.addNpcFriendShip(currentNPC.getName() , 200);
        }
        if(player.isFirstGiftNpc(currentNPC.getName())){
            player.addNpcFriendShip(currentNPC.getName() , 50);
        }
        player.giftNPC(currentNPC.getName());
        return new Result(data);
    }
    public Result friendShipNpc(){
        Map<String , Object> data = new HashMap<>();
        Map<String , Integer> friendships = new HashMap<>();
        Player player = App.getCurrentGame().getCurrentPlayer();
        for (NPC gameNPC : App.getCurrentGame().getGameNPCs()) {
            friendships.put(gameNPC.getName(), player.getNpcFriendship(gameNPC.getName()));
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
        /*MapController mapController = new MapController();
        if(!mapController.Isadj(player.getXlocation(), player.getYlocation(), owner)){
            data.put("flg" , false);
            data.put("message", "you are not close to this NPC");
            return new Result(data);
        }*/
        nowQuest.doQuest(player, owner);
        data.put("flg" , true);
        data.put("message" , "quest done!");
        return new Result(data);
    }
}
