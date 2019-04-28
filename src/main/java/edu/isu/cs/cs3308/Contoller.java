package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.AdjacencyListGraph;
import edu.isu.cs.cs3308.structures.impl.GraphBuilder;

import java.util.Scanner;

public class Contoller {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String filePath = View.greet(scanner);
        GraphBuilder graphBuilder = new GraphBuilder();
        AdjacencyListGraph<String, Integer> graph = graphBuilder.buildGraph(filePath);
        String selection = "0";

        while (!selection.equals("4")) {
            selection = View.printMenu(scanner);

            switch (selection) {
                case "0":
                    View.printConfig(Model.networkConfig(graph));
                    break;
                case "1":
                    String node = View.routingTableNode(scanner);
                    View.printRoutingTable(Model.routingTable(graph, node));
                    break;
                case "2":
                    String[] nodes = View.shortestPathNodes(scanner);
                    View.printShortestPath(Model.shortestPath(graph, nodes));
                    break;
//                case 3:
//                    View.findSarahConner();
            }
        }

        System.out.println("Until next time.");
    }
}
