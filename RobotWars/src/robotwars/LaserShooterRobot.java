package robotwars;

import java.util.ArrayList;

public class LaserShooterRobot extends Robot{
    private int reload;
    //constructor
    public LaserShooterRobot(Room room)
    {
        super(room, 2, 20); //stamina=2   energyNeeded=20
        reload = 3;         //starts uncharged
    }
    
    public int getReload(){
        return reload;
    }
    
    public void act(Colony colony){
        
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

                    reload = 3;
                    //doesnt stop after first hit
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
