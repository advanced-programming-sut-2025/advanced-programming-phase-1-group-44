package view;

import java.util.Scanner;
import java.util.regex.Matcher;

import com.badlogic.gdx.Screen;
import commands.GameMenuCommands;
import commands.MainMenuCommands;
import controller.GamePlayController;
import controller.MainMenuController;

public class MainMenu extends AppMenu {
//    @Override
    public void process(Scanner IOScanner) {
        String input = IOScanner.nextLine(); input = input.trim();
        MainMenuController controller = new MainMenuController();
        Matcher matcher;

        if ((matcher = getMatcher("enterMenu", input)).matches()) {
            System.out.println(controller.enterMenu(matcher.group("menuName")).getData().get("message"));
        }
        else if((matcher= GameMenuCommands.nextturn.getMatcher(input))!=null){
            GamePlayController gmcf=new GamePlayController();
            gmcf.nextTurn();
        }
        else if ((matcher = getMatcher("showMenu", input)).matches()) {
            System.out.println("current menu is: main.Main Menu");
        }

        else if ((input.equals("user logout"))) {
            System.out.println(controller.logout().getData().get("message"));
        }




    }

    static private Matcher getMatcher(String commandName, String input) {
        for (MainMenuCommands command : MainMenuCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
