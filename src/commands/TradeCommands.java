package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeCommands {
    trade("trade -u (?<username>\\w+) -t (?<type>\\w+) -i (?<reqItem>\\w+) -a (?<amount>\\S+)(?<price> -p (\\S+))?(?<targetItem> -ti (\\w+))?(?<targetAmount> -ta (\\S+))?", "trade"),
    trade_response("trade response -(?<type>accept|reject) -i (?<ID>\\w+)", "trade response"),
    ;
    private final String pattern, name;

    TradeCommands(String pattern, String name) {
        this.pattern = pattern;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Matcher getMatcher (String input){
        return Pattern.compile(this.pattern).matcher(input);
    }
}
