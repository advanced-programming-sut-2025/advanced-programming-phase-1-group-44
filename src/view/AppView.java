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
/*
register -u asd -p qazwsxecdA1385@a qazwsxecdA1385@a -n asd -e asd@gmail.com -g male
pick question -q 1 -a blue -c blue
register -u asd1 -p qazwsxecdA1385@a1 qazwsxecdA1385@a1 -n asd1 -e asd1@gmail.com -g male
pick question -q 1 -a blue -c blue
register -u asd2 -p qazwsxecdA1385@a2 qazwsxecdA1385@a2 -n asd2 -e asd2@gmail.com -g male
pick question -q 1 -a blue -c blue
login -u asd -p qazwsxecdA1385@a

menu enter game

game new -u
game new -u <username_1> <username_2> <username_3> amirhosein
game new -u asd1 asd2
game map 2
next turn
game map 1
next turn
game map 2
next turn

menu enter main
menu enter play
show current menu
 */