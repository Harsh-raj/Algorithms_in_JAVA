package com.company;


public class JohnsonsAlgorithm {
    private int SOURCE_NODE;
    private int numberOfNodes;
    private int augmentedMatrix[][];
    private int potential[];
    private BellmanFord bellmanFord;
    private DijkstraShortesPath dijsktraShortesPath;
    private int[][] allPairShortestPath;

    public static final int MAX_VALUE = 999;

    public JohnsonsAlgorithm(int numberOfNodes) {
        this.numberOfNodes = numberOfNodes;
        augmentedMatrix = new int[numberOfNodes + 2][numberOfNodes + 2];
        SOURCE_NODE = numberOfNodes + 1;
        potential = new int[numberOfNodes + 2];
        bellmanFord = new BellmanFord(numberOfNodes + 1);
        dijsktraShortesPath = new DijkstraShortesPath(numberOfNodes);
        allPairShortestPath = new int[numberOfNodes + 1][numberOfNodes + 1];
    }

    public String johnsonsAlgorithms(int adjacencyMatrix[][]) {
        StringBuilder ret = new StringBuilder();
        computeAugmentedGraph(adjacencyMatrix);

        bellmanFord.BellmanFordEvaluation(SOURCE_NODE, augmentedMatrix);
        potential = bellmanFord.getDistances();

        int reweightedGraph[][] = reweightGraph(adjacencyMatrix);
        for (int i = 1; i <= numberOfNodes; i++) {
            for (int j = 1; j <= numberOfNodes; j++) {
                ret.append(reweightedGraph[i][j]).append("\t");
            }
            ret.append("\n");
        }

        for (int source = 1; source <= numberOfNodes; source++) {
            dijsktraShortesPath.dijkstraShortestPath(source, reweightedGraph);
            int[] result = dijsktraShortesPath.getDistances();
            for (int destination = 1; destination <= numberOfNodes; destination++) {
                allPairShortestPath[source][destination] = result[destination]
                        + potential[destination] - potential[source];
            }
        }

        ret.append("\n");
        for (int i = 1; i <= numberOfNodes; i++) {
            ret.append("\t").append(i);
        }
        ret.append("\n");
        for (int source = 1; source <= numberOfNodes; source++) {
            ret.append(source).append("\t");
            for (int destination = 1; destination <= numberOfNodes; destination++) {
                ret.append(allPairShortestPath[source][destination]).append("\t");
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    private void computeAugmentedGraph(int[][] adjacencyMatrix) {
        for (int source = 1; source <= numberOfNodes; source++) {
            for (int destination = 1; destination <= numberOfNodes; destination++) {
                augmentedMatrix[source][destination] = adjacencyMatrix[source][destination];
            }
        }
        for (int destination = 1; destination <= numberOfNodes; destination++) {
            augmentedMatrix[SOURCE_NODE][destination] = 0;
        }
    }

    private int[][] reweightGraph(int[][] adjacencyMatrix) {
        int[][] result = new int[numberOfNodes + 1][numberOfNodes + 1];
        for (int source = 1; source <= numberOfNodes; source++) {
            for (int destination = 1; destination <= numberOfNodes; destination++) {
                result[source][destination] = adjacencyMatrix[source][destination]
                        + potential[source] - potential[destination];
            }
        }
        return result;
    }
}

class BellmanFord {
    private int distances[];
    private int numberofvertices;

    public static final int MAX_VALUE = 999;

    public BellmanFord(int numberofvertices) {
        this.numberofvertices = numberofvertices;
        distances = new int[numberofvertices + 1];
    }

    public void BellmanFordEvaluation(int source, int adjacencymatrix[][]) {
        for (int node = 1; node <= numberofvertices; node++) {
            distances[node] = MAX_VALUE;
        }

        distances[source] = 0;

        for (int node = 1; node <= numberofvertices - 1; node++) {
            for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++) {
                for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++) {
                    if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                        if (distances[destinationnode] > distances[sourcenode]
                                + adjacencymatrix[sourcenode][destinationnode]) {
                            distances[destinationnode] = distances[sourcenode]
                                    + adjacencymatrix[sourcenode][destinationnode];
                        }
                    }
                }
            }
        }

        for (int sourcenode = 1; sourcenode <= numberofvertices; sourcenode++) {
            for (int destinationnode = 1; destinationnode <= numberofvertices; destinationnode++) {
                if (adjacencymatrix[sourcenode][destinationnode] != MAX_VALUE) {
                    if (distances[destinationnode] > distances[sourcenode]
                            + adjacencymatrix[sourcenode][destinationnode])
                        System.out.println("The Graph contains negative egde cycle");
                }
            }
        }
    }

    public int[] getDistances() {
        return distances;
    }
}

class DijkstraShortesPath {
    private boolean settled[];
    private boolean unsettled[];
    private int distances[];
    private int adjacencymatrix[][];
    private int numberofvertices;

    public static final int MAX_VALUE = 999;

    public DijkstraShortesPath(int numberofvertices) {
        this.numberofvertices = numberofvertices;
    }

    public void dijkstraShortestPath(int source, int adjacencymatrix[][]) {
        this.settled = new boolean[numberofvertices + 1];
        this.unsettled = new boolean[numberofvertices + 1];
        this.distances = new int[numberofvertices + 1];
        this.adjacencymatrix = new int[numberofvertices + 1][numberofvertices + 1];

        int evaluationnode;
        for (int vertex = 1; vertex <= numberofvertices; vertex++) {
            distances[vertex] = MAX_VALUE;
        }

        for (int sourcevertex = 1; sourcevertex <= numberofvertices; sourcevertex++) {
            for (int destinationvertex = 1; destinationvertex <= numberofvertices; destinationvertex++) {
                this.adjacencymatrix[sourcevertex][destinationvertex]
                        = adjacencymatrix[sourcevertex][destinationvertex];
            }
        }

        unsettled[source] = true;
        distances[source] = 0;
        while (getUnsettledCount(unsettled) != 0) {
            evaluationnode = getNodeWithMinimumDistanceFromUnsettled(unsettled);
            unsettled[evaluationnode] = false;
            settled[evaluationnode] = true;
            evaluateNeighbours(evaluationnode);
        }
    }

    public int getUnsettledCount(boolean unsettled[]) {
        int count = 0;
        for (int vertex = 1; vertex <= numberofvertices; vertex++) {
            if (unsettled[vertex] == true) {
                count++;
            }
        }
        return count;
    }

    public int getNodeWithMinimumDistanceFromUnsettled(boolean unsettled[]) {
        int min = MAX_VALUE;
        int node = 0;
        for (int vertex = 1; vertex <= numberofvertices; vertex++) {
            if (unsettled[vertex] == true && distances[vertex] < min) {
                node = vertex;
                min = distances[vertex];
            }
        }
        return node;
    }

    public void evaluateNeighbours(int evaluationNode) {
        int edgeDistance = -1;
        int newDistance = -1;

        for (int destinationNode = 1; destinationNode <= numberofvertices; destinationNode++) {
            if (settled[destinationNode] == false) {
                if (adjacencymatrix[evaluationNode][destinationNode] != MAX_VALUE) {
                    edgeDistance = adjacencymatrix[evaluationNode][destinationNode];
                    newDistance = distances[evaluationNode] + edgeDistance;
                    if (newDistance < distances[destinationNode]) {
                        distances[destinationNode] = newDistance;
                    }
                    unsettled[destinationNode] = true;
                }
            }
        }
    }

    public int[] getDistances() {
        return distances;
    }
}
