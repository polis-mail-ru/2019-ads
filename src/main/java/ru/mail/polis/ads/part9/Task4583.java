package ru.mail.polis.ads.part9;

import java.io.*;
import java.util.*;

/**
 * https://www.e-olymp.com/ru/submissions/6419738
 */
public class Task4583 {
    private static final int MAX = Integer.MAX_VALUE;

    static class Node {
        int node;
        int distance;

        int getDistance() {
            return distance;
        }

        Node(int node, int weight) {
            this.node = node;
            this.distance = weight;
        }
    }

    public static void solve() {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = in.nextInt();
        int m = in.nextInt();

        int s = in.nextInt();
        int f = in.nextInt();

        List<List<Node>> graph = new ArrayList<>(n + 1);

        boolean[] used = new boolean[n + 1];

        int[] distance = new int[n + 1];

        int[] previous = new int[n + 1];

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Comparator.comparing(Node::getDistance));

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = MAX;
        }
        distance[s] = 0;

        for (int i = 0; i < m; i++) {
            int b = Integer.parseInt(in.next());
            int e = Integer.parseInt(in.next());

            graph.get(b).add(new Node(e, 1));
            graph.get(e).add(new Node(b, 1));
        }

        priorityQueue.add(new Node(s, 0));

        while (!priorityQueue.isEmpty()) {
            int curr = priorityQueue.poll().node;
            if (used[curr])
                continue;
            used[curr] = true;
            for (Node node :
                    graph.get(curr)) {
                int b = node.node;
                int w = node.distance;
                if (distance[b] > distance[curr] + w) {
                    distance[b] = distance[curr] + w;
                    priorityQueue.add(new Node(b, distance[b]));
                    previous[b] = curr;
                }
            }
        }

        List<Integer> result = new LinkedList<>();
        if (distance[f] != MAX)
            System.out.println(distance[f]);
        else
            System.out.println(-1);
        while (previous[f] != 0) {
            result.add(0, f);
            f = previous[f];
        }
        result.add(0, s);
        for (int a : result) {
            System.out.print(a + " ");
        }
    }


    public static void main(String[] args) {
        solve();
    }
}