package controller;

import model.App;
import model.Player;
import model.Result;
import service.ProfileService;

import java.util.HashMap;
import java.util.Map;

public class ProfileMenuController implements MenuController{
    ProfileService service = new ProfileService();

    @Override
    public Result exit() {
        return new Result(Map.of("message", "you should go to singup/login menu first"));
    }
    @Override
    public Result enterMenu() {
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }

    public Result changeUsername(String username) {
        Player user = App.getAdmin();
        if (user.getUsername().equals(username)) return new Result(Map.of("message", "username is the same"));
        if (!service.checkUsername(username)) return new Result(Map.of("message", "username format is invalid"));
        if (service.checkUsernameExistance(username)) return new Result(Map.of("message", "username exist"));
        user.setUsername(username);
        return new Result(Map.of("message", "username changed successfully"));
    }
    public Result changeNickname(String nickname){
        App.getAdmin().setNickname(nickname);
        return new Result(Map.of("message", "nickname changed successfully"));
    }
    public Result changePassword(String password, String oldPasswrod){
        Player user = App.getAdmin();
        if (!user.getPassword().equals(oldPasswrod)) return new Result(Map.of("message", "password is incorrect"));
        if (!user.getPassword().equals(password)) return new Result(Map.of("message", "password is the same as the old one"));
        if (!service.checkPass(password)) return new Result(Map.of("message", "password's format is invalid"));
        if (!service.checkStrongPass(password)) return new Result(Map.of("message", "password is weak"));
        user.setPassword(password);
        return new Result(Map.of("message", "password changed successfully"));
    }
    public Result changeEmail(String email){
        Player user = App.getAdmin();
        if (user.getEmail().equals(email)) return new Result(Map.of("message", "email is the same"));
        if (!service.checkEmail(email)) return new Result(Map.of("message", "email format is invalid"));
        user.setEmail(email);
        return new Result(Map.of("message", "email changed successfully"));
    }
    public Result userInfo(){
        String message = "";
        message += "username: " + App.getAdmin().getUsername() + "\n";
        message += "nickname: " + App.getAdmin().getNickname() + "\n";
        message += "maximum score: " + App.getAdmin().getMaxScore() + "\n";
        message += "number of games played: " + App.getAdmin().getNumberGamesPlayed();
        
        return new Result(Map.of("message", message));
    }
}
