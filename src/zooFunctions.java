import java.util.*;
import java.io.File;
import java.io.FileWriter;
class zooFunctions{
    // gets the records for a certain type of animal from the text files, puts into an array then returns it
    public animal[] getData(int animalType){
        try {
            //open file
            File animalData = new File("Resources/animalData.txt");
            //create scanner for file
            Scanner FileReader = new Scanner(animalData);
            //create empty animals array for the animal type
            ArrayList<animal> animals = new ArrayList<>();
            //while there is still more lines in the file
            int count =0;
            while (FileReader.hasNextLine()){
                //split the animal details into an array
                String[] details = FileReader.nextLine().split(",");
                //get the animal type as int, 1= monkey, 2 = bird of prey, 3 = big cat
                int type = Integer.parseInt(details[details.length-1]);
                if(type==animalType){
                    animals.add(addAnimal(animalType, details));
                }
            }
            return animals.toArray(new animal[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    Scanner keyboard = new Scanner(System.in);
    public String getInfo(String words){
        System.out.println(words);
        return keyboard.nextLine();
    }
    //method for allowing the user to add animals
    public animal addAnimal(int animalType){
        return switch (animalType) {
            case 0 ->
                    new monkey(getInfo("Name?"), getInfo("Colour?"), Integer.parseInt(getInfo("Age?")), Integer.parseInt(getInfo("Weight?")), getInfo("Species?"), getInfo("Health?(Good,bad)"));
            case 1 ->
                    new birdOfPrey(getInfo("Name?"), getInfo("Colour?"), Integer.parseInt(getInfo("Age?")), Integer.parseInt(getInfo("Weight?")), getInfo("Species?"), getInfo("Wing health?(Good,Bad)"), getInfo("Health?(Good,bad)"), Boolean.parseBoolean(getInfo("Flightless?(true/false)")));
            case 2 ->
                    new bigCat(getInfo("Name?"), getInfo("Colour?"), Integer.parseInt(getInfo("Age?")), Integer.parseInt(getInfo("Weight?")), getInfo("Species?"), getInfo("Health?(Good,bad)"));
            default -> null;
        };
    }
    //method for the system adding animals. using overriding for simplicity and polymorphism
    public animal addAnimal(int animalType,String[] details){
        return switch (animalType) {
            case 1 ->
                    new monkey(details[0], details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]), details[4], details[5]);
            case 2 ->
                    new birdOfPrey(details[0], details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]), details[4], details[5],details[6],Boolean.parseBoolean(details[7]));
            case 3 ->
                    new bigCat(details[0], details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]), details[4], details[5]);
            default -> null;
        };
    }
    public zooKeeper[] getZooKeepers() {
        try {
            File animalData = new File("Resources/zooKeeperData.txt");
            Scanner FileReader = new Scanner(animalData);
            ArrayList<zooKeeper> zooKeepers = new ArrayList<>();
            int count = 0;
            while (FileReader.hasNextLine()) {
                String[] details = FileReader.nextLine().split(",");
                zooKeepers.add(new zooKeeper(details[0], details[1]));
            }
            return zooKeepers.toArray(new zooKeeper[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public String getAnimalColour(animal[] creatures){
        String colour = "";
        int count = 0;
        ArrayList<String> uniqueColours = new ArrayList<>();
        ArrayList<String> allColours = new ArrayList<>();
        for(animal i: creatures){
            if(!uniqueColours.contains(i.colour)){
                uniqueColours.add(i.colour);
            }
            allColours.add(i.colour);
        }
        for(String j: uniqueColours){
            if(Collections.frequency(allColours,j)>count){
              count = Collections.frequency(allColours,j);
              colour = j;
            }
        }
        return colour;
    }
    public String getZooData(animal[][] animals,zooKeeper currentKeeper,String choice){
        if(choice.equals("file")){
            int numAnimals = 0;
            for(int i=0; i <animals.length;i++){
                numAnimals += animals[i].length;
            }
            //name, number of animals, predominate colour, last time animals were checked up on
            return"Belfast Zoo,"+numAnimals+","+currentKeeper.name;
        }
        int numAnimals = 0;
        String data = "Zoo: Belfast Zoo\n"+"Number of animals: ";
        int[] specific = new int [3];
        for(int i=0; i <animals.length;i++){
            specific[i] = animals[i].length;
            numAnimals+= animals[i].length;
        }
        data+= "\nNumber of monkeys: "+specific[0]+"\nNumber of big cats: "+specific[1]+"\nNumber of birds: "+specific[2] + "\nTotal: "+numAnimals;
        data+="\nPredominate monkey colour: "+ getAnimalColour(animals[0])+"\nPredominate big cat colour: "+getAnimalColour(animals[1])+"\nPredominate bird colour: "+getAnimalColour(animals[2]);
        return+numAnimals+"Logged in zookeeper: "+currentKeeper.name+data;
    }
    public String saveAnimalZooData(animal[][] animals, zooKeeper current) {
        try {
            FileWriter animalfile = new FileWriter("Resources/animalData.txt");
            FileWriter zooFile = new FileWriter("Resources/zooData.txt");
            for (int i = 0; i < animals.length; i++) {
                System.out.println(i);
                for (int j = 0; j < animals[i].length; j++) {
                    animalfile.write(animals[i][j].getAnimalData()+"\r\n");
                }
            }
            zooFile.write(getZooData(animals,current,"file"));
            animalfile.flush();
            zooFile.flush();
            return "Successfully saved animal and zoo data";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public animal findAnimal(animal[][] animals){
        String name = getInfo("What is the name of the animal you're looking for?");
        String species = getInfo("What is the species of the animal you're looking for?");
        //loop through animals array
        boolean found = false;
        for(int i =0; i < animals.length;i++){
            //if the first class of that array does not have the target species, skip it
            if(animals[i].length == 0 || !animals[i][0].species.equals(species)){
                continue;
            }
            //if its the right species, iterate through it until the specific animal is found
            for(int j=0; j<animals[i].length;j++){
                if(animals[i][j].name.equals(name)){
                    found = true;
                    return animals[i][j];
                }
            }
        }
            return null;
    }
}