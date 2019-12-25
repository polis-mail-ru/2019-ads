package ru.mail.polis.ads.part9.bardaev;

import java.io.*;
import java.util.StringTokenizer;

public class Task1453 {

    private static class Edge {
        int first;
        int second;
        int value;

        Edge(int f, int s, int v) {
            this.first = f;
            this.second = s;
            this.value = v;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        Edge[] edges = new Edge[m+1];
        int path[] = new int[n+1];

        path[1] = 0;
        for (int i = 2; i <= n; i++) path[i] = 30000;

        for (int i = 1; i <= m; i++) edges[i] = new Edge(in.nextInt(), in.nextInt(), in.nextInt());

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                if (path[edges[j].first] == 30000) continue;
                if (path[edges[j].second] > path[edges[j].first] + edges[j].value)
                    path[edges[j].second] = path[edges[j].first] + edges[j].value;
            }
        }
        for (int i = 1; i <= n; i++) out.print(path[i] + " ");
        out.flush();
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