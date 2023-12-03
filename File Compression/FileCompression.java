import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class FileCompression{
    // IF ANY METHOD PROTOTYPE GOT CHANGED, LIST THE CHANGES IN THE "methods.txt" FILE 
    // UNIT TEST ALL THE METHODS

    /**************************************************/
    /**************************************************/
    /* 
        Tasks: 1) Implement minifyXML_Json method, minify the file removing the /n and spaces and making all lines stick to each other
               2) Implement compress method, compress the file by encoding
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

    public static String minify(ArrayList<String> lines){
		String str = "",endString = "";
		boolean prevChar,nextChar;
		int length,start,end;
		for(String line : lines)
		{
			length = line.length();    
        		start = 0;
			end = 0;    
        		char val[] = line.toCharArray();

			while( end < length )
			{
				start = end;
				while( (start < length) && (val[start] <= ' ') )
				{
					start++;
				}

				end = start;

				while( (end < length) &&  (val[end] != ' ') )
				{
					end++;
				}
				
				str = str + line.substring(start, end) + " ";
				
				end++;
			}
		}
		start = 0;
		length = str.length();
		end = str.indexOf(" ",start);
		while( end != -1 )
		{
			prevChar = false;
			nextChar = false;
			if( (end-1 >= 0) && (end+1 < length) )
			{
				prevChar = Character.isLetterOrDigit(str.charAt(end-1));
				nextChar = Character.isLetterOrDigit(str.charAt(end+1));
				if( (!prevChar) || (!nextChar) )
				{
					endString = endString + str.substring(start, end);
				}
				else
				{
					endString = endString + str.substring(start, end+1);
				}
			}
			else
			{
				endString = endString + str.substring(start, end+1);
			}

			start = end+1;
			if(start >= length)
			{
				break;
			}
			end = str.indexOf(" ",start);
		}
		return endString;
	}

    
    public void compressXML(){
        //TODO
        
    }
}
