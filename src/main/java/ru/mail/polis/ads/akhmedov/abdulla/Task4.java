package main.java.ru.mail.polis.ads.akhmedov.abdulla;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Task4 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();

        final List<Integer>[] graph = new List[n + 1];
        final List<Integer>[] graphT = new List[n + 1];
        colorsComb = new Set[n + 1];

        for (int i = 1; i <= n; ++i) {
            graph[i] = new ArrayList<>();
            graphT[i] = new ArrayList<>();
            colorsComb[i] = new TreeSet<>();
        }

        for (int i = 1; i <= m; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();

            graph[first].add(second);
            graphT[second].add(first);
        }

        final boolean[] flags = new boolean[n + 1];
        final ArrayList<Integer> sorted = new ArrayList<>(n + 1);
        for (int i = 1; i <= n; ++i) {
            if (!flags[i]) {
                sort(i, graph, flags, sorted);
            }
        }

        int counter = 0;
        int[] colors = new int[n + 1];
        for (int i = n - 1; i >= 0; --i) {
            if (flags[sorted.get(i)]) {
                ++counter;
                backdDfs(sorted.get(i), graphT, flags, counter, colors);
            }
        }

        int result = 0;
        for (int i = 1; i <= n; ++i) {
            result += colorsComb[i].size();
        }
        System.out.println(result);
    }

    static void sort(int current, List<Integer>[] graph, boolean[] visited, ArrayList<Integer> sorted) {
        visited[current] = true;

        for (Integer integer: graph[current]) {
            if (!visited[integer]) {
                sort(integer, graph, visited, sorted);
            }
        }
        sorted.add(current);
    }

    private static Set<Integer>[] colorsComb;

    static void backdDfs(int current, List<Integer>[] graph, boolean[] unvisited, int currentColor, int[] colors) {
        unvisited[current] = false;
        colors[current] = currentColor;

        for (Integer integer: graph[current]) {
            if (unvisited[integer]) {
                backdDfs(integer, graph, unvisited, currentColor, colors);
            } else if (colors[integer] != currentColor) {
                colorsComb[currentColor].add(colors[integer]);
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
}