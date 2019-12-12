package ru.mail.polis.ads.part.ninth;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @v.ivlev
 * https://www.e-olymp.com/ru/submissions/6336014
 */
public class TaskOne {
    private static ArrayList<ArrayDeque<Integer>> listDeques;
    private static ArrayDeque<Integer> deque;
    private static boolean isError = false;
    private static int[] array;

    public static void solve(FastScanner in, PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        array = new int[n + 1];
        listDeques = new ArrayList<>(n);
        deque = new ArrayDeque<>(n);
        for (int i = 0; i <= n; i++) {
            listDeques.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            listDeques.get(start).push(end);
        }
        for (int i = 1; i <= n; i++) {
            if (array[i] == 0) {
                dfs(i);
            }
        }
        if (isError || deque.size() != n) {
            out.println(-1);
        } else {
            for (Integer x : deque) {
                out.print(x + " ");
            }
        }
    }

    private static void dfs(int i) {
        array[i] = 1;
        for (Integer u : listDeques.get(i)) {
            if (array[u] == 0) {
                dfs(u);
            } else if (array[u] == 1) {
                isError = true;
                break;
            }
        }
        array[i] = 2;
        deque.addFirst(i);
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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