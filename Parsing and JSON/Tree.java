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
    public static void main(String[] args){
        /******************TEST CODE***********************************/
        /*  TreeNode nodeOne = new TreeNode("5", "book11");
            TreeNode nodeTwo = new TreeNode("1", "Adam");
            TreeNode nodeThree = new TreeNode("2", "Data Analysis");
            TreeNode nodeFour = new TreeNode("3", "Data Cleaning");
            TreeNode nodeFive = new TreeNode("6", "Data Gathering");


            Tree mainTree = new Tree(nodeOne);
            mainTree.addNode(nodeTwo, mainTree.getRoot());
            mainTree.addNode(nodeThree, mainTree.getRoot());
            mainTree.addNode(nodeFour, nodeThree);
            mainTree.addNode(nodeFive, nodeThree);
            System.out.println("*********************************************");
            mainTree.printTree(); 
            */

    }
}
