public class monkey extends animal implements climable{
    String escapeRisk;
    monkey(String iname, String icolour, int iage, int iweight, String ispecies,String ihealth,String lastMaintenance, String latestZooKeeper,String escapeRisk) {
        super(iname, icolour, iage, iweight,ispecies,ihealth,lastMaintenance,latestZooKeeper);
        this.escapeRisk = escapeRisk;
        super.type = 1;
    }
    @Override
    public String climbing(){
        return "I am climbing";
    }
    @Override
    public String swingFromVine(){return "swinging from vine...";}
    @Override
    public String makeSound(){
        return "oo oo a a!"+super.makeSound();
    }
    @Override
    public String toString(){
        return super.toString()+"\nEscape risk: "+escapeRisk;
    }
    @Override
    public String getAnimalData(){
        return super.getAnimalData()+","+escapeRisk;
    }
}
