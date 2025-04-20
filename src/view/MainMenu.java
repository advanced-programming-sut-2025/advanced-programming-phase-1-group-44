package view;

import java.util.Scanner;
import java.util.regex.Matcher;

import commands.MainMenuCommands;
import controller.MainMenuController;

public class MainMenu implements AppMenu {
    static private String input;
    @Override
    public void process(Scanner IOScanner) {
        input = IOScanner.nextLine(); input = input.trim();
        MainMenuController controller = new MainMenuController();
        Matcher matcher;

        if ((matcher = getMatcher("enterMenu")).matches()) { 
            controller.enterMenu(matcher.group("menuName"));
        }


        
    }

    static private Matcher getMatcher(String commandName) {
        for (MainMenuCommands command : MainMenuCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
    
}
