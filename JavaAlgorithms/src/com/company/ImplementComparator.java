package com.company;

import java.util.Comparator;
class ImplementComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode x, HuffmanNode y) {
            return x.item - y.item;
        }
}
