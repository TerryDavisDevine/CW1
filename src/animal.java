import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class animal{
    String name;
    String colour;
    int age;
    int weight;
    String species;
    String health;
    int type;
    String lastMaintenance;
    zooKeeper latestZooKeeper;
    animal(String iname, String icolour, int iage, int iweight, String ispecies, String ihealth){
        name = iname;
        colour = icolour;
        age = iage;
        weight = iweight;
        species = ispecies;
        health = ihealth;
    }
    public String toString(){
        return "Name: "+name+"\nColour: "+colour+"\nAge: "+age+"KG\nWeight: "+weight+"\nSpecies: "+species+"\nHealth: "+health +
                "Sound: "+makeSound()+"\nLast maintenance: "+ lastMaintenance+"\nLast zookeeper: "+latestZooKeeper;
    }
    public String makeSound(){
        String vowels = "aeiou";
        if(vowels.contains(String.valueOf(species.charAt(0)))){
            return "I am an " + species + "called " + name + ", i am " + age + " years old";
        }
        return " I am a " + species + " called " + name + ", i am " + age + " years old";
    }
    public void heal(){
        health = "Good";
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        lastMaintenance = myDateObj.format(myFormatObj);
    }
    public String getAnimalData(){
        return name+","+colour+","+age+","+weight+","+species+","+health+","+lastMaintenance+","+latestZooKeeper+","+type;
    }
}