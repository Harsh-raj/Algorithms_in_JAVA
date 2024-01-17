package com.company;

import java.util.Comparator;

public class Node implements Comparator<Node> {
    // Member variables of the Node class
    public int n;
    public int weight;

// Constructors of this class

    // Constructor 1
    public Node()
    {

    }

    // Constructor 2
    public Node(int n, int weight)
    {
        this.n = n;
        this.weight = weight;
    }

    @Override
    public int compare(Node n1, Node n2) {

        if (n1.weight < n2.weight) {
            return -1;
        }
        if (n1.weight > n2.weight) {
            return 1;
        }

        return 0;
    }

}
