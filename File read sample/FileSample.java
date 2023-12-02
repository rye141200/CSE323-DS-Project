import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class FileSample {
     public static ArrayList<String> fileReader(String filepath) throws Exception{
            File file = new File(filepath);
            Scanner input = new Scanner(file);
            ArrayList<String> lines = new ArrayList<String>();
            while(input.hasNext()){
                String added_line = input.nextLine();
                lines.add(added_line.trim());
            }
            return lines;
    }
    public static ArrayList<String> fileSampleSelector() throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the file path: ");
        String filepath = in.nextLine();
       return fileReader(filepath);
    }
}
