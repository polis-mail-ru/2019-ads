package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Task5 {
    static class Node {
        private int color;
        private int id;
        private List<Node> edge;

        Node(int color, int id) {
            this.color = color;
            this.id = id;
            this.edge = new ArrayList<>();
        }
    }


    private static int[] distance;
    private static int[] parents;
    private static Queue<Node> edges;

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        edges = new ArrayDeque<>();
        int n = Integer.parseInt(in[0]);
        int m = Integer.parseInt(in[1]);
        in = br.readLine().split(" ");
        int fromNode = Integer.parseInt(in[0]);
        int toNode = Integer.parseInt(in[1]);
        Node[] nodes = new Node[n + 1];
        distance = new int[n + 1];
        parents = new int[n + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[fromNode] = 0;
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(0, i);
        }

        for (int i = 0; i < m; i++) {
            in = br.readLine().split(" ");
            int from = Integer.parseInt(in[0]);
            int to = Integer.parseInt(in[1]);
            nodes[from].edge.add(nodes[to]);

            nodes[to].edge.add(nodes[from]);
        }

        deykstra(nodes[fromNode]);
        while (!edges.isEmpty()) {
            deykstra(edges.remove());
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
        for (int i = 0; i < from.edge.size(); i++) {
            Node to = from.edge.get(i);
            if (distance[to.id] > distance[from.id] + 1) {
                distance[to.id] = distance[from.id] + 1;
                parents[to.id] = from.id;
            }
            if (to.color == 0) {
                edges.add(to);
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

