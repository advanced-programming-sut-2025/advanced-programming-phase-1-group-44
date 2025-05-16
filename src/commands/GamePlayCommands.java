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
    buildBuilding("build\\-a\\S+(?<name>\\S+)\\S+\\-l\\S+(?<x>\\d+) (?<y>\\d+)", "buildBuilding"),
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

    cheatWeather("cheat weather set (?<type>\\S+)", "cheatWeather"),
    inventoryTrash("inventory trash -i (?<item>\\S+)", "inventory trash"),
    inventoryTrashWithNumber("inventory trash -i (?<item>\\S+) -n (?<number>\\d+)", "inventory trash with number"),

    toolsEquip("tools equip (?<name>\\S+)" , "tools equip");

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
