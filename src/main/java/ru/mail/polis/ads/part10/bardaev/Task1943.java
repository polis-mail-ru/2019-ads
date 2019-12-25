package ru.mail.polis.ads.part10.bardaev;

import java.io.*;
import java.util.*;

public class Task1943 {

    private static class Node {
        private int edge;
        private int id;

        Node(int edge, int id) {
            this.edge = edge;
            this.id = id;
        }
    }

    private static int timer = 0;
    private static int[] tin, fup;
    private static List<Node>[] node;

    private static int bridgeNumber;
    private static boolean[] isBridge;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        tin = new int [n + 1];
        fup = new int [n + 1];
        node = new ArrayList[n + 1];
        bridgeNumber = 0;
        isBridge = new boolean[m + 1];

        for (int i = 1; i <= n; i++) node[i] = new ArrayList<>();

        Arrays.fill(tin, 0);
        Arrays.fill(fup, 0);

        for (int i = 1; i < m + 1; i++) {
            int a = in.nextInt(), b = in.nextInt();
            node[a].add(new Node(b, i));
            node[b].add(new Node(a, i));
        }

        findBridges(n);

        out.println(bridgeNumber);

        for (int i = 1; i < m + 1 && bridgeNumber > 0; i++) {
            if (isBridge[i]) {
                out.print(i + " ");
                bridgeNumber--;
            }
        }
        out.flush();
    }

    private static void findBridges(int n) {
        for (int i = 1; i < n + 1; i++) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int edgeId, int prev) {
        tin[edgeId] = fup[edgeId] = ++timer;
        for (Node link : node[edgeId]) {
            if (link.id == prev) {
                continue;
            }
            if (tin[link.edge] == 0) {
                dfs(link.edge, link.id);
                fup[edgeId] = Math.min(fup[edgeId], fup[link.edge]);
            } else {
                fup[edgeId] = Math.min(fup[edgeId], tin[link.edge]);
            }
        }
        if (prev != -1 && fup[edgeId] == tin[edgeId]) {
            bridgeNumber++;
            isBridge[prev] = true;
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

