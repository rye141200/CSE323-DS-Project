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
    
}
