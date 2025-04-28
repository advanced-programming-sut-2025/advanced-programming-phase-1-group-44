package view;

import java.util.Scanner;
import java.util.regex.Matcher;

import commands.GameMenuCommands;
import commands.GamePlayCommands;
import commands.SignupLoginMenuCommands;
import controller.GamePlayController;
import controller.SignupMenuController;

public class GamePlay implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        GamePlayController controller = new GamePlayController();
        String input = IOScanner.nextLine(); input = input.trim();
        Matcher matcher;


        if (input.equals("show current menu")) {
            System.out.println(controller.showCurrentMenu().getData().get("message"));
        }
        else if (input.equals("exit")) {
            System.out.println(controller.exit().getData().get("message"));
        }
        else if (input.equals("time")) {
            System.out.println(controller.getTime().getData().get("message"));
        }
        else if (input.equals("date")) {
            System.out.println(controller.getDate().getData().get("message"));
        }
        else if (input.equals("datetime")) {
            System.out.println(controller.getTime().getData().get("message") + " " + controller.getDate().getData().get("message"));
        }

        else if (input.equals("day of week")) {
            System.out.println(controller.getDayOfWeek().getData().get("message"));
        }
        
    }

    static private Matcher getMatcher(String commandName, String input) {
        for (GamePlayCommands command : GamePlayCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
}
