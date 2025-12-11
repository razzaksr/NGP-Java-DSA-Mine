package graph;

import java.util.*;

public class FindTownJudge {
    public static int findJudge(int n, int[][] trust) {
        int[] score = new int[n + 1]; // people labeled 1..n

        for (int[] relation : trust) {
            int a = relation[0];
            int b = relation[1];
            score[a]--;   // a trusts someone → not judge
            score[b]++;   // b is trusted → potential judge
        }

        for (int i = 1; i <= n; i++) {
            if (score[i] == n - 1) {
                return i; // judge found
            }
        }
        return -1; // no judge
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // number of people
        int m = sc.nextInt(); // number of trust relationships
        int[][] trust = new int[m][2];

        for (int i = 0; i < m; i++) {
            trust[i][0] = sc.nextInt();
            trust[i][1] = sc.nextInt();
        }

        System.out.println(findJudge(n, trust));
        sc.close();
    }
}