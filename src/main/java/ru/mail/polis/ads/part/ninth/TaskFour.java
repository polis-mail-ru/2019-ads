package ru.mail.polis.ads.part.ninth;

import java.io.*;
import java.util.*;

/**
 * @v.ivlev
 * https://www.e-olymp.com/ru/submissions/6338737
 */
public class TaskFour {
    private static int[] dist;
    private static int[] parent;
    private static boolean[] used;
    private static ArrayList<ArrayDeque<Edge>> edges;
    private static PriorityQueue<Edge> list;

    public static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        dist = new int[n + 1];
        parent = new int[n + 1];
        used = new boolean[n + 1];
        list = new PriorityQueue<>(Comparator.comparing(x -> {
            return dist[x.end];
        }));
        ArrayDeque<Integer> res = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        edges = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int w = in.nextInt();
            edges.get(start).push(new Edge(end, w));
            edges.get(end).push(new Edge(start, w));
        }

        list.add(new Edge(s, 0));
        while (!list.isEmpty()) {
            int node = list.poll().end;
            for (Edge edge : edges.get(node)) {
                int newDist = dist[node] + edge.weight;
                if (!used[edge.end] && newDist < dist[edge.end]) {
                    dist[edge.end] = newDist;
                    list.add(edge);
                    parent[edge.end] = node;
                }
                used[node] = true;
            }
        }
        if (dist[f] != Integer.MAX_VALUE) {
            out.println(dist[f]);
            int prev = parent[f];
            while (prev != 0) {
                res.addFirst(prev);
                prev = parent[prev];
            }
            for (Integer i : res) out.print(i + " ");
            out.print(f);
        } else {
            out.println(-1);
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

    private static class Edge {
        int weight;
        int end;

        Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
