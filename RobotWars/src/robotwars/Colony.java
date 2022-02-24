package robotwars;

import java.io.Serializable;
import java.util.ArrayList;

public class Colony implements Serializable{
    private int energy;
    private MasterRoom masterRoom;
    private ArrayList<ArrayList<Room>> map;
    private ArrayList<EntryRoom> entryRooms;

    
    //constructor
    public Colony(int energy, int numTunnels, int tunnelLength){
        this.energy=energy;
        map= new ArrayList<>();
        
        //creates refences entryrroms to empty arraylists/tunnels
        for(int i=0; i<numTunnels; i++){
            map.add(new ArrayList<Room>()); //tunnel (reminder i guess)
        } 
        
        //initialize masterRoom
        masterRoom=new MasterRoom();
        masterRoom.setRobot(new MasterRobot(masterRoom));
        //creates each tunnel of tunnelLenght
        for(int i=0; i<numTunnels; i++){
            for(int j=0; j<tunnelLength-1; j++){
                Room room = new Room();
                map.get(i).add(room);
            }
            map.get(i).add(masterRoom);
            //MasterRoom Mroom = new MasterRoom();
            //map.get(i).add(Mroom);
        }

        //links rooms
        for(int i=0; i<numTunnels; i++){
            int j=0;
            //entryroom
            map.get(i).get(j).setEntry(null);
            map.get(i).get(j).setExit(map.get(i).get(j+1));

            //rooms
            for(j=1; j<tunnelLength-1; j++){
                map.get(i).get(j).setEntry(map.get(i).get(j-1));
                map.get(i).get(j).setExit(map.get(i).get(j+1));
            }

            //masterroom
            //map.get(i).get(tunnelLength-1).setEntry(map.get(i).get(tunnelLength-2));
            //map.get(i).get(tunnelLength-1).setExit(null);
            //doesnt really need entry
        }
             
        //creates entryRooms
        entryRooms = new ArrayList<>();
        
        for(int i = 0; i < numTunnels; i++)
        {
            entryRooms.add(new EntryRoom());
        }
        
        //links EntryRooms
        for(int i=0; i<numTunnels; i++){
            
            entryRooms.get(i).setExit(map.get(i).get(0));
        }
        
    }
    
    public int getNumTunnels(){
        return map.size();
    }
    
    public int getTunnelLength(int tunnel){
        return map.get(tunnel).size();
    }
    
    public int maxLength(){
        int max=0;
        for(int i=0; i<getNumTunnels(); i++){
            if(getTunnelLength(i)>max){
                max=getTunnelLength(i);
            }
        }
        return max;
    }
    
    public EntryRoom getEntryRoom(int i){
        return entryRooms.get(i);
    }

    public ArrayList<Room> getTunnel(int i){
        return map.get(i);
    }
    
    public Room getRoom(int i, int j){
        return map.get(i).get(j);
    }
    
    public MasterRoom getMasterRoom(){
        return masterRoom;
    }
 
    
    public int getEnergy(){
        return energy;
    }
    
    public void setEnergy(int energy){
        this.energy=energy;
    }
    
    public void incEnergy(int i){
        energy = energy + i;
    }
    
    public int getAllSoldiersNumber()
    {
        int sum = 0;
        
        for(int i = 0; i < map.size(); i++)
        {
            for(int j = 0; j < map.get(i).size(); j++ )
            {
                Room room = map.get(i).get(j);
                sum = sum + room.getSoldiersNumber();
            }
        }
        
        return sum;
    }
    
    public String printTunnel(int tunnelId){
        String alphabetTunnel="";
        if(tunnelId>=0 && tunnelId<27){
            alphabetTunnel= String.valueOf((char)((tunnelId + 1) + 64));
        }
        String full;
        String[] str= new String[getTunnelLength(tunnelId)];
        
        //initialize str
        full="";
        for(int i=0; i<getTunnelLength(tunnelId);i++){
            str[i]="";
        }

        for(int i=0; i<getTunnelLength(tunnelId)-1;i++){
            if(i==0){
                str[0]=str[0]+"   ---------- ";
            }
            else{
                str[0]=str[0]+"---------- ";
            }
            
        }
        if(tunnelId==0){
                str[0]=str[0]+"---------| \n";
        }

        else{
            str[0]=str[0]+"|        | \n";
        }
        
        
        for(int i=0; i<getTunnelLength(tunnelId)-1;i++){
            if(i==0){
                str[1]=str[1]+"   |        | ";
            }
            else{
                str[1]=str[1]+"|        | ";
            }
        }
        str[1]=str[1]+"|        | \n";
          
        
        str[2]=alphabetTunnel;
        for(int i=0; i<getTunnelLength(tunnelId)-1;i++){    
            if(getRoom(tunnelId,i).getSoldiersNumber()!=0 && getRoom(tunnelId,i).getRobot()!=null){
                if(i==0){
                    str[2]=str[2]+"  =  \u001b[31;1m*  \u001b[36m*\u001b[0m  = ";
                }
                else{
                    str[2]=str[2]+"=  \u001b[31;1m*  \u001b[36m*\u001b[0m  = ";
                }
            }
            else if(getRoom(tunnelId,i).getSoldiersNumber()==0 && getRoom(tunnelId,i).getRobot()!=null){
                if(i==0){
                    str[2]=str[2]+"  =     \u001b[36m*\u001b[0m  = ";
                }
                else{
                    str[2]=str[2]+"=     \u001b[36m*\u001b[0m  = ";
                }
            }
            else if(getRoom(tunnelId,i).getSoldiersNumber()!=0 && getRoom(tunnelId,i).getRobot()==null){
                if(i==0){
                    str[2]=str[2]+"  =  \u001b[31;1m*\u001b[0m     = ";
                }
                else{
                    str[2]=str[2]+"=  \u001b[31;1m*\u001b[0m     = ";
                }
            }    
            else{
                if(i==0){
                    str[2]=str[2]+"  =        = ";
                }
                else{
                    str[2]=str[2]+"=        = ";
                }
            }
        }
        if(getRoom(tunnelId,getTunnelLength(tunnelId)-1).getSoldiersNumber()!=0){
            str[2]=str[2]+"=    \u001b[31;1m!\u001b[0m   | \n";
        }
        else{
            str[2]=str[2]+"=    \u001b[33m*\u001b[0m   | \n";
        }
        

        for(int i=0; i<getTunnelLength(tunnelId)-1;i++){
            if(i==0){
                str[3]=str[3]+"   |        | ";
            }
            else{
                str[3]=str[3]+"|        | ";
            }
        }
        str[3]=str[3]+"|        | \n";
        
        for(int i=0; i<getTunnelLength(tunnelId)-1;i++){
            if(i==0){
                str[4]=str[4]+"   ---------- ";
            }
            else{
                str[4]=str[4]+"---------- ";
            }
        }        
        if(tunnelId==getNumTunnels()-1){
            str[4]=str[4]+"---------| \n";
        }
        else{
            str[4]=str[4]+"|        | \n";
        }
        
    
        for(int x=0; x<getTunnelLength(tunnelId);x++){
             full= full+str[x];
        }
        return full;
    }
        
 
    
    
    public String toString(){
        String full;
        String label;
        String[] str= new String[getNumTunnels()];
        
        //initialize str
        full="";
        label="   ";
        for(int i=0; i<getNumTunnels();i++){
            str[i]="";
        }
        
                
        for(int x=0;x<maxLength();x++){
            label=label+("     "+(x+1)+"     ");
        }
        
        for(int i=0; i<getNumTunnels(); i++){
            str[i]=str[i]+printTunnel(i);
        }

        full=full+"\n\n"+label+"\n";
        for(int x=0; x<getNumTunnels();x++){
             full= full+str[x];
        }
        return full;
    }
    

}
