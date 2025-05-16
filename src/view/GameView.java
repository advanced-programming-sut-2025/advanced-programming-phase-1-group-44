package view;

import commands.GameMenuCommands;
import controller.GameMenuController;
import controller.GamePlayController;
import model.App;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameView implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        GameMenuController mc = new GameMenuController();
        String input = IOScanner.nextLine(); input = input.trim();
        Matcher matcher;
        if((matcher=GameMenuCommands.gamenew4user.getMatcher(input))!=null){
            System.out.println("more than 3 user");
        }else if((matcher=GameMenuCommands.gamenew3user.getMatcher(input))!=null){
            ArrayList<String> allname=new ArrayList<String>();
            allname.add(App.getCurrentGame().getAdmin().getUsername());
            allname.add(matcher.group("username_1").trim());
            allname.add(matcher.group("username_2").trim());
            allname.add(matcher.group("username_3").trim());
            mc.createNewGame(allname);
        }else if((matcher=GameMenuCommands.gamenew2user.getMatcher(input))!=null){
            ArrayList<String> allname=new ArrayList<String>();
            allname.add(App.getCurrentGame().getAdmin().getUsername());
            allname.add(matcher.group("username_1").trim());
            allname.add(matcher.group("username_2").trim());
            mc.createNewGame(allname);
        }else if((matcher=GameMenuCommands.gamenew1user.getMatcher(input))!=null){
            ArrayList<String> allname=new ArrayList<String>();
            allname.add(App.getCurrentGame().getAdmin().getUsername());
            allname.add(matcher.group("username_1").trim());
            mc.createNewGame(allname);
        }else if((matcher=GameMenuCommands.gamemap.getMatcher(input))!=null){
            System.out.println("haji ba khodet mikhai bazi konu?");
        }else if((matcher=GameMenuCommands.gamemap.getMatcher(input))!=null){
            if(matcher.group("map_number").equals("1")) {
                mc.chooseGameMap(1);
            }else{
                mc.chooseGameMap(2);
            }
        }else if((matcher=GameMenuCommands.loadgame.getMatcher(input))!=null){
            //todo baba sakhte nemifahmam
        }else{
            System.out.println("chy migy");
        }
    }
}
