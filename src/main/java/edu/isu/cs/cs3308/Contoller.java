package edu.isu.cs.cs3308;

import edu.isu.cs.cs3308.structures.impl.AdjacencyListGraph;

import java.util.Scanner;

public class Contoller {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String filePath = View.greet(scanner);
        int selection = 0;

        while (selection != 4) {
            selection = View.printMenu(scanner);
        }


    }
}
