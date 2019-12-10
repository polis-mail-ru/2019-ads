package ru.mail.polis.ads.part9.nekobitlz;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Task3 {

    private static final int INF = 30000;

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();

        ArrayList<Edge> edges = new ArrayList<>();


        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int weight = in.nextInt();

            edges.add(new Edge(start, end, weight));
        }

        int[] distance = new int[n + 1];
        distance[1] = 0;

        for (int i = 2; i <= n; i++) {
            distance[i] = INF;
        }

        for (int i = 0; i < n - 1; i++) {
            for (Edge edge : edges) {
                if (distance[edge.start] != INF) {
                    distance[edge.end] = Math.min(distance[edge.end], distance[edge.start] + edge.weight);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            out.print(distance[i] + " ");
        }

        out.close();
    }

    private static class Edge {
        private int start;
        private int end;
        private int weight;

        private Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
