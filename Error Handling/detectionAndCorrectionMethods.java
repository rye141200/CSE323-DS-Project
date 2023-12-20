import java.sql.SQLOutput;
import java.util.* ;
import java.io.* ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
        /*----------------Test-------------------------*/
    public static void main(String[] args)throws IOException {

        String path = "C:\\Users\\mohmo\\Desktop\\sample1.xml"; // put your xml file location
        System.out.println(detectError(path));
        correctError(path);
    }
/////////////////////////////////////////////////////////////////////////////////////////////
   /*------------------------ Abstract Methods------------------------- */

        public static void correctError(String path) throws IOException
        {
            ////////////////////////////////////////////
            ArrayList<String> parsedTags = parseTagsFromFile(path);
            //////////////////////////////////////////////

            /////////////////////////////////////////////
            ArrayList<String> data1 = detectTagsErrors(parsedTags) ;
            correctTagsErrors(data1,path);
            ///////////////////////////////////////

            ///////////////////////////////////////////
            ArrayList<String>data2=detectMissingClosingTag(parsedTags);
            correctMissingClosingTag(path,data2);
            /////////////////////////////////////////
        }

        public static ArrayList<Integer>detectError(String path)throws IOException{
            ArrayList<Integer> detectionAll = new ArrayList<>();
            ////////////////////////////////////////////
            ArrayList<String> parsedTags = parseTagsFromFile(path);
            //////////////////////////////////////////////

            ////////////////////////////////////////////
            ArrayList<String>messages1=detectTagsErrors(parsedTags);
            ArrayList<Integer>detect1 = pickLastNumbers(messages1);
            ///////////////////////////////////////////

            /////////////////////////////////////////
            ArrayList<String>messages2=detectMissingClosingTag(parsedTags);
            ArrayList<Integer>detect2 = pickLastNumbers(messages2);
            //////////////////////////////////////////////
            detectionAll.addAll(detect1);
            detectionAll.addAll(detect2);
            return detectionAll;
        }


/////////////////////////////////////////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
    public static ArrayList<String>detectMissingClosingTag (ArrayList<String>parsedLines){
        String message ;
        ArrayList<String> x = new ArrayList<>() ;
        Stack<String> stack = new Stack<>() ;
        String openingTag ;
        String closingTag ;
        int lineNumber=1  ;
        int spareNumber ;
        for (String line  : parsedLines) {
            if (isOpeningTagOnly(line)) {
                openingTag = removeAttributesFromOpeningTag(line);
                if(!stack.empty())
                {
                    if (! stack.peek() .equals( openingTag )) {
                        stack.push(openingTag);
                    } else {
                        spareNumber=lineNumber-1;
                        message = "MISSED CLOSING TAG FOR "+stack.peek()+"IN LINENUMBER " +spareNumber;
                        x.add(message);
                        stack.pop();
                        stack.push(openingTag);
                    }
                }
                else {
                    stack.push(openingTag);
                }
            }
            if (isClosingTagOnly(line)){
                closingTag=getOpeningAndClosingTags(line).getFirst().replace("/","");
                closingTag="<"+closingTag+">";
                if (!stack.peek().equals(closingTag)){
                    spareNumber=lineNumber-1 ;
                    message = "MISSED CLOSING TAG FOR "+stack.peek()+"IN LINENUMBER " +spareNumber;
                    x.add(message);
                    stack.pop();
                }
                else {
                    stack.pop();
                }
            }

            lineNumber++ ;

        }
        while (!stack.isEmpty()) {
            spareNumber = lineNumber - 2;
            message = "MISSED CLOSING TAG FOR " + stack.peek() + " IN LINENUMBER " + spareNumber;
            x.add(message);
            stack.pop();
            lineNumber++;
        }
        return x ;
    }

public static void correctMissingClosingTag (String path,ArrayList<String> messages)
{
    for (String message : messages){
        String closingTag= "</"+extractLowerCaseWords(message).getFirst()+">";
        writeToLine(path,extractLastNumber(message),closingTag);
        closingTag="";
    }
}
/////////////////////////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////////////////////
public static ArrayList<String> detectTagsErrors(ArrayList<String> lines ){
    String message = " ";
    int lineNumber=1;
    String openingTag , closingTag ;
    ArrayList<String> tags =new ArrayList<>() ;
    ArrayList<String>errors=new ArrayList<>() ;
    Stack<String>stack =new Stack<>();
    for (String line : lines){
        line=line.trim();
        tags=getOpeningAndClosingTags(line);
        if (tags.size()>1) {
            openingTag=tags.get(0);
            closingTag=tags.get(1).replace("/","");
            stack.push(openingTag);
            if (stack.peek().equals(closingTag)){
                stack.pop();

            }
            else {
                message="MISMATCH BETWEEN OPENING TAG "+openingTag+" AND CLOSING TAG "+closingTag+" IN LINE NUMBER "+lineNumber ;
                errors.add(message);
                stack.pop();

            }
        }
        else if (startsWithOpeningTagAndText(line)){
            openingTag=tags.get(0);
            message="MISSING CLOSING TAG FOR OPENING TAG "+openingTag +" IN LINE NUMBER "+lineNumber;
            errors.add(message);

        }
        else if(startsWithTextAndHasClosingTags(line)){
            closingTag=tags.get(0).replace("/","");
            message="MISSING OPENING TAG FOR CLOSING TAG "+closingTag +" IN LINE NUMBER "+lineNumber;
            errors.add(message);

        }

        lineNumber++;
    }
    return errors;
}

    public static void correctTagsErrors (ArrayList<String> errorMessages  , String path){

        for (String message : errorMessages){
            String closingTag="" ;
            String openingTag="" ;
            String lineFromFile="" ;
            String longerTag="" ;
            if (message.contains("MISSING CLOSING TAG FOR OPENING TAG")){
                closingTag = extractLowerCaseWords(message).getFirst();
                closingTag ="</"+closingTag+">" ;
                lineFromFile = getSpecificLine(path , extractLastNumber(message));
                lineFromFile+=closingTag;
                writeToLine(path,extractLastNumber(message),lineFromFile);
                lineFromFile="";
            }
            if (message.contains("MISSING OPENING TAG FOR CLOSING TAG")){
                openingTag=extractLowerCaseWords(message).getFirst();
                openingTag="<"+openingTag+">" ;
                lineFromFile = getSpecificLine(path,extractLastNumber(message));
                lineFromFile = openingTag+lineFromFile ;
                lineFromFile=removeSpaces(lineFromFile);
                writeToLine(path,extractLastNumber(message),lineFromFile);
                lineFromFile = "" ;
            }
            if(message.contains("MISMATCH BETWEEN OPENING TAG")){
                openingTag=extractLowerCaseWords(message).getFirst();
                closingTag=extractLowerCaseWords(message).getLast();
                longerTag = findLongerString(openingTag,closingTag) ;
                lineFromFile=getSpecificLine(path,extractLastNumber(message));
                lineFromFile=removeTags(lineFromFile);
                lineFromFile ="<"+longerTag+">"+lineFromFile+"</"+longerTag+">";
                lineFromFile=removeSpaces(lineFromFile);
                writeToLine(path,extractLastNumber(message),lineFromFile);
                lineFromFile = "" ;
            }
        }
    }

///////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////
            /*-----------Helper Methods ----------------*/
    public static String removeTags(String line) {
        return line.replaceAll("<[^>]*>", "");
    }
    public static String findLongerString(String str1, String str2) {
        if (str1.length() > str2.length()) {
            return str1;
        } else if (str2.length() > str1.length()) {
            return str2;
        } else {
            return "Both strings are of equal length";
        }
    }
    public static String getSpecificLine(String filePath, int lineNumber) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    return line;
                }
                currentLine++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if the line number is out of range or an error occurs
    }
    public static ArrayList<String> extractLowerCaseWords(String input) {
        ArrayList<String> lowerCaseWords = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\b[a-z]+\\b");
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {
            lowerCaseWords.add(matcher.group());
        }

        return lowerCaseWords;
    }
    public static int extractLastNumber(String input) {
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(input);
        int lastNumber = 0;

        while (matcher.find()) {
            lastNumber = Integer.parseInt(matcher.group());
        }

        return lastNumber;
    }
    public static String removeSpaces(String line) {
        return line.replaceAll("\\s", "");
    }

    public static ArrayList<String> parseTagsFromFile(String filePath) throws IOException {
     File file = new File(filePath);
     Scanner scan = new Scanner(file) ;
     ArrayList<String> data = new ArrayList<>();
     String line ;
     while (scan.hasNextLine()){
         line = scan.nextLine().trim();
         data.add(line);
     }
     return data ;
    }
    public static ArrayList<String> getOpeningAndClosingTags(String line) {
        ArrayList<String> tags = new ArrayList<>();
        Pattern pattern = Pattern.compile("<(.*?)>");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            tags.add(matcher.group(1));
        }

        return tags;
    }
    public static boolean startsWithOpeningTagAndText(String line) {
        Pattern pattern = Pattern.compile("^<([^/][^\\s>]*)>([^<]+)<\\/\\1>$|^<([^/][^\\s>]*)>([^<]+)$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    public static boolean startsWithTextAndHasClosingTags(String line) {
        Pattern pattern = Pattern.compile("^[^<]+</[^>]+>$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

public static boolean isOpeningTagOnly(String line) {
    Pattern pattern = Pattern.compile("^<([^\\s>/]+)(\\s+[^>]+)?>$");
    Matcher matcher = pattern.matcher(line);
    return matcher.matches();
}
    public static boolean isClosingTagOnly(String line) {
        Pattern pattern = Pattern.compile("^</([^\\s>]*)>$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }

    public static ArrayList<Integer> pickLastNumbers(ArrayList<String> messages ){
        ArrayList<Integer> errorLine = new ArrayList<>();
        for (String message : messages) {
            // Extract the line number from the message
            String[] parts = message.split("\\s+");
            for (String part : parts) {
                try {
                    int lineNumber = Integer.parseInt(part);
                    errorLine.add(lineNumber);
                    break; // Break once a valid number is found
                } catch (NumberFormatException e) {
                    // Ignore non-numeric parts
                }
            }
        }
        return errorLine;
    }

    public static String removeAttributesFromOpeningTag(String openingTag) {
        Pattern pattern = Pattern.compile("<([^\\s>]+)(\\s+[^>]+)?>");
        Matcher matcher = pattern.matcher(openingTag);

        if (matcher.find()) {
            String tagName = matcher.group(1);
            return "<" + tagName + ">";
        }

        return openingTag; // Return unchanged if no match is found
    }
    public static void writeToLine(String filePath, int lineNumber, String content) {
        try {
            File file = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder inputBuffer = new StringBuilder();
            String line;
            int currentLine = 1;

            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    inputBuffer.append(content).append("\n");
                } else {
                    inputBuffer.append(line).append("\n");
                }
                currentLine++;
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(inputBuffer.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}