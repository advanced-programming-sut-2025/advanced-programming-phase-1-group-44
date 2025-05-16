package view;

import commands.GamePlayCommands;
import commands.TradeCommands;
import controller.TradeMenuController;
import model.Result;
import model.Trade;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeView implements AppMenu{
    TradeMenuController controller = new TradeMenuController();
    @Override
    public void process(Scanner IOScanner) {
        String input = IOScanner.nextLine();  input = input.trim();
        Matcher matcher;
        if(input.equals("end trade")){
            print(controller.endTrade());
        }
        else if((matcher = getMatcher("trade", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("name", matcher.group("username"));
            args.put("type", matcher.group("type"));
            args.put("req item", matcher.group("reqItem"));
            args.put("req cnt" , matcher.group("amount"));
            if(matcher.group("price") != null){
                args.put("target money", matcher.group("price"));
            } else{
                args.put("target money" , "0");
            }
            if(matcher.group("targetItem") != null){
                args.put("target item", matcher.group("targetItem"));
            } else {
                args.put("target item", "parsaError");
            }
            if(matcher.group("targetAmount") != null){
                args.put("target cnt", matcher.group("target amount"));
            } else {
                args.put("target cnt" , "0");
            }
            print(controller.trade(args));
        }
        else if(input.equals("trade list")){
            Result result = controller.tradeList();
            ArrayList<Trade> trades = (ArrayList<Trade>) result.getData().get("trades");
            for (Trade trade : trades) {
                System.out.println(trade.toString());
            }
        }
        else if((matcher = getMatcher("trade response", input)).matches()){
            HashMap<String, String> args = new HashMap<>();
            args.put("respond", matcher.group("type"));
            args.put("ID", matcher.group("ID"));
            print(controller.tradeResponse(args));
        }
        else if(input.equals("trade History")){
            Result result = controller.tradeHistory();
            ArrayList<Trade> rejectedTrades = (ArrayList<Trade>) result.getData().get("rejected trades");
            ArrayList<Trade> acceptedTrades = (ArrayList<Trade>) result.getData().get("accepted trades");
            System.out.println("rejected Trades : ");
            for (Trade rejectedTrade : rejectedTrades) {
                System.out.println(rejectedTrade.toString());
            }
            System.out.println("Accepted Trades : ");
            for (Trade acceptedTrade : acceptedTrades) {
                System.out.println(acceptedTrade.toString());
            }
        }
        else{
            System.out.println("invalid command");
        }
    }

    static private void print(Result result) {
        System.out.println(result.getData().get("message"));
    }

    static private Matcher getMatcher(String commandName, String input) {
        for (TradeCommands command : TradeCommands.values()) {
            if (command.getName().equals(commandName)) return command.getMatcher(input);
        }
        return null;
    }
}
