package ru.mail.polis.ads.part10.gogun;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task1 {

    private static int[] tin;
    private static int[] fup;
    private static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    private static boolean[] isBridge;
    private static int timer;
    private static int bridgesNum;

    private static class Edge {
        private int order;
        private int end;


        Edge(int order, int end){
            this.order = order;
            this.end = end;

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
        final int n = in.nextInt();
        final int m = in.nextInt();
        tin = new int[n + 1];
        fup = new int[n + 1];

        isBridge = new boolean[m + 1];

        Arrays.fill(tin, 0);
        Arrays.fill(fup, 0);

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        timer = 0;

        for (int i = 0; i < m; ++i) {
            int vertexFrom = in.nextInt();
            int vertexTo = in.nextInt();

            graph.get(vertexFrom).add(new Edge(i + 1, vertexTo));
            graph.get(vertexTo).add(new Edge(i + 1, vertexFrom));
        }

        findBridge(n);

        out.println(bridgesNum);
        for (int i = 1; i < m + 1; ++i) {
            if (isBridge[i]) {
                out.printf("%d ", i);
            }
        }
    }

    private static void findBridge(int n){
        timer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int curr, int prev){
        tin[curr] = fup[curr] = ++timer;
        for (Edge link : graph.get(curr)) {
            if (link.order == prev) {
                continue;
            }
            if (tin[link.end] == 0) {
                dfs(link.end, link.order);
                fup[curr] = Math.min(fup[curr], fup[link.end]);
            } else {
                fup[curr] = Math.min(fup[curr], tin[link.end]);
            }
        }
        if (prev != -1 && fup[curr] == tin[curr]) {
            bridgesNum++;
            isBridge[prev] = true;
        }
    }

}
