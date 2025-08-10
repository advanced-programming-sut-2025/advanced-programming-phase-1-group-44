package controller;

import main.Main;
import main.NetworkClient;
import model.App;
import model.Player;
import model.Result;
import model.enums.Menu;
import service.ProfileService;

import java.util.Map;

public class ProfileMenuController extends MenuController{
    ProfileService service = new ProfileService();

    @Override
    public Result exit() {
        return new Result(Map.of("message", "you should go to singup/login menu first"));
    }
    @Override
    public Result enterMenu(String menuName) {
        if (menuName.equals("main")) {
            Menu.MainMenu.setMenu();
            return new Result(Map.of("message", "entered main menu"));
        }
        return new Result(Map.of("message", "you should go to main menu for this command"));
    }

    public void changeUsername2(String oldUsername, String username) {
        Player user = App.findUserByUsername(oldUsername);
        user.setUsername(username);
    }

    public void changePassword2(String username, String password) {
        Player user = App.findUserByUsername(username);
        user.setPassword(password);
    }

    public void changeNickname2(String username, String nickname) {
        Player user = App.findUserByUsername(username);
        user.setNickname(nickname);
    }

    public void changeEmail2(String username, String email) {
        Player user = App.findUserByUsername(username);
        user.setEmail(email);
    }


    public Result changeUsername(String username) {
        Player user = App.getAdmin();
        if (user.getUsername().equals(username)) return new Result(Map.of("message", "username is the same"));
        if (!service.checkUsername(username)) return new Result(Map.of("message", "username format is invalid"));
        if (service.checkUsernameExistence(username)) return new Result(Map.of("message", "username exist"));

        Main.getNetworkClient().changeProfile(user.getUsername(), username, user.getPassword(), user.getPassword(),
            user.getEmail(), user.getNickname());

        user.setUsername(username);
        return new Result(Map.of("message", "username changed successfully"));
    }
    public Result changeNickname(String nickname){
        if (App.getAdmin().getNickname().equals(nickname)) return new Result(Map.of("message", "the same"));
        Player user = App.getAdmin();

        Main.getNetworkClient().changeProfile(user.getUsername(), user.getUsername(), user.getPassword(), user.getPassword(),
            user.getEmail(), nickname);

        App.getAdmin().setNickname(nickname);
        return new Result(Map.of("message", "nickname changed successfully"));
    }
    public Result changePassword(String password, String oldPasswrod){
        Player user = App.getAdmin();
        if (!user.getPassword().equals(oldPasswrod)) return new Result(Map.of("message", "password is incorrect"));
        if (!user.getPassword().equals(password)) return new Result(Map.of("message", "password is the same as the old one"));
        if (!service.checkPass(password)) return new Result(Map.of("message", "password's format is invalid"));
        if (!service.checkStrongPass(password)) return new Result(Map.of("message", "password is weak"));

        Main.getNetworkClient().changeProfile(user.getUsername(), user.getUsername(), user.getPassword(), password,
            user.getEmail(), user.getNickname());

        user.setPassword(password);
        return new Result(Map.of("message", "password changed successfully"));
    }
    public Result changeEmail(String email){
        Player user = App.getAdmin();
        if (user.getEmail().equals(email)) return new Result(Map.of("message", "email is the same"));
        if (!service.checkEmail(email)) return new Result(Map.of("message", "email format is invalid"));

        Main.getNetworkClient().changeProfile(user.getUsername(), user.getUsername(), user.getPassword(), user.getPassword(),
            email, user.getNickname());

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
