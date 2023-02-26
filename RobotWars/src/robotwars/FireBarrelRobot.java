package robotwars;

public class FireBarrelRobot extends Robot{
    
    //constructor
    public FireBarrelRobot(Room room)
    {
        super(room, 1, 30); //stamina=1   energyNeeded=30
    }
    
    public void act(Colony colony){
        
    }
    
    public void leaveRoom(){

        //start from current room
        Room tmpRoom = getRoom();

        while(tmpRoom != null)
        {
            //erase everyone in current room
            for(int i = tmpRoom.getSoldiersNumber() - 1; i >= 0; i--)
            {
                tmpRoom.getSoldierList().get(i).reduceStamina(3);
            }
            //go to previous room
            tmpRoom = tmpRoom.getEntry();
        }
        super.leaveRoom();
    }
    
}