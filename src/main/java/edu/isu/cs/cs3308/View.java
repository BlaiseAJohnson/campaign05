/**
 * Blaise Johnson
 * Isaac Griffith
 * CS 3308
 * 4/28/19
 */
package edu.isu.cs.cs3308;

import java.util.List;
import java.util.Scanner;

/**
 * View class for MVC
 */
public class View {


    /**
     * Greets the user and prompts for a file name.
     * @param input
     * @return
     */
    public static String greet(Scanner input) {
        System.out.println();
        System.out.println("Hello. Welcome to Skynet Network Path Analyzer\n");
        System.out.print("Please enter a network description file name: \n");

        return input.next();
    }


    /**
     * Prints the main menu of the program to the console.
     * @param input
     * @return
     */
    public static String printMenu(Scanner input) {
        System.out.println(
                "\n" +
                "Menu\n" +
                "----\n" +
                "0. Print Current Network Config\n" +
                "1. View Routing Table for Node\n" +
                "2. Find Shortest Path Between Two Nodes\n" +
                "3. Exit\n");

        System.out.print("Selection: ");

        return input.next();
    }


    /**
     * Formatted output function for printing network config.
     * @param routes
     */
    public static void printConfig(List<String[]> routes) {
        System.out.println("Current Network Config:");
        System.out.println("-----------------------");

        for (String[] route: routes) {
            System.out.println(String.format("%s -> %s", route[0], route[1]));
        }

        System.out.println("-----------------------\n");
    }


    /**
     * Prompts user for two nodes of the network.
     * @param input
     * @return
     */
    public static String[] shortestPathNodes(Scanner input) {
        String[] pathNodes = new String[2];

        System.out.print("Starting Node: ");
        pathNodes[0] = input.next();
        System.out.println();
        System.out.print("Ending Node: ");
        pathNodes[1] = input.next();
        System.out.println();

        return pathNodes;
    }


    /**
     * Formatted output function for printing the shortest path
     * between two network nodes.
     * @param routeList
     */
    public static void printShortestPath(List<String[]> routeList) {
        System.out.println("Path:");
        System.out.println("-----");

        for (String[] route: routeList) {
            System.out.println(String.format("   %s -> %s", route[1], route[0]));
        }

        System.out.println("\n");
    }


    /**
     * Prompts a user for a single node in the network.
     * @param input
     * @return
     */
    public static String routingTableNode(Scanner input) {
        System.out.print("Node to view: ");
        return input.next();
    }


    /**
     * Formatted output function used for printing the routing
     * table for a network node.
     * @param nodeList
     */
    public static void printRoutingTable(List<String[]> nodeList) {
        System.out.println("------------------------");
        System.out.println("Destination         Next");
        System.out.println("-----------         ----");

        for (String[] node: nodeList) {
            System.out.println(String.format("%s                 %s", node[0], node[1]));
        }

        System.out.println("------------------------");
    }
}
