package view;

import java.util.Scanner;

public class GameMenu implements AppMenu {
    @Override
    public void process(Scanner IOScanner) {
        
        if ((matcher = getMatcher("enterMenu", input)).matches()) {
            System.out.println("for enetering menu go to main menu");
        }
    }
}

