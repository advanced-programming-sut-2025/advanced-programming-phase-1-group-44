package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MenuesCommands implements Commands{
    enterMenu("menu enter\\s+(?<menuName>\\S+)"),
    exit("menu exit"),
    showMenu("show current menu");
    private final String pattern;

    MenuesCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input)
    {
        Matcher matcher = Pattern.compile(this.pattern).matcher(input);
        if(matcher.matches()){
            return matcher;
        }
        return null;
    }}
