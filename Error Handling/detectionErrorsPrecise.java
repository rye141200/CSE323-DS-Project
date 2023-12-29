package com.example.demo.ErrorHandling;
import java.util.*;
public class detectErrorsPrecise {
    public static boolean checkConsistency(ArrayList<String> lines) throws Exception{
        //1)Initializations
        Stack<String> scopeStack = new Stack<>();
        //3) Scope errors
        for(String line:lines){
            if(isOpenTag(line))
                scopeStack.push("</"+line.substring(1));
            else if(isClosedTag(line) && line.equals(scopeStack.peek()))
                scopeStack.pop();
        }
        return !scopeStack.empty();
    }
    public static boolean containsError(ArrayList<String> lines) throws Exception{
        /*****************************************************************/
        /* Detects a single error in an XML file, once found return true */
        /*****************************************************************/
        int currentLine = -1;
        //2) Any single valued error
        for(String line:lines){
            currentLine++;
            if(!line.contains("<") && !line.contains(">") && !line.contains("/"))
                continue;
            if(congestionWithinAngleBrackets(line))
                return true;
            else if(line.contains("<") && line.contains(">") && !line.contains("/")){
                if(isValue(lines.get(currentLine+1)) && isClosedTag(lines.get(currentLine+2))){
                    if(!isEqualOpenAndCloseTag(line, lines.get(currentLine+2)))    
                        return true;
                }
                else if(isOpenTag(lines.get(currentLine+2)) && isValue(lines.get(currentLine+1)))
                    return true;
            }
        }
        //3) Scope errors
        return checkConsistency(lines);
    }
    //Don't look :D
    private static boolean isOpenTag(String line){
        return line.contains("<") &&line.contains(">") && !line.contains("/");
    }
    private static boolean isEqualOpenAndCloseTag(String openTag,String closedTag){
        return openTag.substring(1,openTag.length()-1).equals(closedTag.substring(2,closedTag.length()-1));
    }
    private static boolean congestionWithinAngleBrackets(String line){
        return line.charAt(0) !='<' || line.charAt(line.length()-1) !='>' || (line.contains("/") && line.charAt(1) != '/') ;
    }
    private static boolean isClosedTag(String line){
        return line.contains("/") && line.contains("<") && line.contains(">");
    }
    private static boolean isValue(String line){
        return !line.contains("<") && !line.contains(">") && !line.contains("/");
    }
}
