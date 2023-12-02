import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
/*****************************/
/* 
    Authors: -Ahmad Mahfouz
             -Yousef Shawky
    Description: - Implementation of Parsing Logic
    
 */
/*****************************/
public class Parsing {
    public static  void parseXML() throws Exception{
        
    }
    
    public static void main(String [] args) throws Exception{
        ArrayList<String> lines = FileSample.fileSampleSelector();

        for(String line: lines){
            //Case (1): Line with delimiter 
            if(line.matches(".*<.*") && line.matches(".*/.*") && line.charAt(1) !='/')
                System.out.println(parseLineWithDelimiter(line));
            //Case(2): Line without delimiter, only tagName
            else if(line.charAt(1) !='/' && line.charAt(0) == '<')
                System.out.println(parseLineNoDelimiter(line));
        }
    }

    //Helper methods

    //WORKS 100% InShaa' Allah 🤲 
    private static ArrayList<String> parseLineWithDelimiter(String line){
        String tagName = line.substring(line.indexOf('<')+1,line.indexOf('>'));
        String tagValue = line.substring(line.indexOf('>')+1,line.indexOf('/')-1);
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(tagName.trim());
        retVal.add(tagValue.trim());
        return retVal;
    }
    private static String parseLineNoDelimiter(String line){
        //TODO
        return line.substring(1,line.length()-1); //remove this nigga
    }
}

