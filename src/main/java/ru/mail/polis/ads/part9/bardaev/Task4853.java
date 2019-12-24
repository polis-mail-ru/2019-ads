package ru.mail.polis.ads.part9.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class Task4853 {

    private static int n;
    private static int m;
    private static ArrayList<Integer> adj[];
    private static boolean used[];
    private static Queue<Integer> queue;
    private static int start;
    private static int end;
    private static int[] distance;
    private static int[] parents;

    private static void solve(final FastScanner in, final PrintWriter out) {
        readData(in);
        bfs(start, out);
        out.flush();
    }

    private static void bfs(int v, final PrintWriter out) {
        if (used[v]) {
            return;
        }
        queue.add(v);
        used[v] = true;
        while (!queue.isEmpty()) {
            v = queue.poll();
            out.print((v + 1) + " ");
            for (int i = 0; i < adj[v].size(); ++i) {
                int w = adj[v].get(i);
                if (used[w]) { continue; }
                queue.add(w);
                used[w] = true;
            }
        }
//        from.color = 1;
//        for (int i = 0; i < from.edge.size(); i++) {
//            Node to = from.edge.get(i);
//            if (distance[to.id] > distance[from.id] + 1) {
//                distance[to.id] = distance[from.id] + 1;
//                parents[to.id] = from.id;
//            }
//            if (to.color == 0) {
//                edges.add(to);
//            }
//        }
//        from.color = 2;
    }

    private static void readData(final FastScanner in) {
        n = in.nextInt();
        m = in.nextInt();
        start = in.nextInt()-1;
        end = in.nextInt()-1;
        adj = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            adj[i] = new ArrayList();
        }
        for (int i = 0; i < m; ++i) {
            int v = in.nextInt();
            int w = in.nextInt();
            v--;
            w--;
            adj[v].add(w);
            adj[w].add(v);
            used = new boolean[n];
            Arrays.fill(used, false);
            queue = new LinkedList();
        }
        distance = new int[n + 1];
        parents = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;
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
