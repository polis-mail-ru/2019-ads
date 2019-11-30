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

        Node(int color, int id) {
            this.color = color;
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
            nodes[i] = new Node(0, i);
        }

        for (int i = 0; i < m; i++) {
            String[] link = br.readLine().split(" ");
            int from = Integer.parseInt(link[0]);
            int to = Integer.parseInt(link[1]);

            nodes[from].links.add(nodes[to]);
        }

        sort();
        if (stack.isEmpty()) {
            System.out.print(-1);
            return;
        }
        stack.forEach(num -> System.out.print(num + " "));
        System.out.println();
    }

    private static void sort() {
        for (int i = 1; i < nodes.length; i++) {
            if (nodes[i].color == 0) {
                dfs(nodes[i]);
            }
        }
    }

    private static void dfs(Node node) {
        node.color = 1;
        node.links.forEach(tempNode -> {
            if (tempNode.color == 1) {
                System.out.println(-1);
                System.exit(0);
            }
            if (tempNode.color == 0) {
                dfs(tempNode);
            }
        });
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