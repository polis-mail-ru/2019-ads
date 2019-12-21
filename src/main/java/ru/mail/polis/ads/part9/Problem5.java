package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.Queue;


/**
 * submission - https://www.e-olymp.com/ru/submissions/6401189
 */
public class Problem5 {

    static class Node {
        private int color;
        private int id;
        private List<Node> adjacent;
        private List<Integer> weights;

        Node(int color, int id) {
            this.color = color;
            this.id = id;
            this.adjacent = new ArrayList<>();
            this.weights = new ArrayList<>();
        }

    }

    private static int[] distances;
    private static int[] parents;

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        tokens = br.readLine().split(" ");
        int fromNode = Integer.parseInt(tokens[0]);
        int toNode = Integer.parseInt(tokens[1]);
        Node[] nodes = new Node[n + 1];
        distances = new int[n + 1];
        parents = new int[n + 1];
        Arrays.fill(distances, Integer.MAX_VALUE);
        distances[fromNode] = 0;
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(0, i);
        }

        for (int i = 0; i < m; i++) {
            tokens = br.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);
            int weight = Integer.parseInt(tokens[2]);

            nodes[from].adjacent.add(nodes[to]);
            nodes[from].weights.add(weight);

            nodes[to].adjacent.add(nodes[from]);
            nodes[to].weights.add(weight);
        }

        dijkstra(nodes[fromNode]);

        if (distances[toNode] == Integer.MAX_VALUE) {
            System.out.println(-1);
            System.exit(0);
        }
        System.out.println(distances[toNode]);
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

    private static void dijkstra(Node from) {
        from.color = 1;
        Queue<Node> links = new ArrayDeque<>();
        for (int i = 0; i < from.adjacent.size(); i++) {
            Node to = from.adjacent.get(i);
            int weight = from.weights.get(i);
            if (distances[to.id] > distances[from.id] + weight) {
                distances[to.id] = distances[from.id] + weight;
                parents[to.id] = from.id;
            }
            if (to.color == 0) {
                links.add(to);
            }
        }
        from.color = 2;
        while (!links.isEmpty()) {
            dijkstra(links.remove());
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
