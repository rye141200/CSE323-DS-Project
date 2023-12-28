package com.example.demo.Parsing;

import java.util.Stack;
import java.util.ArrayList;
/*****************************/
/* 
    Authors: -Ahmad Mahfouz
             -Yousef Shawky
    Description: - Implementation of Parsing Logic
    
 */
/*****************************/

public class Parsing {
    public static  Tree parseXML(ArrayList<String> lines) throws Exception{
        int currentLineCounter = 0;
        Tree XMLTree = new Tree(null);
        Stack<TreeNode> scopeStack = new Stack<>();

        for(String line: lines){
            //1) Case of root node (BASIS CONDITION) 
            if(!line.isEmpty() && currentLineCounter == 0){
                TreeNode root = new TreeNode(line.substring(1,line.length()-1),lines.get(currentLineCounter+1).contains("<") ? null:lines.get(currentLineCounter+1),null);
                XMLTree.setRoot(root);
                scopeStack.push(root);
            }
            else{
                //2) Case of opening tag
                if(!line.isEmpty() && line.charAt(0) == '<' && line.charAt(1) != '/'){
                    ArrayList<String> tagNameValue = new ArrayList<>();
                    TreeNode node;
                    if((lines.get(currentLineCounter+1)).contains("<"))
                    {
                        tagNameValue.add(line.substring(1,line.length()-1));
                        tagNameValue.add(null);
                    }
                    else{
                        tagNameValue.add(line.substring(1,line.length()-1));
                        tagNameValue.add(lines.get(currentLineCounter+1));
                    }
                    node = new TreeNode(tagNameValue.get(0),tagNameValue.get(1),scopeStack.peek());
                    XMLTree.addNode(node,scopeStack.peek());
                    scopeStack.add(node);
                }
                //3) Case of closed tag
                else if(!line.isEmpty() && line.charAt(0) == '<' && line.charAt(1) == '/')
                    scopeStack.pop();
                
            }
            currentLineCounter++;
        }
        return XMLTree;       
    }
}

