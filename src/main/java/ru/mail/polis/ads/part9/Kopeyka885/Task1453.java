package ru.mail.polis.ads.part9.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1453 {

    private static class Edge {
        private int from;
        private int to;
        private int dist;

        private Edge(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexAmount = in.nextInt();
        int edgeAmount = in.nextInt();

        Edge[] edges = new Edge[edgeAmount];

        for (int i = 0; i < edgeAmount;i++) {
            int from = in.nextInt();
            int to = in.nextInt();
            int weight = in.nextInt();
            edges[i] = new Edge(from, to, weight);
        }
            int[] minWeights = new int[vertexAmount+1];

            for(int i = 0; i < minWeights.length;i++){
                minWeights[i] = 30000;
            }
            minWeights[1] = 0;

        for (int i = 1; i < vertexAmount; i++) {
            for (int j = 0; j < edgeAmount; j++) {
                if (minWeights[edges[j].from] < 30000) 
                {
                    minWeights[edges[j].to] = Math.min(minWeights[edges[j].to],minWeights[edges[j].from] + edges[j].dist);
                }
            }
        }

        for (int i = 1; i < vertexAmount + 1; i++) {
            System.out.print(minWeights[i] + " ");
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
