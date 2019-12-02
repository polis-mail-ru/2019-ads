package ru.mail.polis.ads.part9.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SecondTask {

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
    private static boolean isCyclic;
    private static int idFirstCyclicNode;
    private static Deque<Integer> stack;

    private static void solve() throws IOException {
        int minCyclicNode = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stack = new ArrayDeque<>();
        minCyclicNode = Integer.MAX_VALUE;
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
            nodes[to].links.add(nodes[from]);
        }

        sort();
        if (!stack.isEmpty()) {
            int index;
            while (!stack.isEmpty()) {
                index = stack.pop();
                if (minCyclicNode > index) {
                    minCyclicNode = index;
                }
            }
            System.out.println("Yes");
            System.out.println(minCyclicNode);
            return;
        }
        System.out.println("No");
    }

    private static void sort() {
        for (int i = nodes.length - 1; i > 0; i--) {
            dfs(nodes[i], 0);
        }
    }

    private static void dfs(Node node, int idLastNode) {
        if (node.color == 2 || isCyclic) {
            return;
        }
        if (node.color == 1) {
            idFirstCyclicNode = node.id;
            isCyclic = true;
            return;
        }
        stack.push(node.id);
        node.color = 1;
        for (Node tempNode : node.links) {
            if (tempNode.id != idLastNode) {
                dfs(tempNode, node.id);
            }
        }
        if (!isCyclic) {
            stack.remove(node.id);
        }

        if (idFirstCyclicNode == node.id) {
            isCyclic = false;
        }
        node.color = 2;
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
