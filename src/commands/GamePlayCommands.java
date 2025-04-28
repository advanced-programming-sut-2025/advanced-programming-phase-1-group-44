package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GamePlayCommands implements Commands {
    enterMenu("enter menu\\s+(?<menuName>\\S+)", "enterMenu"),
    showMenu("show current menu", "showMenu")
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
