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
energy show
energy set -v 3
energy show
crafting craft Mayonnaise Machine
energy show
energy unlimited
energy show
energy set -v 2
inventory show
crafting craft Mayonnaise Machine
inventory show
energy show
energy set -v 10
energy show
inventory show
inventory trash -i stone -n 10
inventory show
inventory trash -i stone
inventory show
cooking show recipes
cheat add item -n egg -c 2
cooking prepare rangool
cooking prepare salad
cooking prepare fried egg
cooking refrigerator put stone
cooking refrigerator put wood
cooking refrigerator put egg
inventory show
cooking refrigerator pick egg
inventory show
cooking refrigerator put egg
inventory show
cooking prepare fried egg
inventory show
eat kalam
eat wood
energy show
eat fried egg
energy show
show all products
go to store blacksmith
show all products
show all available products
go to store jojamart
go to store stardropSaloon
show money
purchase pizza recipe
cheat add 200000 dollars
show money
purchase pizza recipe
cooking show recipes
show all products
show all available products
go to store pierresGeneralStore
show money
purchase parsnip seeds
show money
purchase melon seeds
show money
inventory show
strat trade
show current menu
end trade
show current menu
inventory show
show money
start trade
trade -u asd -t offer -i wood -a 20 -p 1000
trade -u asd2 -t offer -i wood -a 20 -p 1000
trade -u parsa -t offer -i wood -a 20 -p 1000
trade -u asd -t meow -i wood -a 20 -p 1000
trade -u asd -t offer -i woody -a 20 -p 1000
trade -u asd -t offer -i wood -a 2.0 -p 1000
trade -u asd -t offer -i wood -a 20 -ti stone -ta 10
trade -u parsa -t offer -i wood -a 20 -ti stone -ta 10
trade -u asd2 -t offer -i wood -a 20 -ti stone -ta 10
trade -u asd2 -t request -i wood -a 2.0 -ti stone -ta 10
trade list
trade response -accept -i 1
trade response -accept -i 3
trade response -reject -i 3
trade history
end trade
show money
meet NPC amirhosein
meet NPC parsa
meet NPC lia
meet NPC harvey
friendship NPC list
gift NPC lia -i wood
gift NPC robin -i wood
friendship NPC list
inventory show
show money
quests list
quests finish -i 3
inventory show
show money
print map -l 0,0 -s 150
fishing -p training rod
cheat add item -n training rod -c 1
fishing -p training rod
go to store blacksmith
tools upgrade hoe
tools upgrade hoe
tools equip axe
 */