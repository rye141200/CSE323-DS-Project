package com.example.demo.Parsing;
import java.util.ArrayList;
/*****************************/
/* 
    Author: Youssef Shawky
    Description: - Implementation of TreeNode
    
 */
/*****************************/
public class TreeNode {
    private String tagName;
    private String tagValue;
    private TreeNode parentNode;
    private ArrayList<TreeNode> children;
    
    public TreeNode(String tagName, String tagValue) {
        this.tagName = tagName;
        this.tagValue = tagValue;
        this.children = new ArrayList<>();
    }
    public TreeNode(String tagName,String tagValue,TreeNode parentNode){
        this.tagName = tagName;
        this.tagValue = tagValue;
        this.children = new ArrayList<>();
        this.parentNode = parentNode;
    }
    public String getTagName() {
        return tagName;
    }

    public String getTagValue() {
        return tagValue;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }
    public TreeNode getParentNode(){
        return this.parentNode;
    }
    public void addChildren(TreeNode Node) {
        this.children.add(Node);
    }
    public boolean hasChildren(){
        return !this.children.isEmpty();
    }
    public void printNode(){
        System.out.println("Tag name: " + tagName);
        System.out.println("Tag Value: " + tagValue);
        System.out.println(children);
        if(children.isEmpty())
            return;
        for(TreeNode node: children)
            node.printNode();
    }
    @Override
    public String toString(){
        if(parentNode == null) 
            return tagName + " " + tagValue+ " "+"\n";
        else
            return  tagName + " " + tagValue+ " "+ parentNode.getTagName() +"\n"; 
    }
    public void getAll(ArrayList<TreeNode> temp){ //O(n)
        temp.add(this);
        if(children.isEmpty())
            return;
        
        for(TreeNode node: children){
            node.getAll(temp);
        }
    }
    private boolean isJSONObject(){//O(n) rarely occurs
        if(this.getChildren().size() == 1)
            return true;
        if(this.hasChildren()){
            String currentTagName = children.get(0).getTagName();
            for(int i =0;i<children.size()-1 ; i++){
                if(!currentTagName.matches(children.get(i+1).getTagName()))
                    return true;
            }
        }
        return false; //it's an array
    }
    private boolean isParentIsRoot(TreeNode node){
        if(node.parentNode == null)
            return true;
        return false;
    }
    public StringBuilder getJSON(StringBuilder temp,int depth){
        // Case 1 Line Object (Basis Condition)
        if(children.isEmpty()){
            JSONConverter.appendIdentations(temp,depth+2);
            temp.append("\"" + tagName + "\"").append(": ").append("\"" + tagValue + "\""); // "name" : "value"
            if(parentNode.isJSONObject() && parentNode.children.size() > 1 && this.parentNode.children.get(parentNode.children.size()-1) != this) // Parent is JSON and Have Children > 1
                temp.append(",\n");
            else
                temp.append("\n");
            return temp;
        }
        
        depth+=2;
        JSONConverter.appendIdentations(temp, depth);
        
        int lastChildOfParent = (isParentIsRoot(this)) ? 0 : this.parentNode.getChildren().size() -1; 
        // Case Array  
        if(!isJSONObject()){
            // 1) Opening Tag Array
            temp.append("\""+tagName+"\": [ \n"); 
            // 2) Loop Over Elements of Array
            for(TreeNode node: children){
                // 1) Open Tag of element of Array
                JSONConverter.appendIdentations(temp, depth);
                temp.append("{\n");
                // 2) Element Of Array
                node.getJSON(temp,depth);
                // 3) Close Tag of element of Array
                JSONConverter.appendIdentations(temp, depth);
                if(node == children.get(children.size()-1)) // if this element was Last element in Array
                    temp.append("}\n");
                else
                    temp.append("},\n");
            }
            // 3) Close Tag Array
            JSONConverter.appendIdentations(temp, depth);
            depth-=2;
            if(isParentIsRoot(this)) 
                return temp.append("] \n");
            
            return this.parentNode.children.get(lastChildOfParent) == this ?temp.append("] \n") :   temp.append("], \n");
            
        }

        // Case Normal Object
        // 1) Open Tag Object
        temp.append("\""+tagName+"\": ");
        temp.append("{ \n");
        // 2) Loop Over attributes of Object
        for(TreeNode node: children){
            node.getJSON(temp,depth);
        }
        // 3) CLose Tag Object
        JSONConverter.appendIdentations(temp, depth);
        depth-=2;
        if(isParentIsRoot(this)) 
            return temp.append("} \n");
        return ((this.parentNode.children.get(lastChildOfParent) == this) || !parentNode.isJSONObject())? temp.append("} \n"): temp.append("}, \n");
        
    }
}
