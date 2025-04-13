package model;

import java.io.FileReader;

public class Friendship {
    private final Player person1, person2;
    private int level = 0;

    Friendship(Player person1, Player person2) {
        this.person1 = person1;
        this.person2 = person2;
    }

    public void askMarriage() {}
}
