package ru.mail.polis.ads.part9.nik27090;

import java.io.InputStreamReader;
import java.util.*;

// Задача: Крайчайший путь (1)
// Решение: https://www.e-olymp.com/ru/submissions/6414839

public class ShortesWay {
    private static boolean[] used;
    private static int[] dist;
    private static int[] parent;
    private static ArrayList<ArrayDeque<Edge>> graph;
    private static PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparing(x -> dist[x.vertex]));

    private static class Edge {
        int vertex;
        int w;

        Edge(int vertex, int w) {
            this.vertex = vertex;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        int n = sc.nextInt();
        int m = sc.nextInt();
        int s = sc.nextInt();
        int f = sc.nextInt();

        dist = new int[n + 1];
        parent = new int[n + 1];
        used = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[s] = 0;

        graph = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayDeque<>());
        }

        for (int i = 0; i < m; i++) {
            int in = sc.nextInt();
            int out = sc.nextInt();
            int weight = sc.nextInt();

            graph.get(in).push(new Edge(out, weight));
            graph.get(out).push(new Edge(in, weight));
        }

        pq.add(new Edge(s, 0));
        while (!pq.isEmpty()) {
            int v = pq.poll().vertex;
            for (Edge edge : graph.get(v)) {
                int newDist = dist[v] + edge.w;
                if (!used[edge.vertex] && newDist < dist[edge.vertex]) {
                    dist[edge.vertex] = newDist;
                    pq.add(edge);
                    parent[edge.vertex] = v;
                }
                used[v] = true;
            }
        }
        if (dist[f] != Integer.MAX_VALUE) {
            System.out.println(dist[f]);
            ArrayDeque<Integer> res = new ArrayDeque<>();
            int prev = parent[f];
            while (prev != 0) {
                res.addFirst(prev);
                prev = parent[prev];
            }
            for (Integer i : res) System.out.print(i + " ");
            System.out.print(f);
        } else {
            System.out.println(-1);
        }
    }
}
