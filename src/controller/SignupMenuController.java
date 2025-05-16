package controller;

import model.App;
import model.Player;
import model.Result;
import model.enums.Menu;
import service.SignupService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SignupMenuController extends MenuController{
    SignupService service = new SignupService();

    @Override
    public Result exit() {
        App.enterMenu(Menu.ExitMenu);
        return new Result(Map.of(
            "valid", false));
    }
    @Override
    public Result enterMenu() {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }


    private static Character randomChar() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-";
        Random random = new Random();
        return chars.charAt(random.nextInt(chars.length()));

    }

    private static final String CHAR_SET = 
        "abcdefghijklmnopqrstuvwxyz" +  // lowercase
        "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +  // uppercase
        "0123456789" +                  // digits
        "-_!@#$%^&*()+=[]{}|;:,.<>?";   // special characters
    private static final ArrayList<String> questions = new ArrayList<>();

    private static final Random random = new Random();

    static {
        questions.add("your favorite color");
        questions.add("your father's car");
        questions.add("your home address");
    }

    public static char randomCharPass() {
        return CHAR_SET.charAt(random.nextInt(CHAR_SET.length()));
    }

    public Result register(String username, String password, String confirmPassword,
                            String nickName, String email, String gender){
        
        // the result has:
        boolean newUsername, validUsername, validEmail, validPass, strongPass, validConfirm, randomPass;
            

        newUsername = !service.checkUsernameExistance(username);
        validEmail = service.checkEmail(email);
        validUsername = service.checkUsername(username);
        validPass = service.checkPass(password);
        validConfirm = password.equals(confirmPassword);
        strongPass = service.checkStrongPass(password);
        randomPass = password.equals("random");

        while (service.checkUsernameExistance(username)) {
            username = username + randomChar();
        }


        
        if (!validUsername) return new Result(Map.of("message", "invalid username", "isValid", false));
        if (!validEmail) return new Result(Map.of("message", "invalid email", "isValid", false));
        if (randomPass) return new Result(Map.of("message", "generating random pass...", "isValid", false));
        if (!validPass) return new Result(Map.of("message", "invalid password", "isValid", false));
        if (!strongPass) return new Result(Map.of("message", "password is weak, reason: " + service.passwordWeakness(password), "isValid", false));
        if (!validConfirm) return new Result(Map.of("message", "confirm password is not equal to password", "isValid", false));
        if (!newUsername) return new Result(Map.of("message", "username exist, " +
            username + " is a valid username do you want it?",
            "username", username, "isValid", false));


        App.addPlayer(new Player(username, password, nickName, email, gender));
        
        return new Result(Map.of("message", "user created successfully!, pick a questoin: " + questions, "isValid", true));
        
    }

    public String getQuestoins() {
        String result = "";
        int cnt = 1;
        for (String ques : questions) {
            result += cnt + ". " + ques + '\n';
            cnt++;

        }
        return result;
    }

    public Result pickQuestion(String username, String quesNumber, String answer, String answerConfirm) {
        
        if (!answer.equals(answerConfirm)) return new Result(Map.of("message", "answer confirm is not equal to answer",
            "isValid", false));
        int index = Integer.valueOf(quesNumber) - 1;
        App.findUserByUsername(username).setQuestion(index, answer);
        
        return new Result(Map.of("message", "answer is okay, all done!", "isValid", true));
    }

    public Result login(String username, String password, String stayLoggedIn) {
        if (!service.checkUsernameExistance(username)) return new Result(Map.of("message", "user doesn't exist!"));
        Player user = App.findUserByUsername(username);
        if (!user.getPassword().equals(password)) return new Result(Map.of("message", "password is incorrect!"));
        App.login(user);
        if (stayLoggedIn != null) {
            service.lockUser(user);
        }

        else stayLoggedIn = "don't stay-logged-in";

        App.enterMenu(Menu.MainMenu);

        return new Result(Map.of("message", "user logged in successfully!, " + stayLoggedIn));
    }
    public String generatePass() {
        String pass = new String();
        while (!service.checkPass(pass) || !service.checkStrongPass(pass)) {
            pass = pass + randomCharPass();
        }
        return pass;
    }

    public Result forgetPassword(String username) {
        if (!service.checkUsernameExistance(username)) return new Result(Map.of("message", "invalid username", "isValid", false));
        return new Result(Map.of("message", "now answer your selected question:", "isValid", true));
    }

    public Result answerQuestion(String username, String answer) {
        Player user = App.findUserByUsername(username);
        if (user.getQuestionAnswer().equals(answer))
            return new Result(Map.of("message", "password is: " + user.getPassword()));
        else return new Result(Map.of("message", "given answer is incorrect"));
    }
}
