import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class ErrorHandler{
    // IF ANY METHOD PROTOTYPE GOT CHANGED, LIST THE CHANGES IN THE "methods.txt" FILE 
    // UNIT TEST ALL THE METHODS

    /**************************************************/
    /**************************************************/
    /* 
        Tasks: 1) Implement detectError method
               2) Implement correctError method
               3) Implement containsError method
    */
    /**************************************************/
    /**************************************************/

    //Use the FileSample.fileSampleSelector() method in the main to test your code
    /********************EXAMPLE**************************************************/
    /*  
        public static void main(String [] args) throws Exception{
            FileSample.fileSampleSelector();
        }
    */

    public boolean containsError(String path){

        ArrayList Errors = detectError(path) ; 
        if(Errors.size() == 0 ) return false ; 
       else  return true; 
    }

    public ArrayList<String> detectError(String path)throws IOException{
        /* 
            Task:
            - Implement a method that finds the mistakes in the XML file, store the lines with the mistakes in an ArrayList of strings and return it
        */
        return detectionLastStep(path);
        
    }

    public void correctError(){
        /* 
            Task:
            - Implement a method that corrects the mistakes by writing into a new file, and asks the user where to store it
            - Use the method detectError() to get the info about the lines containing the mistakes 
         */

        //TODO
    }





}