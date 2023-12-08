import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Stack;

public class Formatting {
    // IF ANY METHOD PROTOTYPE GOT CHANGED, LIST THE CHANGES IN THE "methods.txt" FILE 
    // UNIT TEST ALL THE METHODS

    /**************************************************/
    /**************************************************/
    /* 
        Tasks: 1) Implement formatXML method that handles identation in the given XML file, write into a new XML file and ask the user
                where to store it in their own directory
    */
    /**************************************************/
    /**************************************************/


    //Use the FileSample.fileSampleSelector() method in the main to test your code
    /********************EXAMPLE**************************************************/
      
    
    
    public static FileWriter formatXML() throws Exception{
        //TODO
        int depth = 0;
        int index = 0;
        int INDENT = 2;
        ArrayList<String> lines = (FileSampleEnhanced.readFileParsed());
        System.out.println(lines);
        Stack<String> scope = new Stack<>();
        ArrayList<StringBuilder> Builder_lines = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            Builder_lines.add(new StringBuilder(lines.get(i)));

            if(lines.get(i).contains("<name>") || lines.get(i).contains("<id>"))
            {
                StringBuilder Bn7bk_ya_bashmohnds_bounes_marks_elwad3_sy2 = new StringBuilder(lines.get(i) + lines.get(i+1) + lines.get(i+2));
                Builder_lines.set(index,Bn7bk_ya_bashmohnds_bounes_marks_elwad3_sy2);
                append_spaces(Builder_lines.get(index), depth);
                i+=2;
            }
            else if(lines.get(i).startsWith("</")){
                depth-=INDENT;
                append_spaces(Builder_lines.get(index), depth);
            }
            else if(lines.get(i).startsWith("<")){
                append_spaces(Builder_lines.get(index), depth);
                depth+=INDENT;

            }
            else{
                append_spaces(Builder_lines.get(index), depth);
            }

            index++;

        }
        FileWriter formatted = new FileWriter("Formatting(Prettier)/Formatted.xml");
        for (StringBuilder s :
                Builder_lines) {
            formatted.append(s.toString());
            formatted.append("\n");

            System.out.println(s);
        }
        formatted.close();
    return formatted;
    }
    private static void  append_spaces(StringBuilder line, int depth){
    for (int i = 0; i < depth; i++){
            line.insert(0," ");

        }

    }

        public static void main(String [] args) throws Exception{
           FileWriter test = formatXML();


        }

}

