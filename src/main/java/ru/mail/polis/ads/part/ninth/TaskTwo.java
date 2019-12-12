package ru.mail.polis.ads.part.ninth;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * @v.ivlev
 * https://www.e-olymp.com/ru/submissions/6338683
 */
public class TaskTwo {
    private static ArrayList<ArrayDeque<Integer>> listDeques;
    private static int[] usedArray;
    private static int[] parent;
    private static boolean[] loop;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        listDeques = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            listDeques.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt() - 1;
            int end = in.nextInt() - 1;
            listDeques.get(start).add(end);
            listDeques.get(end).add(start);
        }

        int min = Integer.MAX_VALUE;
        parent = new int[n];
        usedArray = new int[n];
        loop = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (usedArray[i] == 0) {
                dfs(i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (loop[i] && min > i) {
                min = i;
            }
        }
        if (min != Integer.MAX_VALUE) {
            out.println("Yes");
            out.println(min + 1);
        } else {
            out.println("No");
        }
    }

    private static void dfs(int v) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(v);
        while (!stack.isEmpty()) {
            int start = stack.peek();
            usedArray[start] = 1;
            boolean newChild = false;
            for (Integer end : listDeques.get(start)) {
                if (parent[start] != end) {
                    if (usedArray[end] == 0) {
                        parent[end] = start;
                        stack.addFirst(end);
                        newChild = true;
                        break;
                    } else if (usedArray[end] == 1) {
                        loop[end] = true;
                        int u = start;
                        if (!loop[start]) {
                            while (u != end) {
                                loop[u] = true;
                                u = parent[u];
                                if (loop[u]) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (!newChild) {
                usedArray[start] = 2;
                stack.removeFirst();
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