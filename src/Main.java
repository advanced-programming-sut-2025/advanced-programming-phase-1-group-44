import view.AppView;

public class Main {
    public static void main(String[] args) {
        (new AppView()).run();
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

menu enter main
menu enter play
show current menu
print map -l 0,0 -s 150
walk -l 1,7
YES
print map -l 0,0 -s 150
crafting show recipes
cheat add item -n wood -c 100
cheat add item -n stone -c 100
crafting craft Mayonnaise Machine
cheat add item -n copper bar -c 100
crafting craft Mayonnaise Machine
 */