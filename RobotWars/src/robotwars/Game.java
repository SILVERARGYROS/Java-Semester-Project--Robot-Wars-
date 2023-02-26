package robotwars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import static robotwars.RobotWars.clearScreen;
import java.util.Scanner;
import static robotwars.RobotWars.delay;

public class Game implements Serializable{
    
    private String gamemode;
    private String difficulty;
    private int soldierModifier;
    private boolean randomGen;
    private int maxRound;
    private Colony colony;
    private int currentRound;
    private boolean onGoingMatch;
    private boolean save;
    private double pauseDuration;
            
    public Game(String gamemode, String difficulty, int soldierModifier, boolean randomGen, int maxRound, int numTunnels, int tunnelLength, int energy)
    {
        this.gamemode = gamemode;
        this.difficulty= difficulty;
        this.soldierModifier = soldierModifier;
        this.randomGen = randomGen;
        this.maxRound = maxRound;
        
        this.colony = new Colony(energy, numTunnels, tunnelLength, randomGen);
        
        this.currentRound = 0;
        this.onGoingMatch = true;
        this.save=false;
        pauseDuration = 0;
    }
    
    
    
    public void gameOn(){
        
        Scanner input = new Scanner(System.in);
        
        do{         //onGoing Match
            
            //CORRECT ORDER NOTE
            playerTurn();       //
            if(!onGoingMatch){
                    break;
            }
           
            spawnSoldiers();    //done
            clearScreen();
            scannerMode();
            delay(pauseDuration);
            
            soldiersTurn();     //done
            clearScreen();
            scannerMode();
            delay(pauseDuration);
            
            robotsTurn();       //done
            clearScreen();
            scannerMode();
            delay(pauseDuration);
            
            currentRound++;
            
            if(gameOver())      //semi done
            {
                onGoingMatch = false;
                //press enter to return to main menu
                //System.out.print("Press enter to return to main menu");
                //input.nextLine();
//                System.out.println("onGOingMatch = " + onGoingMatch);
//                return;
                System.out.print("Press enter to return to main menu");
                input.nextLine();
            }
        }while(onGoingMatch);
            
    }
    
    public void scannerMode(){
        //System.out.println("--ROBOT COLONY--");
        System.out.println("             ___  ____  ___  ____  ______  _________  __   ____  _  ____  __\n" +
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
        System.out.println("End turn \t\t(4)");
        System.out.println("Save \t\t\t(5)");
        System.out.println("Quit \t\t\t(6)");
//        System.out.println("Turn animations on/off\t(7)");

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
                    System.out.println("Energy Producer Robot    -- 3  ENERGY  (1)");
                    System.out.println("Armored Robot            -- 3  ENERGY  (2)");
                    System.out.println("Fighter Robot            -- 4  ENERGY  (3)");
                    System.out.println("Shooter Robot            -- 4  ENERGY  (4)");
                    System.out.println("Fire Robot               -- 4  ENERGY  (5)");
                    System.out.println("Power House Robot        -- 8  ENERGY  (6)");
                    System.out.println("Obsidian Robot           -- 10 ENERGY  (7)");
                    System.out.println("Radiation Robot          -- 25 ENERGY  (8)");
                    System.out.println("Long Range Shooter Robot -- 8  ENERGY  (9)");
                    System.out.println("Laser Shooter Robot      -- 20 ENERGY (10)");
                    System.out.println("Fire Barrel Robot        -- 30 ENERGY (11)");
                    System.out.println("BFG9000 Robot            -- 50 ENERGY (12)");
                    System.out.println("Exit                                   (0)");
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

                            if(playerChoice < 0 || playerChoice > 12)
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
                            case 6:
                                robot = new PowerHouseRobot(null);
                                break;
                            case 7:
                                robot = new ObsidianRobot(null);
                                break;
                            case 8:
                                robot = new RadiationRobot(null);
                                break;
                            case 9:
                                robot = new LongRangeShooterRobot(null);
                                break;
                            case 10:
                                robot = new LaserShooterRobot(null);
                                break;
                            case 11:
                                robot = new FireBarrelRobot(null);
                                break;
                            case 12:
                                robot = new BFG9000Robot(null);
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
//            else if(playerChoice == 7){  //turn animations on / off 
//                if(pauseDuration > 0)
//                {
//                    pauseDuration = 0;
//                }
//                else if(pauseDuration == 0)
//                {
//                    pauseDuration = 0.8;
//                }
//            }
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
            for(int j = colony.getTunnelLength(i)-2; j >= 0 ; j--) //from right to left
            {
                ArrayList<Soldier> soldierList = colony.getRoom(i, j).getSoldierList();
                Robot robotBeforeTurn = colony.getRoom(i, j).getRobot();

                for(int k = soldierList.size() - 1; k >= 0; k--)
                {
                    Soldier soldier = soldierList.get(k);
                    
                    soldier.act(colony);
                    
                    Robot robotAfterTurn = colony.getRoom(i, j).getRobot();
                    System.out.println("soldier at room " + i + j + " acted");
                    //if robot dies soldiers dont move further
                    if(robotBeforeTurn != robotAfterTurn)
                    {
                        break;
                    }
                }
            }
        }
        
        //all soldiers of entryRooms
        for(int i = 0; i < colony.getNumTunnels(); i++)
        {
            ArrayList<Soldier> soldierList = colony.getEntryRoom(i).getSoldierList();
            
            for(int j = soldierList.size() - 1; j >= 0; j--)
            {
                Soldier soldier = soldierList.get(j);

                soldier.act(colony);
            }
        }
    }

    public boolean gameOver(){
//        MasterRobot master = null;
        boolean gameOver = false;
        clearScreen();

        if(gamemode.equals("Siege"))
        {
             //check master's condition
            if(colony.getMasterRoom().breached())
            {
                scannerMode();
                gameOver = true;
                System.out.println("\u001b[31;1mOH NO! THE MASTER ROOM WAS BREACHED!\u001b[0m");
            }
            //check soldiers count
            if(colony.getAllSoldiersNumber() == 0)
            {
                scannerMode();
                gameOver = true;
                System.out.println("\u001b[36mCONGRATULATIONS! THERE ARE NO THREATS IN THE COLONY!\u001b[0m");
                System.out.println("\u001b[33mTO OUR FALLEN COMRADES, YOU DID NOT DEDICATE YOUR MECHANICAL HEARTS IN VAIN...\u001b[0m");
            }
        }
        else if(gamemode.equals("Survival"))
        {   
            if(colony.getMasterRoom().breached() && currentRound<maxRound){
                scannerMode();
                gameOver = true;
                System.out.println("\u001b[31;1mOH NO! THE MASTER ROOM WAS BREACHED BEFORE ROUND "+ maxRound+"!\u001b[0m");
            }
            else if(currentRound>=maxRound){
                scannerMode();
                gameOver = true;
                System.out.println("\u001b[36mCONGRATULATIONS! YOU COULD HOLD THE FORT FOR "+ maxRound+"ROUNDS WHILE WAITING FOR THE REINFORCEMENTS!\u001b[0m");
                System.out.println("\u001b[33mTO OUR FALLEN COMRADES, YOU DID NOT DEDICATE YOUR MECHANICAL HEARTS IN VAIN...\u001b[0m");
            }
 
        }
        else if(gamemode.equals("Endless"))
        {           
            if(colony.getMasterRoom().breached()){
                scannerMode();
                gameOver = true;
                System.out.println("\u001b[33mTHE MASTER ROOM WAS BREACHED IN ROUND "+ currentRound+"\nCONGRATULATIONS FOR FIGHTING SO VALIANTLY!\u001b[0m");
                System.out.println("\u001b[33mTO OUR FALLEN COMRADES, YOU DID NOT DEDICATE YOUR MECHANICAL HEARTS IN VAIN...\u001b[0m");
            }
        }
        return gameOver;
    }
    

}