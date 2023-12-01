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
    private ArrayList<TreeNode> children;
    
    public TreeNode(String tagName, String tagValue) {
        this.tagName = tagName;
        this.tagValue = tagValue;
        this.children = new ArrayList<>();
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

    public void addChildren(TreeNode Node) {
        this.children.add(Node);
    }
    public void printNode(){
        System.out.println("Tag name: " + tagName);
        System.out.println("Tag Value: " + tagValue);

        if(children.isEmpty())
            return;
        for(TreeNode node: children)
            node.printNode();
    }
}
