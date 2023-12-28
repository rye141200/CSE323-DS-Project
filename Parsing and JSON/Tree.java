package com.example.demo.Parsing;
import java.util.ArrayList;
/*****************************/
/* 
    Author: Ahmad Mahfouz
    Description: - Implementation of Tree
    
 */
/*****************************/
public class Tree {
    // Data fields
    private TreeNode root;

    //Constructor
    public Tree(TreeNode root){
        this.root = root;
    }

    //Custom methods
    public TreeNode getRoot(){
        return root;
    }
    public void setRoot(TreeNode root){
        this.root = root;
    }
    public void addNode(TreeNode node,TreeNode parent){
        parent.addChildren(node);
    }
    public void printTree(){
        //Breadth first
        root.printNode();
    }
    public ArrayList<TreeNode> traverseDepthFirst(){
        ArrayList<TreeNode> temp = new ArrayList<>();
        root.getAll(temp);
        return temp;
    }
    public StringBuilder toJSON(StringBuilder temp,int depth){
        return root.getJSON(temp,depth);
    }
    public static void main(String[] args){
        /******************TEST CODE***********************************/
        TreeNode nodeOne = new TreeNode("Book", "book11");
        TreeNode nodeTwo = new TreeNode("Author", "Adam");
        TreeNode nodeThree = new TreeNode("Title", "Data Analysis");
        TreeNode nodeFour = new TreeNode("Unit", "Data Cleaning");
        TreeNode nodeFive = new TreeNode("Unit", "Data Gathering");

        Tree mainTree = new Tree(nodeOne);
        mainTree.addNode(nodeTwo, mainTree.getRoot());
        mainTree.addNode(nodeThree, mainTree.getRoot());
        mainTree.addNode(nodeFour, nodeThree);
        mainTree.addNode(nodeFive, nodeThree);
        System.out.println("*********************************************");
        System.out.println(mainTree.traverseDepthFirst());          

    }
}
