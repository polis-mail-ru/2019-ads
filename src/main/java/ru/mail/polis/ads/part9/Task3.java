package ru.mail.polis.ads.part9;

import java.util.Arrays;
import java.util.Scanner;

public class Task3 {

    public static final int MAX = 30000;
    private static class Edge {
        int from;
        int to;
        int weight;

        private Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Edge[] edges = new Edge[m];

        for (int i = 0; i < m; ++i) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int dist = sc.nextInt();
            edges[i] = new Edge(from, to, dist);
        }

        int[] paths = new int[n + 1];
        Arrays.fill(paths, MAX);
        paths[1] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (paths[edges[j].from] < MAX) {
                    paths[edges[j].to] = Math.min(paths[edges[j].to], paths[edges[j].from] + edges[j].weight);
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            System.out.print(paths[i] + " ");
        }
    }

    public static void main(String[] args) {
        solve();
    }

}

