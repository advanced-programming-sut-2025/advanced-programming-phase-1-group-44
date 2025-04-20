package view;

import java.util.Scanner;
import java.util.regex.Matcher;

import commands.MainMenuCommands;
import controller.SignupMenuController;
import model.Result;

public class SignupLoginMenu implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        SignupMenuController controller = new SignupMenuController();
        String input = IOScanner.nextLine(); input = input.trim();
        Matcher matcher;
        

        if ((matcher = getMatcher("enterMenu", input)).matches()) {
            System.out.println("for enetering menu go to main menu");
        }

        else if ((matcher = getMatcher("exit", input)).matches()) {
            controller.exit();
        }

        else if ((matcher = getMatcher("showMenu", input)).matches()) {
            System.out.println("current menu is: Signup/Login Menu");
        }

        else if ((matcher = getMatcher("register", input)).matches()) {
            Result result = controller.register(matcher.group("username"), matcher.group("password"),
            matcher.group("password_confirm"), matcher.group("nickname"), matcher.group("email"), matcher.group("gender"));

            if (!result.flg()) {
                System.out.println("given username exist");
                controller.generateUsername(matcher.get)
            }
        }



    }

    static private Matcher getMatcher(String commandName, String input) {
        for (MainMenuCommands command : MainMenuCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
}
