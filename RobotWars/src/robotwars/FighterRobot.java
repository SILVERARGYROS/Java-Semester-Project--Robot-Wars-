package robotwars;

public class FighterRobot extends Robot{
    //constructor
    public FighterRobot(Room room)
    {
        super(room, 1, 4); //stamina=1   energyNeeded=4
      
    }
    
    public void act(Colony colony){
        
        //find current tunnel
        Room room = getRoom();
        Room tmpRoom = null;
        
        int currentTunnel = 0;
        
        for(int i = 0; i < colony.getNumTunnels(); i++)
        {
            for(int j = 0; j < colony.getTunnelLength(i); j++)
            {
                tmpRoom = colony.getRoom(i, j);
                
                if(room == tmpRoom)
                {
                    currentTunnel = i;
                    break;
                }
            }
            
            //makes code slightly faster
            if(room == tmpRoom)
            {
                currentTunnel = i;
                break;
            }
        }
        
        //scan tunnel backwards
        for(int j = colony.getTunnelLength(currentTunnel) - 1; j >= 0; j--)
        {
            tmpRoom = colony.getRoom(currentTunnel, j);
            if(tmpRoom.getSoldierList().size() > 0)
            {
                tmpRoom.getSoldierList().get(0).reduceStamina(1);
                break;
            }
        }
        
//        //hit soldier
//        int i,j;
//        for(i=0; i<colony.getNumTunnels(); i++){
//            j = colony.getTunnel(i).indexOf(getRoom());
//            if(j> -1){
//                break;
//            }
//        }
//        
//        int p = colony.getTunnel(i).indexOf(getRoom());
//        for(i=p; i<colony.getTunnelLength(i)-1; i++){
//            if(getRoom().getSoldiersNumber() != 0)
//            {
//                getRoom().getSoldierList().get(0).reduceStamina(1);
//                break;
//            }
//        }
//    
//        
//        if(getStamina()<=0){
//            leaveRoom();
//        }
    }
    
}
