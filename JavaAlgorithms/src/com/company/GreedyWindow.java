package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GreedyWindow {
    private JLabel n = new JLabel("Various Graph Algorithm Implementation");
    private JTextArea l;
    private JTextArea m;
    private JButton pri;
    private JButton jsh;
    private JButton btn;
    private JButton huf;
    private String s1;
    private JFrame f;
    static StringBuilder ret;

    public GreedyWindow(){
        window();
    }

    public static String invoke(String s1, int option) {
        String[] str = s1.replaceAll("\n", " ").split(" ");
        int[] arr = new int[str.length];
        ret = new StringBuilder();

        switch (option) {
            case 0:
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

                MSTAlgorithm m = new MSTAlgorithm(arr[0]);
                int[][] graph = new int[arr[0]][arr[0]];

                for(int i=0;i<arr[0];i++){
                    for(int j=0;j<arr[0];j++){
                        graph[i][j] = 0;
                    }
                }

                int i=1;

                while(arr[i]!=-1) {
                    graph[arr[i]][arr[i+1]] = arr[i+2];
                    i+=3;
                }

                s1 = m.primMST(graph);
                ret.append(s1);
                break;
            case 1:
                ArrayList<JobSchedulingAlgo>  r = new ArrayList<>();
                i=0;
                while(str[i].compareToIgnoreCase("-1") !=0) {
                    r.add(new JobSchedulingAlgo(str[i].charAt(0), Integer.parseInt(str[i + 1]), Integer.parseInt(str[i + 2])));
                    i+=3;
                }
                ret.append("Following is maximum profit sequence of jobs\n");
                JobSchedulingAlgo jb = new JobSchedulingAlgo();

                jb.printJobScheduling(r, Integer.parseInt(str[str.length-1]));
                break;
            case 2:
                int n = Integer.parseInt(str[0]);
                char[] charArray = new char[n];
                int[] charfreq = new int[n];
                i=0;

                while(str[2*i+1].compareToIgnoreCase("-1") !=0) {
                    charArray[i] = str[(2 * i) + 1].charAt(0);
                    charfreq[i] = Integer.parseInt(str[2*i+2]);
                    i++;
                }

                PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n, new ImplementComparator());

                for(i=0;i<n;i++){
                    HuffmanNode hn = new HuffmanNode();

                    hn.c = charArray[i];
                    hn.item = charfreq[i];

                    hn.left = null;
                    hn.right = null;

                    q.add(hn);
                }

                HuffmanNode root = null;

                while (q.size() > 1) {

                    HuffmanNode x = q.peek();
                    q.poll();

                    HuffmanNode y = q.peek();
                    q.poll();

                    HuffmanNode f = new HuffmanNode();

                    f.item = x.item + y.item;
                    f.c = '-';
                    f.left = x;
                    f.right = y;
                    root = f;

                    q.add(f);
                }
                GreedyWindow.ret.append("Char\t| Huffman code\n------------------------------------------------\n");
                new Huffman().printCode(root, "");
                break;
            default: ret.append("Something went wrong\n");
        }
        return ret.toString();
    }

    public void window(){
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

        pri = new JButton("Prims Algo");
        pri.setBounds(620, 150, 150, 50);
        pri.setEnabled(false);
        pri.addActionListener(e->{
            String r = invoke(s1, 0);
            m.setText(r);
            pri.setEnabled(false);
        });

        jsh = new JButton("Job Sch Algo");
        jsh.setBounds(620, 200, 150, 50);
        jsh.setEnabled(false);
        jsh.addActionListener(e->{
            String r = invoke(s1, 1);
            m.setText(r);
            jsh.setEnabled(false);
        });

        huf = new JButton("Huffman coding");
        huf.setBounds(620, 250, 150, 50);
        huf.setEnabled(false);
        huf.addActionListener(e->{
            String r = invoke(s1, 2);
            m.setText(r);
            huf.setEnabled(false);
        });

        btn = new JButton("create Graph");
        btn.setBounds(620,100,150,50);
        btn.addActionListener(e -> {
            s1 = l.getText();
            pri.setEnabled(true);
            jsh.setEnabled(true);
            huf.setEnabled(true);
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
        f.add(pri);
        f.add(jsh);
        f.add(huf);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
