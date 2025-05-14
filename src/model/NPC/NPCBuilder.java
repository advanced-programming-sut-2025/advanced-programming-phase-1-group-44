package model.NPC;

import model.Item;

import java.util.ArrayList;

public class NPCBuilder {
    NPC npc = new NPC();
    public void buildName(String name){
        npc.setName(name);
    }
    public void buildJob(String job){
        npc.setJob(job);
    }
    public void buildFavorites(ArrayList<Item> favoriteItems){
        npc.setFavoriteItems(favoriteItems);
    }
    public void buildQuests(ArrayList<Quest> quests){
        npc.setQuests(quests);
    }
    public void buildDialogues(ArrayList<String> dialogues){
        npc.setDialogues(dialogues);
    }
    public NPC getNpc(){
        return npc;
    }
}
