package ru.mail.polis.ads.part10.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// Submission here https://www.e-olymp.com/ru/submissions/6413708

public class Exercise_1 {

    private static class Edge {
        int start;
        int end;

        Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    private static List<List<Edge>> graph;

    private static int[] fin;
    private static int[] fup;
    private static boolean[] used;

    private static int timer = 0;
    private static int counter = 0;

    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);

        final int n = scanner.nextInt();
        final int m = scanner.nextInt();

        graph = new ArrayList<>();
        used = new boolean[m+1];
        fin = new int[n+1];
        fup = new int[n+1];

        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<>());
            fin[i] = 0;
            fup[i] = 0;
        }

        for (int i = 1; i < m+1; i++) {
            final int a = scanner.nextInt();
            final int b = scanner.nextInt();

            graph.get(a).add(new Edge(i, b));
            graph.get(b).add(new Edge(i, a));
        }

        for (int i = 1; i < n+1; i++) {
            if (fin[i] == 0) {
                dfs(i, -1);
            }
        }

        System.out.println(counter);
        for (int i = 1; i < m+1 && counter > 0; i++) {
            if (used[i]) {
                System.out.print(i + " ");
                counter--;
            }
        }
    }


    private static void dfs(int v, int p) {
        fup[v] = ++timer;
        fin[v] = fup[v];

        for (Edge subEdge : graph.get(v)) {
            if (subEdge.start == p)  {
                continue;
            }
            if (fin[subEdge.end] != 0) {
                fup[v] = Math.min(fup[v], fin[subEdge.end]);
            } else {
                dfs(subEdge.end, subEdge.start);
                fup[v] = Math.min(fup[v], fup[subEdge.end]);
            }
        }

        if (p != -1 && fup[v] == fin[v]) {
            counter++;
            used[p] = true;
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
}
