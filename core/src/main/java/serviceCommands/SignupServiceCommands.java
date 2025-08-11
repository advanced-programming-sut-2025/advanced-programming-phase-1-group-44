package serviceCommands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupServiceCommands {
    email("(?<name>[\\w0-9]+[a-zA-Z0-9\\.\\-\\_]*[\\w0-9]+)\\@(?<domain>[\\w0-9]+[A-Za-z0-9\\-]*[\\w0-9]+\\.\\S{1,}[\\w0-9]+)"),
    password("\\S+"),
    strongPass("(?=.*\\w+)(?=.*[0-9]+)(?=.*[\\!\\@\\#\\$\\%\\^\\&\\*\\-\\_]+)\\S{8,}"),
    username("[\\w0-9\\-]{1,8}")
    ;
    private final String pattern;

    SignupServiceCommands(String pattern) {
        this.pattern = pattern;
    }

    public Matcher getMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    } 
}
