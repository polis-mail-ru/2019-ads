package ru.mail.polis.ads.marashov.alexander;

import java.io.*;
import java.util.*;

public class Task1 {

    private static class Edge {
        int to;
        int number;

        Edge(int to, int number) {
            this.to = to;
            this.number = number;
        }
    }

    private static void dfs(int current, int prevEdgeIndex, int[] tme, int[] tin, int[] fup, List<Edge>[] graph, boolean[] answer, int[] bridgesCount) {
        tin[current] = fup[current] = ++tme[0];
        for (Edge edge: graph[current]) {
            if (edge.number == prevEdgeIndex) {
                continue;
            }
            if (tin[edge.to] == 0) {
                dfs(edge.to, edge.number, tme, tin, fup, graph, answer, bridgesCount);
                fup[current] = Math.min(fup[current], fup[edge.to]);
            } else {
                fup[current] = Math.min(fup[current], tin[edge.to]);
            }
        }

        if (fup[current] == tin[current] && prevEdgeIndex != -1) {
            ++bridgesCount[0];
            answer[prevEdgeIndex] = true;
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

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int nodesCount = in.nextInt();
        final int edgesCount = in.nextInt();

        List<Edge>[] graph;
        int[] tin;
        int[] fup;
        boolean[] answer;

        graph = new List[nodesCount + 1];
        tin = new int[nodesCount + 1];
        fup = new int[nodesCount + 1];

        for (int i = 1; i <= nodesCount; ++i) {
            graph[i] = new ArrayList<>();
            tin[i] = 0;
            fup[i] = 0;
        }

        for (int i = 1; i <= edgesCount; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();
            graph[first].add(new Edge(second, i));
            graph[second].add(new Edge(first, i));
        }

        answer = new boolean[edgesCount + 1];
        int[] tme = new int[1];
        int[] bridgesCount = new int[1];
        for (int i = 1; i <= nodesCount; ++i) {
            if (tin[i] == 0) {
                dfs(i, -1, tme, tin, fup, graph, answer, bridgesCount);
            }
        }

        try (PrintWriter out = new PrintWriter(System.out)) {
            out.println(bridgesCount[0]);
            int bc = bridgesCount[0];
            for (int i = 1; i <= edgesCount && bc > 0; ++i) {
                if (answer[i]) {
                    --bc;
                    out.print(i + " ");
                }
            }
        }

    }
}
