package controller;

import model.App;
import model.Result;
import model.enums.Menu;
import service.SignupService;

import java.util.HashMap;
import java.util.Map;

public class SignupMenuController implements MenuController{

    @Override
    public Result exit() {
        App.enterMenu(Menu.ExitMenu);
        return new Result(Map.of(
            "valid", false));
    }


    public Result register(String username, String password, String confirmPassword,
                            String nickName, String email, String gender){
        
        // the result has:
        boolean newUsername, validUsername, validEmail, validPass, strongPass, validConfirm, randomPass;
            
        newUsername = SignupService.checkUsernameExistance(username);
        validEmail = SignupService.checkEmail(email);
        validUsername = SignupService.checkUsername(username);
        validPass = SignupService.checkPass(password);
        validConfirm = password.equals(confirmPassword);
        strongPass = SignupService.checkStrongPass(password);
        randomPass = password.equals("random");


        
        return new Result(Map.of(
            "newUsername", newUsername,
            "validUsername", validUsername,
            "validEmail", validEmail,
            "validPass", validPass,
            "strongPass", strongPass,
            "validConfirm", validConfirm,
            "radomPass", randomPass));
        
    }

    public Result pickQuestion(HashMap<String, String> args) {

        return null;
    }
}
