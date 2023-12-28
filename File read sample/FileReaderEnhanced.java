package com.example.demo.FileReading;
import java.util.ArrayList;
import java.util.Arrays;

public class FileReaderEnhanced {
    private static boolean isOpenTagWithAttributes(String element,ArrayList<String> tags){
        for(String tag:tags)
            if(element.contains(tag))
                return true;
        return false;
    }

    public static ArrayList<String> readFileParsed(ArrayList<String> linesList) throws Exception{ //O(n*m*k)
        ArrayList<String> linesListParsed = new ArrayList<>();
        ArrayList<String> tags = new ArrayList<>();

        //1) Get the tags
        for(String line:linesList)
            if(line.contains("/"))
                tags.add(line.substring(2,line.length()-1));
        
        //2) Looping to add all open tags
        int index =0;
        for(String element:linesList){
            if(tags.contains(element) || isOpenTagWithAttributes(element, tags)) 
                if(element.contains("=") && element.charAt(element.length()-2) !='/')
                    linesList.set(index,"<"+element+">");
        
            index++;
        }
        //3) Removing attributes and replacing them with opening tags
        for(int i =0;i<linesList.size();i++){
            String element = linesList.get(i);
            if(isOpenTagWithAttributes(element, tags) && !element.contains("/")&& element.contains("=")){
                //Inside the tag with attribute
                ArrayList<String> openingTagAttributes = new ArrayList<>(Arrays.asList(element.split(" ")));
                openingTagAttributes.removeAll(Arrays.asList("",null));

                for(int j=0;j<openingTagAttributes.size();j++){
                    ArrayList<String> tagValue = new ArrayList<>(Arrays.asList(openingTagAttributes.get(j).split("=")));
                    if(j==0)
                        linesListParsed.add(openingTagAttributes.get(0)+">");
                    else if(j == openingTagAttributes.size()-1)
                        AttributePrintHandler(linesListParsed, tagValue, 2);
                    else
                        AttributePrintHandler(linesListParsed, tagValue, 1);
                }
                //Object with value
                if(!linesList.get(i+1).contains("/") && !linesList.get(i+1).contains("<") && !linesList.get(i+1).contains(">")){
                    linesListParsed.add("<#text>");
                    linesListParsed.add(linesList.get(i+1));
                    linesListParsed.add("</#text>");
                }
            }
            else if(!linesList.get(i).contains("/") && !linesList.get(i).contains("<") && !linesList.get(i).contains(">") && isOpenTagWithAttributes(linesList.get(i-1), tags) && linesList.get(i-1).contains("=")){
                continue;
            }
            else if(isClosedTagWithAttributes(element)){
                ArrayList<String> closedTagWithAttributes = new ArrayList<>(Arrays.asList(element.split(" ")));
                closedTagWithAttributes.removeAll(Arrays.asList("",null)); //safety condition
                linesListParsed.add(closedTagWithAttributes.get(0) +">");
                for(int j =1;j<closedTagWithAttributes.size();j++){
                    ArrayList<String> tagValue = new ArrayList<>(Arrays.asList(closedTagWithAttributes.get(j).split("=")));
                    if(j == closedTagWithAttributes.size()-1)
                       AttributePrintHandler(linesListParsed, tagValue, 3);
                    else
                       AttributePrintHandler(linesListParsed, tagValue, 1);
                    
                }
                linesListParsed.add("</"+closedTagWithAttributes.get(0).substring(1)+">");
            }
            else{
                linesListParsed.add(element);
            }
        }
        return linesListParsed;
    }
    private static boolean isClosedTagWithAttributes(String element){
        return element.contains("/") && element.contains("<") && element.contains(">") && element.contains("=");
    }
    private static void AttributePrintHandler(ArrayList<String> linesListParsed,ArrayList<String> tagValue,int n){
        linesListParsed.add("<" +tagValue.get(0).trim()+">");
        linesListParsed.add(tagValue.get(1).trim().substring(1,tagValue.get(1).length()-n));
        linesListParsed.add("</"+tagValue.get(0) +">");
    }
    public static void main(String[] args) throws Exception {
    ArrayList<String> lines = FileReaderEnhanced.readFileParsed(FileSampleEnhanced.readFileParsed("C:\\Users\\omar\\demo\\sample.xml"));
        System.out.println(lines);
    }
}
