package com.example.demo.Visualization;
import java.awt.*;
import java.util.Random;

import com.example.demo.FileReading.FileReaderEnhanced;
import com.example.demo.FileReading.FileSampleEnhanced;
import com.example.demo.SharedDataModel;
import com.example.demo.Parsing.Parsing;
import javafx.application.Application;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.brunomnsilva.smartgraph.graph.Digraph;
import com.brunomnsilva.smartgraph.graph.DigraphEdgeList;
import com.brunomnsilva.smartgraph.graph.Vertex;
import com.brunomnsilva.smartgraph.graphview.SmartCircularSortedPlacementStrategy;
import com.brunomnsilva.smartgraph.graphview.SmartGraphPanel;
import com.brunomnsilva.smartgraph.graphview.SmartPlacementStrategy;

import static javafx.application.Application.launch;
import javafx.scene.Scene;

import java.util.ArrayList;

/**
 *
 * @author azazk
 */
public class GraphVisualizer extends Application{
    public static void showXMLGraph(SocialGraph userGraph){
        Stage stage = new Stage();
        Digraph<String,EmptyString> socialGraph = new DigraphEdgeList<>();
        ArrayList<UserNode> users = userGraph.getUsers();
        //ArrayList<PairObject<UserNode,Vertex<String>>>
        for(UserNode user : users){
            socialGraph.insertVertex(user.getName());
        }
        for(UserNode user: userGraph.getUsers()){
            //Vertex<String> vertexOne = socialGraph.insertVertex(user.getName()+": "+user.getId());
            for(UserNode follower: user.getFollowers()){
                EmptyString bullShit = new EmptyString();
                //Vertex<String> vertexTwo = socialGraph.insertVertex(follower.getName() +": "+follower.getId());
                socialGraph.insertEdge( user.getName() ,follower.getName(), bullShit);
            }
        }
        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        SmartGraphPanel<String, EmptyString> graphView = new SmartGraphPanel<>(socialGraph, strategy);
         SplitPane splitPane = new SplitPane();
         splitPane.getItems().add(graphView);
        Scene scene = new Scene(splitPane, 1024, 760);

        Button button = new Button("Social network analysis");
        button.setBounds(512, 600, 70, 20);

        Image icon = new Image("MiniLogo_2.png");
        stage.getIcons().add(icon);
        stage.setTitle("Graph Visualizer");
        stage.setScene(scene);
        stage.show();
        graphView.init();
    }
    private static String randomBullShitGenerator(){
        String set = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        return set.substring(0,(2+rand.nextInt(26))%26);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{


        ArrayList<String> lines = FileReaderEnhanced.readFileParsed(FileSampleEnhanced.readFileParsed(SharedDataModel.getPath()));
        SocialGraph userGraph = TreeToGraph.convertTreeToGraph(Parsing.parseXML(lines));
        Digraph<String,EmptyString> socialGraph = new DigraphEdgeList<>();


        ArrayList<Vertex<String>> verticesList = new ArrayList<>();
        for(UserNode user: userGraph.getUsers()){
            Vertex<String> currentVertex = socialGraph.insertVertex(user.getName()+": "+user.getId());
            verticesList.add(currentVertex);
        }
        int vertexCounter =0;
        for(UserNode user: userGraph.getUsers()){
            for(UserNode follower: user.getFollowers()){
                //user.getId()+" -> "+follower.getId() : 1 -> 2
                EmptyString bullShit = new EmptyString();

                socialGraph.insertEdge(verticesList.get(vertexCounter),verticesList.get(Integer.parseInt(follower.getId())-1),bullShit);
                /*graphView.getStylableEdge(bullShit).setStyle("    -fx-stroke-width: 2;\n" +
                        "    -fx-stroke: #FF6D66;  \n" +
                        "    -fx-stroke-dash-array: 2 5 2 5;   \n" +
                        "    -fx-fill: transparent; \n" +
                        "    -fx-stroke-line-cap: round;\n" +
                        "    -fx-opacity: 0.8;\n");*/
            }
            vertexCounter++;
        }
        SmartPlacementStrategy strategy = new SmartCircularSortedPlacementStrategy();
        SmartGraphPanel<String, EmptyString> graphView = new SmartGraphPanel<>(socialGraph, strategy);
        Scene scene = new Scene(graphView, 1024, 768);

        /*scene.getStylesheets().add(getClass().getResource("src/main/java/com/example/dsprojectv2/smartgraph.css").toExternalForm());
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("src/main/java/com/example/dsprojectv2/smartgraph.properties"));
        */
        Image icon = new Image("MiniLogo_2.png");
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Graph Visualizer");
        primaryStage.setScene(scene);
        primaryStage.show();
        graphView.init();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
class EmptyString {
    public String toString(){
        return "";
    }
}
