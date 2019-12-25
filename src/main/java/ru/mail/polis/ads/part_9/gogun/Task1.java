package ru.mail.polis.ads.part_9.gogun;

import java.io.*;
import java.util.*;

public class Task1 {

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final BufferedInputStream in) {
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

    enum Color {
        WHITE,
        GRAY,
        BLACK
    }

    private static Color[] vertexColor;
    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static int[] result;
    private static boolean isCycle = false;
    private static int count = 0;

    private static void solve(FastScanner in, PrintWriter out) {
        int vertexNum = in.nextInt();
        int edgeNum = in.nextInt();
        for (int i = 0; i < vertexNum; ++i) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < edgeNum; ++i) {
            int vertexFrom = in.nextInt();
            int vertexTo = in.nextInt();

            graph.get(vertexFrom - 1).add(vertexTo);
        }
        result = new int[vertexNum];

        vertexColor = new Color[vertexNum];

        Arrays.fill(vertexColor, Color.WHITE);

        sort();

        if (isCycle) {
            out.println("-1");
        } else {
            for (int i = result.length - 1; i >= 0; --i) {
                out.printf("%d ", result[i]);
            }
        }
    }

    private static void dfs(int vertex) {
        vertexColor[vertex] = Color.GRAY;
        for (int curr : graph.get(vertex)) {
            if (vertexColor[curr - 1] == Color.WHITE) {
                dfs(curr - 1);
            } else if (vertexColor[curr - 1] == Color.GRAY) {
                isCycle = true;
                return;
            }
        }

        vertexColor[vertex] = Color.BLACK;
        result[count++] = vertex + 1;
    }

    private static void sort() {
        for (int i = 0; i < graph.size(); ++i) {
            if (vertexColor[i] == Color.WHITE) {
                dfs(i);
            }
        }
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }


}
