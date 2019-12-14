package ru.mail.polis.ads.part10.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class ThirdTask {

    private static class Edge {

        private int first;
        private int second;
        private int danger;

        public Edge(int first, int second, int danger) {
            this.first = first;
            this.second = second;
            this.danger = danger;
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static int[] parent;

    private static void solve() {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();
        int m = fastScanner.nextInt();

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Queue<Edge> queue = new PriorityQueue<>(n, Comparator.comparing(edge -> edge.danger));

        for (int i = 0; i < m; i++) {
            int firstId = fastScanner.nextInt();
            int secondId = fastScanner.nextInt();
            int weight = fastScanner.nextInt();

            queue.add(new Edge(firstId, secondId, weight));
        }

        Edge edge = null;
        for (int i = 0; i < m; i++) {
            edge = queue.poll();
            unionSet(edge.first, edge.second);
            if (findSet(1) ==  findSet(n)) {
                break;
            }
        }

        System.out.println(edge.danger);
    }

    private static void unionSet(int firstNode, int secondNode) {
        firstNode = findSet(firstNode);
        secondNode = findSet(secondNode);
        if (firstNode != secondNode) {
            parent[secondNode] = firstNode;
        }
    }

    private static int findSet(int idNode) {
        if (idNode == parent[idNode]) {
            return idNode;
        }
        return parent[idNode] = findSet(parent[idNode]);
    }

    public static void main(String[] args) {
        solve();
    }
}
