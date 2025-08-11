package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameViewCommands implements Commands {
    enterMenu("enter menu\\s+(?<menuName>\\S+)"),
    showMenu("show current menu"),
    cheatTime("cheat advance time\\s+(?<time>\\d+)h"),
    cheatDay("cheat advance date\\s+(?<day>\\d+)d"),
    craftInfo("craftinfo -n\\s+(?<name>\\S+)");
    private final String pattern;

    GameViewCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }
}
