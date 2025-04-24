package service;

import java.util.regex.Matcher;

import model.App;
import model.Player;
import model.Result;
import serviceCommands.SignupServiceCommands;

public class SignupService {
    
    public boolean checkEmail(String email) {
        int cnt = 0;
        for (int i = 0; i < email.length(); i++) {
            if (email.charAt(i) == '@') cnt++;
        }
        if (cnt>1) return false;

        Matcher matcher = SignupServiceCommands.email.getMatcher(email);
        if (!matcher.matches()) return false;

        String name = matcher.group("name");
        String domain = matcher.group("domain");

        for (int i = 1; i < name.length(); i++) {
            if (name.charAt(i) == '.' && name.charAt(i - 1) == '.') return false;
        }

        return true;


    }
    public boolean checkPass(String pass) {
        Matcher matcher = SignupServiceCommands.password.getMatcher(pass);
        return matcher.matches();
        
    }
    public boolean checkStrongPass(String pass) {
        Matcher matcher = SignupServiceCommands.strongPass.getMatcher(pass);
        return matcher.matches();
    }
    public String passwordWeakness(String pass) {
        if (pass.length() < 8) return "length is less than 8";
        return "invalid character is given, use only lower and upper case letters or numbers or special characters";
    }
    public boolean checkUsernameExistance(String username) {
        for (Player player : App.getPlayers()) {
            if (player.getUsername().equals(username)) return true;
        }
        return false;
    }
    public boolean checkUsername(String username) {
        Matcher matcher = SignupServiceCommands.username.getMatcher(username);
        return matcher.matches();
    }
}
