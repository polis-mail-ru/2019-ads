package ru.mail.polis.ads.part.ten;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/6416477
 */
public class TaskFour {

    private static int counter = 0;
    private static int result = 0;

    private static Set<Integer>[] set;
    private static ArrayList<Integer> stackList;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();
        boolean[] mask = new boolean[n + 1];
        int[] colorArray = new int[n + 1];
        set = new Set[n + 1];
        stackList = new ArrayList<>(n + 1);
        List<Integer>[] graph = new List[n + 1];
        List<Integer>[] graphRep = new List[n + 1];

        for (int i = 1; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
            graphRep[i] = new ArrayList<>();
            set[i] = new TreeSet<>();
        }
        for (int i = 1; i < m + 1; i++) {
            int id = in.nextInt(), b = in.nextInt();
            graph[id].add(b);
            graphRep[b].add(id);
        }
        for (int i = 1; i < n + 1; i++) {
            if (!mask[i]) {
                dfs(i, graph, mask, stackList);
            }
        }
        for (int i = n - 1; i > -1; i--) {
            if (mask[stackList.get(i)]) {
                counter++;
                dfs(stackList.get(i), graphRep, mask, counter, colorArray);
            }
        }
        for (int i = 1; i < n + 1; i++) {
            result += set[i].size();
        }

        out.println(result);
    }

    static void dfs(
            int vert, List<Integer>[] graph,
            boolean[] used, ArrayList<Integer> stack) {
        used[vert] = true;
        for (Integer integer : graph[vert]) {
            if (!used[integer]) {
                dfs(integer, graph, used, stack);
            }
        }
        stack.add(vert);
    }

    static void dfs(
            int vert, List<Integer>[] revGraph,
            boolean[] revUsed, int color, int[] colors) {
        revUsed[vert] = false;
        colors[vert] = color;
        for (Integer integer : revGraph[vert]) {
            if (revUsed[integer]) {
                dfs(integer, revGraph, revUsed, color, colors);
            } else if (colors[integer] != color) {
                set[color].add(colors[integer]);
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

