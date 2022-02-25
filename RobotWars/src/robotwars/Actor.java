package robotwars;

import java.io.Serializable;

public abstract class Actor implements Serializable{
    private Room room;
    private int stamina;
    
    //constructor
    public Actor(Room room, int stamina){
        this.stamina=stamina;
        this.room=room;
    }
    
    public boolean setRoom(Room room){
            this.room=room;
            return true;
    }
    
    public int getStamina(){
        return stamina;
    }
    
    public Room getRoom(){
        return room;
    }
    
    public void reduceStamina(int amount){
        stamina= stamina-amount;
        if(stamina<=0){
            leaveRoom();
        }
    }
    
    public void leaveRoom(){
        if(getClass() == Soldier.class){
            System.out.println("in leaveRoom");
            room.getSoldierList().remove(this);
//            System.out.println("in leaveRoom after deleting soldier from list");
        }
        else
        {            
            room.setRobot(null);
            System.out.println("robot died");
        }
        room=null;
        System.out.println("out of leaveRoom");
    }
    
    public abstract void act(Colony colony);
    
}
