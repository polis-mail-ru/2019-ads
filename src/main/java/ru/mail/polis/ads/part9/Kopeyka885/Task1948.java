package ru.mail.polis.ads.part9.Kopeyka885;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1948 {

    static ArrayList<ArrayList<Integer>> graph;
    static ArrayList<Integer> result;
    static int[] used;
    static boolean error = false;

    static void dfs (int vertex){
        used[vertex] = 1;
        for(int i = 0; i < graph.get(vertex).size(); i++) {
            int v = graph.get(vertex).get(i);
            if (used[v]==1){
                error = true;
                break;
            } else if (used[v]==0){
                dfs(v);
            }
        }
        used[vertex] = 2;
        result.add(vertex);
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
            int n = in.nextInt();
            int m = in.nextInt();
            graph = new ArrayList<>();
            result =  new ArrayList<>();
            for (int i = 0; i <= n ; i++) {
                graph.add(new ArrayList<>());
            }
            for (int i = 0; i < m; i++) {
                int x1 = in.nextInt();
                int x2 = in.nextInt();
                graph.get(x1).add(x2);
            }
            used =  new int[n+1];
            for (int i = 1; i <= n; i++) {
                if (used[i]==0) {
                    dfs(i);
                }
            }

            if (error || result.size() != n) System.out.println("-1");
            else {
                for(int i = result.size() - 1; i >= 0; i--)
                    out.print(result.get(i) + " ");
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



