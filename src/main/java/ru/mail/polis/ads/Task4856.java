package ru.mail.polis.ads.part9.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4856 {
    private static int INF = Integer.MAX_VALUE / 2;
    private static int start;
    private static int end;
    private static int n;
    private static int m;
    private static ArrayList<Integer>[] adj;
    private static ArrayList<Integer>[] weight;
    private static boolean[] used;
    private static int[] dist;
    private static int[] pred;

    private static void solve(final FastScanner in, final PrintWriter out) {
        readData(in);
        dejkstra(start);
        out.println(dist[end]);
        printWay(end, out);
    }

    private static void printWay(int v, final PrintWriter out) {
        if (v == -1) { return; }
        printWay(pred[v], out);
        out.print((v + 1) + " ");
    }

    private static void dejkstra(int s) {
        dist[s] = 0;
        for (int iter = 0; iter < n; ++iter) {
            int v = -1;
            int distV = INF;
            for (int i = 0; i < n; ++i) {
                if (used[i]) { continue; }
                if (distV < dist[i]) { continue; }
                v = i;
                distV = dist[i];
            }
            for (int i = 0; i < adj[v].size(); ++i) {
                int u = adj[v].get(i);
                int weightU = weight[v].get(i);
                if (dist[v] + weightU < dist[u]) {
                    dist[u] = dist[v] + weightU;
                    pred[u] = v;
                }
            }
            used[v] = true;
        }
    }

    private static void readData(final FastScanner in) {
        n = in.nextInt();
        m = in.nextInt();
        start = in.nextInt()-1;
        end = in.nextInt()-1;
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList<>();
        }
        weight = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            weight[i] = new ArrayList();
        }
        for (int i = 0; i < m; ++i) {
            int u = in.nextInt();
            int v = in.nextInt();
            int w = in.nextInt();
            u--;
            v--;
            adj[u].add(v);
            weight[u].add(w);
        }
        used = new boolean[n];
        Arrays.fill(used, false);
        pred = new int[n];
        Arrays.fill(pred, -1);
        dist = new int[n];
        Arrays.fill(dist, INF);
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
