package ru.mail.polis.ads.part9.Kopeyka885;

import java.io.*;
import java.util.*;

public class Task4853 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexAmount = in.nextInt();
        int edgeAmount = in.nextInt();
        int fromNode = in.nextInt();
        int toNode = in.nextInt();

        List<List<Edge>> graph = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 0; i <= vertexAmount; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeAmount; i++) {
            int from = in.nextInt();
            int to = in.nextInt();

            graph.get(from).add(new Edge(to, 1));
            graph.get(to).add(new Edge(from, 1));
        }

        int[] paths = new int[vertexAmount + 1];
        Arrays.fill(paths, Integer.MAX_VALUE);
        paths[fromNode] = 0;

        int[] parent = new int[vertexAmount + 1];
        boolean[] used = new boolean[vertexAmount + 1];

        pq.add(new Edge(fromNode, 0));

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();
            int v = currentEdge.vertex;
            int w = currentEdge.weight;

            if (w > paths[v]) {
                continue;
            }

            for (Edge edge : graph.get(v)) {
                int to = edge.vertex;
                int weight = edge.weight;

                if (!used[to])
                {
                    if (paths[to] > paths[v] + weight)
                    {
                        paths[to] = paths[v] + weight;
                        pq.add(new Edge(to, paths[to]));
                        parent[to] = v;
                    }
                }
            }
            used[v] = true;
        }

        ArrayList<Integer> result = new ArrayList<>();
        out.println(paths[toNode] != Integer.MAX_VALUE ? paths[toNode] : -1);

        while (parent[toNode] != 0) {
            result.add(0, toNode);
            toNode = parent[toNode];
        }

        result.add(0, fromNode);

        for (int v : result) {
            out.print(v + " ");
        }

        out.close();
    }

    private static class Edge implements Comparable {
        private int vertex;
        private int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Object o) {
            return Integer.compare(weight, ((Edge) o).weight);
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