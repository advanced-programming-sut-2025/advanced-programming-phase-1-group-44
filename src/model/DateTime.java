package model;

import java.util.Map;

import model.enums.Season;

public class DateTime {
    private int time; // clock
    private int dayOfWeek; // day of the week 1-7
    private int day; // day of the month 1-28
    private Season season;
    private static final Map<Season, Season> nextSeason = Map.of(
            Season.SPRING, Season.SUMMER,
            Season.SUMMER, Season.FALL,
            Season.FALL, Season.WINTER,
            Season.WINTER, Season.SPRING
    );


    public DateTime() {
        this.time = 9;
        this.dayOfWeek = 1;
        this.day = 1;
        this.season = Season.SPRING;
    }

    public void nextDay() {
        this.day++;
        if (this.day > this.season.getNumberOfDays()) {
            this.day = 1;
            season = nextSeason.get(season);
        }
        this.dayOfWeek++;
        if (this.dayOfWeek > 7) {
            this.dayOfWeek = 1;
        }
        this.time = 9;
    }

    public void setClock(int time) {
        this.time = time;
    }

    public boolean endOfDay() {
        return this.time == 22;
    }

    public String getDate() {
        return day + " of " + season.toString();
    }
    public int getDay() {
        return day;
    }
    public int getDayOfWeek() {
        return dayOfWeek;
    }
    public Season getSeason() {
        return season;
    }
    public int getTime() {
        return time;
    }

}
