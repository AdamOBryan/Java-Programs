
/**
 *
 * @author Adam O'Bryan  
 * 
 * adamobryan.com       github.com/aob412
 */
import java.util.Scanner;
import java.io.*;

public class Assig2 {
    /**
     * This Program Allows you to play high, low, or Seven
     * and saves user information
     */
    public static void main(String[] args) throws IOException {
        String playerFirstName;
        boolean stillPlaying = true;
        Scanner userInput = new Scanner(System.in);
        
        System.out.println("Please Enter Your First Name: ");
        playerFirstName = userInput.nextLine();
        Player gambler = new Player(playerFirstName);
        final int priorRounds = gambler.historicRounds() ;
        final int priorWins = gambler.historicWins();
        
        if (gambler.getBalance()<= 0){
                System.out.println("Awe! You're Out Of Money!");
                System.out.println("Try A New Name?!");
                System.exit(0);
        }
        
        while(stillPlaying)
        {
            int betAmount = 0;
            int gamblerGuess = 0;
            int dealerAmount = 0;
            int payout = 0;
            String betChoice;
            
            System.out.println("How Much Do You Want to Bet?");
            System.out.println("You Have $" + gambler.getBalance()+
                                " in your wallet");
            betAmount = userInput.nextInt();
            
                // cannot exceed wallet amount
                while(betAmount <= 0 || betAmount > gambler.getBalance())
                {
                    System.out.println("Please Enter a Valid Amount");
                    System.out.println("How Much Do You Want to Bet?");
                    betAmount = userInput.nextInt();
                }
                
            System.out.println("Choose Your Bet! ");
            do{
                System.out.println("Over-[O],  Under-[U],  or  7[S]");
                betChoice = userInput.next();
                if ("o".equalsIgnoreCase(betChoice)){
                    gamblerGuess =2;}
                else if ("u".equalsIgnoreCase(betChoice)){
                    gamblerGuess = 1;}
                else if ("s".equalsIgnoreCase(betChoice) || 
                        "7".equals(betChoice)){
                    gamblerGuess = 0;}
                else{
                gamblerGuess = 7;}
            }
            while(gamblerGuess <0 || gamblerGuess >3);
            
       //roll the dice
            dealerAmount = rollEm();

           
      //compare dealer and player
        if (dealerAmount == gamblerGuess)
        {
            System.out.println("Congrats! You Won!");
            System.out.println("$" + gambler.getBalance());
            if (dealerAmount == 0){
                payout = betAmount * 4;
                gambler.roundWon();
                gambler.addMoney(payout);
            }
            else{
                payout = betAmount;
                gambler.roundWon();
                gambler.addMoney(payout);
            }
            System.out.println("     + $" + payout);
        }
        else{
            System.out.println("Blahh! You Lost :(");
            System.out.println("$" + gambler.getBalance());
            System.out.println("     - $" + betAmount);
            gambler.subtractMoney(betAmount);
            gambler.roundLost();
            }
        
        //after each round
        if (gambler.getBalance()<= 0){
            System.out.println("Awe! You're Out Of Money!");
            gambler.roundLost();
            //Save and end
            System.out.println("Saving....");
            gambler.savePlayer();
            letsQuit(gambler, priorRounds, priorWins);
            }
        System.out.println("Wallet Balance: " + gambler.getBalance());
        System.out.println("Press: 1 to save | 2 to Quit | or 3 to continue");
        int userAnswer = userInput.nextInt();
            //check for correct input
        while (userAnswer < 0 || userAnswer > 3){
            System.out.println("Press: 1 to save | 2 to Quit | or 3 to continue");
            userAnswer = userInput.nextInt();
        }
             
        switch (userAnswer){
            case 1:
                System.out.println("Saving....");
                gambler.savePlayer();
                break;
            case 2:
                System.out.println("Saving....");
                gambler.savePlayer();
                System.out.println("Quiting");
                letsQuit(gambler, priorRounds, priorWins);
            default:
        }
            
        }//end while loop

    }//end main
    
    public static void letsQuit(Player gambler, int priorRounds, int priorWins){
        //this method wraps up everything when quiting
        int thisRounds = gambler.historicRounds() - priorRounds;
        int thisWins = gambler.historicWins() - priorWins;
        int thisAverage = thisWins/thisRounds * 100;  
        System.out.print(gambler.playerOutput());
        
        System.out.println("During this game:");
        System.out.println("Rounds Played: " + thisRounds);
        System.out.println("Rounds Won: " + thisWins);
        System.out.println("Win Percentage: " + thisAverage + "%");
        System.out.println("");
        System.out.println("Overall:");
        System.exit(0);
    }
    
    public static int rollEm(){
        //this method rolls 2 dice and returns the sum
        Die die1 = new Die();
        Die die2 = new Die();
        int roll1 = die1.roll();
        int roll2 = die2.roll();
        int rollAmount = roll1 + roll2;
        int dealerAmount = 0;
        
        System.out.println("Die One.... " + roll1);
        System.out.println("Die Two.... " + roll2);
        System.out.println("Dice Total.... " + rollAmount);
        
        if (rollAmount == 7){
            dealerAmount = 0;}
        if (rollAmount < 7){
            dealerAmount = 1;}
        if (rollAmount > 7){
            dealerAmount = 2;}
        return dealerAmount;
    }
  
}//end main program






/*
 * Adam OBryan Player Class
 * creates a Die object in the main program
 */
import java.util.Random;

public class Die {
       private int value = 0;
        
        public Die(){
            
                }
        
        public int roll(){
            value = 0;
            Random randNumber = new Random();
            value = randNumber.nextInt(6)+1;
            return value;
        }
} //end die class



/*
 * Adam OBryan Player Class
creates a gambler object in the main program
 */
import java.io.*;
import java.util.*;


public class Player {
    private String firstName ="";
    private String lastName ="";
    private int walletBallance = 1000;
    private int roundsPlayed=0;
    private int roundsWon=0;
    
    public Player(String firstName) throws IOException{
      this.firstName = firstName;
      loadPlayer(firstName);
    }

    public String playerOutput(){
        //PRINT PLAYER INFO
        return  "\n"+firstName +" "+lastName+
                "\nMoney Available: $"+walletBallance+
                "\nLifetime Rounds Played: "+ roundsPlayed+
                "\nLifetime Rounds Won: "+ roundsWon +
                "\nLifetime Win Percentage: "+ winPercentage() +" % \n\n";   
    }
 
    public void addMoney(int winnings){
        walletBallance += winnings;
    }
    
    public void subtractMoney(int loss){
        walletBallance -= loss;
    }
    
    public void roundWon(){
        roundsPlayed+= 1;
        roundsWon+= 1;
    }
    
    public void roundLost(){
        roundsPlayed+=1;
    }
    
    public int historicRounds(){
        return roundsPlayed;
    }
    
    public int historicWins(){
        return roundsWon;
    }
    
    public int winPercentage(){
        int tally = roundsWon/roundsPlayed;
        return tally * 100;
    }
    
    public String getName(){
        String wholeName = firstName + " " + lastName;
        return wholeName;
    }
    public int getBalance(){
        return walletBallance;
    }
    
    
    public void savePlayer() throws IOException{
        //saves player data 
        FileWriter writer = new FileWriter(firstName.toLowerCase() +".txt");
        writer.write(lastName);
        writer.write("\n" + firstName);
        writer.write("\n" + (int) walletBallance);
        writer.write("\n" + (int) roundsPlayed);
        writer.write("\n" + (int) roundsWon);
        writer.close();
        System.out.println("saved it");   
    }
    
    
    public void loadPlayer(String name) throws IOException{
        //loads player data
        String userName = name.toLowerCase();
        File userFile = new File (userName + ".txt");
        if(userFile.exists()){
            File inFile;
            inFile = new File(userName + ".txt");
            Scanner input = new Scanner(inFile);
            lastName = input.nextLine();
            firstName = input.nextLine();
            walletBallance = input.nextInt();
            roundsPlayed = input.nextInt();
            roundsWon = input.nextInt();
            while(input.hasNext()){
            String garbage = input.nextLine();}
            input.close();
            
        }
        else{
             Scanner input = new Scanner(System.in);
            System.out.println("Enter Your Last Name");
            lastName = input.nextLine();
            savePlayer();
        }
        
    }
     
     
}//end program

