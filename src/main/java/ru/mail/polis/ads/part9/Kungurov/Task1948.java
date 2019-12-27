package ru.mail.polis.ads.part9.Kungurov;

import java.io.*;
import java.util.*;

/**
 * https://www.e-olymp.com/ru/submissions/6419703
 */
public class Task1948 {

    private static Node[] nodes;
    private static Deque<Integer> stack;

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

    private static void solve() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        stack = new ArrayDeque<>();
        String[] arrs = br.readLine().split(" ");
        final int n = Integer.parseInt(arrs[0]);
        final int m = Integer.parseInt(arrs[1]);
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


    public static void main(final String[] arg) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
