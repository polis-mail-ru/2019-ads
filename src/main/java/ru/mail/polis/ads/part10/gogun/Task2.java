package ru.mail.polis.ads.part10.gogun;

import java.io.*;
import java.util.*;


public class Task2 {
    private static class Edge {
        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int[] prev;

    private static int find(int vert) {
        if (vert == prev[vert]) {
            return vert;
        }
        return prev[vert] = find(prev[vert]);

    }

    private static void union(int first, int second) {
        first = find(first);
        second = find(second);
        if (first != second) {
            prev[second] = first;
        }
    }

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

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }

    private static void solve(FastScanner in, PrintWriter out) {
        final int verticesNum = in.nextInt();
        final int edgesCount = in.nextInt();
        if (edgesCount == 0) {
            System.out.println(0);
            System.exit(0);
        }
        prev = new int[verticesNum + 1];
        for (int i = 1; i <= verticesNum; i++) {
            prev[i] = i;
        }
        Queue<Edge> queue = new PriorityQueue<>(verticesNum, Comparator.comparing(edge -> edge.weight));
        for (int i = 1; i <= edgesCount; ++i) {
            final int from = in.nextInt();
            final int to = in.nextInt();
            final int weight = in.nextInt();
            queue.add(new Edge(from, to, weight));
        }
        int min = 0;
        int counter = verticesNum;
        int currentAddedAmount = 1;

        while (currentAddedAmount < verticesNum) {
            Edge edge = queue.poll();
            if (find(edge.from) == find(edge.to)) {
                continue;
            }

            union(edge.from, edge.to);
            ++currentAddedAmount;
            min += edge.weight;
        }
        out.print(min);
    }
}
