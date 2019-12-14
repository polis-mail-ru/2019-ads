package ru.mail.polis.ads.part10.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class ForthTask {

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    private static class Node {

        private int id;
        private byte color;
        private List<Node> links;

        public Node(int id) {
            this.id = id;
            this.color = 0;
            this.links = new ArrayList<>();
        }
    }

    private static int n;
    private static int m;
    private static Node[] nodes;
    private static Node[] backNodes;
    private static boolean[] used;
    private static int[] colors;
    private static Deque<Integer> stack;

    private static void solve() {
        FastScanner fastScanner = new FastScanner(System.in);
        n = fastScanner.nextInt();
        m = fastScanner.nextInt();

        nodes = new Node[n + 1];
        backNodes = new Node[n + 1];
        colors = new int[n + 1];
        used = new boolean[n + 1];
        Arrays.fill(used, false);
        Arrays.fill(colors, -1);

        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new Node(i);
            backNodes[i] = new Node(i);
        }

        for (int i = 0; i < m; i++) {
            int from = fastScanner.nextInt();
            int to = fastScanner.nextInt();

            if (from == to) {
                continue;
            }

            nodes[from].links.add(nodes[to]);
            backNodes[to].links.add(backNodes[from]);
        }

        stack = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                dfs1(nodes[i]);
            }
        }

        int color = 0;

        for (int i = 1; i <= n; i++) {
            int id = stack.pop();
            if (colors[id] == -1) {
                dfs2(backNodes[id], color++);
            }
        }

        System.out.println(color + 1);
    }

    private static void dfs1(Node node) {
        used[node.id] = true;
        node.links.forEach(tempNode ->{
            if (!used[tempNode.id]) {
                dfs1(tempNode);
            }
        });
        stack.push(node.id);
    }

    private static void dfs2(Node node, int color) {
        colors[node.id] = color;
        node.links.forEach(tempNode -> {
            if (colors[tempNode.id] == -1) {
                dfs2(tempNode, color);
            }
        });
    }

    public static void main(String[] args) {
        solve();
    }
}
