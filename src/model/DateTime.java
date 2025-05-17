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

    public void nextHour() {
        this.time++;
        if (this.time == 23) nextDay();;
    }
    

    public boolean areEqual(DateTime d1, DateTime d2) {
        if (d1.getTime() != d2.getTime()) return false;
        if (!d1.getDate().equals(d2.getDate())) return false;
        if (d1.getDayOfWeek() != d2.getDayOfWeek()) return false;
        return true;
    }

    boolean comp(DateTime d1, DateTime d2) {
        if (d2.getSeason().getID() > d1.getSeason().getID()) return true;
        if (d2.getDay() > d1.getDay()) return true;
        if (d2.getTime() > d1.getTime()) return true;
        return false;
    }

    public int exactDiff(DateTime date1, DateTime date2) {
        DateTime d1 = date1.clone(), d2 = date2.clone();
        int hours = 0;
        if (comp(d1, d2)) {
            while (!areEqual(d1, d2)) {
                d1.nextHour();
                hours++;
            }
        }
        else {
            while (!areEqual(d1, d2)) {
                d2.nextHour();
                hours++;
            }
        }
        return hours;
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

    public static int DateDiff(DateTime date1, DateTime date2) {
        int diff = 0;
        if (date1.season == date2.season) {
            diff = date1.day - date2.day;
        } else {
            diff = (date1.season.getNumberOfDays() - date1.day) + date2.day;
            
        }
        return diff;
    }

    public DateTime clone() {
        DateTime clone = new DateTime();
        clone.time = this.time;
        clone.dayOfWeek = this.dayOfWeek;
        clone.day = this.day;
        clone.season = this.season;
        return clone;
    }
}