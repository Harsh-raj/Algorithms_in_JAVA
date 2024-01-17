package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class GraphWindow {
    private JLabel n = new JLabel("Various Graph Algorithm Implementation");
    private JTextArea l;
    private JTextArea m;
    private JButton btn;
    private JButton djk;
    private JButton jhn;
    private JButton ffk;
    private JButton top;
    private JButton cyc;
    private JButton dbs;
    private String s1;
    private JFrame f;

    public static String invoke_d_j_f(String s1, int option) {
        String[] str = s1.replaceAll("\n", " ").split(" ");
        int[] arr = new int[str.length];
        String ret = null;

        for (int i = 0; i < str.length; i++) {
            arr[i] = Integer.parseInt(str[i]);
        }

        List<List<Node>> adjacent = new ArrayList<>();

        for (int i = 0; i < arr[0]; i++) {
            List<Node> itm = new ArrayList<>();
            adjacent.add(itm);
        }

        int in1 = 1;
        while (arr[in1] != -1) {
            adjacent.get(arr[in1]).add(new Node(arr[in1 + 1], arr[in1 + 2]));
            in1 += 3;
        }

        switch (option) {
            case 0:
                DijkstraAlgorithm obj = new DijkstraAlgorithm(arr[0]);
                obj.dijkstra(adjacent, arr[arr.length - 1]);

                ret = "The shortest path from the node :\n";

                for (int j = 0; j < obj.distance.length; j++){
                    if (obj.distance[j] == 2147483647)
                        ret = ret.concat(arr[arr.length - 1] + " to " + j + " is INF\n");
                    else
                        ret = ret.concat(arr[arr.length - 1] + " to " + j + " is " + obj.distance[j] + "\n");
                }

                int k=0;

                for ( int a: obj.parent
                     ) {
                    ret = ret.concat(k++ + "->"+a+" ");
                }
                break;
            case 1:
                int number_of_vertices = arr[0];
                int[][] adjacency_matrix = new int[number_of_vertices+1][number_of_vertices+1];

                for(int i=1;i<=number_of_vertices;i++) {
                    for(int j=1;j<=number_of_vertices;j++) {
                        if(i!=j) adjacency_matrix[i][j] = JohnsonsAlgorithm.MAX_VALUE;
                        else adjacency_matrix[i][j] = 0;
                    }
                }

                int i=1;

                while(arr[i]!=-1) {
                    adjacency_matrix[arr[i]+1][arr[i+1]+1] = arr[i+2];
                    i+=3;
                }

                try{
                    JohnsonsAlgorithm johnsonsAlgorithm = new JohnsonsAlgorithm(number_of_vertices);
                    ret = johnsonsAlgorithm.johnsonsAlgorithms(adjacency_matrix);
                } catch (InputMismatchException inputMismatch) {
                    ret = "Wrong Input Format";
                }
                break;
            case 2:
                int num_of_ver = arr[0];
                int[][] adj_mat = new int[num_of_ver][num_of_ver];

                for(int j=0;j<num_of_ver;j++) {
                    for(int l=0;l<num_of_ver;l++) {
                        adj_mat[j][l] = MaxFlow.MIN_FLOW;
                    }
                }

                int j=1;

                while(arr[j]!=-1) {
                    adj_mat[arr[j]][arr[j+1]] = arr[j+2];
                    j+=3;
                }

                try {
                    MaxFlow m = new MaxFlow();
                    ret =  m.MaxFlow_Res(adj_mat, arr);

                }catch (InputMismatchException e){ ret = "wrong input format\n"; }

                break;
            case 3: DetectCycle d = new DetectCycle(arr[0]);
                int e = 1;

                while(arr[e]!=-1){
                    d.addEdge(arr[e++], arr[e++]);
                    e++;
                }


                if(!d.isCyclic()) {
                    TopSortAlgorithm t = new TopSortAlgorithm(arr[0]);
                    e = 1;

                    while (arr[e] != -1) {
                        t.addEdge(arr[e++], arr[e++]);
                        e++;
                    }

                    ret = "Following is all possible Topological sorts\n" + t.allTopologicalSorts();
                }else ret = "Graph contains cycle\n";
                break;
            case 4: DetectCycle d1 = new DetectCycle(arr[0]);
                e = 1;

                while(arr[e]!=-1){
                    d1.addEdge(arr[e++], arr[e++]);
                    e++;
                }
                if(d1.isCyclic())
                    ret = "Graph contains cycle\n";
                else
                    ret = "Graph doesn't contain cycle\n";
                break;
            case 5:
                int v=arr[0];
                DfsBfsAlgo df = new DfsBfsAlgo(v);
                i=1;
                while(arr[i]!=-1) {
                    df.addEdge(arr[i++], arr[i++]);
                    i++;
                }
                ret = df.bfs(arr[arr.length-1]);
                break;
            default: ret = "Something went wrong\n";
        }
        return ret;
    }

    public GraphWindow(){
        window();
    }

    public void window() {
        n.setBounds(50, 10, 500, 50);

        l = new JTextArea("Your input here:\nFormat: <no of vertices>\n<source destination weight>\n<-1 to end the edge input>\n<source vertex>");
        l.setBounds(50, 100, 200, 400);
        l.setEditable(true);
        JScrollPane scroll = new JScrollPane ( l );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        m = new JTextArea("Output for your graph will be shown here");
        m.setBounds(300, 100, 300, 400);
        m.setEditable(false);
        JScrollPane scroll1 = new JScrollPane ( m );
        scroll1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll1.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );

        djk = new JButton("run Dijkstra Algo");
        djk.setBounds(620,150,150,50);
        djk.setEnabled(false);
        djk.addActionListener(e -> {
            String r = invoke_d_j_f(s1,0);
            m.setText(r);
            djk.setEnabled(false);
            l.setText("");
        });

        jhn = new JButton("run Johnson Algo");
        jhn.setBounds(620,200,150,50);
        jhn.setEnabled(false);
        jhn.addActionListener(e -> {
            String r = invoke_d_j_f(s1,1);
            m.setText(r);
            jhn.setEnabled(false);
            //l.setText("");
        });

        ffk = new JButton("run FordFlk Algo");
        ffk.setBounds(620, 250, 150, 50);
        ffk.setEnabled(false);
        ffk.addActionListener(e -> {
            String r = invoke_d_j_f(s1, 2);
            m.setText(r);
            ffk.setEnabled(false);
        });

        top = new JButton("run AllTopSort Algo");
        top.setBounds(620, 300, 150, 50);
        top.setEnabled(false);
        top.addActionListener(e -> {
            String r = invoke_d_j_f(s1, 3);
            m.setText(r);
            top.setEnabled(false);
        });

        cyc = new JButton("detect Cycle Algo");
        cyc.setBounds(620, 350, 150, 50);
        cyc.setEnabled(false);
        cyc.addActionListener(e ->{
            String r = invoke_d_j_f(s1, 4);
            m.setText(r);
            cyc.setEnabled(false);
        });

        dbs = new JButton("DFS/BFS Algo");
        dbs.setBounds(620, 400, 150, 50);
        dbs.setEnabled(false);
        dbs.addActionListener(e->{
            String r = invoke_d_j_f(s1, 5);
            m.setText(r);
            dbs.setEnabled(false);
        });

        btn = new JButton("create Graph");
        btn.setBounds(620,100,150,50);
        btn.addActionListener(e -> {
            s1 = l.getText();
            djk.setEnabled(true);
            jhn.setEnabled(true);
            ffk.setEnabled(true);
            top.setEnabled(true);
            cyc.setEnabled(true);
            dbs.setEnabled(true);
        });

        scroll.setBounds(50, 100, 200, 400);
        scroll1.setBounds(300, 100, 300, 400);

        f = new JFrame("Graph Algorithms");
        f.setLayout(null);
        f.setSize(800, 600);
        f.setVisible(true);
        f.add(n);
        f.add(scroll);
        f.add(scroll1);
        f.add(btn);
        f.add(djk);
        f.add(jhn);
        f.add(ffk);
        f.add(top);
        f.add(cyc);
        f.add(dbs);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}