package ru.mail.polis.ads.marashov.alexander;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Task3 {

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

    private static class Edge implements Comparable<Edge> {
        int to;
        int weight;
        int from;
        Edge(int to, int weight, int from) {
            this.to = to;
            this.weight = weight;
            this.from = from;
        }

        @Override
        public int compareTo(Edge edge) {
            if (weight == edge.weight) {
                return edge.to - to;
            }
            return weight - edge.weight;
        }
    }

    public static void main(String[] args) {
        final int inf = 1000001;
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();

        Queue<Edge>[] graph = new Queue[n + 1];
        int[] dist = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dist[i] = inf;
            graph[i] = new PriorityQueue<>();
        }

        Map<Integer, Edge> graphMap = new TreeMap<>();
        for (int i = 1; i <= m; ++i) {
            final int first = in.nextInt();
            final int second = in.nextInt();
            final int weight = in.nextInt();
            Edge edge1 = graphMap.get(first);
            if (edge1 == null) {
                graphMap.put(first, new Edge(second, weight, 0));
                graphMap.put(second, new Edge(first, weight, 0));
            } else {
                if (weight < edge1.weight) {
                    edge1.weight = weight;
                    graphMap.get(second).weight = weight;
                }
            }
        }
        graphMap.forEach((integer, edge) -> graph[integer].add(edge));

        Queue<Edge> queue = new PriorityQueue<>(m + 1);
        Edge start = graph[1].poll();
        dist[1] = 0;
        dist[start.to] = start.weight;
        start.from = 1;
        queue.add(start);

        while (true) {
            Edge current = queue.poll();
            if (current.to == n) {
                System.out.println(dist[n]);
                System.exit(0);
            }

            while (!graph[current.to].isEmpty()) {
                Edge newEdge = graph[current.to].poll();
                int newWeight = Math.max(newEdge.weight, dist[current.to]);
                if (dist[newEdge.to] == inf || newWeight < dist[newEdge.to]) {
                    newEdge.from = current.to;
                    dist[newEdge.to] = newWeight;
                    queue.add(newEdge);
                    break;
                }
            }

            if (current.from != 0) {
                while (!graph[current.from].isEmpty()) {
                    Edge newEdge = graph[current.from].poll();
                    int newWeight = Math.max(newEdge.weight, dist[current.to]);
                    if (dist[newEdge.to] == inf || newWeight < dist[newEdge.to]) {
                        newEdge.from = current.to;
                        dist[newEdge.to] = newWeight;
                        queue.add(newEdge);
                        break;
                    }
                }
            }
        }

    }
}
