package model;

import model.Abilities.Extracing;
import model.Abilities.Farming;
import model.Abilities.Fishing;
import model.Abilities.Foraging;
import model.Farms.FirstFarm;
import model.Tools.Backpack;
import model.Tools.TrashCan;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Gender;
import model.enums.Recipe;

import java.util.ArrayList;

public class Player {
    private final Extracing extracing = new Extracing();
    private final Farming farming = new Farming();
    private final Fishing fishing = new Fishing();
    private final Foraging foraging = new Foraging();
    private MapFarm mapFarm=new FirstFarm();
    private String username, nickname, email, password, questionAnswer;
    private Integer questionNumber;
    private Gender gender;
    public int energy , money;
    public boolean unlimitedEnergy;
    private Backpack backpack = new Backpack();
    private TrashCan trashCan;
    private int Xlocation=1,Ylocation=1;
    private MapFarm currentfarm=null;

    public void setXlocation(int xlocation) {
        Xlocation = xlocation;
    }

    public void setYlocation(int ylocation) {
        Ylocation = ylocation;
    }

    public void setCurrentfarm(MapFarm currentfarm) {
        this.currentfarm = currentfarm;
    }

    public MapFarm getCurrentfarm() {
        return currentfarm;
    }

    public void setMapFarm(MapFarm mapFarm) {
        this.mapFarm = mapFarm;
    }

    public MapFarm getMapFarm() {
        return mapFarm;
    }

    private ArrayList<Recipe> recipes;
    private ArrayList<CraftableItem> craftableItems;

    public Player(String username, String password, String nickname, String email, String gender) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        if (gender.equals("male")) this.gender = Gender.MALE;
        else this.gender = Gender.FEMALE;
    }

    public void setQuestion(int questionNumber, String answer) {
        this.questionNumber = questionNumber;
        this.questionAnswer = answer;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }



    public void plant() {

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    // TO-DO
    public Integer getMaxScore() {
        // TODO
        return 0;
    }
    public Integer getNumberGamesPlayed() {
        // TODO
        return 0;
    }

    public Extracing getExtracing() {
        return extracing;
    }

    public Farming getFarming() {
        return farming;
    }

    public Fishing getFishing() {
        return fishing;
    }

    public Foraging getForaging() {
        return foraging;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public TrashCan getTrashCan() {
        return trashCan;
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }

    public ArrayList<CraftableItem> getCraftableItems() {
        return craftableItems;
    }
    public CraftableItem CanCraft(String itemName){
        for (CraftableItem craftableItem : craftableItems) {
            if(craftableItem.getName().equalsIgnoreCase(itemName)){
                return craftableItem;
            }
        }
        return null;
    }

    public int getEnergy() {
        return energy;
    }
    public void decreaseEnergy(int x){
        this.energy -= x;
    }
}