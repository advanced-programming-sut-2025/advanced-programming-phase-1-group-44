package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GamePlayCommands implements Commands {
    energyset("\\s*energy\\s+set\\s+-v\\s+(?<value>[\\d]*)\\s*","energySet"),
    moveanimal("\\s*shepherd\\s+animals\\s+-n\\s+(?<name>[\\s\\S]*[\\S])\\s+-l\\s+(?<x>[\\d]*)\\s*,\\s*(?<y>[\\d]*)\\s*","moveAnimal"),
    enterMenu("enter menu\\s+(?<menuName>\\S+)", "enterMenu"),
    showMenu("show current menu", "showMenu"),
    cheatTime("cheat advance time\\s+(?<time>\\d+)h", "cheatTime"),
    cheatDay("cheat advance date\\s+(?<day>\\d+)d", "cheatDate"),
    craftInfo("craftinfo -n\\s+(?<name>\\S+)", "craftInfo"),
    buildBuilding("build\\s+\\-a\\s+(?<name>\\S+\\s?\\S+)\\s+\\-l\\s+(?<x>\\d+) (?<y>\\d+)", "buildBuilding"),
    buyAnimal("buy animal -a (?<animalName>\\S+) -n (?<name>\\S+)", "buyAnimal"),
    pet("pet -n (?<name>\\S+)", "pet"),
    cheatSetFriendship("cheat set friendship -n (?<name>\\S+) \\-c (?<amount>\\d+)", "cheatSetFriendship"),
    feedHay("feed hay -n (?<name>\\S)", "feedHay"),
    collectProduct("collect produce -n (?<name>\\S+)", "collectProduce"),

    talk("talk -u (?<username>\\S+) -m (?<message>.+)", "talk"),
    talkHistory("talk history -u (?<username>\\S+)", "talkHistory"),
    gift("gift -u (?<username>\\S+) -i (?<itemName>\\S+) -a (?<amount>\\d+)", "gift"),
    rateGift("gift rate -i (?<giftId>\\S+) -r (?<rate>\\d+)", "rateGift"),
    giftHistory("gift history -u (?<username>\\S+)", "giftHistory"),
    hug("hug -u (?<username>\\S+)", "hug"),
    flower("flower -u (?<username>\\S+)", "flower"),
    askMarriage("ask marriage -u (?<username>\\S+) -r (?<ring>\\S+)", "askMarriage"),
    respondProposal("respond (?<response>\\S+) -u (?<username>\\S+)", "respondProposal"),

    cheatWeather("cheat weather set (?<type>.+)", "cheatWeather"),
    inventoryTrash("inventory trash -i (?<item>.+)", "inventory trash"),
    inventoryTrashWithNumber("inventory trash -i (?<item>.+) -n (?<number>\\d+)", "inventory trash with number"),

    craft("crafting craft (?<name>.+)", "craft"),
    cheatAddItem("cheat add item -n (?<name>.+) -c (?<cnt>\\d+)", "cheat add item"),
    cheatAddMoney("cheat add (?<count>\\d+) dollars", "cheat add money"),
    cookingPrepare("cooking prepare (?<name>.+)", "cooking prepare"),
    cookingRefrigerator("cooking refrigerator (?<type>put|pick) (?<name>.+)","cooking refrigerator"),
    Eat("eat (?<name>.+)", "eat"),
    toolsEquip("tools equip (?<name>.+)" , "tools equip"),
    toolsUpgrade("tools upgrade (?<name>.+)", "tools upgrade"),
    toolsUse("tools use -d (?<direction>(left|right)?(up|down)?)", "tools use"),

    goToStore("go to store (?<name>\\S+)", "go to store"),
    purchase("purchase (?<name>.+)", "purchase"),
    purchaseWithCount("purchase (?<name>.+) -n (?<count>\\d+)","purchase with count"),
    sell("sell (?<name>.+)", "sell"),
    sellWithCount("sell (?<name>.+) -n (?<count>\\d+)" , "sell with count"),
    ;
    private final String pattern, name;

    GamePlayCommands(String pattern, String name) {
        this.pattern = pattern;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public Matcher getMatcher (String input){
        return Pattern.compile(this.pattern).matcher(input);
    }
}
