package robotwars;

public abstract class Robot extends Actor{
    private int energyNeeded;
    
    //constructor
    public Robot(Room room, int stamina, int energyNeeded)
    {
        super(room, stamina);
        this.energyNeeded = energyNeeded;
    }
    
    public String getType(){
        if(getClass() == MasterRobot.class){
            return "Master Robot";
        }
        else if(getClass() == FireRobot.class){
            return "Fire Robot";
        }
        else if(getClass() == ShooterRobot.class){
            return "Shooter Robot";
        }
        else if(getClass() == FighterRobot.class){
            return "Fighter Robot";
        }
        else if(getClass() == EnergyProducerRobot.class){
            return "Energy Producer Robot";
        }
        else if(getClass() == ArmoredRobot.class){
            return "Armored Robot";
        }
        else{
            return null;
        }
    }
    
    public int getEnergyNeeded(){
        return energyNeeded;
    }
    
    
    public abstract void act(Colony colony);
    
}
