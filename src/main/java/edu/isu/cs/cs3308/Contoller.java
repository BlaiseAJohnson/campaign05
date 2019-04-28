/**
 * Blaise Johnson
 * Isaac Griffith
 * CS 3308
 * 4/28/19
 */
package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.AdjacencyListGraph;
import edu.isu.cs.cs3308.structures.impl.GraphBuilder;

import java.util.Scanner;

/**
 * Controller class for MVC
 */
public class Contoller {

    public static void main(String[] args){

        // Retrieves file path and creates graph if possible
        Scanner scanner = new Scanner(System.in);
        String filePath = View.greet(scanner);
        GraphBuilder graphBuilder = new GraphBuilder();
        AdjacencyListGraph<String, Integer> graph = graphBuilder.buildGraph(filePath);
        String selection = "0";

        if (graph == null || graph.numVertices() == 0) {
            System.out.println("File could not be found or did not contain a network!");
            System.out.println("Program now terminating...");
            selection = "3";
        }
        else {
            while (!selection.equals("3")) {
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
                }
            }

            System.out.println("Until next time.");
        }
    }
}
