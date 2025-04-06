package model.enums;

import java.util.regex.Matcher;

public interface Commands {
    public Matcher getMatcher(String input);
}
