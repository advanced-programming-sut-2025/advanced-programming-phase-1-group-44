package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands implements Commands{
    gamenew4user("\\s*game\\s+new\\s+-u\\s+(?<username1>[\\s\\S]*[\\S])\\s+(?<username2>[\\s\\S]*[\\S])\\s+(?<username3>[\\s\\S]*[\\S])\\s+(?<username4>[\\s\\S]*[\\S])\\s*"),
    gamenew3user("\\s*game\\s+new\\s+-u\\s+(?<username1>[\\s\\S]*[\\S])\\s+(?<username2>[\\s\\S]*[\\S])\\s+(?<username3>[\\s\\S]*[\\S])\\s*"),
    gamenew2user("\\s*game\\s+new\\s+-u\\s+(?<username1>[\\s\\S]*[\\S])\\s+(?<username2>[\\s\\S]*[\\S])\\s*"),
    gamenew1user("\\s*game\\s+new\\s+-u\\s+(?<username1>[\\s\\S]*[\\S])\\s*"),
    gamenew("\\s*game\\s+new[\\s\\S]*"),
    gamemap("\\s*game\\s+map\\s+(?<mapnumber>[1|2])\\s*"),
    loadgame("\\s*load\\s+game\\s*"),
    exitgame("\\s*exit\\s+game\\s*"),
    nextturn("\\s*next\\s+turn\\s*");

    private final String pattern;

    GameMenuCommands(String pattern) {
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
    }



}
