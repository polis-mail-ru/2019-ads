package ru.mail.polis.ads.part.ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/6416464
 */
public class TaskTwo {

    private static int[] prevArray;

    private static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int reverse = n;
        long min = 0;
        Queue<Edge> queue = new PriorityQueue<>(n, Comparator.comparing(edge -> edge.weight));
        prevArray = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            prevArray[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt();
            int s = in.nextInt();
            int w = in.nextInt();
            queue.add(new Edge(f, s, w));
        }
        while (reverse != 1) {
            Edge edge = queue.poll();
            if (findSet(edge != null ? edge.from : 0) != findSet(edge != null ? edge.to : 0)) {
                unionSet(edge != null ? edge.from : 0, edge != null ? edge.to : 0);
                min += edge != null ? edge.weight : 0;
                reverse--;
            }
        }
        out.println(min);
    }

    private static void unionSet(int from, int to) {
        from = findSet(from);
        to = findSet(to);
        if (from != to) {
            prevArray[to] = from;
        }
    }

    private static int findSet(int id) {
        if (id == prevArray[id]) {
            return id;
        }
        return prevArray[id] = findSet(prevArray[id]);
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
