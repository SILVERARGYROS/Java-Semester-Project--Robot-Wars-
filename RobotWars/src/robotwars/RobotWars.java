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

            System.out.println("New Game\t\t(1)");
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
                    System.out.println("Endless \t\t(3)");
                    
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
                    game.gameOn();
                    
                    if(game.saveOnOff()){                    
                        System.out.print("Enter file name (no need to include \".obj\"): ");
                        String fileName= input.nextLine();
                        saveGame(game,fileName);
                        clearScreen();
                        System.out.println("Game saved under name \""+ fileName +".obj\"");
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
                    }
                    catch (IOException e) {
                        System.err.println(e);
                    }
                    catch (ClassNotFoundException e) {
                        System.err.println(e);
                    }
                    
                    if(loadedGame!=null){
                        //System.out.println(loadedGame);
                        loadedGame.setGameOnOff(true);
                        loadedGame.gameOn();
                        //resave
                        if(loadedGame.saveOnOff()){                    
                            System.out.print("Enter file name (no need to include \".obj\"): ");
                            String newFileName= input.nextLine();
                            saveGame(loadedGame,newFileName);
                            clearScreen();
                            System.out.println("Game saved under name \""+ newFileName +".obj\"");
                        }
                        else{
                            clearScreen();
                        }
                    }
                   
                break;

                case 3: //instructions
                    instructions();
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
    }
}
