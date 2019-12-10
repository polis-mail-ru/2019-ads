package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task4 {
    static class Node {
        private int color;
        private int id;
        private List<Node> links;
        private List<Integer> weights;

        Node(int color, int id) {
            this.color = color;
            this.id = id;
            this.links = new ArrayList<>();
            this.weights = new ArrayList<>();
        }
    }

    private static int[] distance;
    private static int[] parents;

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] entered = br.readLine().split(" ");
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
            int weight = Integer.parseInt(entered[2]);
            nodes[from].links.add(nodes[to]);
            nodes[from].weights.add(weight);

            nodes[to].links.add(nodes[from]);
            nodes[to].weights.add(weight);
        }

        deykstra(nodes[fromNode]);

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
        Queue<Node> links = new ArrayDeque<>();
        for (int i = 0; i < from.links.size(); i++) {
            Node to = from.links.get(i);
            int weight = from.weights.get(i);
            if (distance[to.id] > distance[from.id] + weight) {
                distance[to.id] = distance[from.id] + weight;
                parents[to.id] = from.id;
            }
            if (to.color == 0) {
                links.add(to);
            }
        }
        from.color = 2;
        while (!links.isEmpty()) {
            deykstra(links.remove());
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch ( IOException e) {
            e.printStackTrace();
        }
    }
}


