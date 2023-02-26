package robotwars;

public class FireRobot extends Robot{
    
    //constructor
    public FireRobot(Room room)
    {
        super(room, 1, 4); //stamina=1   energyNeeded=4
    }
    
    public void act(Colony colony){
        
    }
    
    public void leaveRoom(){
        
        //erase everyone in current room
        for(int i = getRoom().getSoldiersNumber() - 1; i >= 0; i--)
        {
            getRoom().getSoldierList().get(i).reduceStamina(3);
        }
        super.leaveRoom();
    }
    
}
