package com.example.demo.SNA_Undo;
import com.example.demo.Visualization.*;
import com.example.demo.FileReading.FileReaderEnhanced;
import com.example.demo.FileReading.FileSampleEnhanced;
import com.example.demo.Parsing.Parsing;
import com.example.demo.Visualization.*;

import java.util.ArrayList;
import java.util.*;
public class SNA {
    /************************** HELPER METHODS *****************************/
    private static PairObject<UserNode,Double> searchByDegree(Double maxDegree, ArrayList<PairObject<UserNode,Double>> mappedDegreeList){
        for(PairObject<UserNode,Double> userNodeDegreePair: mappedDegreeList)
            if(userNodeDegreePair.getValue() == maxDegree)
                return userNodeDegreePair;
        return null;
    }
    
    private static PairObject<UserNode,Integer> searchByDegree(Integer maxDegree,ArrayList<PairObject<UserNode,Integer>> mappedDegreeList){
        for(PairObject<UserNode,Integer> userNodeDegreePair: mappedDegreeList)
            if(userNodeDegreePair.getValue() == maxDegree)
                return userNodeDegreePair;
        return null;
    }
    /************************** LevelIIPackage.SNA methods ********************************/
    public static PairObject<UserNode,Double> getMostInfluencer(SocialGraph userSocialGraph){
        ArrayList<PairObject<UserNode,Double>> mappedDegreeList = new ArrayList<>();
        ArrayList<Double> degreesNormalized = new ArrayList<>();
        for(UserNode user: userSocialGraph.getUsers()){
            Double degreeCentrality = (double)(user.getFollowers().size()) / userSocialGraph.getUsers().size();
            degreesNormalized.add(degreeCentrality);
            mappedDegreeList.add(new PairObject<UserNode,Double>(user,degreeCentrality));
        }
        Collections.sort(degreesNormalized);
        Collections.reverse(degreesNormalized);
        Double maxDegree = degreesNormalized.get(0);
        return searchByDegree(maxDegree, mappedDegreeList);
    }
    public static PairObject<UserNode,Integer> getMostActiveUser(SocialGraph userSocialGraph){
        /* 
         * We need to find the user who has the most following count (i.e follows people the most)
         */

        //2) Loop over the nodes/vertices
        int[] frequencyList = new int[userSocialGraph.getUsers().size()+1];
        //ArrayList<Integer> followingList = new ArrayList<>();
        for(UserNode user: userSocialGraph.getUsers()){
            for(UserNode follower: user.getFollowers()){
                frequencyList[Integer.parseInt(follower.getId())]++;
            }
        }
        int max = 0;
        int id = 0;
        for(int i=1;i<frequencyList.length;i++){
            if(max<frequencyList[i]){
                max = frequencyList[i];
                id =i;
            }
        }
        UserNode gaymer = TreeToGraph.searchById(userSocialGraph.getUsers(),Integer.toString(id));
        return new PairObject<>(gaymer, max);
    }

    public static ArrayList<UserNode> getMutualFollower(UserNode userOne, UserNode userTwo, SocialGraph userSocialGraph){
        //1) Getting the adjacency "Followers" lists of the users
        ArrayList<UserNode> followersOfUserOne = userOne.getFollowers();
        ArrayList<UserNode> followersOfUserTwo = userTwo.getFollowers();
        ArrayList<UserNode> mutualFollowers = new ArrayList<>();
        //2) Create HashMap or ErrorHandlerPackage.PairObject, if the frequency of this ID exceeds 1, then it is mutual and append it
        int[] frequencyList = new int[userSocialGraph.getUsers().size()+1];
        for(UserNode follower : followersOfUserOne)
            frequencyList[Integer.parseInt(follower.getId())]++;        
        for(UserNode follower : followersOfUserTwo)
            frequencyList[Integer.parseInt(follower.getId())]++; 
        
        Integer i = 0;
        for(int count:frequencyList){
            if(count>1)
                mutualFollowers.add(TreeToGraph.searchById(userSocialGraph.getUsers(),i.toString()));
            i++;
        }
        return mutualFollowers;
    }
    public static ArrayList<UserNode> suggestFollowers(SocialGraph userSocialGraph, UserNode user){
        ArrayList<UserNode> followersList = user.getFollowers();
        ArrayList<UserNode> suggestedFollowersList = new ArrayList<>();

        for(UserNode follower: followersList){
            for(UserNode followerOfFollower: follower.getFollowers())
                if(!followersList.contains(followerOfFollower) && followerOfFollower != user && !suggestedFollowersList.contains(followerOfFollower))
                    suggestedFollowersList.add(followerOfFollower);
        }
        return suggestedFollowersList;
    }
    public static ArrayList<Post> searchByTopic(SocialGraph userSocialGraph, String keyTopic){
        ArrayList<Post> topicsList = new ArrayList<>();
        //1)Loop over each user
        for(UserNode user:userSocialGraph.getUsers())
            for(Post post: user.getPosts())
                for(String topic:post.getTopics())
                    if(topic.toLowerCase().contains(keyTopic.toLowerCase()))
                        topicsList.add(post);
        return topicsList;
    }
    public static ArrayList<Post> searchByParagraph(SocialGraph userSocialGraph,String keyParagraph){
        ArrayList<Post> paragraphList = new ArrayList<>();
        for(UserNode user:userSocialGraph.getUsers())
            for(Post post: user.getPosts())
                if(post.getBody().toLowerCase().contains(keyParagraph.toLowerCase()))
                    paragraphList.add(post);
        return paragraphList;
    }


    public static void main(String[] args) throws Exception{
        SocialGraph shawkyChan =  TreeToGraph.convertTreeToGraph(Parsing.parseXML(FileReaderEnhanced.readFileParsed(FileSampleEnhanced.readFileParsed("example_-_Copy.xml"))));
        //System.out.println(getMostInfluencer(shawkyChan));
        System.out.println(getMostActiveUser(shawkyChan));
        //System.out.println(getMutualFollower(shawkyChan.getUsers().get(1),shawkyChan.getUsers().get(2) , shawkyChan));
        //System.out.println(suggestFollowers(shawkyChan, shawkyChan.getUsers().get(2)));
       System.out.println("Hello shawky chan");
    }


}
