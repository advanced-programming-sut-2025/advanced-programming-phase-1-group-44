package model;

import java.util.ArrayList;

public class GroupQuest {
    public String itemName;
    public int reqCnt , capacity , reward , time;
    public DateTime startTime, finishTime;
    public ArrayList<String> players = new ArrayList<>();

    public GroupQuest() {
    }

    public GroupQuest(String itemName, int reqCnt, int reward, int capacity , int time) {
        this.itemName = itemName;
        this.reqCnt = reqCnt;
        this.reward = reward;
        this.capacity = capacity;
        this.time = time;
    }

    public void addPlayer(String name){
        if (!isFull()) {
            players.add(name);
        }
    }

    public boolean isFull() {
        return players.size() >= capacity;
    }

    public void startQuest(DateTime startTime){
        this.startTime = startTime;
        this.finishTime = startTime;
        for(int i = 0 ; i < time ; i++)
            finishTime.nextDay();
    }

    public int getRemainingTime(){
        return startTime.exactDiff(startTime, finishTime);
    }
}
