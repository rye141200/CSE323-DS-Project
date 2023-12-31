package com.example.demo.Compression;
import com.example.demo.FileReading.FileReaderEnhanced;
import com.example.demo.FileReading.FileSampleEnhanced;

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
	// Functions: compressFile(String fileType, ArrayList<String> linesParsed):String, decompressFile(ArrayList<String> linesParsed):String
	// to compress and decompress XML or JSON file


	// Data Fields
	static ArrayList<String> xmlDecoddedTags;
	static ArrayList<String> xmlEncoddedTags;
	static ArrayList<String> jsonDecoddedTags;
	static ArrayList<String> jsonEncoddedTags;


	// Compresses XML or JSON file, returns the compressed content of the file
	// it compresses the lines in the linesParsed ArrayList
	public static String compressFile(String fileType, ArrayList<String> textLinesParsed){

		ArrayList<String> decoddedTags = new ArrayList<>();
		ArrayList<String> encoddedTags = new ArrayList<>();
		ArrayList<String> linesParsed = new ArrayList<>(textLinesParsed);
		String compressedFileContent = "";
		String start;
		String end;


		// using unicode characters to encode
		// note that we can change it if we need more characters to encode with
		int code = 200;

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

		// going through the file lines (linesParsed) line by line
		for (int i = 0; i < linesParsed.size(); i++){

			String line = linesParsed.get(i);
			int numberOfDecoddedTags = ("begin"+line+"end").split(end).length - 1;

			// going through each tag in the line
			while (numberOfDecoddedTags != 0){

				if (linesParsed.get(i).contains(start) && linesParsed.get(i).contains(end)){
					int startingIndex = line.indexOf(start);
					int endingIndex = line.indexOf(end, startingIndex);
					String tag = line.substring(startingIndex, endingIndex + 1);

					// if it wasn't encodded before, then we will get a unique value for it and store it
					// and we will encode it using that unique value
					if (!decoddedTags.contains(tag)){
						decoddedTags.add(tag);
						encoddedTags.add(String.valueOf((char)code));
						linesParsed.set(i, line.replace(tag, String.valueOf((char)code)));
						code++;
					}
					// but if it was encodded before then it must be in the decoddedTags
					// so, we will use the same unique value (in the encoddedTags) we used before to encode it
					else{
						linesParsed.set(i, line.replace(tag, encoddedTags.get(decoddedTags.indexOf(tag))));
					}

				}

				line = linesParsed.get(i);
				numberOfDecoddedTags--;
			}

			compressedFileContent += (line + "\n");
		}

		if (fileType.toLowerCase().contains("json")){
			jsonDecoddedTags = decoddedTags;
			jsonEncoddedTags = encoddedTags;

		}
		else {
			xmlDecoddedTags = decoddedTags;
			xmlEncoddedTags = encoddedTags;
		}

		return compressedFileContent;
	}


	// Decompresses a XML or JSON that was encodded with the compressFile function
	// it decompresses the lines in the linesParsed ArrayList that was already compressed
	public static String decompressFile(String fileType, ArrayList<String> textLinesParsed){

		ArrayList<String> linesParsed = new ArrayList<>(textLinesParsed);
		ArrayList<String> decoddedTags;
		ArrayList<String> encoddedTags;
		String decompressedFileContent = "";

		if (fileType.toLowerCase().contains("json")){
			decoddedTags = jsonDecoddedTags;
			encoddedTags = jsonEncoddedTags;

		}
		else {
			decoddedTags = xmlDecoddedTags;
			encoddedTags = xmlEncoddedTags;
		}

		for (int i = 0; i < linesParsed.size(); i++){

			String line = linesParsed.get(i);

			for (String encoddedTag : encoddedTags){

				while (line.contains(encoddedTag)){
					linesParsed.set(i, line.replace(encoddedTag, decoddedTags.get(encoddedTags.indexOf(encoddedTag))));
					line = linesParsed.get(i);
				}

			}

			decompressedFileContent += (line + "\n");
		}

		return decompressedFileContent;
	}

///////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////

	public static void main(String[] args) throws Exception{
		System.out.println(FileCompression.compressFile("XML", FileReaderEnhanced.readFileParsed(FileSampleEnhanced.readFileParsed("C:\\Users\\omar\\demo\\sample.xml"))));
	}
}
