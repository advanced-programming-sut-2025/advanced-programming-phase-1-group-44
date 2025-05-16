package model;

import model.Abilities.Extracing;
import model.Abilities.Farming;
import model.Abilities.Fishing;
import model.Abilities.Foraging;
import model.Animals.Animal;
import model.Farms.FirstFarm;
import model.NPC.NPC;
import model.Tools.Backpack;
import model.Tools.TrashCan;
import model.enums.CraftingItems.CraftableItem;
import model.enums.Gender;
import model.enums.Recipe;
import model.enums.AnimalEnum.AnimalType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Player {
    private final Extracing extracing = new Extracing();
    private final Farming farming = new Farming();
    private final Fishing fishing = new Fishing();
    private final Foraging foraging = new Foraging();
    private MapFarm mapFarm=new FirstFarm();
    private String username, nickname, email, password, questionAnswer;
    private Integer questionNumber;
    private Gender gender;
    public int energy;
    public double money;
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
    private Refrigerator refrigerator; //TODO  check to move to home
    private HashMap<AnimalType, Integer> animalsBoughtToday = new HashMap<>();
    public ArrayList<Animal> animals = new ArrayList<>();
    private ArrayList<Gift> receivedGiftList = new ArrayList<>(), sentGiftList = new ArrayList<>();
    private int giftCount = 0;

    private ArrayList<MarriageRequest> marriageRequsts = new ArrayList<>();


    public void addMarriageRequest(Player sender, Item ring) {
        marriageRequsts.add(new MarriageRequest(sender, this, ring));
    }
    public boolean hasMarriageRequest(Player sender) {
        for (MarriageRequest marriageRequest : marriageRequsts) {
            if (marriageRequest.getSender().equals(sender)) {
                return true;
            }
        }
        return false;
    }
    public MarriageRequest getMarriageRequest(Player sender) {
        for (MarriageRequest marriageRequest : marriageRequsts) {
            if (marriageRequest.getSender().equals(sender)) {
                return marriageRequest;
            }
        }
        return null;
    }
    private Map<String , Integer> friendshipNPC;
    private Map<String , Boolean> firstGiftNpc , firstMeetNpc;
    public ArrayList<Animal> getAnimals() {
        return animals;
    }

    public Gender getGender() {
        return gender;
    }

    public void getGift(Item item, int amount, Player sender) {
        backpack.putItem(item, amount);
        receivedGiftList.add(new Gift(item, amount, giftCount++, sender, this));
    }
    public ArrayList<Gift> getReceivedGiftList() {
        return receivedGiftList;
    }
    public ArrayList<Gift> getSentGiftList() {
        return sentGiftList;
    }
    public boolean sendGift(Item item, int amount, Player receiver) {
        boolean ok = backpack.removeItem(item, amount);
        if (!ok) {
            return false;
        }
        sentGiftList.add(new Gift(item, amount, giftCount++, this, receiver));
        return true;
    }
    public int rateGift(int id, int rating) {
        for (Gift gift : sentGiftList) {
            if (gift.getId() == id) {
                gift.rate(rating);

                break;
            }
        }
        return (rating - 3) * 30 - 15;
    }


    public void setMapFarm(MapFarm mapFarm) {
        this.mapFarm = mapFarm;
    }

    public MapFarm getMapFarm() {
        return mapFarm;
    }
    public HashMap<AnimalType, Integer> getAnimalsBoughtToday() {
        return animalsBoughtToday;
    }
    public void buyAnimal(AnimalType animal) {
        if (animalsBoughtToday.containsKey(animal)) {
            animalsBoughtToday.put(animal, animalsBoughtToday.get(animal) + 1);
        } else {
            animalsBoughtToday.put(animal, 1);
        }
    }
        


    public void addAnimal(Animal animal) {
        this.animals.add(animal);
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
        this.refrigerator = new Refrigerator();
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
    public void decreaseMoney(int x) {this.money -= x;}
    public void spendMoney(double money) {
        this.money -= money;
    }

    public void nextDay() {
        // TODO
        animalsBoughtToday.clear();
    }

    public void removeAnimal(Animal animal) {
        this.animals.remove(animal);
        this.mapFarm.removeAnimal(animal);
    }
    public Integer getNpcFriendship(String name){
        return this.friendshipNPC.get(name);
    }
    public void addNpcFriendShip(String name, int x){
        int current = this.friendshipNPC.get(name);
        current += x;
        current = Integer.min(current, 799);
        this.friendshipNPC.put(name , current);
    }
    public boolean isFirstGiftNpc(String name){
        return !this.firstGiftNpc.get(name);
    }
    public boolean isFirstMeet(String name){
        return !this.firstMeetNpc.get(name);
    }
    public void giftNPC(String name){
        this.firstGiftNpc.put(name , true);
    }
    public void meetNPC(String name){
        this.firstMeetNpc.put(name , true);
    }
    public void ResetNpc(){
        //TODO check this
        for (NPC gameNPC : App.getCurrentGame().getGameNPCs()) {
            this.firstGiftNpc.put(gameNPC.getName(), false);
            this.firstGiftNpc.put(gameNPC.getName(), false);
        }
    }
    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public Refrigerator getRefrigerator() {
        return refrigerator;
    }
    public Recipe getRecipe(String name){
        for (Recipe recipe : recipes) {
            if(recipe.getName().equalsIgnoreCase(name)){
                return recipe;
            }
        }
        return null;
    }
}