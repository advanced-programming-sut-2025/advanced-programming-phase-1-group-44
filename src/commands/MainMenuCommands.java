package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands implements Commands{
    logouot("\\s*user\\s+logout\\s*"),
    changeusername("change\\s+username\\s+-u\\s+(?<username>[\\s\\S]*[\\S])\\s*"),
    changenickname("\\s*change\\s+nickname\\s+-u\\s+(?<nickname>[\\s\\S]*[\\S])\\s*"),
    changeemail("\\s*change\\s+email\\s+-e\\s+(?<email>[\\s\\S]*[\\S])\\s*"),
    changepass("\\s*change\\s+password\\s+-p\\s+(?<new_password>[\\s\\S]*[\\S])\\s+-o\\s+(?<old_password>[\\s\\S]*[\\S])\\s*"),
    userinfo("uesr info");
    private final String pattern;
    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }
}
