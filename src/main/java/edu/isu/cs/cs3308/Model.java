package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.Edge;
import edu.isu.cs.cs3308.structures.Graph;
import edu.isu.cs.cs3308.structures.Vertex;
import edu.isu.cs.cs3308.structures.impl.AdjacencyListGraph;
import edu.isu.cs.cs3308.structures.impl.AdjacencyListGraph.InnerVertex;
import edu.isu.cs.cs3308.structures.impl.AdjacencyListGraph.InnerEdge;

import java.util.*;

public class Model {
    public static List<String[]> networkConfig(Graph<String, Integer> graph) {
        List<String[]> nodeList = new LinkedList<>();

        for (Edge<Integer> e: graph.edges()) {
            String[] currentEdgeToString = new String[2];
            Vertex<String>[] currentEdgeEndPoints = e.getEndpoints();
            currentEdgeToString[0] = currentEdgeEndPoints[0].toString();
            currentEdgeToString[1] = currentEdgeEndPoints[1].toString();

            nodeList.add(currentEdgeToString);
        }

        if (nodeList.isEmpty()) {
            String[] emptyGraphMessage = new String[2];
            emptyGraphMessage[0] = "EMPTY";
            emptyGraphMessage[1] = "GRAPH";
            nodeList.add(emptyGraphMessage);
        }

        return nodeList;
    }


    public static List<String[]> shortestPath(Graph<String, Integer> graph, String[] pathNodes) {
        if (verifyNodes(graph, pathNodes)) {
            Vertex<String> source = ((AdjacencyListGraph<String, Integer>) graph).getVertex(pathNodes[0]);
            Vertex<String> destination = ((AdjacencyListGraph<String, Integer>) graph).getVertex(pathNodes[1]);
            dijkstra(graph, (InnerVertex) source);
            Stack<Vertex<String>> pathStack = loadPath(graph, destination);
            return formatPath(pathStack);
        }
        else {
            List<String[]> invalidPathList = new LinkedList<>();
            String[] invalidPath = new String[2];
            invalidPath[0] = "Invalid";
            invalidPath[1] = "Path";
            invalidPathList.add(invalidPath);
            return invalidPathList;
        }
    }

    private static void dijkstra(Graph<String, Integer> graph, InnerVertex source) {
        List<InnerVertex> unknownVertices = new LinkedList<>();

        for (Vertex<String> vertex: graph.vertices()) {
            ((InnerVertex) vertex).initialize();
            unknownVertices.add((InnerVertex) vertex);
        }
        source.setCost(0);

        while (!unknownVertices.isEmpty()) {
            InnerVertex currentVertex = removeMin(unknownVertices);
            currentVertex.setKnown(true);
            unknownVertices.remove(currentVertex);

            List<Edge<Integer>> edges = currentVertex.getOutgoing();
            for (Edge<Integer> edge: edges) {
                InnerVertex currentOpposite = (InnerVertex) graph.opposite(currentVertex, edge);

                if (!currentOpposite.isKnown()) {
                    if(currentVertex.getCost() + edge.getElement() <= currentOpposite.getCost()) {
                        currentOpposite.setCost(currentVertex.getCost() + edge.getElement());
                        currentOpposite.setPreviousNode(currentVertex);
                    }
                }
            }
        }
    }

    private static InnerVertex removeMin(List<InnerVertex> list) {
        InnerVertex minVertex = list.get(0);

        for (InnerVertex vertex: list) {
            if (vertex.getCost() < minVertex.getCost()) {
                minVertex = vertex;
            }
        }

        return minVertex;
    }

    private static Stack<Vertex<String>> loadPath(Graph<String, Integer> graph, Vertex<String> destination) {
        Stack<Vertex<String>> nodePath = new Stack<>();
        InnerVertex destinationNode = (InnerVertex) destination;

        while (destinationNode.getPreviousNode() != null) {
            nodePath.push(destinationNode);
            destinationNode = (InnerVertex) destinationNode.getPreviousNode();
        }

        return nodePath;
    }

    private static List<String[]> formatPath(Stack<Vertex<String>> nodeList) {
        List<String[]> formattedStringList = new LinkedList<>();

        while (!nodeList.isEmpty()) {
            String[] formattedString = new String[2];
            Vertex<String> currentNode = nodeList.pop();

            formattedString[0] = currentNode.getElement();
            formattedString[1] = ((InnerVertex) currentNode).getPreviousNode().getElement().toString();

            formattedStringList.add(formattedString);
        }

        return formattedStringList;
    }

    private static boolean verifyNodes(Graph<String, Integer> graph, String[] nodes) {
        boolean verified = true;

        if (((AdjacencyListGraph<String, Integer>) graph).getVertex(nodes[0]) == null) {
            verified = false;
        }

        if (((AdjacencyListGraph<String, Integer>) graph).getVertex(nodes[1]) == null) {
            verified = false;
        }

        return verified;
    }
}
