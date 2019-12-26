package ru.mail.polis.ads;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Task2 {
    private Task2() {}
    private static int[] parent;
    private static long STW = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int m = in.nextInt();

        Queue<Edge> queue = new PriorityQueue<>(n, Comparator.comparing(edge -> edge.w));
        int reverse = n;

        parent = new int[n + 1];
        for (int i = 1; i < n+1; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < m; i++) {
            int f = in.nextInt();
            int s = in.nextInt();
            int w = in.nextInt();
            queue.add(new Edge(f, s, w));
        }
        while (reverse != 1) {
            Edge edge = queue.poll();
            if (findSet(edge != null ? edge.f : 0) !=
                    findSet(edge != null ? edge.s : 0)) {
                unionSet(edge != null ? edge.f : 0,
                        edge != null ? edge.s : 0);
                STW += edge != null ? edge.w : 0;
                reverse--;
            }
        }

        out.println(STW);
    }

    private static void unionSet(int f, int s) {
        f = findSet(f);
        s = findSet(s);
        if (f != s) {
            parent[s] = f;
        }
    }

    private static int findSet(int link) {
        if (link == parent[link]) {
            return link;
        }
        return parent[link] =
                findSet(parent[link]);
    }

    private static class Edge {
        private int f;
        private int s;
        private int w;

        Edge(int f, int s, int w) {
            this.f = f;
            this.s = s;
            this.w = w;
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
