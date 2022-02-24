package robotwars;

public class FighterRobot extends Robot{
    //constructor
    public FighterRobot(Room room)
    {
        super(room, 1, 4); //stamina=1   energyNeeded=4
      
    }
    
    public void act(Colony colony){
        
        //room cordinates
        int i,j;
        for(i=0; i<colony.getNumTunnels(); i++){
            j = colony.getTunnel(i).indexOf(getRoom());
            if(j> -1){
                break;
            }
        }
        
        int p = colony.getTunnel(i).indexOf(getRoom());
        for(i=p; i<colony.getTunnelLength(i)-1; i++){
            if(getRoom().getSoldiersNumber() != 0)
            {
                getRoom().getSoldierList().get(0).reduceStamina(1);
                break;
            }
        }
    
        
        if(getStamina()<=0){
            leaveRoom();
        }
    }
    
}
