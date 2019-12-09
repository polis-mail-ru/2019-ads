package ru.mail.polis.ads.part9.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Task1948 {

    static int n, m;
    static ArrayList<Integer> adjList[];
    static int color[];
    static boolean cyclic = false;
    static ArrayList<Integer> topSort = new ArrayList<>();

    private static void solve(final FastScanner in, final PrintWriter out) {
        n = in.nextInt();
        m = in.nextInt();

        adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            a--; b--;
            adjList[a].add(b);
        }

        color = new int[n];
        Arrays.fill(color, 0);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < adjList.length; i++) map.put(i, i);
        for (int i = 0; i < adjList.length; i++) {
            for (int j = 0; j < adjList[i].size(); j++) {
                map.remove(adjList[i].get(j));
            }
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) sort(entry.getKey());

        if (cyclic) {
            out.print(-1);
        } else {
            for (int i = topSort.size() - 1; i >= 0; i--) {
                out.print(topSort.get(i) + " ");
            }
        }
        out.flush();
    }

    private static void sort(int v) {
        if (color[v] == 2) return;
        if (cyclic) return;
        if (color[v] == 1) {
            cyclic = true;
            return;
        }
        color[v] = 1;
        for (int i = 0; i < adjList[v].size(); ++i) {
            int w = adjList[v].get(i);
            sort(w);
            if (cyclic) return;
        }
        color[v] = 2;
        topSort.add(v+1);
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
