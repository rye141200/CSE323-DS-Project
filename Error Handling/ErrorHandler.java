package com.example.demo.ErrorHandling;
import java.util.ArrayList;

import static com.example.demo.ErrorHandling.DetectionAndCorrectionMethods.*;

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

    public static boolean containsError(ArrayList<String> lines) throws Exception {
    return detectErrorsPrecise.containsError(lines);
    }


    // should be called at first
    public static ArrayList<Integer>detectError(StringBuilder XMLText)throws Exception{
        ArrayList<Integer> detectionAll = new ArrayList<>();
        ////////////////////////////////////////////
        ArrayList<String> parsedTags = parseTagsFromFile(XMLText);

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



    public static ArrayList<String> correctError(StringBuilder XMLText) throws Exception
    {
        ////////////////////////////////////////////
        ArrayList<String> parsedTags = parseTagsFromFile(XMLText);
        //////////////////////////////////////////////

        /////////////////////////////////////////////
        ArrayList<String> data1 = detectTagsErrors(parsedTags) ;
        correctTagsErrors(data1,parsedTags);
        ///////////////////////////////////////

        ///////////////////////////////////////////
        ArrayList<String>data2=detectMissingClosingTag(parsedTags);
        correctMissingClosingTag(parsedTags,data2);
        /////////////////////////////////////////
        return parsedTags;
    }



    public static void main(String[] args) throws Exception {
        //System.out.println(detectError("sample.xml"));
        //correctError("sample.xml");
    }
}
