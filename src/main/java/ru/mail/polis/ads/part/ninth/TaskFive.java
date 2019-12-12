package ru.mail.polis.ads.part.ninth;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @v.ivlev
 * https://www.e-olymp.com/ru/submissions/6338797
 */
public class TaskFive {
    private static int[] dist;
    private static int[] parent;
    private static ArrayList<ArrayDeque<Integer>> edges;

    public static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        int s = in.nextInt();
        int f = in.nextInt();
        dist = new int[n + 1];
        parent = new int[n + 1];
        ArrayDeque<Integer> res = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            dist[i] = -1;
        }
        dist[s] = 0;
        edges = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            edges.get(start).push(end);
            edges.get(end).push(start);
        }
        bfs(s);
        if (dist[f] != -1) {
            out.println(dist[f]);
            int prev = parent[f];
            while (prev != 0) {
                res.addFirst(prev);
                prev = parent[prev];
            }
            for (Integer i : res) {
                out.print(i + " ");
            }
            out.print(f);
        } else {
            out.println(-1);
        }
    }

    private static void bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[start] = 0;
        queue.addLast(start);
        while (!queue.isEmpty()) {
            Integer node = queue.pollFirst();
            for (Integer u : edges.get(node)) {
                if (dist[u] == -1) {
                    queue.addLast(u);
                    dist[u] = dist[node] + 1;
                    parent[u] = node;
                }
            }
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