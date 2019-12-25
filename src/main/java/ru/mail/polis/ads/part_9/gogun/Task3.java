package ru.mail.polis.ads.part_9.gogun;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task3 {

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final BufferedInputStream in) {
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
        int begin;
        int end;
        int weight;

        Edge(int begin, int end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }
    }

    private static Edge[] graph;
    private static int[] minDist;

    private static void fordBellman() {
        for (int i = 1; i < minDist.length; ++i) {
            for (Edge next : graph) {
                if (minDist[next.begin] == 30000) {
                    continue;
                }
                minDist[next.end] = Math.min(minDist[next.end], minDist[next.begin] + next.weight);
            }
        }
    }

    private static void solve(FastScanner in, PrintWriter out) {
        int vertexNum = in.nextInt();
        int edgeNum = in.nextInt();

        minDist = new int[vertexNum + 1];
        Arrays.fill(minDist, 30000);
        minDist[1] = 0;

        graph = new Edge[edgeNum];
        for (int i = 0; i < graph.length; ++i) {
            graph[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());

        }

        fordBellman();

        for (int i = 1; i < minDist.length; ++i) {
            out.printf("%d ", minDist[i]);
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
