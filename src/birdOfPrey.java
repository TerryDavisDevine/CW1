public class birdOfPrey extends animal implements flyable{
    String wingHealth;
    boolean flightless;
    birdOfPrey(String iname, String icolour, int iage, int iweight, String ispecies, String health, String iwingHealth,boolean iflightless) {
        super(iname, icolour, iage, iweight, ispecies,health);
        wingHealth = iwingHealth;
        flightless = iflightless;
        super.type = 2;
    }
    @Override
    public String toString(){
        return super.toString() + "\nWing health: "+wingHealth;
    }
    @Override
    public String canFly() {
        if(flightless){
            return "No, this is a flightless bird";
        } else if (wingHealth.equals("Bad")) {
            return "No, there is wing damage that needs healing";
        }
        return "Yes";
    }

    @Override
    public void heal() {
        super.heal();
        wingHealth = "Good";
    }

    @Override
    public String getAnimalData() {
        return super.getAnimalData()+","+wingHealth+","+flightless;
    }
}
