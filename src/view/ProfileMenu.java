package view;

import java.util.Scanner;
import java.util.regex.Matcher;

import commands.ProfileMenuCommands;
import controller.ProfileMenuController;

public class ProfileMenu implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        String input = IOScanner.nextLine(); input = input.trim();
        ProfileMenuController controller = new ProfileMenuController();
        Matcher matcher;

        if ((matcher = getMatcher("enterMenu", input)).matches()) { 
            System.out.println(controller.enterMenu().getData().get("message"));
        }

        else if ((matcher = getMatcher("showMenu", input)).matches()) {
            System.out.println("current menu is: Main Menu");
        }

        else if ((matcher = getMatcher("changeUsername", input)).matches()) {
            System.out.println(controller.changeUsername(matcher.group("username")).getData().get("message"));
        }

        else if ((matcher = getMatcher("changeNickname", input)).matches()) {
            System.out.println(controller.changeNickname(matcher.group("changeNickname")));
        }

        else if ((matcher = getMatcher("changePassword", input)).matches()) {
            System.out.println(controller.changePassword(matcher.group("password"), matcher.group("oldPassword")).getData().get("message"));
        }

        else if ((matcher = getMatcher("changeEmail", input)).matches()) {
            System.out.println(controller.changeEmail(matcher.group("email")).getData().get("message"));
        }
        else if (input.equals("user info")) {
            System.out.println(controller.userInfo());
        }
        
    }

    static private Matcher getMatcher(String commandName, String input) {
        for (ProfileMenuCommands command : ProfileMenuCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
}
