package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class JobSchedulingAlgo {
    // Each job has a unique-id,profit and deadline
    char id;
    int deadline, profit;

    // Constructors
    public JobSchedulingAlgo() {}

    public JobSchedulingAlgo(char id, int deadline, int profit)
    {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }

    // Function to schedule the jobs take 2 arguments
    // arraylist and no of jobs to schedule
    void printJobScheduling(ArrayList<JobSchedulingAlgo> arr, int t)
    {
        // Length of array
        int n = arr.size();

        // Sort all jobs according to decreasing order of
        // profit
        Collections.sort(arr,
                (a, b) -> b.profit - a.profit);

        // To keep track of free time slots
        boolean result[] = new boolean[t];

        // To store result (Sequence of jobs)
        char job[] = new char[t];

        // Iterate through all given jobs
        for (int i = 0; i < n; i++) {
            // Find a free slot for this job (Note that we
            // start from the last possible slot)
            for (int j
                 = Math.min(t - 1, arr.get(i).deadline - 1);
                 j >= 0; j--) {
                // Free slot found
                if (result[j] == false) {
                    result[j] = true;
                    job[j] = arr.get(i).id;
                    break;
                }
            }
        }

        // Print the sequence
        for (char jb : job)
            GreedyWindow.ret.append(jb).append(" ");
//        GreedyWindow.ret.append("\n");
    }
}
