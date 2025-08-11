package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ImproveCommands implements Commands{
    info("\\s*craft\\s+info\\s+-n\\s+(?<craftname>[\\s\\S]*[\\S])\\s*"),
    infotree("\\s*Tree\\s+info\\s+-n\\s+(?<craftname>[\\s\\S]*[\\S])\\s*"),
    infoforagingtree("\\s*Foraging\\s+Tree\\s+info\\s+-n\\s+(?<craftname>[\\s\\S]*[\\S])\\s*"),
    infoforagingcrops("\\s*Foraging\\s+crops\\s+info\\s+-n\\s+(?<craftname>[\\s\\S]*[\\S])\\s*"),
    shokhm("\\s*shokhm\\s+(?<x>[\\d]*)\\s*,\\s*(?<y>[\\d]*)\\s*"),
    plant("\\s*plant\\s+-s\\s+(?<seed>[\\s\\S]*[\\S])\\s+-d\\s+(?<direction>[\\s\\S]*[\\S])\\s*"),
    showplant("\\s*showplant\\s+-l\\s+(?<x>[\\d]*)\\s*,\\s*(?<y>[\\d]*)\\s*"),
    fertilize("\\s*fertilize\\s+-f\\s+(?<fertilizer>[\\s\\S]*[\\S])\\s+-d\\s+(?<direction>[\\d]*)\\s*"),
    dero("\\s*dero\\s+(?<x>[\\d]*)\\s*,(?<y>[\\d]*)\\s*");
    private final String pattern;
    ImproveCommands(String pattern) {
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
