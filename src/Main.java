import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

class Main {
    //creating a method to be used for recursion so the program can be run multiple times, cleaner than using main for recursion

    public static void login(){
        zooFunctions access = new zooFunctions();
        zooKeeper current = null;
        String name = access.getInfo("What is your name?");
        String password =access.getInfo("What is your password");
        zooKeeper[] zooKeepers = access.getZooKeepers();
        for(zooKeeper i: zooKeepers){
            if(i.name.equals(name) && i.password.equals(password)){
                current = i;
            }
        }
        if(current == null){
            System.out.println("Name or password is incorrect, please try again.");
            login();
        }else{menu(zooKeepers,current);}
    }
    public static void menu(zooKeeper[] zookeepers, zooKeeper current){
        String lastMaintenceDate = null;
        // zooData will hold data on the zoo, last time the animals were checked on, last time maintence was performed, etc...
        zooFunctions access = new zooFunctions();
        //2d array, an array that contains 3 smaller arrays which contain the animal classes
        animal[][] animals = {access.getData(1),access.getData(2),access.getData(3)};
        int choice = Integer.parseInt(access.getInfo("Welcome to Belfast Zoo what would you like to do?\n(1 Add an animal\n(2 Perform maintenance on the animals\n(3 Check on the animals in the zoo\n(4 View zoo data"));

        if(choice == 1){
            //works
            int type = Integer.parseInt(access.getInfo("What type of animal would you like to add?\n(1 monkey\n(2 Bird of Prey\n(3 Big cat"))-1;
            //convert the original array into a list so elements can be added easier
            ArrayList<animal> animalList = new ArrayList<>(Arrays.asList(animals[type]));
            animalList.add(access.addAnimal(type));
            System.out.println(animalList);
            //set the sub array of animals equal to the new array being created (old array + new animal)
            animals[type] = animalList.toArray(animal[]::new);
            System.out.println("Animal added");

        } else if (choice == 2) {
            //works
            animal creature = access.findAnimal(animals);
            System.out.println(creature.health);
            if(creature.type == 2){
                birdOfPrey bird = (birdOfPrey) creature;
                System.out.println(bird.wingHealth);
            }
            if(access.getInfo("Was maintence perfomed?(Y/N)").toUpperCase(Locale.ROOT).equals("Y")){
                creature.heal();
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                lastMaintenceDate = myDateObj.format(myFormatObj);
                creature.lastMaintenance = lastMaintenceDate;
                creature.latestZooKeeper = current;
            }
        } else if (choice==3) {
            //works
            String dec = access.getInfo("Would you like to (1 look at all animals (2 search for a particular animal");
            if(dec.equals("1")) {
                //loops through the container array(3 elements)
                for (int i = 0; i < animals.length; i++) {
                    //loops through each individual class to get each animal class
                    for (int j = 0; j < animals[i].length; j++) {
                        //i = the type of animal, j = the specific animal
                        System.out.println(animals[i][j].toString());
                    }
                }
            }else if(dec.equals("2")){
                animal beast = access.findAnimal(animals);
                if(!(beast == null)){
                    System.out.println(beast.toString());
                }else{
                    System.out.println("Sorry, the animal could not be found");
                }
            }

        } else if (choice ==4){
            System.out.println(access.getZooData(animals,current,"notfile"));
        }else{
            System.out.println("Please choose a valid option, submit by your choice by entering the number assigned to the option you want");
        }
        String restart = access.getInfo("Would you like to go again(Y/N)?");
        if(restart.toUpperCase(Locale.ROOT).equals("Y")){
            menu(zookeepers,current);
        }else if(restart.toUpperCase(Locale.ROOT).equals("N")){
            System.out.println("Saving animal and zoo data...");
            access.saveAnimalZooData(animals,current);
            System.out.println("Program shutting down, goodbye");
        }
    }
    // Entry point into the program
    static void main(String[] args){
        login();
    }
}