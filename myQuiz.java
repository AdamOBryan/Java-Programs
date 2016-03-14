/*
* ADAM OBRYAN
* adamobryan.com       
* github.com/aob412
*
* THIS PROGRAM READS IN A QUIZ FILE, ALLOWS USER TO TAKE THE QUIZ
* AND RESPONDS WITH THE CORRECT ANSWERS AND VARIOUS QUIZ STATISTICS
*
**/

import java.util.*;
import java.io.*;



public class AOBryan {

    
    public static void main(String[] args) throws IOException {
        //import file from command line
        File file = new File(args[0]);
        if(args.length > 0) {
        file = new File(args[0]);}
        Scanner input = new Scanner(file);
        ArrayList<String> fileData = new ArrayList<>();
        ArrayList<Question> quizArrayList = new ArrayList<>();
      
        while(input.hasNext()){
            fileData.add(input.nextLine());
        } 
        input.close();
    // end import file to fileData arrayList
       
       
       
    //create question objects from FileData arrayList
    int questionNumber = 1;
    while(fileData.size() > 0){
        //get each question's data from main fileData array
        ArrayList<String> answers = new ArrayList<>();
        
        String Q = fileData.remove(0); //actual question
        int N = Integer.parseInt(fileData.remove(0)); //# of choices
        for(int x=1; x<=N; x++){
            answers.add(fileData.remove(0));}  //add choices to answers Array
        int K = Integer.parseInt(fileData.remove(0)); // # for correct answer
        int T = Integer.parseInt(fileData.remove(0)); // times tried
        int C = Integer.parseInt(fileData.remove(0)); //times correct
        
        //create Question object with data
        Question thequestion = new Question(Q, N, K, T, C, answers, questionNumber);
        //add new question to quiz arraylist
        quizArrayList.add(thequestion);
        questionNumber++;
    }//end making Question objects

    
    System.out.println("\nWelcome to my quiz program!, Good Luck!");
    //show questions..get answers
    for (Question num :quizArrayList){
        num.questionString();
        num.getAnswer();
    }
    System.out.print("*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+\n"+
            "\t\tThank you for the answers! \n\n\n\n");
    
    //show rrsults of quiz
    System.out.println("Here are the results...");
    for (Question num :quizArrayList){
        num.showAnswer();
    }
    
    //show stats
    Question.quizPerformance();
    System.out.println("Here are some cumulative statistics");
    for (Question num :quizArrayList){
        num.statString();
    }
    System.out.println("\n\n------");
    
    
   
    
    //quick high low
    int highElement=0;
    double highest=0;
    int lowElement=0;
    double lowest=100;
    for (int i=0;i<quizArrayList.size();i++){
        if(quizArrayList.get(i).percentCorrectEver()>highest){
            highElement=i;
            highest=quizArrayList.get(i).percentCorrectEver();
        }
        if(quizArrayList.get(i).percentCorrectEver()<lowest){
            lowElement=i;
            lowest=quizArrayList.get(i).percentCorrectEver();
        }
    
    }
    System.out.println("Easiest Question: ");
    quizArrayList.get(highElement).statString();
    System.out.println("\nHardest Question: ");
    quizArrayList.get(lowElement).statString();
    
     //write updates to new file for future use
    PrintWriter output = new PrintWriter(file);
    
    for (Question num :quizArrayList){
        output.print(num.writeSave());
    }
    output.close();
}//end main method
}//end main class


class Question {
    
    String question;
    int numberQ = 0;
    int howManyAnswers = 0;
    int correctAnswer = 0;
    int timesTried = 0;
    int timesCorrect = 0;
    int userAnswer = 0;
    static int nowRight = 0; // to keep track of current stats
    static int nowWrong = 0; // to keep track of current stats
    ArrayList possibleAnswers;
    Scanner keyBoard = new Scanner(System.in);

    public Question(String Q, int N, int K, int T, int C, ArrayList answers, int qNum){
    //BUILD OBJECT FROM INFO
        question = Q;
        howManyAnswers = N;
        correctAnswer = K;
        timesTried = T;
        timesCorrect = C;
        possibleAnswers = answers;
        numberQ = qNum;
    }
    
    public void getAnswer(){
    //ASK USER FOR ANSWER AND SAVE INPUT
        int userPick;
        
        System.out.println("\nWhat is your answer? (pick a number)");
        userPick = keyBoard.nextInt();
        
        while(0>userPick || userPick > howManyAnswers){
            System.out.println("What is your answer? (pick a number)");
            userPick = keyBoard.nextInt();
        }
        
        this.userAnswer = userPick - 1;
        checkAnswer(userAnswer);
    }
   
    public void showAnswer(){
    // PRINTS QUESTION, USER ANSWER, AND CORRECT ANSWER
        System.out.print(
                "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+\n"+
                        
            "Question #" + numberQ + ": \n" +
            question + "\n\n" +
            "Your Answer: \n\t" + 
              (userAnswer+1)+")"+ possibleAnswers.get(userAnswer) + "\n" + 
            "Correct Answer: \n\t" + 
               (correctAnswer +1)+")"+ possibleAnswers.get(correctAnswer) + "\n" );
        
        if (userAnswer == correctAnswer){
            System.out.println("\nCorrect! Nice Job!");
        } else{
            System.out.println("\nBummer, remember that for next time!");
        }
    }
    
    public void checkAnswer(int userPick){
    //KEEPS TRACK OF RIGHT AND WRONG ANSWERS   
        if (userPick == correctAnswer){
            timesTried++;
            timesCorrect++;
            nowRight++;
        } else{
            timesTried++;
            nowWrong++;
        }
    }
    
    public void questionString(){
    //PRINTS QUESTION AND ANSWER CHOICES
        System.out.print(
                "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+\n"+
            "Question #" + numberQ + ": \n" +
            question + "\n\n" +
            "Choices: \n" 
        );
        int x = 1;
        for(Object i : possibleAnswers){
            System.out.println("\t"+x+")"+i);
            x++;
        }
        
    }
    public static void quizPerformance(){
        System.out.print(
                "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+\n\n"+
            "\tYour overall performance today: " + 
            "\n\t\t"  + "Right: " + nowRight +
            "\n\t\t"  + "Wrong: " + nowWrong +
            "\n\t\t" + "Percent Correct: " );
            System.out.format("%.2f %% \n\n", percentCorrectToday() );
            System.out.print("*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+"
                + "*+*+*+*+*+*+*+*+*+*+*+\n\n");
    }
    
    public void statString(){  
    //PRINTS STATS ABOUT QUESTION
        System.out.print(
            "Question #" + numberQ + ": " +
            "\n" + question +
            "\n\t" + "Times Tried: " + timesTried + 
            "\n\t" + "Times Correct: " + timesCorrect + 
            "\n\t" + "Percent Correct: " );
                    System.out.format("%.2f %% \n", percentCorrectEver() );    
    }
    
    public  float percentCorrectEver(){
        float Perc = ((float)timesCorrect*100/timesTried);
        return Perc;
    }
    
    public  static float percentCorrectToday(){
        float Perc = ((float)nowRight*100/(nowRight+nowWrong));
        return Perc;
    }
    
    public String writeSave(){
        //writes info exactly as was formatted in original txt file
        String sendOff;

        sendOff = 
            question +"\n"+
            howManyAnswers +"\n";

        for(Object val:possibleAnswers)
            sendOff += val+"\n";

        sendOff += correctAnswer +"\n"+
                timesTried +"\n"+
                timesCorrect +"\n"; 

        return sendOff;
    }
}//end Question class


/////////// TEXT FILE HELPER BELOW ( NEEDS TO BE myQuiz.TXT)
What did the cow jump over?
3
the moon
the sun
a bunny
0
0
0
During what month do people sleep the least?
4
January
February
October
December
1
0
0
Who was the first president?
4
Bill Clinton
Jimmi Carter
Abe Lincoln
George Washington
3
0
0
Where do you go to buy walls?
3
Giant Eagle
Target
Wallmart
2
0
0
What is not a fruit?
5
Apple
Orange
Potato
Grapes
Pear
2
0
0
