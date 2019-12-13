package ru.mail.polis.ads.part5.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Task5 {
    private static final int MAX = 100000;
    private static final int INF = 1000000000;
    private static List<Pair<Integer, Integer>> graph[];
    private static List<Integer> ans;
    private static List<Pair<Integer, Integer>> q;
    private static int length[];
    private static int parent[];

    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();
        int s = fs.nextInt();
        int f = fs.nextInt();

        graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }
        length = new int[n + 1];
        parent = new int[n + 1];
        q = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int from = fs.nextInt();
            int to = fs.nextInt();
            int len = 1;
            graph[from].add(new Pair<>(to, len));
            graph[to].add(new Pair<>(from, len));
        }

        for (int i = 0; i <= n; i++) {
            length[i] = INF;
        }
        length[s] = 0;
        q.add(new Pair(length[s], s));
        while (!q.isEmpty()) {
            int v = q.remove(0).value;
            for (int i = 0; i < graph[v].size(); i++) {
                int to = graph[v].get(i).key, len = graph[v].get(i).value;
                if (length[v] + len < length[to]) {
                    q.remove(new Pair(length[to], to));
                    length[to] = length[v] + len;
                    parent[to] = v;
                    q.add(new Pair<>(length[to], to));
                }
            }
        }

        if (length[f] == INF) {
            System.out.println(-1);
            return;
        }

        System.out.println(length[f]);
        ans = new ArrayList<>();
        int c = f;
        while (c != s) {
            ans.add(c);
            c = parent[c];
        }
        ans.add(s);
        System.out.print(ans.get(ans.size() - 1) + " ");
        for (int i = ans.size() - 2; i >= 0; i--) {
            System.out.print(ans.get(i) + " ");
        }
    }

    private static class Pair<Key, Value> {
        Key key;
        Value value;

        public Pair(Key k, Value v) {
            this.key = k;
            this.value = v;
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

