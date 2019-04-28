/**
 * Blaise Johnson
 * Isaac Griffith
 * CS 3308
 * 4/28/19
 */
package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Edge;
import edu.isu.cs.cs3308.structures.Graph;
import edu.isu.cs.cs3308.structures.Vertex;

import java.util.LinkedList;
import java.util.List;

public class AdjacencyListGraph<V, E> implements Graph<V, E> {

    private LinkedList<Vertex<V>> vertices = new LinkedList<>();
    private LinkedList<Edge<E>> edges = new LinkedList<>();

    /**
     * Returns the number of vertices of the graph
     */
    @Override
    public int numVertices() {
        return vertices.size();
    }

    /**
     * Returns an iteration of all the vertices of the graph
     */
    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertices;
    }

    /**
     * Returns the number of edges of the graph
     */
    @Override
    public int numEdges() {
        return edges.size();
    }

    /**
     * Returns an iteration of all edges of the graph
     */
    @Override
    public Iterable<Edge<E>> edges() {
        return edges;
    }

    /**
     * Returns the edge from vertex u to vertex v, if one exists, otherwise returns null.
     * For an undirected graph, there is no difference between getEdge(u, v) and getEdge(v, u)
     *
     * @param u
     * @param v
     */
    @Override
    public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) {
        int pos = u.getOutgoing().indexOf(v);
        if (pos >= 0) {
            return (Edge<E>) u.getOutgoing().get(pos);
        }
        else return null;
    }

    /**
     * Returns the vertex with the specified element, if one exists, otherwise returns null.
     *
     * @param element
     * @return
     */
    public Vertex<V> getVertex(V element) {
        for (Vertex<V> vertex: vertices) {
            if (vertex.getElement().equals(element)) {
                return vertex;
            }
        }

        return null;
    }

    /**
     * Returns an array containing the two endpoint vertices of edge e. If the graph is directed
     * the first vertex is the origin and the second is the destination.
     *
     * @param e
     */
    @Override
    public Vertex<V>[] endVertices(Edge<E> e) {
        return e.getEndpoints();
    }

    /**
     * For edge e incident to vertex v, returns the other vertex of the edge; an error occurs
     * if e is not incident to v.
     *
     * @param v
     * @param e
     */
    @Override
    public Vertex<V> opposite(Vertex<V> v, Edge<E> e) {
        Vertex<V>[] endPoints = endVertices(e);

        if (v.equals(endPoints[0])) {
            return endPoints[1];
        }
        else if (v.equals(endPoints[1])) {
            return endPoints[0];
        }
        else return null;
    }

    /**
     * Returns the number of outgoing edges from vertex v.
     *
     * @param v
     */
    @Override
    public int outDegree(Vertex<V> v) {
        return v.getOutgoing().size();
    }

    /**
     * Returns the number of incoming edges to vertex v. For an undirected graph, this returns
     * the same value as does outDegree(v)
     *
     * @param v
     */
    @Override
    public int inDegree(Vertex<V> v) {
        return v.getIncoming().size();
    }

    /**
     * Returns an iteration of all outgoing edges from vertex v
     *
     * @param v
     */
    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) {
        return v.getOutgoing();
    }

    /**
     * Returns an iteration of all incoming edges to vertex v. For an undirected graph, this
     * returns the same collection as does outgoingEdges(v)
     *
     * @param v
     */
    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> v) {
        return v.getIncoming();
    }

    /**
     * Creates and returns a new Vertex storing element v
     *
     * @param v
     */
    @Override
    public Vertex<V> insertVertex(V v) {
        Vertex<V> newVertex = new InnerVertex(v);
        vertices.add(newVertex);
        return newVertex;
    }

    /**
     * Creates and returns a new Edge from vertex u to vertex v, storing element e; an error occurs
     * if there already exists an edge from u to v
     *
     * @param u
     * @param v
     * @param e
     */
    @Override
    public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E e) {
        if (getEdge(u, v) == null) {
            InnerEdge newEdge = new InnerEdge(e, u, v);
            edges.add(newEdge);
            return newEdge;
        }
        else return null;
    }

    /**
     * Removes vertex v and all its incident edges from the graph
     *
     * @param v
     */
    @Override
    public V removeVertex(Vertex<V> v) {
        for (Edge<E> e: ((InnerVertex) v).getOutgoing()) {
            removeEdge(e);
        }
        for (Edge<E> e: ((InnerVertex) v).getIncoming()) {
            removeEdge(e);
        }

        vertices.remove(v);
        return v.getElement();
    }

    /**
     * Removes edge e from the graph
     *
     * @param e
     */
    @Override
    public E removeEdge(Edge<E> e) {
        Vertex<V>[] endPoints = e.getEndpoints();

        for (Vertex<V> v: endPoints) {
            if (v.getOutgoing().contains(e)) {
                v.getOutgoing().remove(e);
            }
            else if (v.getIncoming().contains(e)) {
                v.getIncoming().remove(e);
            }
        }

        edges.remove(e);
        return e.getElement();
    }






    //---------------------------------//
    //                                 //
    //       Inner Vertex Class        //
    //                                 //
    //---------------------------------//

    /**
     * Inner Vertex class for Adjacency List Graph
     */
    public class InnerVertex implements Vertex<V> {

        private V element;
        private List<Edge<E>> outgoing = new LinkedList<>();
        private List<Edge<E>> incoming = new LinkedList<>();
        private Vertex<V> previousNode;
        private int cost;
        private boolean isKnown;

        public InnerVertex(V element) {
            this.element = element;
        }

        /**
         * Returns the element associated with this vertex
         */
        @Override
        public V getElement() {
            return element;
        }

        /**
         * Returns the list of outgoing edges of this vertex
         */
        @Override
        public List<Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Adds an outgoing edge to this node's list of outgoing edges.
         * @param edge
         */
        public void addOutgoing(Edge<E> edge) {
            outgoing.add(edge);
        }

        /**
         * Reutrns the list of incoming edges of this vertex
         */
        @Override
        public List<Edge<E>> getIncoming() {
            return incoming;
        }

        /**
         * Adds an incoming edge to this node's list of incoming edges.
         * @param edge
         */
        public void addIncoming(Edge<E> edge) {
            incoming.add(edge);
        }

        @Override
        public String toString() {
            return element.toString().replace(":", "");
        }

        /**
         * Returns this node's previous node in a path.
         * @return
         */
        public Vertex<V> getPreviousNode() {
            return previousNode;
        }

        /**
         * Sets this node's previous node in a path.
         * @param previousNode
         */
        public void setPreviousNode(Vertex<V> previousNode) {
            this.previousNode = previousNode;
        }

        /**
         * Returns this node's cost in a path through a weighted graph.
         * @return
         */
        public int getCost() {
            return cost;
        }

        /**
         * Sets this node's cost in a path through a weighted graph.
         * @param cost
         */
        public void setCost(int cost) {
            this.cost = cost;
        }

        /**
         * Represents whether this node's cost is known, or if it has
         * been visited.
         * @return
         */
        public boolean isKnown() {
            return isKnown;
        }

        /**
         * Sets whether this node's cost is known, or if it has
         * been visited.
         * @param known
         */
        public void setKnown(boolean known) {
            isKnown = known;
        }

        /**
         * Initializes the cost, knowledge, and previous node
         * of this node.
         */
        public void initialize() {
            setCost(Integer.MAX_VALUE);
            setKnown(false);
            setPreviousNode(null);
        }
    }





    //---------------------------------//
    //                                 //
    //        Inner Edge Class         //
    //                                 //
    //---------------------------------//

    /**
     * Inner Edge class for Adjacency List Graph
     */
    public class InnerEdge implements Edge<E> {

        private E element;
        private Vertex<V> from;
        private Vertex<V> to;

        public InnerEdge(E element, Vertex<V> from, Vertex<V> to) {
            this.element = element;
            this.from = from;
            this.to = to;

            ((InnerVertex) from).addOutgoing(this);
            ((InnerVertex) to).addIncoming(this);
        }

        /**
         * Returns the element associated with the edge
         */
        @Override
        public E getElement() {
            return element;
        }

        /**
         * Returns an array of the two endpoints of the edge.
         */
        @Override
        public Vertex<V>[] getEndpoints() {
            Vertex<V>[] endPoints = new Vertex[2];
            endPoints[0] = from;
            endPoints[1] = to;

            return endPoints;
        }
    }
}
