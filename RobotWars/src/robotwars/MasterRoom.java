package robotwars;

public class MasterRoom extends Room {
   //multiple master rooms with multiple master bots
    
    //constructor
//    public MasterRoom(Room entry){
//        super(entry,null);
//    }
    
    public MasterRoom(){
        super();
    }
    
    public boolean setRobot(MasterRobot robot){
        if(getRobot()==null){
            super.setRobot(robot); 
            return true;
        }
        return false;    
    } 
    
    //may commment
    public boolean breached(){
         if(getSoldiersNumber()>=1){
             return true;
         }
         return false;
    }
    
    
}
