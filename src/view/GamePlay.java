package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

import commands.GameMenuCommands;
import commands.GamePlayCommands;
import commands.SignupLoginMenuCommands;
import controller.GamePlayController;
import controller.SignupMenuController;
import model.Item;
import model.Result;

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
            for (Item item : items) {
                System.out.println(item.name);
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
