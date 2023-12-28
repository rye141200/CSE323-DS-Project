package com.example.demo.Visualization;


import com.example.demo.Parsing.Tree;
import com.example.demo.Parsing.TreeNode;


import java.util.*;
public class TreeToGraph {
    public static UserNode searchById(ArrayList<UserNode> userNodesList, String id){
        for(UserNode user:userNodesList)
            if(user.getId().equals(id))
                return user;
        return null;
    }
    public static UserNode searchByName(ArrayList<UserNode> userNodeList, String name){
        for(UserNode user: userNodeList)
            if(user.getName().equals(name))
                return user;
        return null;
    }
    public static SocialGraph convertTreeToGraph(Tree XMLTree) throws Exception{
        /* 
         * Algorithm:
         * 1) Get the nodes in a list
         * 2) Loop over the users' children, to parse the values into the UserNode object
         * 3) Check by name, if the child name is either ID or name, store them into the UserNode values
         * 4) Else if, the child name is posts or topics or followers, loop over their children to append these values in the corresponding
         *    ArrayLists, once done, create the new UserNode, append it to the LevelIIPackage.Graph vertices
         */
        //1) Get the nodes
        ArrayList<TreeNode> users = XMLTree.getRoot().getChildren();

        //2) Looping over the users' children to get the posts, name, ID of users
        String tagName="";
        String tagValue = "";
        String name = "";
        String id="";
        ArrayList<UserNode> userNodesList = new ArrayList<>();
        
        for(TreeNode user: users){
            ArrayList<Post> userPosts = new ArrayList<>();
            for(TreeNode child: user.getChildren()){
                tagName = child.getTagName();
                tagValue = child.getTagValue();
                
                if(tagName.equals("name"))
                    name = tagValue;
                
                else if(tagName.equals("id"))
                    id = tagValue;
                
                else if(tagName.equals("posts")){
                    for(TreeNode post:child.getChildren()){
                        String bodyValue = "";
                        ArrayList<String> topics = new ArrayList<>();
                        for(TreeNode element:post.getChildren()){
                            if(element.getTagName().equals("body")){
                                bodyValue = element.getTagValue();
                            }
                            else if(element.getTagName().equals("topics"))
                            for(TreeNode topic: element.getChildren()){
                                topics.add(topic.getTagValue());
                            }
                        }
                        userPosts.add(new Post(bodyValue,topics));
                    }
                }
            }
            UserNode currentUser = new UserNode(name, id,userPosts);
            userNodesList.add(currentUser);
        }
        //System.out.println(userNodes);
        //3) Looping over the users once again to get the followers
        int userCounter = 0;
        for(TreeNode user:users){
            ArrayList<UserNode> followersList = new ArrayList<>();
            for(TreeNode followers: user.getChildren()){
                if(followers.getTagName().equals("followers")){
                    for(TreeNode follower: followers.getChildren()){
                        followersList.add(searchById(userNodesList, follower.getChildren().get(0).getTagValue()));
                    }
                }
            }
            userNodesList.get(userCounter).setFollowers(followersList);
            userCounter++;
        }
        
        //3) Creating the graph
        return new SocialGraph(userNodesList);
    }

//    public static void main(String[] args) throws Exception{
//        SocialGraph shawkyChan = convertTreeToGraph(Parsing.parseXML());
//        shawkyChan.printGraph();
//    }
}
