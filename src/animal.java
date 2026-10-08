import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class animal{
    String name;
    String colour;
    int age = -1;
    int weight = -1;
    String species;
    String health;
    int type;
    String lastMaintenance;
    String latestZooKeeper;
    animal(String iname, String icolour, int iage, int iweight, String ispecies, String ihealth,String lastMaintenance, String latestZooKeeper){
        name = iname;
        colour = icolour;
        age = iage;
        weight = iweight;
        species = ispecies;
        health = ihealth;
        this.lastMaintenance = lastMaintenance;
        this.latestZooKeeper = latestZooKeeper;
    }
    public boolean isEmpty(){
        if(age < 0||weight < 0){
            return true;
        }else if(name.isEmpty()||colour.isEmpty()||species.isEmpty()||health.isEmpty()){
            return true;
        }else if(name.isBlank()||colour.isBlank()||species.isBlank()||health.isBlank()){
            return true;
        }else{
            return false;
        }
    }
    public String toString(){
        return "Name: "+name+"\nColour: "+colour+"\nAge: "+age+"\nWeight: "+weight+"KG\nSpecies: "+species+"\nHealth: "+health +
                "\nSound: "+makeSound()+"\nLast maintenance: "+ lastMaintenance+"\nLast zookeeper: "+latestZooKeeper;
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