package com.company;

import javax.swing.*;

public class OtherAlgo {
    private JLabel n = new JLabel("Various Graph Algorithm Implementation");
    private JTextArea l;
    private JTextArea m;
    private JButton gsh;
    private JButton jsh;
    private JButton btn;
    private JButton huf;
    private String s1;
    private JFrame f;
    static StringBuilder ret;

    public OtherAlgo(){
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

                int [][] prefer = new int[arr[0]][arr[1]];

                int in1 = 0;
                while (arr[4*in1+2] != -1) {
                    prefer[in1] = new int[]{arr[4*in1 + 2], arr[4*in1 + 3], arr[4*in1 + 4], arr[4*in1 + 5]};
                    in1++;
                }

                GayleShapley g = new GayleShapley(arr[1]);

                g.stableMarriage(prefer);
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

        gsh = new JButton("Gayle-Shaply Algo");
        gsh.setBounds(620, 150, 150, 50);
        gsh.setEnabled(false);
        gsh.addActionListener(e->{
            String r = invoke(s1, 0);
            m.setText(r);
            gsh.setEnabled(false);
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
            gsh.setEnabled(true);
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
        f.add(gsh);
        f.add(jsh);
        f.add(huf);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
