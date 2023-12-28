package com.example.demo.Visualization;

import java.util.ArrayList;

public class Post {
    private String body;
    private ArrayList<String> topics;

    public Post(String body) {
        this.body = body;
        topics = new ArrayList<>();
    }

    public Post(String body, ArrayList<String> topics) {
        this.body = body;
        this.topics = topics;
    }

    public void addTopics(String topic) {
        topics.add(topic);
    }

    public String getBody() {
        return body;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }

    @Override
    public String toString() {
        return "Body: " + body + ", \n" +
                "Topics: " + topics + "   \n\n";
    }
}
