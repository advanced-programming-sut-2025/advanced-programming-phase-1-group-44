package controller;

import model.*;
import model.enums.AllItems;
import model.enums.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TradeMenuController{
    public Result endTrade(){
        App.enterMenu(Menu.Gameplay);
        return new Result(Map.of("message", "you returned to game"));
    }
    public String showCurrentMenu(){
        return "TradeMenu";
    }
    public Result tradeHistory(){
        Player player = App.getCurrentGame().getCurrentPlayer();
        Map<String, Object> data = new HashMap<>();
        data.put("rejected trades", player.getRejectedTrades());
        data.put("accepted trades", player.getAcceptedTrades());
        return new Result(data);
    }
    public Result trade(HashMap<String, String> args){
        //TODO check player have the trade items
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player receiver = game.findPlayerByUsername(args.get("name"));
        Map<String, Object> data = new HashMap<>();
        if(receiver == null){
            data.put("flg" , false);
            data.put("message", "player doesn't exist");
            return new Result(data);
        }
        if(!args.get("type").equals("offer") && !args.get("type").equals("request")){
            data.put("flg" , false);
            data.put("message", "invalid trade type");
            return new Result(data);
        }
        Item reqItem = AllItems.getItemByName(args.get("req item"));
        Item targetItem = AllItems.getItemByName(args.get("target item"));
        int targetCnt = Integer.parseInt(args.get("target cnt")), targetMoney = Integer.parseInt(args.get("target money"));
        if((targetCnt != 0 && targetItem == null) || reqItem == null){
            data.put("flg" , false);
            data.put("message", "invalid item name");
            return new Result(data);
        }
        int reqCnt = 0;
        try {
            reqCnt = Integer.parseInt(args.get("req cnt"));
        } catch (Exception e){
            data.put("flg" , false);
            data.put("message" , "invalid amount");
            return new Result(data);
        }
        if(targetCnt != 0 && targetMoney != 0){
            data.put("flg" , false);
            data.put("message", "gamas gamas");
            return new Result(data);
        }
        Trade trade = new Trade(game.getTrades().size(), player, receiver, args.get("type"), reqItem, targetItem, reqCnt, targetMoney, targetCnt);
        game.addTrade(trade);
        data.put("flg" , true);
        data.put("message", "trade added successfully");
        return new Result(data);
    }
    public Result tradeList(){
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        ArrayList<Trade> trades = new ArrayList<>();
        for (Trade trade : game.getTrades()) {
            if(trade.getReceiver().equals(player)){
                trades.add(trade);
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("trades", trades);
        return new Result(data);
    }
    public Result tradeResponse(HashMap<String , String > args){
        Game game = App.getCurrentGame();
        Player player = game.getCurrentPlayer();
        int ID = Integer.parseInt(args.get("ID"));
        Map<String , Object> data = new HashMap<>();
        if(ID >= game.getTrades().size()){
            data.put("flg" , false);
            data.put("message", "invalid ID");
            return new Result(data);
        }
        if(player.getBackpack().isFull()){
            data.put("flg" , false);
            data.put("message", "backpack is full!");
            return new Result(data);
        }
        Trade trade = game.getTrades().get(ID);
        if(!trade.getReceiver().equals(player)){
            data.put("flg" , false);
            data.put("message", "you don't have this trade!");
            return new Result(data);
        }
        if(args.get("respond").equals("reject")){
            data.put("flg" , true);
            data.put("message", "trade rejected successfully!");
            player.rejectTrade(trade);
            return new Result(data);
        }
        else if(args.get("respond").equals("accept")){
            boolean canAccept = true;
            if(trade.getType().equals("offer")){
                if(player.money < trade.getTargetMoney())
                    canAccept = false;
                if(trade.getTargetItem() != null && player.getBackpack().contain(trade.getTargetItem()) < trade.getTargetCnt()){
                    canAccept = false;
                }
                if(canAccept){
                    player.money -= trade.getTargetMoney();
                    if(trade.getTargetItem() != null)
                        player.getBackpack().removeItem(trade.getTargetItem(), trade.getTargetCnt());
                    player.getBackpack().putItem(trade.getReqItem(), trade.getReqCnt());
                }
            }
            else{
                Item item = trade.getReqItem();
                System.out.println("Wtf " + item.name);
                if(player.getBackpack().contain(trade.getReqItem()) < trade.getReqCnt()){
                    canAccept = false;
                    System.out.println("OH shit");
                }
                if(canAccept){
                    player.getBackpack().removeItem(trade.getReqItem(), trade.getReqCnt());
                    player.money += trade.getTargetMoney();
                    if(trade.getTargetCnt() != 0){
                        player.getBackpack().putItem(trade.getTargetItem(), trade.getTargetCnt());
                    }
                }
            }
            if(!canAccept){
                data.put("flg" , false);
                data.put("message", "you don't have wanted things!");
                return new Result(data);
            }
            player.acceptTrade(trade);
            data.put("flg" , true);
            data.put("message", "trade accepted successfully!");
            return new Result(data);
        }
        else{
            data.put("flg" , false);
            data.put("message", "invalid respond!");
            return new Result(data);
        }
    }
}
