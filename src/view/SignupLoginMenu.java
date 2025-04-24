package view;

import java.util.Scanner;
import java.util.regex.Matcher;

import commands.MainMenuCommands;
import commands.SignupLoginMenuCommands;
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
            matcher.group("passwordConfirm"), matcher.group("nickname"), matcher.group("email"), matcher.group("gender"));
            String pass = matcher.group("password");

            if (result.getData().get("message").equals("generating random pass...")) {
                pass = controller.generatePass();;
                System.out.println("is " + pass + " good password for you?");
                String ques; ques = IOScanner.nextLine();
                if (ques.equals("yes")) System.out.println(controller.register(matcher.group("username"), pass,
                pass, matcher.group("nickname"), matcher.group("email"), matcher.group("gender")).getData().get("message"));
                
            }
            else if (((String)result.getData().get("message")).startsWith("username exist")) {
                System.out.println("hhih");
                System.out.println(result.getData().get("message"));
                String username = (String)result.getData().get("username");
                
                String ques; ques = IOScanner.nextLine();
                if (ques.equals("yes")) controller.register(username, matcher.group("password"),
                matcher.group("passwordConfirm"), matcher.group("nickname"), matcher.group("email"), matcher.group("gender"));
            }
            else System.out.println(result.getData().get("message"));
        }

    }

    static private Matcher getMatcher(String commandName, String input) {
        for (SignupLoginMenuCommands command : SignupLoginMenuCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
}
