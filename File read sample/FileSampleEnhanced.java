package com.example.demo.FileReading;
import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
import java.io.*;
/*****************************/
/* 
    Authors: -Ahmad Mahfouz
             -Yousef Shawky
    Description: - Implementation of parsing logic part 1
    
 */

public class FileSampleEnhanced {

    public static StringBuilder readFileMethod(String path) throws Exception{ //O(n)
        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder linesBlock = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
            linesBlock.append(line.trim());
        reader.close(); // close the file
        return linesBlock;
    }
    public static ArrayList<String> readFileParsed(String path) throws Exception{ //O(n^2)
        //1) read the file 
        StringBuilder linesBlock = readFileMethod(path);
        
        //2) Making the ArrayList
        // < >
        ArrayList<String> linesList = new ArrayList<>(Arrays.asList(linesBlock.toString().split("[<>]"))); //O(n)
        linesList.removeAll(Arrays.asList("",null)); // remove empty space elements

        //remove whitespaces
        for(int i =0;i<linesList.size();i++) //O(n)
            linesList.set(i,linesList.get(i).trim());
        
        //3) Seperating tags and values
        int index = 0;
        ArrayList<String> tags = new ArrayList<>(); // all tags in XML
        for(String element:linesList){
            if(element.contains("/")){
                tags.add(element.substring(1));
                element = "<" + element +">"; // /id -> </id>
                linesList.set(index, element);
            }
            index++;
        }
        index =0;
        for(String element:linesList){
            if(tags.contains(element)){ 
                element = "<" + element + ">";
                linesList.set(index,element);
            }
            index++;
        }
        return linesList;
    }



    public static ArrayList<String> readFileParsed(StringBuilder linesBlock) throws Exception{ //O(n^2)

        //1) Making the ArrayList
        ArrayList<String> linesList = new ArrayList<>(Arrays.asList(linesBlock.toString().split("[<>]"))); //O(n)
        linesList.removeAll(Arrays.asList("",null)); // remove empty space elements

        //remove whitespaces
        for(int i =0;i<linesList.size();i++) //O(n)
            linesList.set(i,linesList.get(i).trim());

        //3) Seperating tags and values
        int index = 0;
        ArrayList<String> tags = new ArrayList<>(); // all tags in XML
        for(String element:linesList){
            if(element.contains("/")){
                tags.add(element.substring(1));
                element = "<" + element +">"; // /id -> </id>
                linesList.set(index, element);
            }
            index++;
        }
        index =0;
        for(String element:linesList){
            if(tags.contains(element)){
                element = "<" + element + ">";
                linesList.set(index,element);
            }
            index++;
        }
        linesList.removeAll(Arrays.asList("",null)); // remove empty space elements
        return linesList;
    }


//    public static void main(String[] args) throws Exception{
//        System.out.println(readFileParsed());
//    }
}


