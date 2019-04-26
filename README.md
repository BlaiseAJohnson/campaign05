# Campaign 5 - Graphs (Due April 28, 2019)

## Introduction
This is the final assignment for the course. The goals of this assignment is to provide you with the opportunity to implement and experiment with graph data structures, and to gain experience in the implementation of graph algorithms.

**Note that there are no tests** for this project.

## Problem Statement

### Part 1

Write a program that builds the routing tables for the nodes in a computer network, based on shortest-path routing, where path distance is measured by hop count, that is, the number of edges in a path. The input for this problem is the connectivity information for all the nodes in the network, as in the following example:

`241.12.31.14: 241.12.31.15 241.12.31.18 241.12.31.19`

which indicates three network nodes that are connected to 241.12.31.14, that is three nodes that are one hop away. The routing table for the node at address A is a set of pairs (B, C), which indicates that, to route a message from A to B, the next node to send to (on the shortest path from A to B) is C. Your program should output the routing table for each node in the network, given an input list of node connectivity lists, each of which is input in the syntax as shown above, one per line.

### Part 2

Modify your program to also find the shortest-path routing, where path distance is measured by total latency across the links between nodes. In this case each each between nodes in the network contains a value representing the latency in bits/second across that link. Input files connectivity information in these networks is represented as follows:

`241.12.31.14: 241.12.31.15(100) 241.12.31.18(75) 241.12.31.19(115)`

which indicates three network nodes that are connected to 241.12.31.14 each with latencies 100 bits/s, 75 bits/s, and 115 bits/s, respectively. In the case the some nodes in the connection list do not provide latency values you should assume a default value of 250 bits/sec

## Expected Program Output

Below is an example of output from the program, use this to help define the operation of your program.

```
Welcome to Network Path Analyzer

Enter A Network Description File Name: data/test.graph

Menu
----
0. Print The Current Network Configuration
1. View Routing Table for a Node
2. Find Shortest Path from One Node to Another
3. Exit

Selection: 0

Current Network Configuration:
-----------------------------
174.29.143.186 -> 14.14.121.219
174.29.143.186 -> 43.207.233.165
174.29.143.186 -> 154.199.110.242
174.29.143.186 -> 110.160.161.99
110.160.161.99 -> 174.29.143.186
110.160.161.99 -> 43.207.233.165
110.160.161.99 -> 14.14.121.219
110.160.161.99 -> 154.199.110.242
43.207.233.165 -> 154.199.110.242
43.207.233.165 -> 14.14.121.219
43.207.233.165 -> 174.29.143.186
43.207.233.165 -> 110.160.161.99
154.199.110.242 -> 43.207.233.165
154.199.110.242 -> 174.29.143.186
14.14.121.219 -> 43.207.233.165
14.14.121.219 -> 174.29.143.186
14.14.121.219 -> 110.160.161.99
14.14.121.219 -> 154.199.110.242

-----------------------------

Menu
----
0. Print The Current Network Configuration
1. View Routing Table for a Node
2. Find Shortest Path from One Node to Another
3. Exit

Selection: 1

Node to view: 154.199.110.242

154.199.110.242 Routing Table
--------------------------
Destination     Next
--------------  --------------
174.29.143.186  174.29.143.186
110.160.161.99  43.207.233.165
43.207.233.165  43.207.233.165
14.14.121.219   43.207.233.165

Menu
----  
0. Print The Current Network Configuration
1. View Routing Table for a Node
2. Find Shortest Path from One Node to Another
3. Exit

Selection: 1

Node to view: 241.12.31

Error: 241.12.31 is not a valid Node!

Menu
----
0. Print The Current Network Configuration
1. View Routing Table for a Node
2. Find Shortest Path from One Node to Another
3. Exit

Selection: 2

Start Node: 154.199.110.242
End Node: 14.14.121.219

Path:
  154.199.110.242 ->
  43.207.233.165 ->
  14.14.121.219

Menu
----
0. Print The Current Network Configuration
1. View Routing Table for a Node
2. Find Shortest Path from One Node to Another
3. Exit

Selection: 2

Start Node: 241.12.31.14
End Node: 241.12.31

Error: 241.12.31 is not a valid Node!

Menu
----
0. Print The Current Network Configuration
1. View Routing Table for a Node
2. Find Shortest Path from One Node to Another
3. Exit

Selection: 3

Goodbye!
```

## Additional Information

I have provided a Graph ADT and interfaces for the Vertex and Edges in the package named: `edu.isu.cs.cs3308.structures`


The folder `data` contains sample graph data files for use in testing your program. Additionally, there is a ruby file in `src/main/ruby` called `file_gen.rb` which is capable of generating additional data files.

## Submission
When you have completed the assignment (all tests pass) or it is reaching midnight of the due date, make sure you have completed the following:

1. Committed all changes to your repo
2. Pushed your changes to GitHub
3. Tagged your repo as "COMPLETE"
4. Pushed the "COMPLETE" tag to GitHub

## Grading (50 Points)

* 20 Points - Graph data structures implemented correctly.
* 20 Points - Graph algorithms implemented correctly.
* 10 Points - Coding conventions and documentation.

## Hints

* I would suggest utilizing the provide Graph ADT interface as the basis of a isWeighted and unweighted graph implementation using an Adjacency List based approach. Though you are more than welcome to use an Adjacency Matrix based approach as well.

* I would suggest creating a class which processes the input file using a Java NIO operations and `Files.readAllLines()` method to extract a List<String> of all lines in the input file. From this I would then parse each line and update the graph. Creating either a isWeighted or unweighted graph as necessary.

* The nodes of the graph should probably be some sort of NetworkComponent which is capable of storing a Map relating a label to a value (i.e., java.util.Map implementation such as java.util.HashMap).

If you need any additional clarification please seek guidance via Discord, at my office hours, or by appointment via youcanbook.me
