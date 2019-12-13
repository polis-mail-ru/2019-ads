package ru.mail.polis.ads.part9.DiscreetDmitriy;

import java.io.*;
import java.util.*;

public class Task1TopologicalSorting {

    private static List<List<Integer>> graph;
    private static List<Integer> nodesArray;
    private static Colors[] used;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList<>(n + 1);
        nodesArray = new LinkedList<>();
        used = new Colors[n + 1];

        for (int i = 0; i < n + 1; i++)
            used[i] = Colors.WHITE;


        for (int i = 0; i < n + 1; i++)
            graph.add(new ArrayList<>());

        for (int i = 0; i < m; i++) {
            int b = in.nextInt();
            int e = in.nextInt();
            graph.get(b).add(e);
        }

        topologicalSort();

        for (Integer node : nodesArray)
            out.print(node + " ");

        out.flush();
    }

    private static void dfs(int node) {
        used[node] = Colors.GREY;

        for (Integer ends : graph.get(node)) {
            if (used[ends] == Colors.WHITE)
                dfs(ends);

            if (used[ends] == Colors.GREY) {
                System.out.println(-1);
                System.exit(0);
            }
        }

        used[node] = Colors.BLACK;

        nodesArray.add(0, node);
    }

    private static void topologicalSort() {
        for (int i = 1; i < graph.size(); i++)
            if (used[i] == Colors.WHITE)
                dfs(i);
    }

    enum Colors {
        WHITE,
        GREY,
        BLACK
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
