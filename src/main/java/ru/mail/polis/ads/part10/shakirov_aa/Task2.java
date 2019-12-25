package ru.mail.polis.ads.part10.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Task2 {
    static List<Edge> edgesList[];
    static boolean visitedVertex[];
    static int currentWeightMst;


    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        int m = fs.nextInt();

        edgesList = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            edgesList[i] = new LinkedList<>();
        }

        visitedVertex = new boolean[n + 1];
        for (int i = 1; i <= m; i++) {
            int b = fs.nextInt();
            int e = fs.nextInt();
            int w = fs.nextInt();

            edgesList[b].add(new Edge(e, w));
            edgesList[e].add(new Edge(b, w));
        }

        currentWeightMst = 0;
        primFindMst(1);
        System.out.println(currentWeightMst);
    }

    private static void primFindMst(int start) {
        visitedVertex[start] = true;
        Queue<Edge> queue = new PriorityQueue<>();
        addEdges(queue, start);

        while (!queue.isEmpty()) {
            Edge minEdge = queue.poll();
            if (visitedVertex[minEdge.vertex] == false) {
                currentWeightMst += minEdge.weight;
                visitedVertex[minEdge.vertex] = true;
                addEdges(queue, minEdge.vertex);
            }
        }
    }

    private static void addEdges(Queue<Edge> queue, int vertex) {
        for (Edge edge : edgesList[vertex]) {
            if (visitedVertex[edge.vertex] == true) {
                continue;
            }
            queue.add(edge);
        }
    }

    private static class Edge implements Comparable<Edge> {
        private int vertex;
        private int weight;

        public Edge(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
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
