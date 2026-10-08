import java.util.Locale;

public class bigCat extends animal implements swimmable{
    String[] roars = {"lion", "tiger", "jaguar", "leopard"};
    String sound = "Meow!";
    boolean canSwim = false;
    bigCat(String iname, String icolour, int iage, int iweight, String ispecies, String ihealth,String lastMaintenance, String latestZooKeeper,boolean canSwim) {
        super(iname, icolour, iage, iweight, ispecies,ihealth,lastMaintenance,latestZooKeeper);
        super.type = 3;
        this.canSwim = canSwim;
    }
    @Override
    public boolean getSwim(){
        return canSwim;
    }
    @Override
    public String makeSound(){
        for(String animals:roars){
            if (species.toLowerCase(Locale.ROOT).equals(animals)) {
                sound = "Roar!";
                break;
            }
        }
        return sound + super.makeSound();
    }
    @Override
    public String swimming(){
        return "I am swimming";
    }
    @Override
    public String toString(){
        return super.toString()+"\nCan Swim?: "+canSwim;
    }
    public String getAnimalData(){
        return super.getAnimalData()+","+canSwim;
    }
}
