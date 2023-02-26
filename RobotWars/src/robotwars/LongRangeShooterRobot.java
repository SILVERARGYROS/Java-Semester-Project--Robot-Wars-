package robotwars;

import java.util.ArrayList;

public class LongRangeShooterRobot extends Robot{
    private int reload;
    //constructor
    public LongRangeShooterRobot(Room room)
    {
        super(room, 1, 8);  //stamina=1   energyNeeded=8
        reload = 0;         //starts charged
    }
    
    public int getReload(){
        return reload;
    }
    
    public void act(Colony colony){
        
        ArrayList<Soldier> soldierList = getRoom().getSoldierList();
        if(reload == 0)
        {
            Room tmpRoom = getRoom();
            
            while(tmpRoom != null)
            {
                if(tmpRoom.getSoldierList().size() > 0)
                {
                    //erase one in current room
                    Soldier soldier = tmpRoom.getSoldierList().get(0);
                    soldier.reduceStamina(3);

                    reload = 5;
                    
                    //stops after first hit
                    break;
                }
                //go to previous room
                tmpRoom = tmpRoom.getEntry();
            }
        }
        else if(reload != 0){
            reload--;
        }
    }
    
}