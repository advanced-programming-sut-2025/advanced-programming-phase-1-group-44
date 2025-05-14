package model.NPC;

import model.Item;
import model.enums.AllItems;
import model.enums.Material;

import java.util.ArrayList;
import java.util.Arrays;

public class NPCDirector {
    public NPC constructSebastian(NPCBuilder builder){
        builder.buildName("Sebastian");
        builder.buildJob("plumber");
        ArrayList <Item> items = new ArrayList<>(Arrays.asList(AllItems.Cotton.getItemByType(), AllItems.Pumpkin_Pie.getItemByType(), AllItems.Pizza.getItemByType()));
        builder.buildFavorites(items);
        ArrayList <Quest> quests = new ArrayList<>();
        quests.add(new TradeQuest(AllItems.Iron.getItemByType(), 50, AllItems.Diamond.getItemByType(), 2 , 0 ,0));
        quests.add(new TradeQuest(AllItems.Pumpkin_Pie.getItemByType(), 1, null , 0, 5000, 0));
        quests.add(new TradeQuest(AllItems.Stone.getItemByType(), 150, AllItems.Quartz.getItemByType(), 50, 0 , 0));
        builder.buildQuests(quests);
        ArrayList <String> dialogues = new ArrayList<>();
        dialogues.add("Hello my Friend");
        dialogues.add("tung tung tung tung tung sahur");
        builder.buildDialogues(dialogues);
        return builder.getNpc();
    }
    public NPC constructAbigail(NPCBuilder builder){
        builder.buildName("Abigail");
        builder.buildJob("author");
        ArrayList <Item> items = new ArrayList<>();
        items.add(AllItems.Stone.getItemByType());
        items.add(AllItems.Iron_Ore.getItemByType());
        items.add(AllItems.Coffee.getItemByType());
        builder.buildFavorites(items);
        ArrayList <Quest> quests = new ArrayList<>();
        quests.add(new TradeQuest(AllItems.Gold_Bar.getItemByType(), 1, null, 0 , 0 ,200));
        quests.add(new TradeQuest(AllItems.Pumpkin.getItemByType(), 1, null, 0 , 500, 0));
        Item tmp = AllItems.Automatic_sprinkler.getItemByType();
        tmp.setType(Material.iridium);
        quests.add(new TradeQuest(AllItems.Wheat.getItemByType(), 50, tmp, 1, 0 , 0));
        builder.buildQuests(quests);
        ArrayList <String> dialogues = new ArrayList<>();
        dialogues.add("Hi puda!");
        dialogues.add("Boombardilo corocodila");
        builder.buildDialogues(dialogues);
        return builder.getNpc();
    }
    public NPC constructHarvey(NPCBuilder builder){
        builder.buildName("Harvey");
        builder.buildJob("Drug Dealer(AKA saghi)");
        ArrayList <Item> items = new ArrayList<>();
        items.add(AllItems.Coffee.getItemByType());
        items.add(AllItems.Pickle.getItemByType());
        items.add(AllItems.Wine.getItemByType());
        builder.buildFavorites(items);
        ArrayList <Quest> quests = new ArrayList<>();
        quests.add(new SamePlantQuest(12, null , 0 , 750, 0));
        quests.add(new TradeQuest(AllItems.Salmon.getItemByType(), 1, null, 0 , 0, 200));
        quests.add(new TradeQuest(AllItems.Wine.getItemByType(), 1, AllItems.Salad.getItemByType(), 5, 0 , 0));
        builder.buildQuests(quests);
        ArrayList <String> dialogues = new ArrayList<>();
        dialogues.add("Chao Ragazzi");
        dialogues.add("tralalelo tralala");
        builder.buildDialogues(dialogues);
        return builder.getNpc();
    }
    public NPC constructLia(NPCBuilder builder){
        builder.buildName("Lia");
        builder.buildJob("Musician");
        ArrayList <Item> items = new ArrayList<>();
        items.add(AllItems.Wine.getItemByType());
        items.add(AllItems.Salad.getItemByType());
        items.add(AllItems.Grape.getItemByType());
        builder.buildFavorites(items);
        ArrayList<Quest> quests = new ArrayList<>();
        quests.add(new TradeQuest(AllItems.Wood.getItemByType(), 10, null , 0 , 500 , 0));
        quests.add(new TradeQuest(AllItems.Salmon.getItemByType(), 1, ))
    }
}
