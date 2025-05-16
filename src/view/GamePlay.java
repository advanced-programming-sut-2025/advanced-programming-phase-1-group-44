package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

import commands.GamePlayCommands;
import controller.GamePlayController;
import model.Item;
import model.Result;
import model.Tool;
import model.enums.Weather;

public class GamePlay implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        GamePlayController controller = new GamePlayController();
        String input = IOScanner.nextLine(); input = input.trim();
        Matcher matcher;
        if (input.equals("show current menu")) {
            print(controller.showCurrentMenu());
        }
        else if (input.equals("exit")) {
            print(controller.exit());
        }
        else if (input.equals("time")) {
            print(controller.getTime());
        }
        else if (input.equals("date")) {
            print(controller.getDate());
        }
        else if (input.equals("datetime")) {
            System.out.println(controller.getTime().getData().get("message") + " " + controller.getDate().getData().get("message"));
        }

        else if (input.equals("day of week")) {
            print(controller.getDayOfTheWeek());
        }

        else if ((matcher = getMatcher("cheatTime", input)).matches()) {
            print(controller.cheatTime(matcher.group("time")));
        }
        else if ((matcher = getMatcher("cheatDate", input)).matches()) {
            print(controller.cheatDate(matcher.group("date")));
        }
        else if (input.equals("season")) {
            print(controller.getSeason());
        }
        else if(input.equals("weather")){
            print(controller.getWeather());
        }
        else if(input.equals("weather forecast")){
            Result result = controller.predictWeather();
            ArrayList<Weather> weathers = (ArrayList<Weather>) result.getData().get("weathers");
            System.out.print("tomorrow weather will be : ");
            for(int i = 0 ; i < weathers.size() ; i++)
            {
                System.out.print(weathers.get(i).name());
                if(i + 1 != weathers.size())
                    System.out.print("or ");
            }
            System.out.println(" so please don't forget your umberella");
        }
        else if ((matcher = getMatcher("cheatWeather", input)).matches()){
            HashMap<String , String> args = new HashMap<>();
            args.put("weather" , matcher.group("type"));
            print(controller.cheatWeatherSet(args));
        }
        else if ((matcher = getMatcher("craftInfo", input)).matches()) {
            print(controller.craftInfo(matcher.group("name")));
        }
        else if(input.equals("energy show")){
            System.out.println(controller.showEnergy().getData().get("message"));
        }
        //TODO  collapse
        else if((matcher = getMatcher("energySet", input)).matches()){
            HashMap<String , String> args = new HashMap<>();
            args.put("value", matcher.group("value"));
            System.out.println(controller.cheatSetEnergy(args).getData().get("message"));
        }
        else if(input.equals("energy unlimited")){
            System.out.println(controller.cheatInfiniteEnergy().getData().get("message"));
        }
        else if(input.equals("inventory show")){
            Result result = controller.showInventory();
            ArrayList<Item> items = (ArrayList<Item>) result.getData().get("items");
            System.out.println("inventory : ");
            for (Item item : items) {
                System.out.println(item.name);
            }
        }
        else if((matcher = getMatcher("inventory trash", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("item" , matcher.group("item"));
            print(controller.disappearFromInventory(args));
        }
        else if((matcher = getMatcher("inventory trash with number" , input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("item" , matcher.group("item"));
            args.put("cnt" , matcher.group("number"));
            print(controller.removeFromInventory(args));
        }
        else if ((matcher = getMatcher("tools equip" , input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name" , matcher.group("name"));
            print(controller.equipTool(args));
        }
        else if(input.equals("tools show current")){
            print(controller.showCurrentTool());
        }
        else if(input.equals("tools show available")){
            Result result = controller.showAvailableTools();
            ArrayList<Tool> tools = (ArrayList<Tool>) result.getData().get("tools");
            System.out.println("available tools: ");
            for (Tool tool : tools) {
                System.out.println(tool.name);
            }
        }
        else if ((matcher = getMatcher("buildBuilding", input)).matches()) {
            Result result = controller.buildBuilding(matcher.group("name"), matcher.group("x"), matcher.group("y"));
            print(result);
        }

        else if ((matcher = getMatcher("buyAnimal", input)).matches()) {
            Result result = controller.buyAnimal(matcher.group("animalName"), matcher.group("name"));
            print(result);
        }

        else if ((matcher = getMatcher("pet", input)).matches()) {
            Result result = controller.pet(matcher.group("name"));
            print(result);
        }

        else if ((matcher = getMatcher("cheatSetFriendship", input)).matches()) {
            Result result = controller.cheatSetFriendship(matcher.group("name"), matcher.group("amount"));
            print(result);
        }

        else if (input.equals("animals")) {
            Result result = controller.showAnimals();
            print(result);
        }

        else if ((matcher = getMatcher("moveAnimal", input)).matches()) {
            Result result = controller.moveAnimal(matcher.group("name"), matcher.group("x"), matcher.group("y"));
            print(result);
        }

        else if ((matcher = getMatcher("feedHay", input)).matches()) {
            Result result = controller.feedHay(matcher.group("name"));
            print(result);
        }

        else if (input.equals("produces")) {
            Result result = controller.showProduces();
            print(result);
        }

        else if ((matcher = getMatcher("collectProduce", input)).matches()) {
            Result result = controller.collectProduce(matcher.group("name"));
            print(result);
        }

        else if ((matcher = getMatcher("talk", input)).matches()) {
            Result result = controller.talk(matcher.group("username"), matcher.group("message"));
            print(result);
        }

        else if ((matcher = getMatcher("talkHistory", input)).matches()) {
            Result result = controller.getTalkHistory(matcher.group("username"));
            print(result);
        }

        else if ((matcher = getMatcher("gift", input)).matches()) {
            Result result = controller.gift(matcher.group("username"), matcher.group("itemName"), matcher.group("amount"));
            print(result);
        }

        else if (input.equals("gift list")) {
            Result result = controller.showReceivedGiftList();
            print(result);
        }

        else if ((matcher = getMatcher("rateGift", input)).matches()) {
            Result result = controller.rateGift(matcher.group("giftId"), matcher.group("rate"));
            print(result);
        }

        else if ((matcher = getMatcher("giftHistory", input)).matches()) {
            Result result = controller.getGiftHistory(matcher.group("username"));
            print(result);
        }

        else if ((matcher = getMatcher("hug", input)).matches()) {
            Result result = controller.hug(matcher.group("username"));
            print(result);
        }

        else if ((matcher = getMatcher("flower", input)).matches()) {
            Result result = controller.giveFlower("username");
            print(result);
        }

        else if ((matcher = getMatcher("askMarriage", input)).matches()) {
            Result result = controller.askMarriage(matcher.group("usernamae"), matcher.group("ring"));
            print(result);
        }

        else if ((matcher = getMatcher("respondProposal", input)).matches()) {
            Result result = controller.respondProposal(matcher.group("response"), matcher.group("username"));
            print(result);
        }


        

        else if ((matcher = getMatcher("showMenu", input)).matches()) {
            System.out.println("current menu is: Game Play Menu");
            // GameMenuCommands.getCommand(matcher.group("menuName")).process(IOScanner);
        }

        else if (input.equals("help")) {
            System.out.println("help");
        }

        else {
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
