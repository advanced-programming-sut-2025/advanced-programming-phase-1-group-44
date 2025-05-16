package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GamePlayCommands implements Commands {
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
