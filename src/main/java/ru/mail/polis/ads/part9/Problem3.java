package ru.mail.polis.ads.part9;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1453
 * submission - https://www.e-olymp.com/ru/submissions/6245782
 */
public class Problem3 {

    private static final int MAX = 30000;

    private static class Edge {
        private int from;
        private int to;
        private int dist;

        private Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    private static void solve() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();

        Edge[] edges = new Edge[m];
        for (int i = 0; i < m; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int dist = sc.nextInt();
            edges[i] = new Edge(from, to, dist);
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, MAX);
        distances[1] = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (distances[edges[j].from] < MAX) {
                    distances[edges[j].to] = Math.min(
                        distances[edges[j].to],
                        distances[edges[j].from] + edges[j].dist
                    );
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            System.out.print(distances[i] + " ");
        }
    }

    public static void main(String[] args) {
        solve();
    }
}

