package com.example.demo.Visualization;

import java.util.ArrayList;

public class UserNode {
    private String name;
    private String id;
    private ArrayList<Post> posts;
    private ArrayList<UserNode> followers; //Adjacency list

    public UserNode(String name, String id) {
        this.name = name;
        this.id = id;
        posts = new ArrayList<>();
        followers = new ArrayList<>();
    }

    public UserNode(String name, String id, ArrayList<Post> posts) {
        this.name = name;
        this.id = id;
        this.posts = posts;
        followers = new ArrayList<>();
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void addFollower(UserNode follower) {
        followers.add(follower);
    }

    public void setFollowers(ArrayList<UserNode> followers) {
        this.followers = followers;
    }

    public String getName() {
        return name;
    }

    public ArrayList<UserNode> getFollowers() {
        return this.followers;
    }

    public String getId() {
        return this.id;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    @Override
    public String toString() {
        String returnString = "Name: " + name + "\n" +
                "ID: " + id + "\n" +
                "Posts: " + posts + "\n" + "Followers: ";
        for (UserNode follower : followers)
            returnString += follower.name + " , ID: " + follower.id + "\n";
        return returnString;

    }
}
