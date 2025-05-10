package model;

import model.enums.Weather;

import java.util.ArrayList;

public class Game {
    public DateTime dateTime;
    private ArrayList<Player> users , loggedInUsers;
    public Weather weather , nextDayWeather;
    private Boolean fixedWeather;
    private Player currentPlayer;

    Game() {
        dateTime = new DateTime();
    }


    public DateTime getDateTime() {
        return dateTime;
    }

    public void setClock(int clock) {
        dateTime.setClock(clock);
    }

    public void nextDay() {
        // TODO
    }

    public void setNextDayWeather(Weather nextDayWeather) {
        this.nextDayWeather = nextDayWeather;
        this.fixedWeather = true;
    }
    public void resetFixedWeather(){
        this.fixedWeather = false;
    }
    public Player getCurrentPlayer(){
        //TODO fix this
        return currentPlayer;
    }
}
