package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Commands{
    showMenu("show current menu", "showMenu"),
    energySET("energy set -v (?<value>\\d+)", "energySet"),
    ;
    private final String pattern, name;

    GameMenuCommands(String pattern, String name) {
        this.pattern = pattern;
        this.name = name;
    }

    @Override
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }

    

}
