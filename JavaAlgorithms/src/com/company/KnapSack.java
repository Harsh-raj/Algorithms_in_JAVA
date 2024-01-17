package com.company;

public class KnapSack {
    public int max(int a, int b) {
        return Math.max(a, b);
    }

    // Returns the maximum value that can
    // be put in a knapsack of capacity W
    public void knapSack(int W, int[] wt, int[] val, int n) {
        int i, w;
        int[][] K = new int[n + 1][W + 1];

        // Build table K[][] in bottom up manner
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0;
                else if (wt[i - 1] <= w)
                    K[i][w]
                            = max(val[i - 1]
                                    + K[i - 1][w - wt[i - 1]],
                            K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w];
            }
        }

        int res = K[n][W];
        DynamicWindow.ret.append("Max Profit possible = ").append(res).append("\nFollowing items with their weights are present in the knapsack\n");
        w = W;
        for (i = n; i > 0 && res > 0; i--) {

            // either the result comes from the top
            // (K[i-1][w]) or from (val[i-1] + K[i-1]
            // [w-wt[i-1]]) as in Knapsack table. If
            // it comes from the latter one/ it means
            // the item is included.
            if (res == K[i - 1][w]) {
            }
            else {
                // This item is included.
                DynamicWindow.ret.append("Object ").append(i - 1).append(" of weight ").append(wt[i-1]).append("\n");

                // Since this weight is included its
                // value is deducted
                res = res - val[i - 1];
                w = w - wt[i - 1];
            }
        }
    }
}