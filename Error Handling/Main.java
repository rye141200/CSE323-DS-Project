import java.util.* ;
import java.io.* ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args)throws IOException {

        String path = "C:\\Users\\mohmo\\Desktop\\sample1.xml"; // put your xml file location
        ArrayList<String> parsedTags = parseTagsFromFile(path);
        ArrayList<String> data = detect(parsedTags) ;
        ArrayList<Integer>x= detectErrors(data)  ;
//        System.out.println(startsWithOpeningTagAndText("<price>123"));
        System.out.println(x);
    }

    // function return arraylist of every line in xml file
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
        Pattern pattern = Pattern.compile("^<([^/][^\\s>]*)>$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    public static boolean isClosingTagOnly(String line) {
        Pattern pattern = Pattern.compile("^</([^\\s>]*)>$");
        Matcher matcher = pattern.matcher(line);
        return matcher.matches();
    }
    public static ArrayList<String> detect(ArrayList<String> lines ){
        String message = " ";
        int lineNumber=1;
        int sizeOfLines = lines.size();
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
               message="MISSING OPENING TAG FOR CLOSING TAG "+closingTag +"IN LINE NUMBER "+lineNumber;
               errors.add(message);

           }

           lineNumber++;
           }
       return errors;
    }

    public static ArrayList<Integer> detectErrors (ArrayList<String> messages ){
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
}