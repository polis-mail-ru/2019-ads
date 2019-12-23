package ru.mail.polis.ads.task10.art241111;

import java.io.*;
import java.util.*;

public class Task1{
    private static class Graph{
        private int end;
        private int id;

        Graph(int end, int id) {
            this.end = end;
            this.id = id;
        }
    }

    private static int timer;
    private static int[] tin, fup;
    private static List<Graph>[] graph;

    private static int bridgeNumber;
    private static boolean[] isBridge;

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

    private static void findBridges(int n) {
        timer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int nodeId, int prevLinkId) {
        tin[nodeId] = fup[nodeId] = ++timer;
        for (Graph link : graph[nodeId]) {
            if (link.id == prevLinkId) {
                continue;
            }
            if (tin[link.end] == 0) {
                dfs(link.end, link.id);
                fup[nodeId] = Math.min(fup[nodeId], fup[link.end]);
            } else {
                fup[nodeId] = Math.min(fup[nodeId], tin[link.end]);
            }
        }
        if (prevLinkId != -1 && fup[nodeId] == tin[nodeId]) {
            bridgeNumber++;
            isBridge[prevLinkId] = true;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        tin = new int [n + 1];
        fup = new int [n + 1];
        graph = new ArrayList[n + 1];

        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
            tin[i] = 0;
            fup[i] = 0;
        }

        for (int i = 1; i < m + 1; i++) {
            int a = in.nextInt(), b = in.nextInt();
            graph[a].add(new Graph(b, i));
            graph[b].add(new Graph(a, i));
        }

        bridgeNumber = 0;
        isBridge = new boolean[m + 1];

        findBridges(n);

        out.println(bridgeNumber);

        for (int i = 1; i < m + 1 && bridgeNumber > 0; i++) {
            if (isBridge[i]) {
                out.print(i + " ");
                bridgeNumber--;
            }
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
