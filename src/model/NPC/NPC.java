package model.NPC;

import model.Item;

import java.util.ArrayList;

public class NPC {
    String name, job;
    ArrayList <Item> favoriteItems;
    ArrayList <Quest> quests;
    ArrayList <String> dialogues;
    public void setName(String name) {
        this.name = name;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setFavoriteItems(ArrayList<Item> favoriteItems) {
        this.favoriteItems = favoriteItems;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    public void setDialogues(ArrayList<String> dialogues) {
        this.dialogues = dialogues;
    }
}