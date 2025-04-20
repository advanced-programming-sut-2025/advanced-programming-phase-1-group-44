package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupLoginMenuCommands implements Commands{
    ;
    private final String pattern;

    SignupLoginMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher (String input){
        return Pattern.compile(this.pattern).matcher(input);
    }
}
