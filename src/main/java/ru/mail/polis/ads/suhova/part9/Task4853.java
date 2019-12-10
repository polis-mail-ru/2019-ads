package ru.mail.polis.ads.suhova.part9;


import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task 5: https://www.e-olymp.com/ru/submissions/6244980
 */
public class Task4853 {
    private static int[] dist;
    private static int[] parent;
    private static ArrayList<ArrayDeque<Integer>> edges;

    public static void main(final String[] arg) {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        int n, m, s, f;
        n = in.nextInt();
        m = in.nextInt();
        s = in.nextInt();
        f = in.nextInt();
        dist = new int[n + 1];
        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dist[i] = -1;
        }
        dist[s] = 0;
        edges = new ArrayList<>(n + 1);
        for (int i = 0; i <= n; i++) {
            edges.add(new ArrayDeque<>());
        }
        for (int i = 0; i < m; i++) {
            int start = in.nextInt();
            int end = in.nextInt();
            edges.get(start).push(end);
            edges.get(end).push(start);
        }
        bfs(s);
        if (dist[f] != -1) {
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
    private static void bfs(int start) {
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        dist[start]=0;
        queue.addLast(start);
        while(!queue.isEmpty()){
            Integer node = queue.pollFirst();
            for (Integer u : edges.get(node)) {
                if(dist[u]==-1){
                    queue.addLast(u);
                    dist[u]=dist[node]+1;
                    parent[u] = node;
                }
            }
        }
    }
}