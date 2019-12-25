package ru.mail.polis.ads.part_9.gogun;

import java.io.*;
import java.util.*;

public class Task5 {

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

    private static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    private static int[] distance;
    private static int[] prev;
    private static boolean[] visited;
    private static int vertexFrom;
    private static int vertexTo;

    private static void bfs() {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(vertexFrom);
        while (!queue.isEmpty()) {
            Integer curr = queue.remove();
            for (Integer vertex : graph.get(curr)) {
                if (visited[vertex]) {
                    continue;
                }
                visited[vertex] = true;
                int dist = distance[curr] + 1;
                if (distance[vertex] > dist) {
                    distance[vertex] = dist;
                    queue.add(vertex);
                    prev[vertex] = curr;
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

            graph.get(vertexFrom).add(vertexTo);
            graph.get(vertexTo).add(vertexFrom);
        }

        bfs();
        if (distance[vertexTo] == Integer.MAX_VALUE) {
            out.println(-1);
        } else {
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
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
