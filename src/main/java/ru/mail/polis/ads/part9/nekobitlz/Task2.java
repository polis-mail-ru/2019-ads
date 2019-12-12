package ru.mail.polis.ads.part9.nekobitlz;

import java.io.*;
import java.util.*;

import static java.lang.Math.min;

public class Task2 {

    private static final int COLOR_WHITE = 0;
    private static final int COLOR_GRAY = 1;
    private static final int COLOR_BLACK = 2;

    private static List<List<Integer>> graph;
    private static Stack<Integer> stack;

    private static int[] visited;
    private static int[] previous;
    private static boolean[] cycleEdges;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int minResult = Integer.MAX_VALUE;

        graph = new ArrayList<>(n + 1);
        visited = new int[n + 1];
        previous = new int[n + 1];
        cycleEdges = new boolean[n + 1];

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < k; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for (int i = 1; i <= n; i++) {
            if (visited[i] == COLOR_WHITE) {
                stack = new Stack<>();
                stack.push(i);

                while (!stack.isEmpty()) {
                    int current = stack.peek();
                    dfs(current);
                }
            }
        }

        for (int i = 0; i < cycleEdges.length; i++) {
            if (cycleEdges[i]) {
                minResult = min(i, minResult);
            }
        }

        if (minResult == Integer.MAX_VALUE) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(minResult);
        }

        out.close();
    }

    private static void dfs(int edge) {
        visited[edge] = COLOR_GRAY;
        boolean isCycle = true;

        for (int child : graph.get(edge)) {
            if (previous[edge] == child || visited[child] == COLOR_BLACK) {
                continue;
            }

            if (visited[child] == COLOR_WHITE) {
                previous[child] = edge;
                stack.push(child);
                isCycle = false;
                break;
            }

            if (visited[child] == COLOR_GRAY) {
                cycleEdges[child] = true;
                int i = edge;

                if (cycleEdges[edge]) {
                    continue;
                }

                while (i != child) {
                    cycleEdges[i] = true;
                    i = previous[i];

                    if (cycleEdges[i]) {
                        break;
                    }
                }
            }
        }

        if (isCycle) {
            stack.pop();
            visited[edge] = COLOR_BLACK;
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
