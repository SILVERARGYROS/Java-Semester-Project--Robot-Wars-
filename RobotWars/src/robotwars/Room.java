package robotwars;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable{
    private final int MAX_SOLDIERS;
    private Room entry;
    private Room exit;
    private Robot robot = null;
    private ArrayList<Soldier> soldiers= new ArrayList<>();
    
    //constructor
//    public Room(Room entry, Room exit){
//        MAX_SOLDIERS=8;
//        this.entry=entry;
//        this.exit=exit;
//    }
    
    
    //test
    public Room(){
        MAX_SOLDIERS=8;
        this.entry=null;
        this.exit=null;
    }
    
    public boolean setEntry(Room entry){
        if(this.entry==null){
            this.entry=entry;
            return true;
        }
        return false;
    }
    
    public boolean setExit(Room exit){
        if(this.exit==null){
            this.exit=exit;
            return true;
        }
        return false;
    }
    
    public Room getEntry()
    {
        return entry;
    }
    
    public Room getExit()
    {
        return exit;
    }

    public void setRobot(Robot robot){
        this.robot=robot;    
    } 
    
    public Robot getRobot(){
        return robot;
    }
   
    
    public boolean addSoldier(Soldier soldier)
    {
        if(soldiers.size() < MAX_SOLDIERS)
        {
            soldiers.add(soldier);  
            System.out.println("Soldier got added");
            return true;
        }
        return false;
    }
    
    public boolean wipeSoldier(Soldier soldier)
    {
        if(soldiers.size() > 0)
        {
            soldiers.remove(soldier);
            return true;
        }
        return false;
    }
    
    public int getSoldiersNumber()
    {
        return soldiers.size();
    }
    
    public ArrayList<Soldier> getSoldierList()
    {
        return soldiers;
    }
    
    public String toString(){
        String full; 
        String[] str= new String[15];
        
        
        full="";
        for(int i=0; i<15;i++){
            str[i]="";
        }

        str[0]="------------------------------------------\n";
        str[14]="------------------------------------------\n";
       
        int j=0;
        for(int i=1; i<14;i++){
            
            if(i==2 && robot!=null){
                if(robot instanceof MasterRobot){
                    str[i]="|              \u001b[33m"+robot.getType()+"\u001b[0m              |\n";
                }
                else if(robot instanceof ShooterRobot){
                    str[i]="|              \u001b[36m"+robot.getType()+"\u001b[0m             |\n";
                }
                else if(robot instanceof ArmoredRobot){
                    str[i]="|              \u001b[36m"+robot.getType()+"\u001b[0m             |\n";
                }
                else if(robot instanceof EnergyProducerRobot){
                    str[i]="|          \u001b[36m"+robot.getType()+"\u001b[0m         |\n";
                }
                else if(robot instanceof FighterRobot){
                    str[i]="|             \u001b[36m"+robot.getType()+"\u001b[0m              |\n";
                }
                else if(robot instanceof FireRobot){
                    str[i]="|                \u001b[36m"+robot.getType()+"\u001b[0m              |\n";
                }
                
            }  
            else if(i==3 && robot!=null){
                if(robot instanceof ShooterRobot){
                    str[i]="|         \u001b[36mHP:"+robot.getStamina()+"  Time for reload:"+((ShooterRobot)robot).getReload()+"\u001b[0m        |\n";
                }
                else if(robot instanceof MasterRobot){
                    str[i]="|                  \u001b[33mHP:"+robot.getStamina()+"\u001b[0m                  |\n";
                }
                else{
                    str[i]="|                  \u001b[36mHP:"+robot.getStamina()+"\u001b[0m                  |\n";
                }
                
            }   
            else if(i>=5 && i<=12){
                if(soldiers.size()!=0 && j<soldiers.size()){
                    if(i==6||i==8){
                        if(robot instanceof MasterRobot){
                            str[i]="=                \u001b[33;1mS"+(j+1)+"  HP:"+soldiers.get(j).getStamina()+"\u001b[0m                |\n";
                        }
                        else{
                            str[i]="=                \u001b[31;1mS"+(j+1)+"  HP:"+soldiers.get(j).getStamina()+"\u001b[0m                =\n"; 
                        }

                    }
                    else if(i==7){
                        if(robot instanceof MasterRobot){
                            str[i]="                 \u001b[33;1mS"+(j+1)+"  HP:"+soldiers.get(j).getStamina()+"\u001b[0m                |\n";
                        }
                        else{
                            str[i]="                 \u001b[31;1mS"+(j+1)+"  HP:"+soldiers.get(j).getStamina()+"\u001b[0m                 \n"; 
                        }
                    }
                    else{
                        str[i]="|                \u001b[31;1mS"+(j+1)+"  HP:"+soldiers.get(j).getStamina()+"\u001b[0m                |\n";
                    }
                    j++;
                }    
                else{
                    if(i==6||i==8){
                        if(robot instanceof MasterRobot){
                            str[i]="=                                        |\n"; 
                        }
                        else{
                            str[i]="=                                        =\n";  
                        }
                    }
                    else if(i==7){
                        if(robot instanceof MasterRobot){
                            str[i]="                                         |\n"; 
                        }
                        else{
                            str[i]="                                          \n";  
                        }
                    }
                    else{
                        str[i]="|                                        |\n";
                    }
                    
                }       
            }
            else{
                str[i]="|                                        |\n";
            }       
        }

        for(int i=0; i<15;i++){
            full= full+str[i];
        }
        return full;
    }
    
    
    
}
