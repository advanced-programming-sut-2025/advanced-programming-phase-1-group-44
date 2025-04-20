package view;

import java.util.Scanner;

import model.App;
import model.enums.Menu;

public class AppView {
    public void run() {
        Scanner IOScanner = new Scanner(System.in);
        while (App.getGetCurrentMenu() != Menu.ExitMenu) {
            App.getGetCurrentMenu().processInput(IOScanner);
        }
    }
}
