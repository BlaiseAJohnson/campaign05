package edu.isu.cs.cs3308;

import java.util.List;
import java.util.Scanner;

public class View {

    public static String greet(Scanner input) {
        System.out.println();
        System.out.println("Hello. Welcome to Skynet Network Path Analyzer\n");
        System.out.print("Please enter a network description file name: \n");

        return input.next();
    }

    public static String printMenu(Scanner input) {
        System.out.println(
                "\n" +
                "Menu\n" +
                "----\n" +
                "0. Print Current Network Config\n" +
                "1. View Routing Table for Node\n" +
                "2. Find Shortest Path Between Two Nodes\n" +
                "3. Find Sarah Conner\n" +
                "4. Exit\n");

        System.out.print("Selection: ");

        return input.next();
    }

    public static void printConfig(List<String[]> routes) {
        System.out.println("Current Network Config:");
        System.out.println("-----------------------");

        for (String[] route: routes) {
            System.out.println(String.format("%s -> %s", route[0], route[1]));
        }

        System.out.println("-----------------------\n");
    }

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

    public static void printShortestPath(List<String[]> routeList) {
        System.out.println("Path:");
        System.out.println("-----");

        for (String[] route: routeList) {
            System.out.println(String.format("   %s -> %s", route[1], route[0]));
        }

        System.out.println("\n");
    }

    public static String routingTableNode(Scanner input) {
        System.out.print("Node to view: ");
        return input.next();
    }

    public static void printRoutingTable(List<String[]> nodeList) {
        System.out.println("------------------------");
        System.out.println("Destination         Next");
        System.out.println("-----------         ----");

        for (String[] node: nodeList) {
            System.out.println(String.format("%s                  %s", node[0], node[1]));
        }

        System.out.println("------------------------");
    }
}
