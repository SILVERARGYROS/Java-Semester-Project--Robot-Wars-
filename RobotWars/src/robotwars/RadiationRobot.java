package robotwars;

public class RadiationRobot extends Robot{
    //constructor
    public RadiationRobot(Room room)
    {
        super(room, 1, 25); //stamina=1   energyNeeded=25
      
    }
    
    public void act(Colony colony){
        
        Room tmpRoom = getRoom();
        
        while(tmpRoom != null)
        {
            if(tmpRoom.getSoldierList().size() >0)
            {
                //erase everyone in current room
                for(int i = tmpRoom.getSoldiersNumber() - 1; i >= 0; i--)
                {
                    tmpRoom.getSoldierList().get(i).reduceStamina(1);
                }
            }
            //go to next room
            tmpRoom = tmpRoom.getExit();
        }



        //this code poisons the whole tunnel
//        //find current room indexes
//        for(int i = 0; i < colony.getNumTunnels(); i++)
//        {
//            for(int j = 0; j < colony.getTunnelLength(i); j++)
//            {
//                tmpRoom = colony.getRoom(i, j);
//                
//                if(room == tmpRoom)
//                {
//                    currentTunnelIndex = i;
//                    currentRoomIndex = j;
//                    break;
//                }
//            }
//            
//            //makes code slightly faster
//            if(room == tmpRoom)
//            {
//                break;
//            }
//        }
//        
//        //scan tunnel backwards (starting from the start of the tunnel)
//        for(int j = currentRoomIndex; j < colony.getTunnelLength(currentTunnelIndex); j++)
//        {
//            tmpRoom = colony.getRoom(currentTunnelIndex, j);
//            if(tmpRoom.getSoldierList().size() > 0)
//            {
//                tmpRoom.getSoldierList().get(0).reduceStamina(1);
//                
//                //damages all soldiers
//               
//            }
//        }
    }
    
}
