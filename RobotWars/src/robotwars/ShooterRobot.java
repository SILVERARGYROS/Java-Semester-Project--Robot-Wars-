package robotwars;

import java.util.ArrayList;

public class ShooterRobot extends Robot{
    private int reload;
    //constructor
    public ShooterRobot(Room room)
    {
        super(room, 1, 4); //stamina=1   energyNeeded=4
        reload = 0;
    }
    
    public int getReload(){
        return reload;
    }
    
    public void act(Colony colony){
        System.out.println("Before bug");
        ArrayList<Soldier> soldierList = getRoom().getSoldierList();
        if(soldierList != null)
        {
            
        System.out.println("after bug1");
            if(soldierList.size() > 0 && reload == 0)
            {
                System.out.println("after bug 2");
                Soldier soldier = soldierList.get(0);
                System.out.println("after detecting soldier");
                soldier.reduceStamina(3);
                
                reload = 3;
            }
            else if(reload != 0){
                reload--;
            }
        }
        System.out.println("after shooters turn");

    }
    
}
