package robotwars;

import java.util.ArrayList;

public class ShooterRobot extends Robot{
    private int reload;
    //constructor
    public ShooterRobot(Room room)
    {
        super(room, 1, 4);  //stamina=1   energyNeeded=4
        reload = 0;         //starts charged
    }
    
    public int getReload(){
        return reload;
    }
    
    public void act(Colony colony){
        
        ArrayList<Soldier> soldierList = getRoom().getSoldierList();
        if(soldierList.size() > 0 && reload == 0)
        {
            Soldier soldier = soldierList.get(0);
            soldier.reduceStamina(3);

            reload = 3;
        }
        else if(reload != 0){
            reload--;
        }
    }
    
}
