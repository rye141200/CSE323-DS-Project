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

    public ArrayList<Integer> detectError(String path)throws IOException{
     
        return detectionLastStep(path);
        
    }

    public ArrayList<String> correctError(String path) throws IOException{
        return correctLastStep(path);
    }
 
    // should be called at first 
    public void prepareFile(String path )throws IOException{
        String content = readFromFile(path);
        String processedContent = collapseTags(content);
        String x = formatXml(processedContent);
        x=removeEmptyLines(x);
        writeToFile(path,x);
    }




}