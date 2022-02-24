package robotwars;

public class Soldier extends Actor{
    private final int DAMAGE;
    
    //constructor
    public Soldier(Room room){
        super(room, 3); //stamina=3
        DAMAGE=1;
    }
    
    public void attack(Robot robot){
        robot.reduceStamina(DAMAGE);
    }
    
    public void moveTo(Room room){
        //insert to new node
        if(room.addSoldier(this))
        {
            //disconnect from current node
            System.out.println("BEFORE BUG");
            leaveRoom();
            System.out.println("AFTERBUG");
            //point to new node
            setRoom(room);
            System.out.println("debug Soldier tried to move");
        }
        
    }
    
    public void act(Colony colony){
        
        
        if(getRoom().getClass() == EntryRoom.class)
        {
             moveTo(getRoom().getExit());
             return;
        }
        
        if(getRoom().getRobot()!=null){
            attack(getRoom().getRobot());
        }
        else{
//            int i,j;
//            for(i=0; i<colony.getNumTunnels(); i++){
//                j = colony.getEntryRoom(i).indexOf(getRoom());
//                if(j> -1){
//                    break;
//                }
//            }
//            moveTo(colony.getEntryRoom(i).get(j+1));
            if(getRoom().getClass() == EntryRoom.class)
            {
                System.out.println("debug Soldier summoned");
            }
            else
            {
                System.out.println("debug Soldier moved to main room");
            }
            
            moveTo(getRoom().getExit());
            
        }
    }
    
    
}
