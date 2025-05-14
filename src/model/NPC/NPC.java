package model.NPC;

import model.App;
import model.Item;
import model.MapObj;
import model.enums.Weather;

import java.util.ArrayList;

public class NPC extends MapObj {
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
    public String talk(){
        Weather weather = App.getCurrentGame().weather;
        if(weather.equals(Weather.Sunny)){
            return dialogues.get(1);
        }
        return dialogues.get(0);
    }
}