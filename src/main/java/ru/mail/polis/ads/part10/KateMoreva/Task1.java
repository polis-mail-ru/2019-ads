package ru.mail.polis.ads.part10.KateMoreva;

import java.io.*;
import java.util.*;

public class Task1 {
    private static class Edge {
        private int id;
        private int end;


        Edge(int id, int end){
            this.id = id;
            this.end = end;

        }
    }

    private static int timer;
    private static int[] tin, fup;
    private static List<Edge>[] graph;
    private static int bridgeNumber;
    private static boolean[] isBridge;

    private static void findBridge(int n){
        timer = 0;
        for (int i = 1; i < n + 1; i++) {
            if (tin[i] == 0) {
                dfs(i, -1);
            }
        }
    }

    private static void dfs(int nodeId, int prevId){
        tin[nodeId] = fup[nodeId] = ++timer;
        for (Edge link : graph[nodeId]) {
            if (link.id == prevId) {
                continue;
            }
            if (tin[link.end] == 0) {
                dfs(link.end, link.id);
                fup[nodeId] = Math.min(fup[nodeId], fup[link.end]);
            } else {
                fup[nodeId] = Math.min(fup[nodeId], tin[link.end]);
            }
        }
        if (prevId != -1 && fup[nodeId] == tin[nodeId]) {
            bridgeNumber++;
            isBridge[prevId] = true;
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
        final int n = in.nextInt();
        final int m = in.nextInt();
        tin = new int[n + 1];
        fup = new int[n + 1];
        graph = new List[n + 1];

        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
            tin[i] = 0;
            fup[i] = 0;
        }

        for (int i = 1; i < m + 1; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph[a].add(new Edge(i, b));
            graph[b].add(new Edge(i, a));
        }
        bridgeNumber = 0;
        isBridge = new boolean[m + 1];
        findBridge(n);

        final PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
        out.print(bridgeNumber + "\n");
        for (int i = 1; bridgeNumber > 0; i++) {
            if (isBridge[i]) {
                out.print(i + " ");
                bridgeNumber--;
            }
        }
        out.flush();
    }
}
