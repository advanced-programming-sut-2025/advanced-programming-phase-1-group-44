package view;

import java.util.Scanner;

import controller.GamePlayController;
import model.App;
import model.enums.Menu;

public class AppView {
    public void run() {
        Scanner IOScanner = new Scanner(System.in);
        GamePlayController gm=new GamePlayController();
        while (App.getGetCurrentMenu() != Menu.ExitMenu) {
            App.getGetCurrentMenu().processInput(IOScanner);
        }
    }
}
