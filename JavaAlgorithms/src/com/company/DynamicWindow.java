package com.company;

import javax.swing.*;

public class DynamicWindow {
    private JLabel n;
    private JTextArea l;
    private JTextArea m;
    private JButton btn;
    private JButton mcm;
    private JButton knp;
    private JButton sos;
    private JButton unb;
    private JButton lcs;
    private JButton wjs;
    private String s1;
    private JFrame f;
    public static StringBuilder ret;

    public DynamicWindow(){
        window();
    }

    public static String invoke(String s1, int option) {
        String[] str = s1.replaceAll("\n", " ").split(" ");
        int[] arr = new int[str.length];
        ret = new StringBuilder();

        for (int i = 0; i < str.length; i++) {
            if(option!=4) arr[i] = Integer.parseInt(str[i]);
            else arr[i] = 0;
        }

        int in1 = 0;
        String s;
        int []val;
        int []wt;

        switch (option) {
            case 0: MatrixChainMultiply m = new MatrixChainMultiply();
                int [] matrix = new int[str.length];

                while (in1 < str.length) {
                    matrix[in1] = arr[in1];
                    ++in1;
                }

                m.matrixChainOrder(matrix, str.length);
                break;
            case 1: KnapSack k = new KnapSack();
                val = new int[(str.length-1)/2];
                wt = new int[(str.length-1)/2];

                while (in1 < (str.length-1)/2) {
                    val[in1] = arr[2*in1];
                    wt[in1] = arr[2*in1+1];
                    ++in1;
                }
                k.knapSack(arr[arr.length-1], wt, val, (str.length-1)/2);
                break;
            case 2: SubsetSumAlgo a = new SubsetSumAlgo();
                int sum = arr[arr.length-1];
                s = a.printAllSubsets(arr, arr.length-1, sum);
                ret.append(s);
                break;
            case 3: UnboundedKP ukp = new UnboundedKP();
                val = new int[(arr.length-1)/2];
                wt = new int[(arr.length-1)/2];
                while (in1 < (str.length-1)/2) {
                    val[in1] = arr[2*in1];
                    wt[in1] = arr[2*in1+1];
                    ++in1;
                }
                int g = ukp.unboundedKnapsack(arr[arr.length-1], (arr.length-1)/2, val, wt);
                s = String.valueOf(g);
                ret.append("The max value possible here = ").append(s);
                break;
            case 4:LCSAlgo lc = new LCSAlgo();
                char [] X = str[0].toCharArray();
                char [] Y = str[1].toCharArray();
                int o = X.length;
                int p = Y.length;
                ret.append("Length of LCS is ").append(lc.lcs(X,Y,o,p));
                break;
            case 5:
                WeightedIntervalScheduling w = new WeightedIntervalScheduling();
                int c = arr[0];
                in1 = 0;
                WeightedIntervalScheduling.Job[] ar = new WeightedIntervalScheduling.Job[c];
                while(in1 < (arr.length-1)/3){
                    ar[in1] = new WeightedIntervalScheduling.Job(arr[3*in1+1], arr[3*in1+2], arr[3*in1+3]);
                    ++in1;
                }
                int d = w.schedule(ar);
                ret.append("The optimal profit is ").append(d);
                break;
            default:
                ret.append("Something went wrong\n");
                break;
        }
        return ret.toString();
    }

    public void window() {
        n = new JLabel("Algorithms implementing Dynamic programming");
        n.setBounds(50, 10, 500, 50);

        l = new JTextArea("Your input here:");
        l.setBounds(50, 100, 200, 400);
        l.setEditable(true);
        JScrollPane scroll = new JScrollPane ( l );
        scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );

        m = new JTextArea("Output for your Algorithm will be shown here");
        m.setBounds(300, 100, 300, 400);
        m.setEditable(false);
        JScrollPane scroll1 = new JScrollPane ( m );
        scroll1.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll1.setHorizontalScrollBarPolicy ( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED );

        mcm = new JButton("run min-prd-Matrix Algo");
        mcm.setBounds(620,150,150,50);
        mcm.setEnabled(false);
        mcm.addActionListener(e -> {
            String r = invoke(s1,0);
            m.setText(r);
            mcm.setEnabled(false);
        });

        knp = new JButton("run 0/1 b-K-Sack Algo");
        knp.setBounds(620,200,150,50);
        knp.setEnabled(false);
        knp.addActionListener(e -> {
            String r = invoke(s1,1);
            m.setText(r);
            knp.setEnabled(false);
            //l.setText("");
        });

        sos = new JButton("run subset of sum Algo");
        sos.setBounds(620, 250, 150, 50);
        sos.setEnabled(false);
        sos.addActionListener(e->{
            String r = invoke(s1, 2);
            m.setText(r);
            sos.setEnabled(false);
        });

        unb = new JButton("run unbounded KP Algo");
        unb.setBounds(620, 300, 150, 50);
        unb.setEnabled(false);
        unb.addActionListener(e->{
            String r = invoke(s1, 3);
            m.setText(r);
            unb.setEnabled(false);
        });

        lcs = new JButton("run LCS Algo");
        lcs.setBounds(620,350,150,50);
        lcs.setEnabled(false);
        lcs.addActionListener(e->{
            String r = invoke(s1, 4);
            m.setText(r);
            lcs.setEnabled(false);
        });

        wjs = new JButton("run W-job Scheduling");
        wjs.setBounds(620, 400, 150, 50);
        wjs.setEnabled(false);
        wjs.addActionListener(e->{
            String r = invoke(s1, 5);
            m.setText(r);
            wjs.setEnabled(false);
        });

        btn = new JButton("process input");
        btn.setBounds(620,100,150,50);
        btn.addActionListener(e -> {
            s1 = l.getText();
            mcm.setEnabled(true);
            knp.setEnabled(true);
            sos.setEnabled(true);
            unb.setEnabled(true);
            lcs.setEnabled(true);
            wjs.setEnabled(true);
        });

        scroll.setBounds(50, 100, 200, 400);
        scroll1.setBounds(300, 100, 300, 400);

        f = new JFrame("Dynamic programming Algorithms");
        f.setLayout(null);
        f.setSize(800, 600);
        f.setVisible(true);
        f.add(n);
        f.add(scroll);
        f.add(scroll1);
        f.add(btn);
        f.add(mcm);
        f.add(knp);
        f.add(sos);
        f.add(unb);
        f.add(lcs);
        f.add(wjs);

        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}