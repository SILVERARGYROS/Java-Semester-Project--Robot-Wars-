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
        
        if(getStamina()<=0){
            int loops = getRoom().getSoldiersNumber();
            for(int i=0; i< loops; i++){
                getRoom().getSoldierList().get(0).reduceStamina(3);
            }
            super.leaveRoom();
        }
    }
    
}
