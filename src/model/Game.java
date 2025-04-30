package model;

import java.util.ArrayList;

public class Game {
    public DateTime dateTime;
    private ArrayList<Player> users , loggedInUsers;

    Game() {
        dateTime = new DateTime();
    }


    public DateTime getDateTime() {
        return dateTime;
    }

    
}
