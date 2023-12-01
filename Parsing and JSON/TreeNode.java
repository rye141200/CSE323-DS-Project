import java.util.ArrayList;

public class TreeNode {
    private String TagName;
    private String TagValue;
    private ArrayList<TreeNode> Children;
    
    public TreeNode(String TagName, String TagValue, ArrayList<TreeNode> Children) {
        this.TagName = TagName;
        this.TagValue = TagValue;
        this.Children = Children;
    }

    public String getTagName() {
        return TagName;
    }

    public String getTagValue() {
        return TagValue;
    }

    public ArrayList<TreeNode> getChildren() {
        return Children;
    }

    public void setTagName(String TagName) {
        this.TagName = TagName;
    }

    public void setTagValue(String TagValue) {
        this.TagValue = TagValue;
    }

    public void addChildren(TreeNode Node) {
        this.Children.add(Node);
    }
}
