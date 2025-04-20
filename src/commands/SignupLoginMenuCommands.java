package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupLoginMenuCommands implements Commands{
    enterMenu("enter menu\\s+(?<menuName>\\S+)", "enterMenu"),
    exit("menu exit", "exit"),
    showMenu("show current menu", "showMenu"),
    register("register -u (?<username>\\S+) -p (?<password>\\S+) (?<password_confirm>\\S+) -n "+
    "(?<nickname>\\S+) -e (?<email>\\S+) -g (?<gender>\\S+)", "register");

    private final String pattern, name;

    SignupLoginMenuCommands(String pattern, String name) {
        this.pattern = pattern;
        this.name = name;
    }

    @Override
    public Matcher getMatcher (String input){
        return Pattern.compile(this.pattern).matcher(input);
    }
}
