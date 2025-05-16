package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands implements Commands{
    showMenu("show current menu", "showMenu"),
    changeUsername("change username\\s+\\-u\\s+(?<username>\\S+)", "changeUsername"),
    changeNickname("change nickname\\s+\\-u\\s+(?<nickname>\\S+)", "changeNickname"),
    changeEmail("change email\\s+\\-u\\s+(?<email>\\S+)", "changeEmail"),
    changePassword("change password\\s+\\-p\\s+(?<password>\\s+)\\s+\\-o\\s+(?<oldPassword>\\S+)", "changePassword"),
    userinfo("\\s*uesr\\s+info\\s*","uesrinfo");
    private final String pattern, name;

    ProfileMenuCommands(String pattern, String name) {
        this.pattern = pattern;
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public Matcher getMatcher (String input){
        return Pattern.compile(this.pattern).matcher(input);
    }
}
