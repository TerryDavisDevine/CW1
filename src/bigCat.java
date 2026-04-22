import java.util.Locale;

public class bigCat extends animal{
    String[] roars = {"lion", "tiger", "jaguar", "leopard"};
    String sound = "Meow!";
    bigCat(String iname, String icolour, int iage, int iweight, String ispecies, String ihealth) {
        super(iname, icolour, iage, iweight, ispecies,ihealth);
        super.type = 3;
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

}
