package ru.mail.polis.ads.suhova.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Task 2: https://www.e-olymp.com/ru/submissions/6315842
 */
public class Task2022 {
    private static ArrayList<ArrayDeque<Integer>> g;
    private static int[] color, parent;
    private static boolean[] loop;

    public static void main(final String[] arg) {
        FastScanner in = new FastScanner();
        int n, m;
        n = in.nextInt();
        m = in.nextInt();
        g = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            g.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt() - 1;
            int end = in.nextInt() - 1;
            g.get(start).add(end);
            g.get(end).add(start);
        }
        solve(n);
    }

    private static void solve(int n) {
        int min = Integer.MAX_VALUE;
        parent = new int[n];
        color = new int[n];
        loop = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                dfs(i);
            }
        }
        for (int i = 0; i < n; i++) {
            if (loop[i] && min > i) {
                min = i;
            }
        }
        if (min != Integer.MAX_VALUE) {
            System.out.println("Yes");
            System.out.println(min + 1);
        } else {
            System.out.println("No");
        }
    }

    private static void dfs(int v) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(v);
        while (!stack.isEmpty()) {
            int start = stack.peek();
            color[start] = 1;
            boolean newChild = false;
            for (Integer end : g.get(start)) {
                if (parent[start] != end) {
                    if (color[end] == 0) {
                        parent[end] = start;
                        stack.addFirst(end);
                        newChild = true;
                        break;
                    } else if (color[end] == 1) {
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
                color[start] = 2;
                stack.removeFirst();
            }
        }
    }

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
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
