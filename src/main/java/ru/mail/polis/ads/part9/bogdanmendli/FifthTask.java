package ru.mail.polis.ads.part9.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class FifthTask {

    static class Node {
        private int color;
        private int id;
        private List<Node> links;

        Node(int color, int id) {
            this.color = color;
            this.id = id;
            this.links = new ArrayList<>();
        }

    }

    private static int[] distance;
    private static int[] parents;
    private static Queue<Node> links;

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] entered = br.readLine().split(" ");
        links = new ArrayDeque<>();
        int n = Integer.parseInt(entered[0]);
        int m = Integer.parseInt(entered[1]);
        entered = br.readLine().split(" ");
        int fromNode = Integer.parseInt(entered[0]);
        int toNode = Integer.parseInt(entered[1]);
        Node[] nodes = new Node[n + 1];
        distance = new int[n + 1];
        parents = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[fromNode] = 0;
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(0, i);
        }

        for (int i = 0; i < m; i++) {
            entered = br.readLine().split(" ");
            int from = Integer.parseInt(entered[0]);
            int to = Integer.parseInt(entered[1]);
            nodes[from].links.add(nodes[to]);

            nodes[to].links.add(nodes[from]);
        }

        deykstra(nodes[fromNode]);
        while (!links.isEmpty()) {
            deykstra(links.remove());
        }

        if (distance[toNode] == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(distance[toNode]);
        Deque<Integer> path = new ArrayDeque<>();
        path.push(toNode);
        do {
            toNode = parents[toNode];
            path.push(toNode);
        } while (toNode != fromNode);

        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    private static void deykstra(Node from) {
        from.color = 1;
        for (int i = 0; i < from.links.size(); i++) {
            Node to = from.links.get(i);
            if (distance[to.id] > distance[from.id] + 1) {
                distance[to.id] = distance[from.id] + 1;
                parents[to.id] = from.id;
            }
            if (to.color == 0) {
                links.add(to);
            }
        }
        from.color = 2;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
