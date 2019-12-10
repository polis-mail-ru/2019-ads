package ru.mail.polis.ads.suhova.part9;


import java.io.InputStreamReader;
import java.util.*;

/**
 * Task 4: https://www.e-olymp.com/ru/submissions/6244654
 */
public class Task4856 {
    private static int[] dist;
    private static int[] parent;
    private static boolean[] used;
    private static ArrayList<ArrayDeque<E>> edges;
    private static PriorityQueue<E> list = new PriorityQueue<>(Comparator.comparing(x -> dist[x.end]));

    public static void main(final String[] arg) {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        int n, m, s, f;
        n = in.nextInt();
        m = in.nextInt();
        s = in.nextInt();
        f = in.nextInt();
        dist = new int[n + 1];
        parent = new int[n + 1];
        used = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[s] = 0;
        edges = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            int w = in.nextInt();
            edges.get(start).push(new E(end, w));
            edges.get(end).push(new E(start, w));
        }

        list.add(new E(s, 0));
        while (!list.isEmpty()) {
            int node = list.poll().end;
            for (E e : edges.get(node)) {
                int newDist = dist[node] + e.w;
                if (!used[e.end] && newDist < dist[e.end]) {
                    dist[e.end] = newDist;
                    list.add(e);
                    parent[e.end] = node;
                }
                used[node] = true;
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

    private static class E {
        Integer end, w;

        E(Integer end, Integer w) {
            this.end = end;
            this.w = w;
        }
    }
}
