package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class DfsBfsAlgo {
    private int V;
    private static HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
    private static  LinkedList<Integer> adj[];

    public DfsBfsAlgo(int v){
        V=v;
        adj = new LinkedList[v];
        for(int i=0;i<v;++i) adj[i] = new LinkedList<>();
    }

    public void DFSUtil(int v, boolean visited[], StringBuilder ret){
        visited[v] = true;
        ret.append(v+" ");

        Iterator<Integer> i = adj[v].listIterator();
        while(i.hasNext()){
            int n = i.next();
            if(!visited[n]) DFSUtil(n, visited, ret);
        }
    }

    public String DFS(int v){
        boolean visited[] = new boolean[V];

        StringBuilder ret = new StringBuilder();
        ret.append("\nDFS traversal possible from given source "+ v +" in the graph is:\n");
        DFSUtil(v, visited, ret);
        return ret.toString();
    }
    public void addEdge(int a, int b){
        LinkedList<Integer> l;
        if(graph.containsKey(a)){
            l = graph.get(a);
        }else{
            l = new LinkedList<>();
        }
        l.add(b);
        graph.put(a,l);
        adj[a].add(b);
    }

    public String bfsh(int s, ArrayList<Boolean> visited){
        StringBuilder ret = new StringBuilder();
        LinkedList<Integer> q=new LinkedList<>();

        q.add(s);
        visited.set(s,true);

        while(!q.isEmpty()){
            int f=q.poll();
            ret.append(f).append(" ");

            if(graph.containsKey(f)){

                for (int n : graph.get(f)) {
                    if (!visited.get(n)) {
                        visited.set(n, true);
                        q.add(n);
                    }
                }
            }
        }

        return ret.toString();
    }

    public String bfs(int src){
        ArrayList<Boolean> visited=new ArrayList<>();
        StringBuilder ret = new StringBuilder();
        ret.append("The BFS Traversal for the graph is:\n");
        //Marking each node as unvisited
        for(int i=0;i<V;i++)
        {
            visited.add(i,false);
        }
        for(int i=0;i<V;i++)
        {
            //Checking whether the node is visited or not
            if(!visited.get(i))
            {
                String rt = bfsh(i,visited);
                ret.append(rt);
            }
        }

        ret.append(this.DFS(src));
        return ret.toString();
    }
}
