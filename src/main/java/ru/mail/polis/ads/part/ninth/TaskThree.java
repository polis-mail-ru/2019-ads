package ru.mail.polis.ads.part.ninth;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @v.ivlev
 * https://www.e-olymp.com/ru/submissions/6336097
 */
public class TaskThree {
    private static int[] d;
    private static ArrayList<ArrayDeque<Edge>> listDeques;
    private static ArrayDeque<Integer> deque = new ArrayDeque<>();

    public static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        d = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            d[i] = 30000;
        }
        listDeques = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            listDeques.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int begin = in.nextInt();
            int end = in.nextInt();
            int weight = in.nextInt();
            listDeques.get(begin).push(new Edge(end, weight));
        }

        deque.addLast(1);
        while (!deque.isEmpty()) {
            int node = deque.pollFirst();
            for (Edge edge : listDeques.get(node)) {
                int x = d[node] + edge.weight;
                if (x < d[edge.end]) {
                    d[edge.end] = x;
                    deque.addLast(edge.end);
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            out.print(d[i] + " ");
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

    private static class Edge {
        int weight;
        int end;

        Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}