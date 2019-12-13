package ru.mail.polis.ads.part10.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class SecondTask {

    private static class Edge {

        private int to;
        private int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
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

    private static List<Integer> frame;
    private static long minSum;
    private static List<List<Edge>> edges;
    private static Queue<Edge> queue;

    private static void solve() {
        FastScanner fastScanner = new FastScanner(System.in);
        int n = fastScanner.nextInt();
        int m = fastScanner.nextInt();

        edges = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayList<>(50));
        }

        for (int i = 0; i < m; i++) {
            int firstId = fastScanner.nextInt();
            int secondId = fastScanner.nextInt();
            int weight = fastScanner.nextInt();

            if (firstId == secondId) {
                continue;
            }

            edges.get(firstId).add(new Edge(secondId, weight));
            edges.get(secondId).add(new Edge(firstId, weight));
        }

        frame = new ArrayList<>(n);
        queue = new PriorityQueue<>(Comparator.comparing(edge -> edge.weight));
        minSum = 0;

        for (int i = n; i > 0; i--) {
            if (frame.size() == n) {
                System.out.println(minSum);
                return;
            }

            if (!frame.contains(i)) {
                frame.add(i);
                visit(i);
            }
        }
    }

    private static void visit(int nodeId) {
        queue.addAll(edges.get(nodeId));

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            if (!frame.contains(edge.to)) {
                minSum += edge.weight;
                frame.add(edge.to);
                visit(edge.to);
                break;
            }
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
