package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraAlgorithm {
    // Member variables of the class
    public int[] distance;
    public int[] parent;
    private Set<Integer> settld;
    private PriorityQueue<Node> pQue;

    // Total count of the vertices
    private int totalNodes;
    List<List<Node>> adjacent;

    // Constructor of the class
    public DijkstraAlgorithm(int totalNodes)
    {

        this.totalNodes = totalNodes;
        distance = new int[totalNodes];
        parent = new int[totalNodes];
        settld = new HashSet<Integer>();
        pQue = new PriorityQueue<Node>(totalNodes, new Node());
    }

    public void dijkstra(List<List<Node> > adjacent, int s)
    {
        this.adjacent = adjacent;

        for (int j = 0; j < totalNodes; j++)
        {
// initializing the distance of every node to infinity (a large number)
            distance[j] = Integer.MAX_VALUE;
            parent[j] = -1;
        }

// Adding the source node to pQue
        pQue.add(new Node(s, 0));

// distance of the source is always zero
        distance[s] = 0;

        while (settld.size() != totalNodes)
        {

// Terminating condition check when
// the priority queue contains zero elements, return
            if (pQue.isEmpty())
            {
                return;
            }

// Deleting the node that has the minimum distance from the priority queue
            int ux = pQue.remove().n;

// Adding the node whose distance is
// confirmed
            if (settld.contains(ux))
            {
                continue;
            }

// We don't have to call eNeighbors(ux)
// if ux is already present in the settled set.
            settld.add(ux);

            eNeighbours(ux);
        }
    }

    private void eNeighbours(int ux)
    {
        int edgeDist;
        int newDist;

// All of the neighbors of vx
        for (int j = 0; j < adjacent.get(ux).size(); j++)
        {
            Node vx = adjacent.get(ux).get(j);

// If the current node hasn't been already processed
            if (!settld.contains(vx.n))
            {
                edgeDist = vx.weight;
                newDist = distance[ux] + edgeDist;

                // If the new distance is lesser in the cost
                if (newDist < distance[vx.n])
                {
                    distance[vx.n] = newDist;
                    parent[vx.n] = ux;
                }

                // Adding the current node to the priority queue pQue
                pQue.add(new Node(vx.n, distance[vx.n]));
            }
        }
    }
}