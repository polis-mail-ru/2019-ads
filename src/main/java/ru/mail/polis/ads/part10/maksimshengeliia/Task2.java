package ru.mail.polis.ads.part10.maksimshengeliia;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6424732
 */
public class Task2 {

    static int[] parents;
    static Queue<Edge> queue = new PriorityQueue<>();;

    private static class Edge implements Comparable<Edge> {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge edge) {
            return weight - edge.weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int vertexesNum = in.nextInt();
        int edgesNum = in.nextInt();

        parents = new int[vertexesNum + 1];
        for (int i = 1; i < vertexesNum + 1; i++) {
            parents[i] = i;
        }

        for (int i = 1; i < edgesNum + 1; ++i) {
            int a = in.nextInt();
            int b = in.nextInt();
            int weight = in.nextInt();
            queue.add(new Edge(a, b, weight));
        }

        int min = 0;
        int counter = vertexesNum;
        while (counter != 1) {
            Edge edge = queue.poll();

            if (findSet(edge.start) != findSet(edge.end)) {
                uniteSet(edge.start, edge.end);
                min += edge.weight;
                counter--;
            }
        }
        out.print(min);
    }

    private static void uniteSet(int f, int s) {
        f = findSet(f);
        s = findSet(s);
        if(f != s) {
            parents[s] = f;
        }
    }

    private static int findSet(int x) {
        return x == parents[x] ? x : (parents[x] = findSet(parents[x]));
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

