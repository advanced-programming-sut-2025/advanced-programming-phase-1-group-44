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
            if(App.getGetCurrentMenu()!=Menu.SignupLoginMenu){
                gm.nextTurn();
            }
            App.getGetCurrentMenu().processInput(IOScanner);
        }
    }
}
