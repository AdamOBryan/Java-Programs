/*
* ADAM OBRYAN
* adamobryan.com       
* github.com/aob412
*
*
**/

import java.util.*;
import java.io.*;
public class AOB {

    public static void main(String[] args) {
        boolean stillPlaying = true;
        Scanner sc = new Scanner(System.in);
        System.out.println("How big would you like your world to be?");
        int worldSize = sc.nextInt();
        /*while (!sc.hasNextInt()){
            System.out.println("How big would you like your world to be?");
            worldSize = sc.nextInt();
            }*/
        
        //create array
        char [] generateUniverse =  new char [worldSize];
        for (int i=0; i<generateUniverse.length; i++){
            generateUniverse[i] = '.';
        }
        generateUniverse[0] = '0';
        for (int i=5; i<generateUniverse.length; i= i+5){
            generateUniverse[i] = '^';
        }
        for (int i=7; i<generateUniverse.length; i= i+7){
            generateUniverse[i] = '0';   //change back to zero!!!!!!!!!!!!!!
        }
        for (int i=0; i<generateUniverse.length; i++){
            System.out.print(generateUniverse[i]);
        }
    while(stillPlaying){
        System.out.println("[1]Advance a year , [2]Save,  [3]Quit?");
        int whatToDo = sc.nextInt();
        while (whatToDo < 0 || whatToDo > 3){
            System.out.println("[1]Advance a year , [2]Save,  [3]Quit?");
            whatToDo = sc.nextInt();
        }
        switch(whatToDo){
        case 1:
            System.out.println("Advancing a year");
            for (int i=0; i<generateUniverse.length; i++){
                if(generateUniverse[i]=='2'){
                    try {
                    if (generateUniverse[++i] == '.'){
                        generateUniverse[i] ='2';
                        generateUniverse[i-1] = '.';
                    }}catch(ArrayIndexOutOfBoundsException e){}
                
                    try{
                    if (generateUniverse[i] == '^'){
                        generateUniverse[i] = '0';
                        generateUniverse[i-1] = '.';
                    }       }catch(ArrayIndexOutOfBoundsException e){}
                }
                else if(generateUniverse[i]=='1'){
                    generateUniverse[i] ='2';
                }
                else if(generateUniverse[i]=='0'){
                    generateUniverse[i] ='1';
                }
            }
            for (int f=0; f<generateUniverse.length; f++){
                System.out.print(generateUniverse[f]);
                }
            break;
            
        case 2:
            System.out.println("Saving");/*
            FileWriter fw = new FileWriter(universe.txt);
            Writer output = new BufferedWriter(fw);*/
            break;
        case 3:
            System.out.println("Quiting");
            stillPlaying = false;
            break;
}
            }}}
        
        

        

        

        

        
