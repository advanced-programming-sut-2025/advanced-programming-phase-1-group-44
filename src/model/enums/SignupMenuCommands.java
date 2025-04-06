package model.enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupMenuCommands implements Commands{
    ;
    private final String pattern;

    SignupMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher (String input){
        return Pattern.compile(this.pattern).matcher(input);
    }
}
