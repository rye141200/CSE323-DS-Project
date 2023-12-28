package com.example.demo.Visualization;


import java.util.*;

public class SocialGraph {
    //Data fields
    private ArrayList<UserNode> nodes;
    public SocialGraph(ArrayList<UserNode> userNodesList){
        nodes = userNodesList;
    }
    public void addNode(UserNode node){
        nodes.add(node);
    }
    public void printGraph(){
        for(UserNode node : nodes)
            System.out.println(node);
    }
    public ArrayList<String> getUserNames(){
        ArrayList<String> userNames = new ArrayList<>();
        for(UserNode user: nodes)
            userNames.add(user.getName());
        return userNames;
    }
    public ArrayList<UserNode> getUsers(){
        return nodes;
    }
    public static void main(String[] args){

        UserNode OS = new UserNode("OS Chan", "1");
        UserNode singerKun = new UserNode("Singer-Kun", "2");
        UserNode Gaymer = new UserNode("Gaymer", "3");

        OS.addFollower(Gaymer);
        OS.addFollower(singerKun);
        Post OSPost =new Post("I love to be a dickhead");
        OSPost.addTopics("Bullshit");
        OS.addPost(OSPost);

        singerKun.addFollower(Gaymer);
        singerKun.addFollower(OS);
        Post singerKunPost = new Post("I am very awesome!");
        singerKunPost.addTopics("The phenonemenon of OS Chan loving to lick feets");
        singerKun.addPost(singerKunPost);

        //shawkyChan.addNode(OS);
        //shawkyChan.addNode(singerKun);
        //shawkyChan.addNode(Gaymer);

        //shawkyChan.printGraph();
    }
}
