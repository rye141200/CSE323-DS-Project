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

     public static ArrayList<Integer>detectError(String path)throws IOException{
            ArrayList<Integer> detectionAll = new ArrayList<>();
            ////////////////////////////////////////////
            ArrayList<String> parsedTags = parseTagsFromFile(path);
            //////////////////////////////////////////////

            ////////////////////////////////////////////
            ArrayList<String>messages1=detectTagsErrors(parsedTags);
            ArrayList<Integer>detect1 = pickLastNumbers(messages1);
            ///////////////////////////////////////////

            /////////////////////////////////////////
            ArrayList<String>messages2=detectMissingClosingTag(parsedTags);
            ArrayList<Integer>detect2 = pickLastNumbers(messages2);
            //////////////////////////////////////////////
            detectionAll.addAll(detect1);
            detectionAll.addAll(detect2);
            return detectionAll;
        }



  public static void correctError(String path) throws IOException
        {
            ////////////////////////////////////////////
            ArrayList<String> parsedTags = parseTagsFromFile(path);
            //////////////////////////////////////////////

            /////////////////////////////////////////////
            ArrayList<String> data1 = detectTagsErrors(parsedTags) ;
            correctTagsErrors(data1,path);
            ///////////////////////////////////////

            ///////////////////////////////////////////
            ArrayList<String>data2=detectMissingClosingTag(parsedTags);
            correctMissingClosingTag(path,data2);
            /////////////////////////////////////////
        }




}