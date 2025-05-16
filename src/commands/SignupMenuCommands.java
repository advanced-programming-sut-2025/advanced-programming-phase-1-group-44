package commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupMenuCommands implements Commands{
    register("register -u (?<username>\\S+) -p (?<password>\\S+)\\s+(?<passwordConfirm>\\S+) -n "+
    "(?<nickname>\\S+) -e (?<email>\\S+) -g (?<gender>\\S+)", "register"),
    pickQuestion("pick question\\s+\\-q\\s+(?<number>\\d+)\\s+\\-a\\s+(?<answer>\\S+)\\s+\\-c\\s+(?<confirmAnswer>\\S+)", "pickQuestion"),
    login("login\\s+\\-u\\s+(?<username>\\S+)\\s+\\-p\\s+(?<password>\\S+)\\s*(-stay-logged-in)?", "login"),
    forgetPassword("forget password\\s+\\-u\\s+(?<username>\\S+)", "forgetPassword"),
    answer("answer\\s+\\-a\\s+(?<answer>\\S+)", "answer");

    private final String pattern, name;

    SignupMenuCommands(String pattern, String name) {
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
