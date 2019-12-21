package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * submission - https://www.e-olymp.com/ru/submissions/6401193
 */
public class Problem4 {

    static class Node {
        private int id;
        private List<Node> adjacent;

        Node(int id) {
            this.id = id;
            this.adjacent = new LinkedList<>();
        }

    }

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = br.readLine().split(" ");
        int n = Integer.parseInt(tokens[0]);
        int m = Integer.parseInt(tokens[1]);
        tokens = br.readLine().split(" ");
        int fromNode = Integer.parseInt(tokens[0]);
        int toNode = Integer.parseInt(tokens[1]);

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
            tokens = br.readLine().split(" ");
            int from = Integer.parseInt(tokens[0]);
            int to = Integer.parseInt(tokens[1]);

            nodes[from].adjacent.add(nodes[to]);
            nodes[to].adjacent.add(nodes[from]);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(fromNode);
        while (!queue.isEmpty()) {
            int node = queue.poll();
            if (node == toNode) {
                break;
            }

            for (Node subNode : nodes[node].adjacent) {
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
