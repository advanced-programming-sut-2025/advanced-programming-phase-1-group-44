package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands implements Commands{
    login("\\s*login\\s+-u\\s+(?<username>[\\s\\S]*[\\S])\\s+-p\\s+(?<password>[\\s\\S]*[\\S])\\s+–stay-logged-in\\s*"),
    forgetPassword("\\s*forget\\s+password\\s+-u\\s+(?<username>[\\s\\S]*[\\S])\\s*");
    private final String pattern;

    LoginMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }
}
