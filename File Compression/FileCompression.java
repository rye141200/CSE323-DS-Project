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

	public static String minify(ArrayList<String> lines)
	{
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
		endString = endString.substring(0, endString.length()-1);

		return endString;
	}

	

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
	
    // Author: Abdallah Mohamed (@AntiHexCode)
    // Functions: compressFile(String fileType):void, decompressFile():void
    // to compress and decompress XML or JSON file

	
    // Data Fields
    static ArrayList<String> linesParsed = FileSampleEnhanced.readFileParsed();
    static HashMap <String, String> encodingMap = new HashMap <String, String>();
    static HashMap <String, String> decodingMap = new HashMap <String, String>();
	
    
    // Compresses XML or JSON file, doesn't return anything
    // it compresses the lines in the linesParsed ArrayList
    public static void compressFile(String fileType){

        String start;
        String end;
        
        // using unicode characters to encode
	// note that we can change it if we need more characters to encode with
        int code = 65;
        
        // determining the start and the end of the encodded part of the line
        // depends on the file format (XML or JSON)
        if (fileType.toLowerCase().contains("json")){
            start = "\"";
            end = ":";
        }
        else {
            start = "<";
            end = ">";
        }
        
        for (int i = 0; i < linesParsed.size(); i++){
            
            String line = linesParsed.get(i);
            int numberOfTags = ("begin"+line+"end").split(end).length - 1;
            
            // extracting the part we want to encode
            while (numberOfTags != 0){
                
                if (linesParsed.get(i).contains(start) && linesParsed.get(i).contains(end)){
                    int startingIndex = line.indexOf(start);
                    int endingIndex = line.indexOf(end, startingIndex);
                    String tag = line.substring(startingIndex, endingIndex + 1);

                    // if it wasn't encodded before, then we will get a unique value for it and store it
                    // and we will encode it using that unique value
                    if (!encodingMap.containsKey(tag)){
                        encodingMap.put(tag, String.valueOf((char)code));
                        decodingMap.put(String.valueOf((char)code), tag);
                        linesParsed.set(i, line.replace(tag, String.valueOf((char)code)));
                        code++;
                    }
                    // but if it was encodded before then it must be in the encodingMap
                    // so, we will use the same unique value we used before to encode it
                    else{
                        linesParsed.set(i, line.replace(tag, encodingMap.get(tag)));
                    }
                } 
                line = linesParsed.get(i);
                numberOfTags--; 
	    }  
        } 
	    
    }

    
    // Decompresses a XML or JSON that was encodded with the compressFile function
    // it decompresses the lines in the linesParsed ArrayList that was already compressed
    public static void decompressFile(){
        
        for (int i = 0; i < linesParsed.size(); i++){
            
            String line = linesParsed.get(i);
            
            for (String key : decodingMap.keySet()){
		    
            	// for every key you find, replace it with the right tag
                while (line.contains(key)){
                    linesParsed.set(i, line.replace(key, decodingMap.get(key)));
                    line = linesParsed.get(i);
                }
		    
            }
        }
	    
    }

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////
	
}
