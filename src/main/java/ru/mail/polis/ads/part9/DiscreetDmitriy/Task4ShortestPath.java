package ru.mail.polis.ads.part9.DiscreetDmitriy;

import java.io.*;
import java.util.*;

public class Task4ShortestPath {

    private static final int INF = 1_000_000;

    public static class Node {
        int node;
        int distance;

        int getDistance() {
            return distance;
        }

        public Node(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int m = in.nextInt();

        int s = in.nextInt();
        int f = in.nextInt();

        List<List<Node>> graph = new ArrayList<>(n + 1);

        boolean[] used = new boolean[n + 1];
        int[] distance = new int[n + 1];
        int[] previous = new int[n + 1];

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparing(Node::getDistance));

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = INF;
        }

        distance[s] = 0;

        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int e = in.nextInt();
            int w = in.nextInt();

            graph.get(b).add(new Node(e, w));
            graph.get(e).add(new Node(b, w));
        }

        pq.add(new Node(s, 0));

        while (!pq.isEmpty()) {
            int curr = pq.poll().node;

            if (used[curr])
                continue;

            used[curr] = true;

            for (Node node : graph.get(curr)) {
                int b = node.node;
                int w = node.distance;

                if (distance[b] > distance[curr] + w) {
                    distance[b] = distance[curr] + w;
                    pq.add(new Node(b, distance[b]));
                    previous[b] = curr;
                }
            }
        }

        List<Integer> result = new LinkedList<>();

        out.println(distance[f]);

        while (previous[f] != 0) {
            result.add(0, f);
            f = previous[f];
        }

        result.add(0, s);

        for (Integer a : result)
            out.print(a + " ");

        out.flush();
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
