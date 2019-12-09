package ru.mail.polis.ads.part9.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FifthTask {

    static class Node {
        private int id;
        private List<Node> links;

        Node(int id) {
            this.id = id;
            this.links = new LinkedList<>();
        }

    }

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] entered = br.readLine().split(" ");
        Queue<Integer> queue = new ArrayDeque<>();
        int n = Integer.parseInt(entered[0]);
        int m = Integer.parseInt(entered[1]);
        entered = br.readLine().split(" ");
        int fromNode = Integer.parseInt(entered[0]);
        int toNode = Integer.parseInt(entered[1]);

        Node[] nodes = new Node[n + 1];
        int[] distance = new int[n + 1];
        int[] parents = new int[n + 1];

        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(parents, -1);

        distance[fromNode] = 0;
        parents[fromNode] = fromNode;

        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            entered = br.readLine().split(" ");
            int from = Integer.parseInt(entered[0]);
            int to = Integer.parseInt(entered[1]);
            nodes[from].links.add(nodes[to]);

            nodes[to].links.add(nodes[from]);
        }

        queue.add(fromNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == toNode) {
                break;
            }

            for (Node subNode : nodes[node].links) {
                int idSubNode = subNode.id;
                if (parents[idSubNode] == -1) {
                    parents[idSubNode] = node;
                    distance[idSubNode] = distance[node] + 1;
                    queue.add(idSubNode);
                }
            }
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

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
