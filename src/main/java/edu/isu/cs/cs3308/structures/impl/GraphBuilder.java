package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Vertex;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GraphBuilder {
    private String filePath;
    private boolean isWeighted = false;
    private AdjacencyListGraph<String, Integer> graph;

    public AdjacencyListGraph<String, Integer> getGraph() {
        return graph;
    }

    public AdjacencyListGraph<String, Integer> buildGraph(String fileName) {
        try {
            Path path = FileSystems.getDefault().getPath("data", fileName);
            List<String> nodes = Files.readAllLines(path);
            build(nodes);
            return graph;
        }catch (IOException e) {
            System.out.println("File could not be found...");
        }

        return null;
    }


    private void build(List<String> nodes) {
        graph = new AdjacencyListGraph<>();

        for (String s: nodes) {
            String[] connections = processConnectionsList(s);
            buildConnections(graph, connections);
        }
    }


    private String[] processConnectionsList(String list) {
        String[] connections = list.split(" ");

        if (connections[0].contains(":")) {
            connections[0].replace(":", "");
        }
        if (connections[1].contains("(")) {
            isWeighted = true;
        }

        return connections;
    }


    private void buildConnections(AdjacencyListGraph<String, Integer> graph, String[] connections) {
        Vertex<String> origin = graph.getVertex(connections[0].replace(":", ""));
        if (origin == null) {
            origin = graph.insertVertex(connections[0].replace(":", ""));
        }

        for (int i = 1; i < connections.length; i++) {
            String currentNodeString = connections[i];
            int weight = 0;

            // This is just disgusting. If I have time //TODO Replace with regex or something
            if (isWeighted) {
                currentNodeString = connections[i].substring(0, connections[i].indexOf("("));
                weight = Integer.parseInt(connections[i].substring(connections[i].indexOf("(") + 1, connections[i].indexOf(")")));
            }

            Vertex<String> currentNode = graph.getVertex(currentNodeString);
            if (currentNode == null) {
                currentNode = graph.insertVertex(currentNodeString);
            }

            graph.insertEdge(origin, currentNode, weight);
        }
    }
}
