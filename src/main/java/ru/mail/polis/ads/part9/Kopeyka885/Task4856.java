package ru.mail.polis.ads.part9.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task4856 {

    private static boolean[] used;
    private static int[] dist;
    private static int[] parent;
    private static ArrayList<ArrayDeque<Edge>> graph;
    private static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(x -> dist[x.vertex]));

    private static class Edge {
        int vertex;
        int w;

        Edge(int vertex, int w) {
            this.vertex = vertex;
            this.w = w;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();

        dist = new int[n + 1];
        parent = new int[n + 1];
        used = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }

        for (int i = 0; i < m; i++) {
            int x1 = in.nextInt();
            int x2 = in.nextInt();
            int weight = in.nextInt();

            graph.get(x1).push(new Edge(x2, weight));
            graph.get(x2).push(new Edge(x1, weight));
        }

        pq.add(new Edge(s, 0));
        while (!pq.isEmpty()) {
            int v = pq.poll().vertex;
            for (Edge edge : graph.get(v)) {
                int newDist = dist[v] + edge.w;
                if (!used[edge.vertex] && newDist < dist[edge.vertex]) {
                    dist[edge.vertex] = newDist;
                    pq.add(edge);
                    parent[edge.vertex] = v;
                }
                used[v] = true;
            }
        }
        if (dist[f] != Integer.MAX_VALUE) {
            System.out.println(dist[f]);
            ArrayDeque<Integer> res = new ArrayDeque<>();
            int prev = parent[f];
            while (prev != 0) {
                res.addFirst(prev);
                prev = parent[prev];
            }
            for (Integer i : res) System.out.print(i + " ");
            System.out.print(f);
        } else {
            System.out.println(-1);
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



