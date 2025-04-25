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
            else if ((Boolean)result.getData().get("isValid")) {
                String username = matcher.group("username");
                System.out.println(result.getData().get("message"));
                System.out.println(controller.getQuestoins());
                while (true) {
                    String inputQues = IOScanner.nextLine();
                    if ((matcher = getMatcher("pickQuestion", inputQues)).matches()) {
                        Result resultQuestoin = controller.pickQuestion(username, matcher.group("number"), matcher.group("answer"), matcher.group("confirmAnswer"));
                        System.out.println(resultQuestoin.getData().get("message"));
                        if ((boolean)resultQuestoin.getData().get("isValid")) break;
                    }
                }
            }
            else System.out.println(result.getData().get("message"));
        }


        else if ((matcher = getMatcher("login", input)).matches()) {
            Result result = controller.login(matcher.group("username"), matcher.group("password"), matcher.group(3));
        }

        else if ((matcher = getMatcher("forgetPassword", input)).matches()) {
            Result result = controller.forgetPassword(matcher.group("username"));
            System.out.println(result.getData().get("message"));
            if ((boolean)result.getData().get("isValid")) {
                String username = matcher.group("username");
                String inputAnswer = IOScanner.nextLine();
                if ((matcher = getMatcher("answer", inputAnswer)).matches()) {
                    System.out.println(controller.answerQuestion(username, matcher.group("answer")).getData().get("message"));
                }
            }
        }

    }

    static private Matcher getMatcher(String commandName, String input) {
        for (SignupLoginMenuCommands command : SignupLoginMenuCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
}
