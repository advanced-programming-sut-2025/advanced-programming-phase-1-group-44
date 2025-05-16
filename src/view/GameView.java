package view;

import commands.GameMenuCommands;
import commands.SignupMenuCommands;
import controller.GameMenuController;
import controller.GamePlayController;
import controller.SignupMenuController;
import model.App;
import model.Player;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameView implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        SignupMenuController controller = new SignupMenuController();
        GameMenuController mc = new GameMenuController();
        String input = IOScanner.nextLine(); input = input.trim();
        Matcher matcher;

        if ((matcher = getMatcher("enterMenu", input)).matches()) {

            System.out.println(controller.enterMenu(matcher.group("menuName")).getData().get("message"));
        }
        else if((matcher=GameMenuCommands.loadgame.getMatcher(input))!=null){
            Boolean vas=mc.load();
            if(vas==false){
                System.out.println("dadash oskolemoon kardi!");
            }else{
                System.out.println("bia baba inam bazit");
            }
        }else if((matcher=GameMenuCommands.exitgame.getMatcher(input))!=null){
            GameMenuController mca = new GameMenuController();
            mca.exitgame();
        }
        else if((matcher=GameMenuCommands.nextturn.getMatcher(input))!=null){
            GamePlayController gmcf=new GamePlayController();
            gmcf.nextTurn();
        }
        else if ((matcher = getMatcher("exit", input)).matches()) {
            controller.exit();
        }

        else if ((matcher = getMatcher("showMenu", input)).matches()) {
            System.out.println("current menu is: game view");
        }
        else if((matcher=GameMenuCommands.gamenew4user.getMatcher(input))!=null){
            System.out.println("more than 3 user");
        }else if((matcher=GameMenuCommands.gamenew3user.getMatcher(input))!=null){
            ArrayList<String> allname=new ArrayList<String>();
            allname.add(App.getAdmin().getUsername());
            allname.add(matcher.group("username1").trim());
            allname.add(matcher.group("username2").trim());
            allname.add(matcher.group("username3").trim());
            mc.createNewGame(allname);
            boolean bl=mc.createNewGame(allname);
            if(!bl){
                System.out.println("nasakhtam");
                return ;
            }
            for(Player pl:App.getCurrentGame().getUsers()){
                System.out.println(pl.getNickname());
            }
        }else if((matcher=GameMenuCommands.gamenew2user.getMatcher(input))!=null){
            ArrayList<String> allname=new ArrayList<String>();
            allname.add(App.getAdmin().getUsername());
            allname.add(matcher.group("username1").trim());
            allname.add(matcher.group("username2").trim());
            boolean bl=mc.createNewGame(allname);
            if(!bl){
                System.out.println("nasakhtam");
                return ;
            }
            for(Player pl:App.getCurrentGame().getUsers()){
                System.out.println(pl.getNickname());
            }
        }else if((matcher=GameMenuCommands.gamenew1user.getMatcher(input))!=null){
            ArrayList<String> allname=new ArrayList<String>();
            allname.add(App.getAdmin().getUsername());
            allname.add(matcher.group("username1").trim());
            mc.createNewGame(allname);
            boolean bl=mc.createNewGame(allname);
            if(!bl){
                System.out.println("nasakhtam");
                return ;
            }
            for(Player pl:App.getCurrentGame().getUsers()){
                System.out.println(pl.getNickname());
            }
        }else if((matcher=GameMenuCommands.gamenew.getMatcher(input))!=null){
            System.out.println("haji ba khodet mikhai bazi konu?");
        }else if((matcher=GameMenuCommands.gamemap.getMatcher(input))!=null){
            System.out.println("salam: "+App.getCurrentGame().getCurrentPlayer().getCurrentfarm().getName());
            if(matcher.group("mapnumber").equals("1")) {
                mc.chooseGameMap(1);
            }else{
                mc.chooseGameMap(2);
            }
            System.out.println("wtf: "+App.getCurrentGame().getCurrentPlayer().getUsername());
            System.out.println("salam: "+App.getCurrentGame().getCurrentPlayer().getCurrentfarm().getName());
        }else if((matcher=GameMenuCommands.loadgame.getMatcher(input))!=null){
            //todo baba sakhte nemifahmam
        }else{
            System.out.println("chy migy");
        }
    }

    static private Matcher getMatcher(String commandName, String input) {
        for (SignupMenuCommands command : SignupMenuCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        System.out.println("is null");
        return null;
    }
}
