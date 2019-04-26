package edu.isu.cs.cs3308;

import java.util.Scanner;

public class View {

    public static String greet(Scanner input) {
        System.out.println();
        System.out.println("Hello. Welcome to Skynet Network Path Analyzer\n");
        System.out.print("Please enter a network description file name: \n");

        return input.next();
    }

    public static int printMenu(Scanner input) {
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

        return input.nextInt();
    }
}
