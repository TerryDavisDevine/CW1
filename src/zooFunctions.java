import java.util.*;
import java.io.File;
import java.io.FileWriter;
class zooFunctions{
    // gets the records for a certain type of animal from the text files, puts into an array then returns it
    public animal[] getData(int animalType){
        try {
            //open file
            File animalData = new File("Resources/AnimalDetails.txt");
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
                int type = Integer.parseInt(details[8]);
                if(type==animalType){
                    animals.add(addAnimal(animalType, details));
                }
            }
            return animals.toArray(new animal[0]);
        } catch (Exception e) {
            try {
                System.out.println("The animal data file could not be found, a new file is being created");
                File animalData = new File("Resources/AnimalDetails.txt");
                if(animalData.createNewFile()){
                    System.out.println("New file created");
                }
                return null;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }
    Scanner keyboard = new Scanner(System.in);
    public String getInfo(String words){
        System.out.println(words);
        return keyboard.nextLine();
    }
    //method for allowing the user to add animals
    public animal addAnimal(int animalType) {
        try {
            return switch (animalType) {
                case 0 ->
                        new monkey(getInfo("Name?"), getInfo("Colour?"), Integer.parseInt(getInfo("Age?")), Integer.parseInt(getInfo("Weight?")), getInfo("Species?"), getInfo("Health?(Good,bad)"), getInfo("Last maintenance?"), getInfo("Last zookeeper?"), getInfo("Escape risk (1-10)?"));
                case 1 ->
                        new birdOfPrey(getInfo("Name?"), getInfo("Colour?"), Integer.parseInt(getInfo("Age?")), Integer.parseInt(getInfo("Weight?")), getInfo("Species?"), getInfo("Health?(Good,bad)"),getInfo("Last maintenance?"), getInfo("Last zookeeper?"), getInfo("Wing health?(Good,Bad)"), Boolean.parseBoolean(getInfo("Flightless?(true/false)")));
                case 2 ->
                        new bigCat(getInfo("Name?"), getInfo("Colour?"), Integer.parseInt(getInfo("Age?")), Integer.parseInt(getInfo("Weight?")), getInfo("Species?"), getInfo("Health?(Good,bad)"), getInfo("Last maintenance?"), getInfo("Last zookeeper?"), Boolean.parseBoolean(getInfo("Can it swim?(true/false)")));
                default -> null;
            };
        }catch(NumberFormatException e){
            System.out.println("Please enter a valid value;");
        }
        return null;
    }
    //method for the system adding animals. using overriding for polymorphism
    public animal addAnimal(int animalType,String[] details){
        return switch (animalType) {
            case 1 ->
                    //name,colour,age,weight,species,health,lastmaintence,lastzookeeper,type,escaperisk
                    //Travis,black,12,54,chimp,good,null,null,1,null
                    // 0    ,1,     2 3  4      5    6    7   8  9
                    new monkey(details[0], details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]), details[4], details[5],details[6],details[7],details[9]);
            case 2 ->
                  //         String iname, String icolour, int iage, int iweight, String ispecies, String health, String lastMaintenance, String latestZooKeeper,String iwingHealth,boolean iflightless
                    new birdOfPrey(details[0], details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]), details[4], details[5],details[6],details[7],details[9],Boolean.parseBoolean(details[10]));
            case 3 ->
                    //name,colour,age,weight,species,health,lastmaintence,lastzookeeper,type,canswim
                    new bigCat(details[0], details[1], Integer.parseInt(details[2]), Integer.parseInt(details[3]), details[4], details[5],details[6],details[7],Boolean.parseBoolean(details[9]));
            default -> null;
        };
    }
    public zooKeeper[] getZooKeepers() {
        try {
            File zooData = new File("Resources/zooKeeperData.txt");
            Scanner FileReader = new Scanner(zooData);
            ArrayList<zooKeeper> zooKeepers = new ArrayList<>();
            int count = 0;
            while (FileReader.hasNextLine()) {
                String[] details = FileReader.nextLine().split(",");
                zooKeepers.add(new zooKeeper(details[0], details[1]));
            }
            return zooKeepers.toArray(new zooKeeper[0]);
        } catch (Exception e) {
            try {
                System.out.println("The zookeeper data file could not be found, a new file is being created");
                File zooData = new File("Resources/zooKeeperData.txt");
                if(zooData.createNewFile()){
                    System.out.println("New file created");
                }
                FileWriter zooWriter = new FileWriter("Resources/zooKeeperData.txt");
                zooWriter.write("default,default");
                zooWriter.flush();
                System.out.println("New login created\nname: default\npassword: default");
                return new zooKeeper[]{new zooKeeper("default","default")};
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
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
        String data = "\nZoo: Belfast Zoo\nNumber of animals: ";
        int[] specific = new int [3];
        for(int i=0; i <animals.length;i++){
            specific[i] = animals[i].length;
            numAnimals+= animals[i].length;
        }
        data+= numAnimals+"\nNumber of monkeys: "+specific[0]+"\nNumber of birds: "+specific[1]+"\nNumber of big cats: "+specific[2] + "\nTotal: "+numAnimals;
        data+="\nPredominate monkey colour: "+ getAnimalColour(animals[0])+"\nPredominate bird colour: "+getAnimalColour(animals[1])+"\nPredominate big cat colour: "+getAnimalColour(animals[2]);
        return"Logged in zookeeper: "+currentKeeper.name+data;
    }
    public String saveAnimalZooData(animal[][] animals, zooKeeper current,zooKeeper[] zooKeepers) {
        try {
            FileWriter animalfile = new FileWriter("Resources/AnimalDetails.txt");
            FileWriter zooFile = new FileWriter("Resources/zooDetails.txt");
            FileWriter zooKeeperFile = new FileWriter("Resources/zooKeeperData.txt");
            for (int i = 0; i < animals.length; i++) {
                for (int j = 0; j < animals[i].length; j++) {
                    animalfile.write(animals[i][j].getAnimalData()+"\r\n");
                }
            }
            for(zooKeeper i: zooKeepers){
                zooKeeperFile.write(i.name+","+i.password+"\r\n");
            }
            zooFile.write(getZooData(animals,current,"file"));
            animalfile.flush();
            zooFile.flush();
            zooKeeperFile.flush();
            return "Successfully saved animal and zoo data";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public animal findAnimal(animal[][] animals){
        String name = getInfo("What is the name of the animal you're looking for?");
        String colour = getInfo("What is the colour of the animal you're looking for?");
        //loop through animals array
        for(int i =0; i < animals.length;i++){
            //iterate through it until the specific animal is found
            for(int j=0; j<animals[i].length;j++){
                if(animals[i][j].name.equals(name)&&animals[i][j].colour.equals(colour)){
                    return animals[i][j];
                }
            }
        }
        return null;
    }
}