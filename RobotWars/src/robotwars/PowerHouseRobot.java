package robotwars;

public class PowerHouseRobot extends Robot{
    
    //constructor
    public PowerHouseRobot(Room room)
    {
        super(room, 1, 8); //stamina=1   energyNeeded=8
    }
    
    public void act(Colony colony){
        colony.incEnergy(4);
    }
    
}