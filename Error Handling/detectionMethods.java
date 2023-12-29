package com.example.demo.ErrorHandling;
import java.util.*;
import java.io.* ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
////////////////////////////////////////////////////////////////
/*
 * @authour: mohamed mostafa
 * detection and correction part in part1  :)
 * */

public class detectionMethods {
    public static void main(String[] args) throws Exception{
        String path = "C:\\Users\\user\\OneDrive\\Desktop\\138-KB-XML-File.xml"; // put your own path :)


    }



    ////////////////////////////////////////////////////
// first step before making fully implemented detect function
    public static ArrayList<Integer> detectionLastStep(String path) throws IOException {
        ArrayList<Integer> finalArr = new ArrayList<>();
        ArrayList<Integer> arr1 = detectMissingTag(path);
        ArrayList<String> data = getTagsNames(path);
        ArrayList<String> data2 = removeSymbols(data);
        ArrayList<Integer> arr2 = findErrorsInSameLineDueToMismatchTags(data2);
        ArrayList<Integer> arr3 = detectMissingTag(path);
        ArrayList<Integer> arr4 = identifyErrorLines(path);
        finalArr.addAll(arr1);
        finalArr.addAll(arr2);
        finalArr.addAll(arr3);
        finalArr.addAll(arr4);
        for (int i = 0; i < finalArr.size(); i++) {
            for (int j = i + 1; j < finalArr.size(); j++) {
                if (finalArr.get(i).equals(finalArr.get(j))) {
                    finalArr.remove(j);
                    j--;
                }
            }
        }
        return finalArr;
    }



    ////////////////////////////////////////////////////////////////////////////////////////
    //detect and correct the error due to missing closing tag in another line
    public static ArrayList<Integer> checkRootsPlace(ArrayList<String> wholeData) {
        Stack<String> stack = new Stack<>();
        ArrayList<Integer> requiredLines = new ArrayList<>();
        int lineNumber = 1;

        for (String line : wholeData) {
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '<' && line.charAt(i + 1) != '/') {
                    String openingTag = line.substring(i + 1, line.indexOf(">"));
                    stack.push(openingTag);
                }
                if (line.charAt(i) == '<' && line.charAt(i + 1) == '/') {
                    String closingTag = line.substring(i + 2, line.indexOf(">", i + 2));
                    if (!stack.isEmpty() && closingTag.equals(stack.peek())) {
                        stack.pop();
                    }
                }

            }
            if (line.trim().isEmpty() && !stack.empty()) {
                line = "</" + stack.pop() + ">";
                requiredLines.add(lineNumber);
            }
//            System.out.println(line);
//            System.out.println(lineNumber);
            lineNumber++;
        }

        return requiredLines;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////
    /*this part detect and correct eg.<id>1 to <id>1<id> */
    public static String extractTagContent(String input) {

        String line = input.replace("<", "").replace(">", " ");
        return line;
    }

    public static ArrayList<String> correctMissingTag(String filePath, ArrayList<Integer> lineError) throws IOException {
        ArrayList<String> part1 = getSpecificLines(filePath, lineError);
        int i = 0;
        ArrayList<String> result = new ArrayList<>();
        for (String line : part1) {
            String x[] = extractTagContent(line).split("\\s+");
            line = line + "</" + x[0] + ">";
            result.add(line);
        }

        return result;
    }

    public static ArrayList<Integer> detectMissingTag(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> lineError = new ArrayList<>();
        int requiredLength;
        int lineNumber = 1;
        String line = "";
        while (scanner.hasNextLine()) {
            line = scanner.nextLine().trim();
            requiredLength = 0;
            if (!line.contains("</") && line.startsWith("<") && !line.endsWith(">")) {
                requiredLength = line.indexOf(">");
                if (requiredLength < line.length()) lineError.add(lineNumber);
            }
            lineNumber++;
        }

        return lineError;
    }
    //////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////////
    /*helper may be used */
    public static ArrayList<String> processFirstAndLastElements(ArrayList<String> lines) {
        String data = "";
        ArrayList<String> finalData = new ArrayList<>();
        for (String line : lines) {
            data = "";
            String trimmedLine = line.trim(); // Remove leading and trailing whitespace
            String[] elements = trimmedLine.split("\\s+"); // Split the trimmed line by whitespace
            if (elements.length == 1) {
                data = elements[0];
            } else if (elements.length > 1) {
                data = elements[0]; // Get the first element
                data = data + " " + elements[elements.length - 1]; // Get the last element
            }
            finalData.add(data);
        }
        return finalData;
    }


    public static ArrayList<String> removeSymbols(ArrayList<String> lines) {
        ArrayList<String> data = new ArrayList<>();
        for (String line : lines) {
            line = line.replaceAll("<", " ").replaceAll(">", " ").replaceAll("/", " ");
            data.add(line);
        }
        return data;
    }

    public static void removeEmptySpacesAndWriteToFile(String filePath) {
        try {
            File inputFile = new File(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String lineWithoutSpaces = line.replaceAll("\\s+", "");
                lines.add(lineWithoutSpaces);
            }
            reader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> getSpecificLines(String filePath, ArrayList<Integer> lineNumbers) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 0;

            while ((line = reader.readLine()) != null) {
                currentLine++;

                if (lineNumbers.contains(currentLine)) {
                    lines.add(line);
                }
            }
        }

        return lines;
    }


/////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////
    // detect errors in the same line
    public static ArrayList<Integer> findErrorsInSameLineDueToMismatchTags(ArrayList<String> lines) {
        ArrayList<Integer> errorLines = new ArrayList<>();


        for (int i = 0; i < lines.size(); i++) {
            String currentLine = lines.get(i);
            String[] words = currentLine.trim().split("\\s+");
            if (words.length > 1) {
                String firstWord = words[0];
                String lastWord = words[words.length - 1];
                if (!firstWord.equals(lastWord)) {
                    errorLines.add(i + 1); // Add line number to errorLines (line numbers are 1-based)
                }
            }
        }

        return errorLines;
    }


    /////////////////////////////////////////////////////////////////////
    public static ArrayList<String> getTagsNames(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        ArrayList<String> tags = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) {
                tags.add(" ");
            } else {
                Pattern pattern = Pattern.compile("<(\\w+)[^>]*>.*?</\\1>|<\\w+[^>]*>|</\\w+>");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()) {
                    tags.add(matcher.group());
                }
            }
        }
        return tags;
    }
    /////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////
    /*doneeeeee :)  detect and correct error due to error in symbols like < > or </> or </> --> e.g <email> </email --> <email></email>*/
    public static List<String> extractRepeatedSubstrings(String input) {
        List<String> repeatedSubstrings = new ArrayList<>();
        int n = input.length();
        boolean flag = false;
        for (int len = 1; len <= n / 2; len++) {
            for (int i = 0; i <= n - 2 * len; i++) {
                String substring = input.substring(i, i + len);
                if (input.substring(i + len).contains(substring)) {
                    repeatedSubstrings.add(substring);
                    flag = true;
                }
            }
        }
        if (!flag) {
            repeatedSubstrings.add(input); // Return the input data if no repeated substrings are found
        }
        return repeatedSubstrings;
    }

    public static ArrayList<String> correctErrorInSymbols(ArrayList<Integer> linesError, String path) throws IOException {
        ArrayList<String> dataContainErrors = getSpecificLines(path, linesError);
        Stack<Character> symbols = new Stack<>();
        ArrayList<String> returnedData = new ArrayList<>();
        List<String> repreatedList;
        String requiredData;
        for (String line : dataContainErrors) {

            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '<' && line.charAt(i + 1) == '/') {
                    symbols.push(line.charAt(i + 1));
                    symbols.push(line.charAt(i));
                    line = line.replaceFirst(line.charAt(i) + "", "");
                    line = line.replaceFirst(line.charAt(i + 1) + "", "");
                } else if (line.charAt(i) == '<' || line.charAt(i) == '>') {
                    symbols.push(line.charAt(i));
                    line = line.replaceFirst(line.charAt(i) + "", "");
                }

                line = line.replace("/", "");
            }

            repreatedList = extractRepeatedSubstrings(line);
            int lastIndex = repreatedList.size() - 1;
            requiredData = repreatedList.get(lastIndex);

            line = line.replaceAll(requiredData, "");
            System.out.println(line);
            System.out.println(requiredData);
            if (symbols.size() > 2) line = "<" + requiredData + ">" + line + "</" + requiredData + ">";
//        else if (symbols.size()==2)
            if (symbols.size() == 1) line = "<" + requiredData + ">";
            if (symbols.size() == 2) line = "</" + requiredData + ">";
            System.out.println(line);
            returnedData.add(line);
            line = "";
        }

        return returnedData;
    }

    public static ArrayList<Integer> identifyErrorLines(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        ArrayList<Integer> errorLines = new ArrayList<>();
        Stack<String> tagStack = new Stack<>();

        int lineNumber = 1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            boolean lineHasError = false;
            if (!line.contains(">") || !line.contains("</") || !line.contains("<")) {
                lineNumber++;
                continue;
            }

            int openingTagCount = 0;
            int closingTagCount = 0;
            boolean flag = false;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '<' && !flag) {
                    openingTagCount++;
                    flag = true;
                } else if (line.charAt(i) == '>') {
                    closingTagCount++;
                } else if (line.charAt(i) == '<' && i + 1 < line.length() && line.charAt(i + 1) == '/') {
                    openingTagCount++;
                } else if (line.charAt(i) == '>') {
                    closingTagCount++;
                }
            }

            if (openingTagCount != closingTagCount) {
                errorLines.add(lineNumber);
            }

            lineNumber++;
        }
        return errorLines;
    }
    ////////////////////////////////////////////////////////////////////
}
