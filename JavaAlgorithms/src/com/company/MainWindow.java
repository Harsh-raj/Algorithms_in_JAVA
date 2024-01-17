package com.company;

import javax.swing.*;

public class MainWindow {
    private JLabel n;
    private JButton btn;
    private JRadioButton rbtn1;
    private JRadioButton rbtn2;
    private JRadioButton rbtn3;
    private JRadioButton rbtn4;
    private ButtonGroup bg;
    private JFrame f;

    public MainWindow(){ window();}


    public void window(){
        n = new JLabel("Algorithms demo");
        n.setBounds(50, 10, 500, 50);

        btn = new JButton("OK");
        btn.setBounds(620,200,150,50);
        btn.setEnabled(false);

        rbtn1 = new JRadioButton("Graph Algorithms");
        rbtn1.setBounds(120, 130, 80, 50);
        rbtn1.addActionListener(e -> {
               btn.setEnabled(true);
               n.setText("Graph Algorithms");
        });
        rbtn2 = new JRadioButton("Dynamic Programming");
        rbtn2.setBounds(250, 130, 80, 50);
        rbtn2.addActionListener(e -> {
            btn.setEnabled(true);
            n.setText("Dynamic Programming");
        });
        rbtn3 = new JRadioButton("Greedy Algorithm");
        rbtn3.setBounds(380, 130, 80, 50);
        rbtn3.addActionListener(e -> {
            btn.setEnabled(true);
            n.setText("Greedy Algorithm");
        });
        rbtn4 = new JRadioButton("Others");
        rbtn4.setBounds(510, 130, 80, 50);
        rbtn4.addActionListener(e -> {
            btn.setEnabled(true);
            n.setText("Others");
        });

        bg = new ButtonGroup();
        bg.add(rbtn1);
        bg.add(rbtn2);
        bg.add(rbtn3);
        bg.add(rbtn4);

        btn.addActionListener(e -> {
            int opt;

            if(rbtn1.isSelected()) {
                opt = 1;
                n.setText("Graph Algorthms selected");
            }else if(rbtn2.isSelected()) {
                opt = 2;
                n.setText("Dynamic Programming Algorthms selected");
            }else if(rbtn3.isSelected()) {
                opt = 3;
                n.setText("Greedy Algorthms selected");
            }else {
                opt = 4;
                n.setText("Other algorithms selected");
            }
            switch (opt) {
                case 1 -> new GraphWindow();
                case 2 -> new DynamicWindow();
                case 3 -> new GreedyWindow();
                case 4 -> new OtherAlgo();
            }
        });

        f = new JFrame("Algorithms");
        f.setLayout(null);
        f.setSize(800, 600);
        f.setVisible(true);
        f.add(n);

        f.add(rbtn1);
        f.add(rbtn2);
        f.add(rbtn3);
        f.add(rbtn4);

        f.add(btn);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}