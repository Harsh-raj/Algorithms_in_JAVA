package com.company;

public class MSTAlgorithm {
    private int V;

    public MSTAlgorithm(int V){
        this.V = V;
    }

    int minKey(int[] key, Boolean[] mstSet){
        int min = Integer.MAX_VALUE, min_index = -1;

        for(int v=0; v<V; v++)
            if(!mstSet[v] && key[v]<min){
                min = key[v];
                min_index = v;
            }
        return min_index;
    }

    String printMST(int[] parent, int[][] graph){
        StringBuilder s = new StringBuilder("Edge \tWeight\n");
        for(int i=1;i<V;i++) {
            s.append(parent[i]).append(" - ").append(i).append("\t").append(graph[parent[i]][i]).append("\n");
        }
        return s.toString();
    }

    String primMST(int[][] graph){
        int[] parent = new int[V];

        int[] key = new int[V];

        Boolean[] mstSet = new Boolean[V];

        for(int i=0; i<V;i++){
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        key[0] = 0;

        parent[0] = -1;

        for (int count = 0; count < V - 1; count++) {
            // Pick thd minimum key vertex from the set of
            // vertices not yet included in MST
            int u = minKey(key, mstSet);

            // Add the picked vertex to the MST Set
            mstSet[u] = true;

            // Update key value and parent index of the
            // adjacent vertices of the picked vertex.
            // Consider only those vertices which are not
            // yet included in MST
            for (int v = 0; v < V; v++)

                // graph[u][v] is non zero only for adjacent
                // vertices of m mstSet[v] is false for
                // vertices not yet included in MST Update
                // the key only if graph[u][v] is smaller
                // than key[v]
                if (graph[u][v] != 0 && !mstSet[v]
                        && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
        }

        // print the constructed MST
        return printMST(parent, graph);
    }
}
