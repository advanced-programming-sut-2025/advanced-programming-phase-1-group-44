package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Mapcommands implements Commands{
    walk("\\s*walk\\s+-l\\s+(?<x>[\\d]*)\\s*,\\s*(?<y>[\\d]*)\\s*"),
    printmap("\\s*print\\s+map\\s+-l\\s+(?<x>[\\d]*)\\s*,\\s*(?<y>[\\d]*)\\s*-s\\s*(?<size>[\\d]*)\\s*"),
    helpreadingmap("\\s*help\\s+reading\\s+map\\s*");
    private final String pattern;
    Mapcommands(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }
}
