package ru.mail.polis.ads.part9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task1 {

    static class Node {
        private int color;
        private int i;
        private List<Node> edges;

        Node(int i) {
            this.color = 0;
            this.i = i;
            this.edges = new ArrayList<>();
        }
    }

    private static Node[] nodes;
    private static Deque<Integer> stack;

    private static void solve() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        stack = new ArrayDeque<>();
        String[] tmp = reader.readLine().split(" ");
        final int n = Integer.parseInt(tmp[0]);
        final int m = Integer.parseInt(tmp[1]);
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            String[] edge = reader.readLine().split(" ");
            int from = Integer.parseInt(edge[0]);
            int to = Integer.parseInt(edge[1]);
            nodes[from].edges.add(nodes[to]);
        }

        sort();

        stack.forEach(num -> System.out.print(num + " "));

    }

    private static void sort() {
        for (int i = 1; i < nodes.length; i++) {
            dfs(nodes[i]);
        }
    }

    private static void dfs(Node node) {
        if (node.color == 2) {
            return;
        }
        if (node.color == 1) {
            System.out.println(-1);
            System.exit(0);
        }
        node.color = 1;
        for (Node tempNode : node.edges) {
            dfs(tempNode);
        }
        node.color = 2;
        stack.push(node.i);
    }

    public static void main(String args[]) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
