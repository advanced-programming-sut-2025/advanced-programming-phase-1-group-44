package model.enums;

import model.Item;
import model.Quest;

import java.util.ArrayList;

public enum NPCs {

    ;
    String name, job;
    ArrayList<Item> favoriteItems;
    ArrayList<Quest> quests;
    ArrayList<String> dialogues;

    NPCs(String name, String job, ArrayList<Item> favoriteItems, ArrayList<Quest> quests, ArrayList<String> dialogues) {
        this.name = name;
        this.job = job;
        this.favoriteItems = favoriteItems;
        this.quests = quests;
        this.dialogues = dialogues;
    }
}
