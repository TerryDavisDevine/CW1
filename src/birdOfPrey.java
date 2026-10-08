public class birdOfPrey extends animal implements flyable{
    String wingHealth;
    boolean flightless;
    birdOfPrey(String iname, String icolour, int iage, int iweight, String ispecies, String health, String lastMaintenance, String latestZooKeeper,String iwingHealth,boolean iflightless) {
        super(iname, icolour, iage, iweight, ispecies,health,lastMaintenance,latestZooKeeper);
        wingHealth = iwingHealth;
        flightless = iflightless;
        super.type = 2;
    }
    @Override
    public String toString(){
        return super.toString() + "\nWing health: "+wingHealth+"\nFLightless:"+flightless;
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
    public String flying(){
        return "I am flying";
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
    @Override
    public String makeSound(){
        return "Screech! "+super.makeSound();
    }
    @Override
    public boolean isEmpty(){
        if(age == -1||weight==-1){
            return true;
        }else if(name.isEmpty()||colour.isEmpty()||species.isEmpty()||health.isEmpty()||wingHealth.isEmpty()){
            return true;
        }else if(name.isBlank()||colour.isBlank()||species.isBlank()||health.isBlank()){
            return true;
        }else{
            return false;
        }
    }
}
