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

    // Use the following line of code to get parsed file:
    // FileSampleEnhanced.readFileParsed(); -> Returns ArrayList<String> of parsed lines of file
    /********************EXAMPLE**************************************************/
    /*  
        public static void main(String [] args) throws Exception{
            FileSampleEnhanced.readFileParsed();
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
			endString = endString.substring(0, endString.length()-1);
			end = str.indexOf(" ",start);
		}
		return endString;
	}

    // Use the following line of code to get parsed file:
    // FileSampleEnhanced.readFileParsed(); -> Returns ArrayList<String> of parsed lines of file
    /********************EXAMPLE**************************************************/
    /*  
        public static void compressXML() throws Exception{
            FileSampleEnhanced.readFileParsed();
        }
    */
    public static void compressXML() throws Exception{
        //TODO
        
    }
}
