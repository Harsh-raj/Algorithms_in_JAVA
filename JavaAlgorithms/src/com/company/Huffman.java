package com.company;

public class Huffman {
    public void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {

            GreedyWindow.ret.append(root.c).append("\t|  ").append(s).append("\n");

            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
}
