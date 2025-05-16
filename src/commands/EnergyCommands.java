package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EnergyCommands implements Commands{
    energyshow("\\s*energy\\s+show\\s*"),
    cheatenergyset("\\s*energy\\s+set\\s+-v\\s*(?<value>[\\d]*)\\s*");

    private final String pattern;
    EnergyCommands(String pattern) {
        this.pattern = pattern;
    }
    @Override
    public Matcher getMatcher(String input) {
        return Pattern.compile(this.pattern).matcher(input);
    }

}
