package ru.mail.polis.ads.part9.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FirstTask {

    static class Node {
        private int color;
        private int id;
        private List<Node> links;

        Node(int id) {
            this.color = 0;
            this.id = id;
            this.links = new ArrayList<>();
        }
    }

    private static Node[] nodes;
    private static Deque<Integer> stack;

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stack = new ArrayDeque<>();
        String[] numbersNodesAndLinks = br.readLine().split(" ");
        final int n = Integer.parseInt(numbersNodesAndLinks[0]);
        final int m = Integer.parseInt(numbersNodesAndLinks[1]);
        nodes = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            String[] link = br.readLine().split(" ");
            int from = Integer.parseInt(link[0]);
            int to = Integer.parseInt(link[1]);

            nodes[from].links.add(nodes[to]);
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
        for (Node tempNode : node.links) {
            dfs(tempNode);
        }
        node.color = 2;
        stack.push(node.id);
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}