package robotwars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import static robotwars.RobotWars.clearScreen;
import java.util.Scanner;

public class Game implements Serializable{
    
    private String gamemode;
    private String difficulty;
    private int soldierModifier;
    private boolean randomGen;
    private int maxRound;
    private boolean breachers;
    private Colony colony;
    private int currentRound;
    private boolean  onGoingMatch;
    private boolean save;
            
    public Game(String gamemode, String difficulty, int soldierModifier, boolean randomGen, int maxRound, boolean breachers, int numTunnels, int tunnelLength, int energy)
    {
        this.gamemode = gamemode;
        this.difficulty= difficulty;
        this.soldierModifier = soldierModifier;
        this.randomGen = randomGen;
        this.maxRound = maxRound;
        this.breachers = breachers;
        
        this.colony = new Colony(energy, numTunnels, tunnelLength);
        
        this.currentRound = 0;
        this.onGoingMatch = true;
        this.save=false;
    }
    
    
    
    public void gameOn(){
        
        Scanner input = new Scanner(System.in);
        
        do{         //onGoing Match
            
            //CORRECT ORDER NOTE
            playerTurn();       //
            if(!onGoingMatch){
                    break;
            }
            System.out.println("Debug inGame after playerTurn");
            spawnSoldiers();    //done
            System.out.println("Debug inGame after soldiersSpawn");
            robotsTurn();       //done
            System.out.println("Debug inGame after robotsTurn");
            soldiersTurn();     //done
            System.out.println("Debug inGame after soldiersTurn");
            currentRound++;
            
            if(gameOver())      //semi done
            {
                onGoingMatch = false;
                //press enter to return to main menu
                //System.out.print("Press enter to return to main menu");
                //input.nextLine();
                System.out.println("onGOingMatch = " + onGoingMatch);
//                return;
                System.out.print("Press enter to return to main menu");
                input.nextLine();
            }
        }while(onGoingMatch);
            
    }
    
    public void scannerMode(){
        //System.out.println("--ROBOT COLONY--");
        System.out.println("              ___  ____  ___  ____  ______  _________  __   ____  _  ____  __\n" +
                           "            / _ \\/ __ \\/ _ )/ __ \\/_  __/ / ___/ __ \\/ /  / __ \\/ |/ /\\ \\/ /\n" +
                           "           / , _/ /_/ / _  / /_/ / / /   / /__/ /_/ / /__/ /_/ /    /  \\  / \n" +
                           "          /_/|_|\\____/____/\\____/ /_/    \\___/\\____/____/\\____/_/|_/   /_/  ");
        System.out.println(colony);
              
        System.out.print("     MODE: "+ this.gamemode);
        System.out.print("          DIFFICULTY: "+ this.difficulty);
        System.out.print("          ENERGY: "+ colony.getEnergy());
        System.out.println("             ROUND: " + this.currentRound+"\n");
    }
    
    public void cameraMode(){
        Scanner input = new Scanner(System.in);

         char tc=' ';
         int roomChoice=0;
         int tunnelChoice=0;
         do{
             try{
                 System.out.println("Press X to exit camera mode");
                 System.out.print("Enter room: ");
                 String fullChoice = input.next();
                 if(fullChoice.equals("X")||fullChoice.equals("x")){
                     return;
                 }          

                 input.nextLine();
                 
                 if(fullChoice.length()==2){
                    fullChoice=fullChoice.toUpperCase();
                    tc=fullChoice.charAt(0);
                    tunnelChoice = (int)(tc - 'A');
                    roomChoice= ((fullChoice.charAt(1))-'0')-1;
                 }
                 else{
                     throw new OutOfBoundsException();
                 }
                  

                 if(tunnelChoice<0 || tunnelChoice>=colony.getNumTunnels()|| roomChoice < 0 || roomChoice >= colony.getTunnelLength(tunnelChoice) )
                 {
                     throw new OutOfBoundsException();   
                 }
                 else if(fullChoice.length()>2 && colony.getTunnelLength(tunnelChoice)<10)
                 {
                     throw new OutOfBoundsException();
                 }
                 else if(fullChoice.length()>3 && colony.getTunnelLength(tunnelChoice)>10)
                 {
                     throw new OutOfBoundsException();
                 }
                 else{
                     break;
                 }
             }
             catch(InputMismatchException e){
                 System.out.println("Invalid input\n");
                 input.nextLine();
             }
             catch(OutOfBoundsException e){
                 System.out.println("Invalid room\n");
             }
         }while(true);


        System.out.println("\n~Viewing Room "+tc+(roomChoice+1)+"~");
        System.out.println(colony.getRoom(tunnelChoice, roomChoice));
        cameraMode();
        
        
    }
    
    public void showMenu(){
        System.out.println("MENU\n-----");
        System.out.println("Switch to camera mode \t(1)");
        System.out.println("Buy robot \t\t(2)");
        System.out.println("Scrap Robot \t\t(3)");
//        System.out.println("Barricade \t\t(4)");
        System.out.println("End turn \t\t(4)");
        System.out.println("Save \t\t\t(5)");
        System.out.println("Quit \t\t\t(6)");

    }

    
    public void playerTurn(){
        
        Scanner input = new Scanner(System.in);
        
        boolean onGoingTurn = true;
            
        do      //player's onGoing Turn
        {
            clearScreen();
            scannerMode();                
            showMenu();
            int playerChoice=0;
            do{
                try{        
                    System.out.print("Enter option: ");
                    playerChoice = input.nextInt();
                    input.nextLine();
                    if(playerChoice < 1 || playerChoice > 6)
                    {
                        throw new OutOfBoundsException();
                    }
                    else{
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input\n");
                    input.nextLine();
                }
                catch(OutOfBoundsException e){
                    System.out.println("Invalid option\n");
                }
            }while(onGoingMatch);

            if(playerChoice == 1){  //camera mode
                cameraMode();   
            }
            else if(playerChoice == 2){  //buy robot
                
                Robot robot = null;
                do{
                    if(colony.getEnergy()<0){
                        System.out.println("Not enough energy. You may be able to buy another robot");
                        colony.incEnergy(robot.getEnergyNeeded());
                    }
                    
                    //robot shop menu
                    System.out.println("\nRobot List\n-----------");
                    System.out.println("Energy Producer Robot -- 3 ENERGY \t(1)");
                    System.out.println("Armored Robot -- 3 ENERGY \t\t(2)");
                    System.out.println("Fighter Robot -- 4 ENERGY \t\t(3)");
                    System.out.println("Shooter Robo -- 4 ENERGY \t\t(4)");
                    System.out.println("Fire Robot -- 4 ENERGY \t\t\t(5)");
                    System.out.println("Exit \t\t\t\t\t(0)");
                    System.out.println("\nENERGY: " + colony.getEnergy());


                    playerChoice=0;
                    do{
                        try{
                            System.out.print("Enter option: ");
                            playerChoice = input.nextInt();           
                            input.nextLine();

                            if(playerChoice==0){
                                playerTurn();
                            }

                            if(playerChoice < 0 || playerChoice > 5)
                            {
                                throw new OutOfBoundsException();
                            }
                            else{
                                break;
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input\n");
                            input.nextLine();
                        }
                        catch(OutOfBoundsException e){
                            System.out.println("Invalid option\n");
                        }
                    }while(true);

                    if(playerChoice!=0){
                        //input robot conditionals
                        robot = null;

                        switch(playerChoice)
                        {
                            case 1:
                                robot = new EnergyProducerRobot(null);
                                break;
                            case 2:
                                robot = new ArmoredRobot(null);
                                break;
                            case 3:
                                robot = new FighterRobot(null);
                                break;
                            case 4:
                                robot = new ShooterRobot(null);
                                break;
                            case 5:
                                robot = new FireRobot(null);
                                break;

                        }
                        colony.incEnergy(-robot.getEnergyNeeded());
                    }
                }while(colony.getEnergy()<0);

                
                if(playerChoice!=0){
                    //select room
                    char tc=' ';
                    int roomChoice=0;
                    int tunnelChoice=0;
                    do{
                        try{
                            System.out.println("Press X to exit");
                            System.out.print("Enter room to place robot: ");
                            String fullChoice = input.next();
                            if(fullChoice.equals("X")||fullChoice.equals("x")){
                                colony.incEnergy(robot.getEnergyNeeded());
                                playerTurn();
                            }          
                            input.nextLine();

                            if(fullChoice.length()==2){
                               fullChoice=fullChoice.toUpperCase();
                               tc=fullChoice.charAt(0);
                               tunnelChoice = (int)(tc - 'A');
                               roomChoice= ((fullChoice.charAt(1))-'0')-1;
                            }
                            else{
                                throw new OutOfBoundsException();
                            }

                            if(tunnelChoice<0 || tunnelChoice>=colony.getNumTunnels()|| roomChoice < 0 || roomChoice >= colony.getTunnelLength(tunnelChoice) )
                            {
                                throw new OutOfBoundsException();   
                            }
                            else if(colony.getRoom(tunnelChoice, roomChoice).getRobot() != null ){	
                                throw new RobotAlreadyThereException();	
                            }
                            else if(fullChoice.length()>2 && colony.getTunnelLength(tunnelChoice)<10)
                            {
                                throw new OutOfBoundsException();
                            }
                            else if(fullChoice.length()>3 && colony.getTunnelLength(tunnelChoice)>10)
                            {
                                throw new OutOfBoundsException();
                            }
                            else{
                                break;
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input\n");
                            input.nextLine();
                        }
                        catch(OutOfBoundsException e){
                            System.out.println("Invalid room\n");
                        }
                        catch(RobotAlreadyThereException e){
                            if(colony.getRoom(tunnelChoice, roomChoice).getRobot() instanceof MasterRobot){
                                System.out.println("Master Robot room. Please choose a different room\n");
                            }
                            else{
                                System.out.println("Robot already there. Please choose a different room\n");
                            }
                        }
                    }while(true);

                    Room room = colony.getRoom(tunnelChoice, roomChoice);
                    //place robot
                    room.setRobot(robot);
                    robot.setRoom(room);
                }
                
            }
            else if(playerChoice == 3){  //scrap robot     

                //read room
                char tc=' ';
                int roomChoice=0;
                int tunnelChoice=0;
                do{
                    try{
                        System.out.println("Press X to exit");
                        System.out.print("Enter room to scrap robot: ");
                        String fullChoice = input.next();
                        if(fullChoice.equals("X")||fullChoice.equals("x")){
                            playerTurn();
                        }          
                        input.nextLine();

                        if(fullChoice.length()==2){
                           fullChoice=fullChoice.toUpperCase();
                           tc=fullChoice.charAt(0);
                           tunnelChoice = (int)(tc - 'A');
                           roomChoice= ((fullChoice.charAt(1))-'0')-1;
                        }
                        else{
                            throw new OutOfBoundsException();
                        }


                        if(tunnelChoice<0 || tunnelChoice>=colony.getNumTunnels()|| roomChoice < 0 || roomChoice >= colony.getTunnelLength(tunnelChoice) )
                        {
                            throw new OutOfBoundsException();   
                        }
                        else if(colony.getRoom(tunnelChoice, roomChoice).getRobot() == null){	
                            throw new NoRobotThereException();	
                        }
                        else if(colony.getRoom(tunnelChoice, roomChoice).getRobot() instanceof MasterRobot){
                            throw new NoRobotThereException();
                        }
                        else if(fullChoice.length()>2 && colony.getTunnelLength(tunnelChoice)<10)
                        {
                            throw new OutOfBoundsException();
                        }
                        else if(fullChoice.length()>3 && colony.getTunnelLength(tunnelChoice)>10)
                        {
                            throw new OutOfBoundsException();
                        }
                        else{
                            break;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Invalid input\n");
                        input.nextLine();
                    }
                    catch(OutOfBoundsException e){
                        System.out.println("Invalid room\n");
                    }
                    catch(NoRobotThereException e){
                        if(colony.getRoom(tunnelChoice, roomChoice).getRobot() instanceof MasterRobot){
                            System.out.println("Master Robot room. Please choose a different room\n");
                        }
                        else{
                            System.out.println("No robot there. Please choose a different room\n");
                        }
                    }
                }while(true);
                
                if(colony.getRoom(tunnelChoice, roomChoice).getRobot().getClass() != MasterRobot.class){

                    Room room = colony.getRoom(tunnelChoice, roomChoice);

                    //give small ammount of energy back
                    int refound = room.getRobot().getEnergyNeeded() / 2;
                    colony.incEnergy(refound);

                    //empty room from robot
                    room.getRobot().leaveRoom();
                    room.setRobot(null);
                }
            }
            else if(playerChoice == 4){  //end turn      
                onGoingTurn = false;
            }
            else if(playerChoice == 5){  //save   
                save=true;  
                onGoingMatch = false;
                return;
            }
            else if(playerChoice == 6){  //quit
                System.out.println("\nAre you sure you want to quit without saving?");
                System.out.println("Quit without saving\t(1)");
                System.out.println("Save and quit\t\t(2)");
                System.out.println("Continue\t\t(3)");
               
                playerChoice=0;
                do{
                    try{
                        System.out.print("Enter option: ");
                        playerChoice = input.nextInt();           
                        input.nextLine();

                        if(playerChoice < 0 || playerChoice > 3)
                        {
                            throw new OutOfBoundsException();
                        }
                        else{
                            break;
                        }
                    }
                    catch(InputMismatchException e){
                        System.out.println("Invalid input\n");
                        input.nextLine();
                    }
                    catch(OutOfBoundsException e){
                        System.out.println("Invalid option\n");
                    }
                }while(true);

                if(playerChoice==1 || playerChoice==2){
                    if(playerChoice==2){
                        save=true;  
                    }
                    onGoingMatch = false;
                    return;
                }
        

            }
        }while(onGoingTurn);
    }
    
    public boolean saveOnOff(){
        return save;
    }
    
    public void setSave(boolean x){
        save=x;
    }
    
    public void setGameOnOff(boolean x){
        onGoingMatch=x;
    }
            
    public void spawnSoldiers(){
        int maxSpawnNumber = colony.getNumTunnels() * soldierModifier;
        
        System.out.println("maxSpawnNumber =" + maxSpawnNumber);
        
        for(int i = 0; i < maxSpawnNumber; i++)
        {
            //chooses a tunnel randomly
            int chosenTunnel = (int) (Math.random()*100000) % colony.getNumTunnels();
            
            Room entryRoom = colony.getEntryRoom(chosenTunnel);
            entryRoom.addSoldier(new Soldier(entryRoom));
        }
    }
    
    public void robotsTurn(){           
        
        for(int i = 0; i < colony.getNumTunnels(); i++)
        {
            for(int j = 0; j < colony.getTunnelLength(i); j++)
            {
                Robot robot = colony.getTunnel(i).get(j).getRobot();
                if(robot != null)
                {   
                    robot.act(colony);
                }
            }
        }
    }
    
    public void soldiersTurn(){         
        
        //all soldiers of map
        for(int i = 0; i < colony.getNumTunnels(); i++)
        {
            for(int j = colony.getTunnelLength(i)-1; j >= 0 ; j--) //from right to left
            {
                ArrayList<Soldier> soldierList = colony.getRoom(i, j).getSoldierList();
                Robot robotBeforeTurn = colony.getRoom(i, j).getRobot();
                
                int loops = soldierList.size();
                for(int k = 0; k < loops; k++)
                {
                    Soldier soldier = soldierList.get(0);
                    
                    soldier.act(colony);
                    
                    Robot robotAfterTurn = colony.getRoom(i, j).getRobot();
                    System.out.println("soldier at room " + i + j + " acted");
                    //if robot dies soldiers dont move further
                    if(robotBeforeTurn != robotAfterTurn)
                    {
                        System.out.println("loop broke");
                        break;
                    }
                }
            }
        }
        
        //all soldiers of entryRooms
        for(int i = 0; i < colony.getNumTunnels(); i++)
        {
            ArrayList<Soldier> soldierList = colony.getEntryRoom(i).getSoldierList();
            int loops = soldierList.size();
            
            for(int j = 0; j < loops; j++)
            {
                Soldier soldier = soldierList.get(0);

                soldier.act(colony);
            }
        }
    }

    public boolean gameOver(){
//        MasterRobot master = null;
        boolean gameOver = false;
        
        //find master
        
//        colony.getMasterRoom().breached();
//        for(int i = 0; i < colony.getNumTunnels(); i++)
//        {
//            for(int j = 0; j < colony.getTunnel(i).size(); j++ )
//            {
//                Room room = colony.getRoom(i, j);
//                if(room.getClass() == MasterRoom.class)
//                {
//                    master = room.getMasterRobot();
//                }
//            }
//        }

        if(gamemode.equals("Siege"))
        {
             //check master's condition
            if(colony.getMasterRoom().breached())
            {
                //message
                scannerMode();
                gameOver = true;
                System.out.println("Oh no!!! The Master Room was breached.");
            }
            //check soldiers count
            if(colony.getAllSoldiersNumber() == 0)
            {
                scannerMode();
                //message
                gameOver = true;
                System.out.println("Congratulanions!!! All rooms cleared.");
            }
        }
        else if(gamemode.equals("Survival"))
        {
            
        }
        else if(gamemode.equals("Siege"))
        {
            
        }
       
        
        
        return gameOver;
    }
    

}