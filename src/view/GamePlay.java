package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

import commands.GameMenuCommands;
import commands.GamePlayCommands;
import commands.ImproveCommands;
import commands.Mapcommands;
import controller.GameMenuController;
import controller.GamePlayController;
import controller.MapController;
import model.*;
import model.enums.*;
import model.Stores.ShopItem;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Recipe;
import model.enums.Weather;

import static java.lang.Math.min;

public class GamePlay implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        GamePlayController controller = new GamePlayController();
        String input = IOScanner.nextLine();
        if (input.equals("")) {
            System.out.println("dadash ye chy benevis");
            return;
        }
        input = input.trim();
        Matcher matcher;
        MapController mc = new MapController();
        //TODO  check this lines:
        /*while(App.getCurrentGame().getCurrentPlayer().isCollapsed){
            App.getCurrentGame().nextTurn();
        }*/
        if ((matcher = GameMenuCommands.nextturn.getMatcher(input)) != null) {
            GamePlayController gmcf = new GamePlayController();
            gmcf.nextTurn();
            return;
        } else if ((matcher = GameMenuCommands.exitgame.getMatcher(input)) != null) {
            GameMenuController mca = new GameMenuController();
            mca.exitgame();
            return;
        } else if (input.equals("exit")) {
            print(controller.exit());
        } else if (input.equals("time")) {
            print(controller.getTime());
        } else if (input.equals("date")) {
            print(controller.getDate());
        } else if (input.equals("datetime")) {
            System.out.println(controller.getTime().getData().get("message") + " " + controller.getDate().getData().get("message"));
        } else if (input.equals("day of week")) {
            print(controller.getDayOfTheWeek());
        } else if ((matcher = getMatcher("cheatTime", input)).matches()) {
            print(controller.cheatTime(matcher.group("time")));
        } else if ((matcher = getMatcher("cheatDate", input)).matches()) {
            print(controller.cheatDate(matcher.group("date")));
        } else if (input.equals("season")) {
            print(controller.getSeason());
        } else if (input.equals("weather")) {
            print(controller.getWeather());
        } else if (input.equals("weather forecast")) {
            Result result = controller.predictWeather();
            ArrayList<Weather> weathers = (ArrayList<Weather>) result.getData().get("weathers");
            System.out.print("tomorrow weather will be : ");
            for (int i = 0; i < weathers.size(); i++) {
                System.out.print(weathers.get(i).name());
                if (i + 1 != weathers.size())
                    System.out.print("or ");
            }
            System.out.println(" so please don't forget your umberella");
        } else if ((matcher = getMatcher("cheatWeather", input)).matches()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("weather", matcher.group("type"));
            print(controller.cheatWeatherSet(args));
        }
        else if(input.equals("energy show")){
            System.out.println(controller.showEnergy().getData().get("message"));
        }
        //TODO  collapse
        else if ((matcher = getMatcher("energySet", input)).matches()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("value", matcher.group("value"));
            System.out.println(controller.cheatSetEnergy(args).getData().get("message"));
        } else if (input.equals("energy unlimited")) {
            System.out.println(controller.cheatInfiniteEnergy().getData().get("message"));
        } else if (input.equals("inventory show")) {
            Result result = controller.showInventory();
            ArrayList<Item> items = (ArrayList<Item>) result.getData().get("items");
            System.out.println("inventory : ");
            for (Item item : items) {
                System.out.print(item.name + " : ");
                int cnt = App.getCurrentGame().getCurrentPlayer().getBackpack().contain(item);
                System.out.println(cnt);
            }
        }
        else if ((matcher = getMatcher("inventory trash with number", input)).matches()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("item", matcher.group("item"));
            args.put("cnt", matcher.group("number"));
            System.out.println("item : " + args.get("item"));
            print(controller.removeFromInventory(args));
        }
        else if ((matcher = getMatcher("inventory trash", input)).matches()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("item", matcher.group("item"));
            print(controller.disappearFromInventory(args));
        }
        else if ((matcher = getMatcher("tools equip", input)).matches()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            print(controller.equipTool(args));
        } else if (input.equals("tools show current")) {
            print(controller.showCurrentTool());
        } else if (input.equals("tools show available")) {
            Result result = controller.showAvailableTools();
            ArrayList<Tool> tools = (ArrayList<Tool>) result.getData().get("tools");
            System.out.println("available tools: ");
            for (Tool tool : tools) {
                System.out.println(tool.name);
            }
        } else if ((matcher = getMatcher("tools upgrade", input)).matches()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            print(controller.upgradeTool(args));
        } else if ((matcher = getMatcher("tools use", input)).matches()) {
            HashMap<String, String> args = new HashMap<>();
            args.put("direction", matcher.group("direction"));
            print(controller.useTool(args));
        }
        else if(input.equals("crafting show recipes")){
            Result result = controller.allCraftableItems();
            ArrayList<CraftableItem> items = (ArrayList<CraftableItem>) result.getData().get("items");
            for (CraftableItem item : items) {
                System.out.println(item.toString());
            }
        }
        else if((matcher = getMatcher("craft", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            print(controller.craft(args));
        }
        else if((matcher = getMatcher("cheat add item", input)).matches()){
            HashMap<String , String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            args.put("number", matcher.group("cnt"));
            print(controller.cheatAddItem(args));
        }
        else if(input.equals("cooking show recipes")){
            Result result = controller.cookingShowRecipes();
            ArrayList<Recipe> recipes = (ArrayList<Recipe>) result.getData().get("recipes");
            for (Recipe recipe : recipes) {
                System.out.println(recipe.toString());
            }
        }
        else if((matcher = getMatcher("cooking prepare", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name" , matcher.group("name"));
            print(controller.cookingPrepare(args));
        }
        else if((matcher = getMatcher("cheat add money", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("count", matcher.group("count"));
            print(controller.cheatAddMoney(args));
        }
        else if(input.equals("show current buff")){
            String buff = App.getCurrentGame().getCurrentPlayer().getBuff();
            if(buff == null){
                System.out.println("ghelyan");
            }
            else{
                System.out.println(buff);
            }
        }
        else if((matcher = getMatcher("cooking refrigerator", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("type", matcher.group("type"));
            args.put("item", matcher.group("name"));
            print(controller.cookingRefrigerator(args));
        }
        else if((matcher = getMatcher("eat", input)).matches()){
            HashMap<String , String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            print(controller.eat(args));
        }
        else if ((matcher = getMatcher("buildBuilding", input)).matches()) {
            Result result = controller.buildBuilding(matcher.group("name"), matcher.group("x"), matcher.group("y"));
            print(result);
        } else if ((matcher = getMatcher("buyAnimal", input)).matches()) {
            Result result = controller.buyAnimal(matcher.group("animalName"), matcher.group("name"));
            print(result);
        } else if ((matcher = getMatcher("pet", input)).matches()) {
            Result result = controller.pet(matcher.group("name"));
            print(result);
        } else if ((matcher = getMatcher("cheatSetFriendship", input)).matches()) {
            Result result = controller.cheatSetFriendship(matcher.group("name"), matcher.group("amount"));
            print(result);
        } else if (input.equals("animals")) {
            Result result = controller.showAnimals();
            print(result);
        } else if ((matcher = getMatcher("moveAnimal", input)).matches()) {
            Result result = controller.moveAnimal(matcher.group("name"), matcher.group("x"), matcher.group("y"));
            print(result);
        } else if ((matcher = getMatcher("feedHay", input)).matches()) {
            Result result = controller.feedHay(matcher.group("name"));
            print(result);
        } else if (input.equals("produces")) {
            Result result = controller.showProduces();
            print(result);
        } else if ((matcher = getMatcher("collectProduce", input)).matches()) {
            Result result = controller.collectProduce(matcher.group("name"));
            print(result);
        }

        else if ((matcher = getMatcher("sellAnimal", input)).matches()) {
            Result result = controller.sellAnimal(matcher.group("name"));
            print(result);
        }
        else if ((matcher = getMatcher("gift", input)).matches()) {
            Result result = controller.gift(matcher.group("username"), matcher.group("itemName"), matcher.group("amount"));
            print(result);
        } else if ((matcher = getMatcher("talk", input)).matches()) {
            Result result = controller.talk(matcher.group("username"), matcher.group("message"));
            print(result);
        } else if ((matcher = getMatcher("talkHistory", input)).matches()) {
            Result result = controller.getTalkHistory(matcher.group("username"));
            print(result);
        } else if ((matcher = getMatcher("gift", input)).matches()) {
            Result result = controller.gift(matcher.group("username"), matcher.group("itemName"), matcher.group("amount"));
            print(result);
        } else if (input.equals("gift list")) {
            Result result = controller.showReceivedGiftList();
            print(result);
        } else if ((matcher = getMatcher("rateGift", input)).matches()) {
            Result result = controller.rateGift(matcher.group("giftId"), matcher.group("rate"));
            print(result);
        } else if ((matcher = getMatcher("giftHistory", input)).matches()) {
            Result result = controller.getGiftHistory(matcher.group("username"));
            print(result);
        } else if ((matcher = getMatcher("hug", input)).matches()) {
            Result result = controller.hug(matcher.group("username"));
            print(result);
        } else if ((matcher = getMatcher("flower", input)).matches()) {
            Result result = controller.giveFlower("username");
            print(result);
        } else if ((matcher = getMatcher("askMarriage", input)).matches()) {
            Result result = controller.askMarriage(matcher.group("usernamae"), matcher.group("ring"));
            print(result);
        } else if ((matcher = getMatcher("respondProposal", input)).matches()) {
            Result result = controller.respondProposal(matcher.group("response"), matcher.group("username"));
            print(result);
        } else if ((input.equals("start trade"))) {
            print(controller.startTrade());
        }
        else if((matcher = getMatcher("go to store", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            print(controller.goToShop(args));
        }
        else if(input.equals("show all products")){
            Result result = controller.showAllProducts();
            if(!((boolean) result.getData().get("flg"))){
                print(result);
            }
            else {
                ArrayList<ShopItem> items = (ArrayList<ShopItem>) result.getData().get("items");
                System.out.println("all products : ");
                for (ShopItem item : items) {
                    System.out.println(item.toString());
                }
            }
        }
        else if(input.equals("show all available products")){
            Result result = controller.showAllAvailableProduct();
            if(!((boolean) result.getData().get("flg"))){
                print(result);
            }
            else {
                ArrayList<ShopItem> items = (ArrayList<ShopItem>) result.getData().get("items");
                System.out.println("all products : ");
                for (ShopItem item : items) {
                    System.out.println(item.toString());
                }
            }
        }
        else if((matcher = getMatcher("purchase with count", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            args.put("cnt", matcher.group("count"));
            print(controller.purchase(args));
        }
        else if((matcher = getMatcher("purchase", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name" , matcher.group("name").trim());
            args.put("cnt", "1");
            print(controller.purchase(args));
        }
        else if(input.equals("show money")){
            System.out.println(App.getCurrentGame().getCurrentPlayer().money);
        }
        else if((matcher = getMatcher("sell with count", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            args.put("count", matcher.group("count"));
            print(controller.sell(args));
        }
        else if((matcher = getMatcher("sell", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("name"));
            print(controller.sell(args));
        }
        else if(input.equals("show current player")){
            System.out.println(App.getCurrentGame().getCurrentPlayer().getName());
        }
        else if ((matcher = Mapcommands.walk.getMatcher(input)) != null) {
            try {
                int i = Integer.parseInt(matcher.group("x"));
                int j = Integer.parseInt(matcher.group("y"));
                int dis = mc.walk1(i, j);
                if (dis >= App.inf) {
                    System.out.println("eyvay masiry nist");
                    return;
                }
                dis /= 2;
                System.out.println("you need " + dis + " energy");
                input = IOScanner.nextLine();
                if (input.equals("YES")) {
                    if (App.getCurrentGame().getCurrentPlayer().getEnergy() < dis) {
                        //todo collaps
                        System.out.println("energynadary");
                    } else {
                        App.getCurrentGame().getCurrentPlayer().setEnergy(App.getCurrentGame().getCurrentPlayer().getEnergy() - dis);
                        mc.walk2(i, j);
                    }
                } else {
                    System.out.println("heyfshod");
                }
            } catch (Exception e) {
                System.out.println("dadash vorodit eshtebahe");
            }
            return ;
        }else if((matcher=Mapcommands.printmap.getMatcher(input))!=null){
            ArrayList<Player>pls=App.getCurrentGame().getUsers();
            MapFarm mf=pls.get(0).getCurrentfarm();
            ArrayList<ArrayList<MapObj>> res=new ArrayList<ArrayList<MapObj>>();
            for(int i=0;i<mf.getWidth()*3;i++){
                res.add(new ArrayList<MapObj>());
                for (int j = 0; j < mf.getHigh() * 3; j++) {
                    res.get(i).add(new Space());
                }
            }
            int nowi = 0, nowj = 0;
            for (int i = 0; i < pls.get(0).getCurrentfarm().getWidth(); i++) {
                for (int j = 0; j < pls.get(0).getCurrentfarm().getHigh(); j++) {
                    res.get(i + nowi).set(j + nowj, pls.get(0).getCurrentfarm().GetCell(i, j));
                }
            }
            nowi = 0;
            nowj = mf.getHigh() * 2;
            for (int i = 0; i < pls.get(1).getCurrentfarm().getWidth(); i++) {
                for (int j = 0; j < pls.get(1).getCurrentfarm().getHigh(); j++) {
                    res.get(i + nowi).set(j + nowj, pls.get(1).getCurrentfarm().GetCell(i, j));
                }
            }
            nowi = mf.getWidth() * 2;
            nowj = 0;
            for (int i = 0; i < pls.get(2).getCurrentfarm().getWidth(); i++) {
                for (int j = 0; j < pls.get(2).getCurrentfarm().getHigh(); j++) {
                    res.get(i + nowi).set(j + nowj, pls.get(2).getCurrentfarm().GetCell(i, j));
                }
            }
            nowi = mf.getWidth() * 2;
            nowj = mf.getHigh() * 2;
            for (int i = 0; i < pls.get(3).getCurrentfarm().getWidth(); i++) {
                for (int j = 0; j < pls.get(3).getCurrentfarm().getHigh(); j++) {
                    res.get(i + nowi).set(j + nowj, pls.get(3).getCurrentfarm().GetCell(i, j));
                }
            }
            nowi=mf.getWidth();
            nowj=mf.getHigh();
            for(int i=0;i<App.getCurrentGame().getDehkade().getWidth();i++){
                for(int j=0;j<App.getCurrentGame().getDehkade().getHigh();j++){
                    res.get(i+nowi).set(j+nowj,App.getCurrentGame().getDehkade().GetCell(i,j));
                }
            }
            String RED = "\u001B[31m";
            String GREEN = "\u001B[32m";
            String YELLOW = "\u001B[33m";
            String BLUE = "\u001B[34m";
            String PURPLE = "\u001B[35m";
            String CYAN = "\u001B[36m";
            String RESET = "\u001B[0m";
            int sz = Integer.parseInt(matcher.group("size"));
            int x = Integer.parseInt(matcher.group("x"));
            int y = Integer.parseInt(matcher.group("y"));
            for (int i = x; i < min(mf.getWidth() * 3, x + sz); i++) {
                for (int j = y; j < min(mf.getHigh() * 3, y + sz); j++) {
                    String color = YELLOW;
                    if (res.get(i).get(j).getName().charAt(0) == 'T') {
                        color = GREEN;
                    } else if (res.get(i).get(j).getName().charAt(0) == 'L') {
                        color = BLUE;
                    } else if (res.get(i).get(j).getName().charAt(0) == 'C') {
                        color = RED;
                    } else if (res.get(i).get(j).getName().charAt(0) == 'G') {
                        color = PURPLE;
                    } else if (res.get(i).get(j).getName().charAt(0) == 'S') {
                        color = YELLOW;
                    } else if (res.get(i).get(j).getName().charAt(0) == 'Q') {
                        color = CYAN;
                    } else {
                        color = RESET;
                    }
                    System.out.print(color + res.get(i).get(j).getName().charAt(0) + RESET);
                }
                System.out.print("\n");
            }
            return ;
        }else if((matcher=Mapcommands.helpreadingmap.getMatcher(input))!=null){
               System.out.print("salam\n" +
                       "ma az avalin harf estefade kardim masala age Tree bood ma T ro namayesh midahim listi az cheez ha:\n" +
                       "Tree:T\n" +
                       "Player:P\n" +
                       "Space:S\n" +
                       "Cottage:C\n" +
                       "Lae:L\n" +
                       "Greenhouse:G\n" +
                       "Quaary:Q\n");
               return ;
        }else if((matcher= ImproveCommands.info.getMatcher(input))!=null){
            String name=matcher.group("craftname");
            for(Crops cr: Crops.values()){
                if(cr.getName().equals(name)){
                    System.out.print("name: "+cr.getName()+"\n");
                    System.out.print("source: "+cr.getSource()+"\n");
                    System.out.print("Stages: ");
                    for(Integer i:cr.getStages()){
                        System.out.print(i+"-");
                    }
                    System.out.print("\n");
                    System.out.print("Total Harvest Time: "+cr.getTotalHarvestTime()+"\n");
                    System.out.print("One Time: "+cr.isOneTime()+"\n");
                    System.out.print("Is Edible: "+cr.isEdible()+"\n");
                    System.out.print("season: "+cr.getSeason()+"\n");
                    System.out.print("energy: "+cr.getEnergy()+"\n");
                    System.out.print("base health: "+cr.getBaseHealth()+"\n");
                    System.out.print("base sell price: "+cr.getBaseSellPrice()+"\n");
                    System.out.print("regrowth time: "+cr.getRegrowthTime()+"\n");
                    System.out.print("can become Giant: "+cr.canBecomeGiant()+"\n");

                }
            }
        }else if((matcher=ImproveCommands.infotree.getMatcher(input))!=null){
            String name=matcher.group("craftname");
            for(Trees tr:Trees.values()){
                if(tr.getName().equals(name)) {
                    System.out.print("name: "+tr.getName()+"\n");
                    System.out.print("fruit: "+tr.getFruit()+"\n");
                    System.out.print("Stages: ");
                    for(Integer i:tr.getStages()){
                        System.out.print(i+"-");
                    }
                    System.out.print("\n");
                    System.out.print("source: "+tr.getSource()+"\n");
                    System.out.print("harvent time: "+tr.getTotalHarvestTime()+"\n");
                    System.out.print("fruit harvent cycle: "+tr.getFruitHarvestCycle()+"\n");
                    System.out.print("fruit base sell prive: "+tr.getFruitBaseSellPrice()+"\n");
                    System.out.print("Is fruit edible: "+tr.isFruitEdible()+"\n");
                    System.out.print("Fruit Energy: "+tr.getFruitEnergy()+"\n");
                    System.out.print("season: "+tr.getSeason()+"\n");
                }
            }
        }else if((matcher=ImproveCommands.infoforagingcrops.getMatcher(input))!=null){
            String name=matcher.group("craftname");
            for(ForagingsCrops Fc:ForagingsCrops.values()){
                if(Fc.getName().equals(name)) {
                    System.out.print("name: "+Fc.getName()+"\n");
                    System.out.print("season: "+Fc.getSeason()+"\n");
                    System.out.print("Base Sell Price: "+Fc.getBaseSellPrice()+"\n");
                    System.out.print("Energy: "+Fc.getEnergy()+"\n");
                }
            }
        }else if((matcher=ImproveCommands.infoforagingtree.getMatcher(input))!=null){
            String name=matcher.group("craftname");
            for(ForagingTrees Ft:ForagingTrees.values()){
                if(Ft.getName().equals(name)) {
                    System.out.print("name: "+Ft.getName()+"\n");
                    System.out.print("season: "+Ft.getSeason()+"\n");
                }
            }
        }else if((matcher=ImproveCommands.plant.getMatcher(input))!=null){
            String s=matcher.group("seed");
            int dir=Integer.parseInt(matcher.group("direction"));

        }
        else if ((matcher = getMatcher("showMenu", input)).matches()) {
            System.out.println("current menu is: Game Play Menu");
            // GameMenuCommands.getCommand(matcher.group("menuName")).process(IOScanner);
        } else if (input.equals("help")) {
            System.out.println("help");
        } else {
            System.out.println("invalid command");
        }
    }

    static private void print(Result result) {
        System.out.println(result.getData().get("message"));
    }

    static private Matcher getMatcher(String commandName, String input) {
        for (GamePlayCommands command : GamePlayCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
}
