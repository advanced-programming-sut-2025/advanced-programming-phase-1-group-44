package model;

import java.util.Map;

import model.enums.Season;

public class DateTime {
    private int time;
    private int dayOfWeek;
    private int day;
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

    public boolean endOfDay() {
        return this.time == 22;
    }
}
