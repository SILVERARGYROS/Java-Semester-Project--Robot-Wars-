package robotwars;

public class EnergyProducerRobot extends Robot{
    
    //constructor
    public EnergyProducerRobot(Room room)
    {
        super(room, 1, 3); //stamina=1   energyNeeded=3
    }
    
    public void act(Colony colony){
        colony.incEnergy(1);
    }
    
}
