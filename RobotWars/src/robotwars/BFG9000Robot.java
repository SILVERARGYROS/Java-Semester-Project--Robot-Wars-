package robotwars;

import java.util.ArrayList;

public class BFG9000Robot extends Robot{
    private int reload;
    //constructor
    public BFG9000Robot(Room room) //yes that is a DOOM reference
    {
        super(room, 3, 50); //stamina=3   energyNeeded=50
        reload = 8;         //starts uncharged
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
                    //erase all soldiers in current room
                    for(int i = tmpRoom.getSoldiersNumber() - 1; i >= 0; i--)
                    {
                        Soldier soldier = tmpRoom.getSoldierList().get(0);
                        soldier.reduceStamina(3);
                        
                        //turns each soldier into energy
                        colony.incEnergy(1);
                    }

                    reload = 8;
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
