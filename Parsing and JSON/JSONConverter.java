package com.example.demo.Parsing;
import java.util.*;
/*****************************/
/* 
    Authors: -Ahmad Mahfouz
             -Yousef Shawky
    Description: - Implementation of XML -> JSON conversion
    
 */
/*****************************/
//NOTES:
/* 
 * 1) If a parent has children of the same tagName -> then it is an array
 *    but if, it has children of different tagNames -> then it is an object.
 * 
 */
public class JSONConverter {
    public static void appendIdentations(StringBuilder temp,int currentIdentLevel){
        for(int i =0;i<currentIdentLevel;i++)
            temp.append(" ");
    }
    public static StringBuilder XMLToJSON(ArrayList<String> lines) throws Exception{
        //1) Traversing depth first and getting the elements in JSON-order
        Tree XMLTree = Parsing.parseXML(lines); //O(n^2)
        StringBuilder JSONFileString = new StringBuilder();
        JSONFileString.append("{ \n");
        XMLTree.toJSON(JSONFileString, 0); //O(n) memory+time
        JSONFileString.append("}");
        
        //2) Writing into the file
//        FileWriter JSONFile = new FileWriter("CSE323-DS-Project\\Parsing and JSON\\XMLToJSON.json");
//        JSONFile.write(JSONFileString.toString());
//        JSONFile.close();
        return JSONFileString;
    }

}
