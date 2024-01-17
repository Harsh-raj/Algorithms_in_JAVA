package com.company;

import java.util.Arrays;
import java.util.Comparator;

public class WeightedIntervalScheduling {

    static class Job{
        int start, finish, profit;
        public Job(int start, int finish, int profit){
            this.start = start;
            this.finish = finish;
            this.profit = profit;
        }
    }

    class JobComparator implements Comparator<Job>{

        @Override
        public int compare(Job o1, Job o2) {
            return Integer.compare(o1.finish, o2.finish);
        }
    }

    public int binarySearch(Job jobs[], int index){
        int lo = 0, hi = index - 1;

        // Perform binary Search iteratively
        while (lo <= hi)
        {
            int mid = (lo + hi) / 2;
            if (jobs[mid].finish <= jobs[index].start)
            {
                if (jobs[mid + 1].finish <= jobs[index].start)
                    lo = mid + 1;
                else
                    return mid;
            }
            else
                hi = mid - 1;
        }

        return -1;
    }
    public int schedule(Job[] jobs){
        // Sort jobs according to finish time
        Arrays.sort(jobs, new JobComparator());

        // Create an array to store solutions of subproblems.
        // table[i] stores the profit for jobs till jobs[i]
        // (including jobs[i])
        int n = jobs.length;
        int table[] = new int[n];
        table[0] = jobs[0].profit;

        // Fill entries in M[] using recursive property
        for (int i=1; i<n; i++)
        {
            // Find profit including the current job
            int inclProf = jobs[i].profit;
            int l = binarySearch(jobs, i);
            if (l != -1) {
                inclProf += table[l];
            }

            // Store maximum of including and excluding
            table[i] = Math.max(inclProf, table[i-1]);
        }



//        int res = table[n-1];
//        int time = 0;
//        for (int i = n; i > 0 && res > 0; i--) {
//
//            if (res == table[i - 1] && time <= jobs[n-1].finish) {
//            }
//            else {
//                DynamicWindow.ret.append("Object ").append(i - 1).append(" of weight ").append(jobs[i-1].profit).append("\n");
//                res = res - jobs[i - 1].profit;
//                time = time - (jobs[i - 1].finish - jobs[i - 1].start);
//            }
//        }

        return table[n-1];
    }
}
