package controller;

import model.App;
import model.Result;
import model.enums.Menu;
import service.SignupService;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SignupMenuController implements MenuController{
    SignupService service = new SignupService();

    @Override
    public Result exit() {
        App.enterMenu(Menu.ExitMenu);
        return new Result(Map.of(
            "valid", false));
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

    private static final Random random = new Random();

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


        
        if (!validUsername) return new Result(Map.of("message", "invalid username"));
        if (!validEmail) return new Result(Map.of("message", "invalid email"));
        if (randomPass) return new Result(Map.of("message", "generating random pass..."));
        if (!validPass) return new Result(Map.of("message", "invalid password"));
        if (!strongPass) return new Result(Map.of("message", "password is weak, reason: " + service.passwordWeakness(password)));
        if (!validConfirm) return new Result(Map.of("message", "confirm password is not equal to password"));
        if (!newUsername) return new Result(Map.of("message", "username exist, " +
            username + " is a valid username do you want it?",
            "username", username));


        return new Result(Map.of("message", "user created successfully!"));
        
    }
    public String generatePass() {
        String pass = new String();
        while (!service.checkPass(pass) || !service.checkStrongPass(pass)) {
            pass = pass + randomCharPass();
        }
        return pass;
    }

    public Result pickQuestion(HashMap<String, String> args) {

        return null;
    }
}
