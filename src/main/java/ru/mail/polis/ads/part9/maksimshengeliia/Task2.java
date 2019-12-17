package ru.mail.polis.ads.part9.maksimshengeliia;


import java.io.*;
import java.util.*;

public class Task2 {

    enum Colors {
        WHITE, GREY, BLACK;
    }

    static List<List<Integer>> graph;
    static List<Integer> result = new ArrayList<>();
    static Colors[] colorGraph;
    static int vertexes, edges;
    static boolean cycleFound = false;
    static boolean elementOfCycle = false;

    static int controlVar;
    static void dfs(int v, int from) {
        colorGraph[v] = Colors.GREY;

        List<Integer> closeVertexes = graph.get(v);
        for (int cVertex : closeVertexes) {
            if (colorGraph[cVertex] == Colors.WHITE) {
                dfs(cVertex, v);
            }

            if ((colorGraph[cVertex] == Colors.GREY && cVertex != from) || elementOfCycle) {
                if (!elementOfCycle) {
                    result.clear();
                    cycleFound = true;
                    elementOfCycle = true;
                    controlVar = cVertex;
                }
                break;
            }
        }

        if (elementOfCycle) result.add(v);
        if (v == controlVar) elementOfCycle = false;
        colorGraph[v] = Colors.BLACK;

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        vertexes = in.nextInt();
        edges = in.nextInt();
        graph = new ArrayList<>();
        colorGraph = new Colors[vertexes + 1];

        for (int i = 0; i <= vertexes; i++) {
            graph.add(new ArrayList<>());
            colorGraph[i] = Colors.WHITE;
        }


        for (int i = 0; i < edges; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        for(int i = 1; i <= vertexes; i++) {
            if (colorGraph[i] == Colors.WHITE) {
                dfs(i, 0);
            }
        }

        if (!cycleFound) out.println("No");
        else {
            Collections.sort(result);
            out.println("Yes");
            out.println(result.get(0));
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
