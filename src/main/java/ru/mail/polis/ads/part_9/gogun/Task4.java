package ru.mail.polis.ads.part_9.gogun;

import java.io.*;
import java.util.*;

public class Task4 {

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

    static class Edge {
        int end;
        int weight;

        Edge(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    private static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    private static int[] distance;
    private static int[] prev;
    private static boolean[] visited;
    private static int vertexFrom;
    private static int vertexTo;

    private static void bfs() {
        PriorityQueue<Edge> queue = new PriorityQueue<>(Comparator.comparing(x -> x.weight));
        queue.add(new Edge(vertexFrom, 0));
        while (!queue.isEmpty()) {
            int curr = queue.poll().end;
            if (visited[curr]) {
                continue;
            }
            visited[curr] = true;
            for (Edge edge: graph.get(curr)) {
                int dist = distance[curr] + edge.weight;
                if (distance[edge.end] > dist) {
                    distance[edge.end] = dist;
                    queue.add(new Edge(edge.end, distance[edge.end]));
                    prev[edge.end] = curr;
                }
            }
        }
    }

    private static void solve(FastScanner in, PrintWriter out) {
        int vertexNum = in.nextInt();
        int edgeNum = in.nextInt();

        vertexFrom = in.nextInt();
        vertexTo = in.nextInt();

        distance = new int[vertexNum + 1];
        prev = new int[vertexNum + 1];
        visited = new boolean[vertexNum + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[vertexFrom] = 0;
        for (int i = 0; i < vertexNum + 1; ++i) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < edgeNum; ++i) {
            int vertexFrom = in.nextInt();
            int vertexTo = in.nextInt();
            int weight = in.nextInt();

            graph.get(vertexFrom).add(new Edge(vertexTo, weight));
            graph.get(vertexTo).add(new Edge(vertexFrom, weight));
        }

        bfs();

        LinkedList<Integer> stack = new LinkedList<>();
        out.println(distance[vertexTo]);
        while (prev[vertexTo] != 0) {
            stack.add(vertexTo);
            vertexTo = prev[vertexTo];
        }
        stack.add(vertexTo);
        while (!stack.isEmpty()) {
            out.printf("%d ", stack.pollLast());
        }

    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
