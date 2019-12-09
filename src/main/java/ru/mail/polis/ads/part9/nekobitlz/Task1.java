package ru.mail.polis.ads.part9.nekobitlz;

import java.util.*;
import java.io.*;

public class Task1 {

    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;

    private static ArrayList<ArrayList<Integer>> graph;
    private static ArrayList<Integer> result = new ArrayList<>();
    private static int[] colors;
    private static boolean wrong = false;

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();
        int m = in.nextInt();

        graph = new ArrayList<>();
        colors = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();

            graph.get(start).add(end);
        }

        for (int i = 1; i <= n; i++) {
            if (colors[i] == COLOR_WHITE) {
                dfs(i);
            }
        }

        if (wrong) {
            out.println("-1");
        } else {
            for (int i = result.size() - 1; i >= 0; i--) {
                out.print(result.get(i) + " ");
            }
        }

        out.close();
    }

    private static void dfs(int vertex) {
        colors[vertex] = COLOR_GRAY;
        List<Integer> connections = graph.get(vertex);

        for (int next : connections) {
            switch (colors[next]) {
                case COLOR_GRAY:
                    wrong = true;
                    break;
                case COLOR_WHITE:
                    dfs(next);
                    break;
            }
        }

        colors[vertex] = COLOR_BLACK;
        result.add(vertex);
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        private FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        private String next() {
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