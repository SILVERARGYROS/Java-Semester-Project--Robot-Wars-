// Argyros Konstantinos 
// AM:2022202000014

// Kounadi Vasiliki
// AM:2022202000102

package robotwars;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class RobotWars {

    public static void main(String[] args) {
        System.out.println("\u001b[36mRRRRRR   OOOOO  BBBBB    OOOOO  TTTTTTT    WW      WW   AAA   RRRRRR   SSSSS  \n" +
        "\u001b[36mRR   RR OO   OO BB   B  OO   OO   TTT      WW      WW  AAAAA  RR   RR SS      \n" +
        "\u001b[36mRRRRRR  OO   OO BBBBBB  OO   OO   TTT      WW   W  WW AA   AA RRRRRR   SSSSS  \n" +
        "\u001b[36mRR  RR  OO   OO BB   BB OO   OO   TTT       WW WWW WW AAAAAAA RR  RR       SS \n" +
        "\u001b[36mRR   RR  OOOO0  BBBBBB   OOOO0    TTT        WW   WW  AA   AA RR   RR  SSSSS  ");
        
        System.out.println("\u001b[0m"); //disables prefix from logo
        // \u001b[31m red
        // \u001b[34m blue
        // \u001b[36m cyan
        
        delay(2);
        clearScreen();
        
        while(true)
        {
            clearScreen();
            System.out.println("\nMAIN MENU\n----------");

            System.out.println("New Game\t(1)");
            System.out.println("Load Game\t(2)");
            System.out.println("Instructions\t(3)");
            System.out.println("Credits\t\t(4)");
            System.out.println("Quit\t\t(5)");
            
            Scanner input = new Scanner(System.in);
            int playerChoice=0;
            do{
                try{
                    System.out.print("Enter option: ");

                    playerChoice = input.nextInt();
                    input.nextLine();
                    if(playerChoice < 1 || playerChoice > 5)
                    {
                        throw new OutOfBoundsException(); //not declared throws on top cause its caught in the same method
                    }
                    else{
                        break;
                    }
                }
                catch(InputMismatchException e){
                    System.out.println("Invalid input");
                    input.nextLine();
                }
                catch(OutOfBoundsException e){
                    System.out.println("Invalid option");
                }
            }while(true);

            
            
            /*do{
                System.out.print("Enter option: ");
                playerChoice = input.nextInt();
                if(playerChoice < 1 || playerChoice > 5)
                {
                    //throw new OutOfBoundsException exc;
                    System.out.print("Invalid option");
                }
            }while(playerChoice < 1 || playerChoice > 5);*/
        
        
            
            switch(playerChoice){
                case 1: //new game
                    
                    //SETTINGS
                    String gamemode = "Siege";
                    String difficulty="Easy";
                    int soldierModifier = 1;
                    boolean randomGen = false;
                    int maxRound = 0;
                    boolean breachers = false;
                    int numTunnels = 1;
                    int tunnelLength = 8;
                    int energy = 10;                    
                    
                    clearScreen();
                    //select game mode
                    System.out.println("\n~Enter game mode~");
                    System.out.println("Siege \t\t(1)");
                    System.out.println("Survival \t(2)");
                    System.out.println("Endless \t(3)");
                    
                    do{
                        try{
                            System.out.print("Enter option: ");
                            playerChoice = input.nextInt();
                            input.nextLine();
                            if(playerChoice < 1 || playerChoice > 3)
                            {
                                throw new OutOfBoundsException();
                            }
                            else{
                                break;
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input");
                            input.nextLine();
                        }
                        catch(OutOfBoundsException e){
                            System.out.println("Invalid option");
                        }
                    }while(true);
                    
                    /*do{
                        playerChoice = input.nextInt();
                        if(playerChoice < 1 || playerChoice > 3)
                        {
                            System.out.print("\n--> ");
                        }
                    }while(playerChoice < 1 || playerChoice > 3);*/
                    
                    if(playerChoice == 1)
                    {
                        gamemode = "Siege";
                    }
                    else if(playerChoice == 2)
                    {
                        gamemode = "Survival";
                    }
                    else if(playerChoice == 3)
                    {
                        gamemode = "Endless";
                    }
                    
                    
                    clearScreen();
                    //select Difficutly
                    System.out.println("\n~Enter difficulty level~");
                    System.out.println("Easy \t\t(1)");
                    System.out.println("Normal \t\t(2)");
                    System.out.println("Hard \t\t(3)");
                    System.out.println("Realistic \t(4)");
                    System.out.println("Custom \t\t(5)");
                    do{
                        try{
                            System.out.print("Enter option: ");
                            playerChoice = input.nextInt();
                            input.nextLine();
                            if(playerChoice < 1 || playerChoice > 5)
                            {
                                throw new OutOfBoundsException();
                            }
                            else{
                                break;
                            }
                        }
                        catch(InputMismatchException e){
                            System.out.println("Invalid input");
                            input.nextLine();
                        }
                        catch(OutOfBoundsException e){
                            System.out.println("Invalid option");
                        }
                    }while(true);
                    /*do{
                        playerChoice = input.nextInt();
                        if(playerChoice < 1 || playerChoice > 5)
                        {
                            System.out.print("\n--> ");
                        }
                    }while(playerChoice < 1 || playerChoice > 5);*/
                    
                    if(playerChoice == 1) //Easy
                    {
                        difficulty="Easy";
                        soldierModifier = 1;
                        randomGen = false;
                        breachers = false;
                        numTunnels = 1;
                        tunnelLength = 8;
                        energy = 10;
                        
                        if(gamemode.equals("Survival"))
                        {
                            maxRound = 25;
                        }
                    }
                    if(playerChoice == 2) //Normal
                    {
                        difficulty="Normal";
                        soldierModifier = 2;
                        randomGen = false;
                        breachers = false;
                        numTunnels = 3;
                        tunnelLength = 8;
                        energy = 8;
                        
                        if(gamemode.equals("Survival"))
                        {
                            maxRound = 30;
                        }
                    }
                    if(playerChoice == 3) //Hard
                    {
                        difficulty="Hard";
                        soldierModifier = 3;
                        randomGen = false;
                        breachers = false;
                        numTunnels = 5;
                        tunnelLength = 8;
                        energy = 5;
                        
                        if(gamemode.equals("Survival"))
                        {
                            maxRound = 40;
                        }
                    }
                    if(playerChoice == 4) //Realistic
                    {
                        difficulty="Realistic";
                        soldierModifier = 4;
                        randomGen = true;
                        breachers = true;
                        numTunnels = 5;
                        tunnelLength = 8;
                        energy = 5;
                        
                        if(gamemode.equals("Survival"))
                        {
                            maxRound = 50;
                        }
                        
                    }
                    if(playerChoice == 5) //Custom
                    {
                       
                    }
                    
                      //loading screen??
                    //create game
                    Game game = new Game(gamemode, difficulty, soldierModifier, randomGen, maxRound, breachers, numTunnels, tunnelLength, energy);
                    //start game
                    
                    loadingScreen("Loading");
                    
                    game.gameOn();
                    
                    if(game.saveOnOff()){                    
                        System.out.print("Enter file name (no need to include \".obj\"): ");
                        String fileName= input.nextLine();
                        saveGame(game,fileName);
                        System.out.println("Game saved under name \""+ fileName +".obj\"");
                        System.out.println("Press enter to return to main menu.");
                        input.nextLine();
                        clearScreen();
                    }
                    else{
                        clearScreen();
                    }

                break;

                case 2: //load game
                    Game loadedGame=null;
                    System.out.print("Enter file name (no need to include \".obj\"): ");
                    String fileName= input.nextLine();
                    try{
                        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName+ ".obj"));

                        loadedGame = (Game)(ois.readObject());
                        
                        ois.close();
                    }        
                    catch (FileNotFoundException e) {
                        System.out.println("File not found");
                        System.out.println("Press enter to return to main menu.");
                        input.nextLine();
                        clearScreen();
                    }
                    catch (IOException e) {
                        System.err.println(e);
                    }
                    catch (ClassNotFoundException e) {
                        System.err.println(e);
                    }
                    
                    if(loadedGame!=null){
                        loadedGame.setGameOnOff(true);
                        
                        loadingScreen("Loading");
                        
                        loadedGame.gameOn();
                        //resave
                        if(loadedGame.saveOnOff()){                    
                            System.out.print("Enter file name (no need to include \".obj\"): ");
                            String newFileName= input.nextLine();
                            saveGame(loadedGame,newFileName);
                            System.out.println("Game saved under name \""+ newFileName +".obj\"");
                            System.out.println("Press enter to return to main menu.");
                            input.nextLine();
                            clearScreen();
                        }
                        else{
                            clearScreen();
                        }
                    }
                break;

                case 3: //instructions
                    clearScreen();
                    instructions();
                    System.out.println("\nPress enter to return to main menu.");
                    input.nextLine();
                    clearScreen();
                break;

                case 4: //credits
                    //input.nextLine(); //clears buffer

                    clearScreen();
                                                                                      
                    //System.out.println("\\u001B[1m CREDITS");
                    //System.out.println("CREDITS");
                    
                    //freeze duration between time stops
                    double duration = 0.8;
                    
                    //star wars font
                    //CREDITS
                    delay(duration);
                    System.out.println("  ______ .______       _______  _______   __  .___________.    _______.");
                    delay(duration);
                    System.out.println(" /      ||   _  \\     |   ____||       \\ |  | |           |   /       |");
                    delay(duration);
                    System.out.println("|  ,----'|  |_)  |    |  |__   |  .--.  ||  | `---|  |----`  |   (----`");
                    delay(duration);
                    System.out.println("|  |     |      /     |   __|  |  |  |  ||  |     |  |        \\   \\    ");
                    delay(duration);
                    System.out.println("|  `----.|  |\\  \\----.|  |____ |  '--'  ||  |     |  |    .----)   |   ");
                    delay(duration);
                    System.out.println(" \\______|| _| `._____||_______||_______/ |__|     |__|    |_______/    ");
                    delay(duration);
                    System.out.println("");
                    delay(duration);
                    System.out.println("");
                    delay(duration);

                    System.out.println("");

                    //three point font
                    //VICKY KOUNADI
                    delay(duration);
                    System.out.println("\\  /. _|     |/ _     _  _  _|.");
                    delay(duration);
                    System.out.println(" \\/ |(_|<\\/  |\\(_)|_|| |(_|(_||");
                    delay(duration);
                    System.out.println("         /                                      ");
                    delay(duration);
                    System.out.println("");

                    //three point font
                    //ARGYROS KONSTANTINOS
                    delay(duration);
                    System.out.println(" /\\  _ _    _ _  _  |/ _  _  __|_ _  _ _|_. _  _  _");
                    delay(duration);
                    System.out.println("/~~\\| (_|\\/| (_)_\\  |\\(_)| |_\\ | (_|| | | || |(_)_\\");
                    delay(duration);
                    System.out.println("       _|/                                              ");
                    delay(duration);
                    System.out.println("");
                    delay(duration);

                    //Thank You for playing 
                    //small font ?
                    System.out.println("  _____   _                    _                              __                        _                 _                   _ ");
                    delay(duration);
                    System.out.println(" |_   _| | |_    __ _   _ _   | |__    _  _   ___   _  _     / _|  ___   _ _     _ __  | |  __ _   _  _  (_)  _ _    __ _    | |");
                    delay(duration);
                    System.out.println("   | |   | ' \\  / _` | | ' \\  | / /   | || | / _ \\ | || |   |  _| / _ \\ | '_|   | '_ \\ | | / _` | | || | | | | ' \\  / _` |   |_|");
                    delay(duration);
                    System.out.println("   |_|   |_||_| \\__,_| |_||_| |_\\_\\    \\_, | \\___/  \\_,_|   |_|   \\___/ |_|     | .__/ |_| \\__,_|  \\_, | |_| |_||_| \\__, |   (_)");
                    delay(duration);
                    System.out.println("                                       |__/                                     |_|                |__/             |___/         ");
                    delay(duration);
                    System.out.println("");
                    delay(duration);
                    System.out.println("");


                    System.out.println("");
                    System.out.println("Press enter to return to main menu.");

                    //Original freeze Idea
                    input.nextLine();
                    clearScreen();

                    //StackOverflow freeze Idea
    //                try{System.in.read();}
    //                catch(Exception e){}
    //                
    //                *what's the difference??*


                 break;

                case 5: //quit to desktop
                    return;                
            }
        }
    }
    
    public static void saveGame(Game game, String fileName){
        
        loadingScreen("Saving");
        
        try {
            game.setSave(false);
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName+".obj"));

            oos.writeObject(game);
            
            oos.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File not saved correctly");
        }
        catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public static void loadingScreen(String givenString)
    {
        String message = givenString;
        double duration = 0.5;
        clearScreen();
        
        for(int i = 0; i < 3; i++)
        {
            System.out.print(message + ".");
            delay(duration);
            System.out.print("\r\t\t\t\t\r");
            System.out.print(message + "..");
            delay(duration);
            System.out.print("\r\t\t\t\t\r");
            System.out.print(message + "...");
            delay(duration);
            System.out.print("\r\t\t\t\t\r");
        }

    }
 
    public static void delay(double seconds){
        
        try{
            TimeUnit.MILLISECONDS.sleep((long) (seconds * 1000));
        }
        catch(InterruptedException e){
            System.out.println("CRASH REPORT\nRobotWars.exe stopped running.");
        }
    }
   
    public static void clearScreen() {
        //IDEA 1
//        System.out.print("\033[H\033[2J");
//        System.out.flush();

        //IDEA 2
//        try{
//            Runtime.getRuntime().exec("cls");
//        }
//        catch(IOException e)
//        {
//            System.out.println("CRASH REPORT\nRobotWars.exe could not run.");
//        }

        //IDEA 3
        for(int i = 0; i < 100; i++)
        {
            System.out.println();
        }
        
        //IDEA 4
//        System.out.println("\f");
        
        //IDEA 5
//        System.out.println("\u000c");

        //IDEA 6
//        System.out.println((char) 8);
        
    }
    
    public static void instructions(){
        System.out.println("\n   _____  _______________  __  _____________________  _  ______\n" +
                             "  /  _/ |/ / __/_  __/ _ \\/ / / / ___/_  __/  _/ __ \\/ |/ / __/\n" +
                            " _/ //    /\\ \\  / / / , _/ /_/ / /__  / / _/ // /_/ /    /\\ \\  \n" +
                            "/___/_/|_/___/ /_/ /_/|_|\\____/\\___/ /_/ /___/\\____/_/|_/___/  \n");
        System.out.println("The Robot Colony is threatened by the Empire’s Army. Will you be able to use your unique robot warriors to save your Master?\n" 
                + "The Colony consists of tunnels, which in turn are separated by different rooms. All tunnels "
                + "end up in the room where the Master robot is. \nThere can be only one robot in each room at every round, but it may "
                + "have to fight more than one soldiers of the Empire (maximum soldiers/room: 8).\n" 
                + "The colony has an energy reserve, which is used to equip rooms with a new robots as the game "
                + "progress, and it can be refilled by a certain robot type.");
        
        System.out.println("\nGAME MODES\n-----------\n" +
                "SIEGE:  If a soldier reaches the Master's room, your Master robot is assassinated and youlose the war.\n"
                + "\tHowever, if there are no soldiers in the colony, meaning your robots managed to kill them all, the victory is yours.\n\n"
                + "SURVIVAL: If a soldier reaches the Master's room, your Master robot is assassinated and you lose the war.\n" +
                "\t  However, if you can fend off all the soldiers from the Master room till the maximum round, reinforcements will come to your aid and the victory is yours.\n" +
                "\nENDLESS: If a soldier reaches the Master's room, your Master robot is assassinated and you lose the war. \n" +
                "\t The loss of the war in this mode is inevitable, however the main purpose is to see for how many rounds can the robots fend off the Empire’s soldiers.\n");

        System.out.println("DIFFICULTY LEVELS\n------------------\n" +
                "The size of your colony, as well as the Empire’s manpower, are determined by the level of difficulty you chose.\n\n" +
                "EASY: " +
                "\t1 tunnel with 8 rooms\n" +
                "\t1 soldier enters the tunnel in each round\n" +
                "\tStarting energy reserve: 10\n" +
                "\t(Survival mode) Maximum round: 25\n\n" +
                "NORMAL: " +
                "3 tunnels with 8 rooms\n" +
                "\t6 soldiers enters the tunnels in each round\n" +
                "\tStarting energy reserve: 8\n" +
                "\t(Survival mode) Maximum round: 30\n\n" +
                "HARD: " +
                "\t5 tunnels with 8 rooms\n" +
                "\t15 soldiers enters the tunnels in each round\n" +
                "\tStarting energy reserve: 5\n" +
                "\t(Survival mode) Maximum round: 40\n\n" +
                "REALISTIC: " +
                "5 tunnels with 8 rooms\n" +
                "\t   20 soldiers enters the tunnels in each round\n" +
                "\t   Starting energy reserve: 5\n" +
                "\t   (Survival mode) Maximum round: 50\n\n" +
                "CUSTOM: " +
                "You get to build your own colony and have better control of your defensives at the entry.\n" +
                "\tYou can random build a random colony, where the numbers in each tunnel are not equal.\n" +
                "\t[…] tunnels with […]/(random colony) […] to […]+5 rooms  (maximum tunnels: 26, A-Z)\n" +
                "\t[…]*(number of tunnels) soldiers enters the tunnels in each round\n" +
                "\tStarting energy reserve: […]\n" +
                "\t(Survival mode) Maximum round: […]\n");
    
        System.out.println("\u001b[31;1mSOLDIERS\u001b[0m\n---------\n" +
                "All the Empire’s soldiers are the same. They have stamina 3 and can cause damage 1 per round to the robot they attack in the colony. \n"
                + "Multiple soldiers can enter each tunnel according to the difficulty level chosen. \n"
                + "Their sole purpose is to reach the Master room so they can only move forward and attack robots in the colony.\n");
    
        System.out.println("ROBOTS\n-------\n"
                + "You can buy as many robot warriors as your energy reserve allows you to, in order to barricade your colony. \n"
                + "However, you can place only one robot in each room. Each type of robot warrior can fight for the colony in a different way, "
                + "requires different energy portions \nto be placed in the colony and can withstand a soldier’s attack better or worse.\n\n"
                + "\u001b[33mMASTER ROBOT\u001b[0m:  Stamina: 1\n" +
                "\t\tEnergy portion needed: -\n" +
                "\t\tThe leader of your colony, which already exists in the Master room, the last room of each tunnel. He can’t attack soldiers \n\t\tand once its room is breached, it’s killed at once. Must be protected at all costs!\n\n" +
                "\u001b[36mENERGY PRODUCER ROBOT\u001b[0m: Stamina: 1\n" +
                "\t\t\tEnergy portion needed: 3\n" +
                "\t\t\tThis robot increases the colony’s energy reserve by 1, refilling it.\n\n" +
                "\u001b[36mARMORED ROBOT\u001b[0m: Stamina: 4\n" +
                "\t\tEnergy portion needed: 3\n" +
                "\t\tThis robot can delay the soldiers due to its huge stamina and act as a defense mechanism.\n\n" +
                "\u001b[36mFIGHTER ROBOT\u001b[0m: Stamina: 1\n" +
                "\t\tEnergy portion needed: 4\n" +
                "\t\tThis robot can spot a soldier in the range from its room to the Master room. Once it does, it \n\t\tattacks him and reduces his stamina by 1, acting as a direct attack mechanism.\n\n" +
                "\u001b[36mSHOOTER ROBOT\u001b[0m: Stamina: 1\n" +
                "\t\tEnergy portion needed: 4\n" +
                "\t\tThis robot can kill a soldier in its room instantly, acting as a direct attack mechanism. \n\t\tHowever, if it does attack, it requires 3 rounds to reload so it can kill a soldier again.\n\n" +
                "\u001b[36mFIRE ROBOT\u001b[0m:    Stamina: 1\n" +
                "\t\tEnergy portion needed: 4\n" +
                "\t\tThis robot can only attack when its stamina is reduced to 0 (once a soldier attacks it for the first time). \n\t\tOnce it does, it can annihilate al the soldiers in its room, acting similarly to a bomb, sacrificing itself.\n");

        System.out.println("REPRESANTATION GUIDE\n---------------------\n"
                + "~VIEWING YOUR COLONY~\n" +
                "In each round, the general image of your colony is displayed, where you will be able to see all the tunnels \nlined up and have a general idea of the state of the battle inside each room. \n"
                + "Also, the mode and the difficulty level you are playing, as well as the energy reserve and the round you are currently on appear at the bottom of the screen.\n" +
                "\u001b[31;1m*\u001b[0m means enemy presence ([…] number of soldiers in the room) \n" +
                "Since \u001b[31;1m*\u001b[0m can declare multiple soldiers existing in a room, so unless you kill all the soldiers in one room, the * won’t disappear.\n" +
                "\u001b[36m*\u001b[0m means ally presence ([type of] robot in the room) \n" +
                "If there is a \u001b[36m*\u001b[0m in a room in this round, but in the next one it has disappeared, that means that the robot was killed by a soldier.\n" +
                "\u001b[33m*\u001b[0m means Master robot (in the last room of each tunnel)\n\n" +
                "~ROOM CAMERA MODE~\n" +
                "This function (press 1 in the menu) allows you to view the state of any room of the colony you select in detail, and thus understand the changes that occurred in each round. \nYou can see the "
                + "exact number of soldiers in the room with their remaining stamina, as well as the robot you might have placed along with its type and stamina.\n");

        System.out.println("GAME AS A FILE\n---------------\n" +
                "SAVE: You have the option to save the progress of every new game you play, if you are stuck and however wish to keep playing later. \n"
                +"      The game will be saved in a file under the name you chose, which will be located in the same folder as the program-game Robot Wars.\n "
                +"     You can select this option at any time during your gameplay.\n" +
                "LOAD: If you wish to continue an older game you had saved (in the same folder as the program-game Robot Wars), you \n"
                + "      can choose this option in the main menu, and keep playing from the exact point where you had previously stopped.\n");

    }
    

}
