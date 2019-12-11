package ru.mail.polis.ads.part9.nekobitlz;

import java.io.*;
import java.util.*;

public class Task5 {

    private static final int INF = 1000000;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int e = in.nextInt();

            graph.get(b).add(new Edge(e, 1));
            graph.get(e).add(new Edge(b, 1));
        }

        int[] distances = new int[n + 1];
        Arrays.fill(distances, INF);
        distances[s] = 0;

        int[] parent = new int[n + 1];
        boolean[] used = new boolean[n + 1];

        pq.add(new Edge(s, 0));

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int v = currentEdge.vertex;
            int d = currentEdge.distance;

            if (d > distances[v]) {
                continue;
            }

            for (Edge edge : graph.get(v)) {
                int to = edge.vertex;
                int cost = edge.distance;

                if (!used[to]) {
                    if (distances[to] > distances[v] + cost) {
                        distances[to] = distances[v] + cost;
                        pq.add(new Edge(to, distances[to]));
                        parent[to] = v;
                    }
                }
            }

            used[v] = true;
        }

        ArrayList<Integer> result = new ArrayList<>();
        out.println(distances[f] != INF ? distances[f] : -1);

        while (parent[f] != 0) {
            result.add(0, f);
            f = parent[f];
        }

        result.add(0, s);

        for (int v : result) {
            out.print(v + " ");
        }

        out.close();
    }

    private static class Edge implements Comparable {
        private int vertex;
        private int distance;

        public Edge(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(distance, ((Edge) o).distance);
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
