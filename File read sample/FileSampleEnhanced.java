import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
import java.io.*;
public class FileSampleEnhanced {
    private static StringBuilder readFileMethod() throws Exception{
        Scanner input = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(new FileReader(input.nextLine()));
        StringBuilder linesBlock = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null)
            linesBlock.append(line.trim());

        reader.close(); // close the file
        input.close();
        return linesBlock;
    }
    public static ArrayList<String> readFileParsed() throws Exception{
        //1) read the file 
        StringBuilder linesBlock = readFileMethod();
        //2) Making the ArrayList
        ArrayList<String> linesList = new ArrayList<>(Arrays.asList(linesBlock.toString().split("[<>]")));
        linesList.removeAll(Arrays.asList("",null)); // remove empty space elements
        //remove whitespaces
        for(int i =0;i<linesList.size();i++)
            linesList.set(i,linesList.get(i).trim());
        
            //Seperating tags an values
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
    public static void main(String[] args) throws Exception{
        System.out.println(readFileParsed());
    }
}
