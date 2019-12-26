package ru.mail.polis.ads.part.ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/6416417
 */
public class TaskOne {

    private static List<List<Edge>> graph = new ArrayList<>();
    private static boolean[] usedBridge;
    private static int[] tin;
    private static int[] fup;
    private static int counter;
    private static int timer;

    public static class Edge {
        private int id;
        private int end;

        public Edge(int id, int end) {
            this.id = id;
            this.end = end;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        usedBridge = new boolean[m + 1];
        counter = 0;
        Arrays.fill(usedBridge, false);
        tin = new int[n + 1];
        fup = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            tin[i] = 0;
            fup[i] = 0;
        }
        for (int i = 1; i < m + 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(new Edge(i, b));
            graph.get(b).add(new Edge(i, a));
        }

        bridgesSearcher(n);

        out.println(counter);
        for (int i = 1; i < m + 1 && counter > 0; i++) {
            if (usedBridge[i]) {
                out.print(i + " ");
                counter--;
            }
        }
    }

    private static void bridgesSearcher(final int n) {
        timer = 0;
        for (int i = 1; i < (n + 1); i++) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int v, int p) {
        tin[v] = fup[v] = ++timer;
        for (Edge edge : graph.get(v)) {
            if (edge.id == p) {
                continue;
            }
            if (tin[edge.end] != 0) {
                fup[v] = Math.min(fup[v], tin[edge.end]);
            } else {
                dfs(edge.end, edge.id);
                fup[v] = Math.min(fup[v], fup[edge.end]);
            }
        }

        if (p != -1 && fup[v] == tin[v]) {
            counter++;
            usedBridge[p] = true;
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
