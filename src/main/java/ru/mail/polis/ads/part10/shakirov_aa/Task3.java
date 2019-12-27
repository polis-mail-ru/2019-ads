package ru.mail.polis.ads.part10.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Task3 {
    private static int parent[];

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        Queue<Edge> queue = new PriorityQueue<>();

        for (int i = 0; i < m; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int c = fs.nextInt();
            queue.add(new Edge(a, b, c));
        }

        while (!queue.isEmpty()) {
            Edge edge = queue.poll();
            unionSets(edge.w, edge.v);
            if (findSet(n) == 1) {
                System.out.println(edge.danger);
                break;
            }
        }
    }

    static int findSet(int v) {
        if (v == parent[v]) {
            return v;
        }

        return findSet(parent[v]);
    }

    static void unionSets(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static class Edge implements Comparable<Edge> {
        int w;
        int v;
        int danger;

        public Edge(int w, int v, int danger) {
            this.w = w;
            this.v = v;
            this.danger = danger;
        }

        @Override
        public int compareTo(Edge o) {
            return this.danger - o.danger;
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
}
