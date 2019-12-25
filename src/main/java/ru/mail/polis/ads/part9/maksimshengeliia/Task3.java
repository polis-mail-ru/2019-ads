package ru.mail.polis.ads.part9.maksimshengeliia;


import java.io.*;
import java.util.StringTokenizer;

public class Task3 {

    private static Edge[] edges;
    private static int[] dist;

    static class Edge {
        int first;
        int second;
        int w;

        public Edge(int first, int second, int w) {
            this.first = first;
            this.second = second;
            this.w = w;
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int v = in.nextInt();
        int e = in.nextInt();

        edges = new Edge[e];
        dist = new int[v + 1];
        for (int i = 0; i < dist.length; i++) {
            if (i == 1) {
                dist[i] = 0;
                continue;
            }
            dist[i] = 30_000;
        }

        for (int i = 0; i < e; i++) {
            int f = in.nextInt();
            int s = in.nextInt();
            int w = in.nextInt();
            edges[i] = new Edge(f, s, w);
        }

        for (int i = 1; i < dist.length; i++) {
            for (Edge edge : edges) {
                if (dist[edge.first] != 30_000) {
                    int d = dist[edge.first];
                    int newDist = d + edge.w;
                    if (dist[edge.second] > newDist) {
                        dist[edge.second] = newDist;
                    }
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            out.print(dist[i] + " ");
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
