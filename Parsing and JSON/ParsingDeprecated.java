import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
/*****************************/
/* 
    Authors: -Ahmad Mahfouz
             -Yousef Shawky
    Description: - Implementation of Parsing Logic
    
 */
/*****************************/

public class ParsingDeprecated {
    public static  void parseXML() throws Exception{
        //INITIALIZATIONS
        ArrayList<String> lines = FileSample.fileSampleSelector();
        int currentLineCounter = 0;
        Tree XMLTree = new Tree(null);
        Stack<TreeNode> scopeStack = new Stack<>();        

        for(String line: lines){
            //1) CASES ENCODING
            boolean lineWithDelimiter = line.matches(".*<.*") && line.matches(".*/.*") && line.charAt(1) !='/';
            boolean lineWithoutDelimiter = line.charAt(1) !='/' && line.charAt(0) == '<';
            
            //2) CREATE ROOT
            if(currentLineCounter == 0){
                if(lineWithoutDelimiter){
                    ArrayList<String> tagNameValue = parseLineNoDelimiter(line,lines,currentLineCounter);
                    TreeNode rootNode = new TreeNode(tagNameValue.get(0), tagNameValue.get(1)); 
                    XMLTree.setRoot(rootNode);
                    scopeStack.push(rootNode);
                }
                else{
                    ArrayList<String> tagNameValue = parseLineWithDelimiter(line);
                    TreeNode rootNode = new TreeNode(tagNameValue.get(0), tagNameValue.get(1));
                    XMLTree.setRoot(rootNode);
                    scopeStack.push(rootNode);
                }
                currentLineCounter++;
                continue;
            }
            
            
            //3) CREATE NODES
            if(lineWithDelimiter){ //Case (1): <user> 5ra </user>
                //CALL parseLineWithDelimiter
                ArrayList<String> tagNameValue = parseLineWithDelimiter(line);
                
                //CREATE NODES
                TreeNode node = new TreeNode(tagNameValue.get(0), tagNameValue.get(1));
                XMLTree.addNode(node, scopeStack.peek());
            }
            else if(lineWithoutDelimiter){ //Case(2): only tagName <user>
                ArrayList<String> tagNameValue = parseLineNoDelimiter(line, lines, currentLineCounter);
                //CREATE NODES
                TreeNode node = new TreeNode(tagNameValue.get(0), tagNameValue.get(1));
                XMLTree.addNode(node, scopeStack.peek());
                scopeStack.push(node);
            }
            else if(line.charAt(1) == '/')
                scopeStack.pop();

            currentLineCounter++;
        } 
        XMLTree.printTree();
    }
    
    public static void main(String [] args) throws Exception{
        parseXML();
    }

    // 1) <user>
    // 2) <user> name </user>
    // 3) tagValue
    
    //Helper methods

    //WORKS 100% InShaa' Allah 🤲 
    private static ArrayList<String> parseLineWithDelimiter(String line){
        String tagName = line.substring(line.indexOf('<')+1,line.indexOf('>'));
        String tagValue = line.substring(line.indexOf('>')+1,line.indexOf('/')-1);
        ArrayList<String> retVal = new ArrayList<>();
        retVal.add(tagName.trim());
        retVal.add(tagValue.trim());
        return retVal;
    }

    private static String parseLineNoDelimiterDemo(String line){
        return line.substring(1,line.length()-1); //remove this nigga
    }
    private static ArrayList<String> parseLineNoDelimiter(String line, ArrayList<String> lines, int currentLineCounter){
        ArrayList<String> tagNameValue = new ArrayList<>();
        String tagName = parseLineNoDelimiterDemo(line);
        tagNameValue.add(tagName);

        //In case the node has or doesn't have a tag value
        if(lines.get(currentLineCounter+1).contains("<"))
            tagNameValue.add(null); //SET THE tagValue NULL
        else{
            tagNameValue.add(lines.get(currentLineCounter+1));//GET THE NEXT LINE and set it to tagValue
        }
        return tagNameValue;
    }
}

