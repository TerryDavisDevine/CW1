public class monkey extends animal{
    monkey(String iname, String icolour, int iage, int iweight, String ispecies,String ihealth) {
        super(iname, icolour, iage, iweight,ispecies,ihealth);
        super.type = 1;
    }
    @Override
    public String makeSound(){
        return "oo oo a a!"+super.makeSound();
    }
}
