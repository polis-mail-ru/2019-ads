package ru.mail.polis.ads.part9.maksimshengeliia;

import java.io.*;
import java.util.*;

/*  https://www.e-olymp.com/ru/submissions/6414973
* */
public class Task4 {

    static int from;
    static int to;
    private static Map<Integer, List<Node>> graph = new HashMap<>();
    private static PriorityQueue<Node> notUsed = new PriorityQueue<>();
    private static Map<Integer, Integer> costs = new HashMap<>();
    private static Map<Integer, Integer> parents = new HashMap<>();

    static class Node implements Comparable<Node>{
        int node;
        int value;

        public Node(int node, int value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public int compareTo(Node node) {
            return value - node.value;
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int a  = in.nextInt();
        int b  = in.nextInt();

        from  = in.nextInt();
        to  = in.nextInt();

        for (int i = 0; i < b; i++) {
            int q = in.nextInt();
            int w = in.nextInt();
            int e = in.nextInt();

            graph.computeIfAbsent(q, k -> new ArrayList<>());
            graph.get(q).add(new Node(w, e));

            graph.computeIfAbsent(w, k -> new ArrayList<>());
            graph.get(w).add(new Node(q, e));
        }

        for (int s : graph.keySet()) {
            if (s == from) {
                notUsed.add(new Node(from, 0));
                costs.put(from, 0);
                continue;
            }
            notUsed.add(new Node(s, Integer.MAX_VALUE));
            costs.put(s, Integer.MAX_VALUE);
        }



        Node node;
        while ((node = notUsed.poll()) != null) {
            List<Node> neighbors = graph.get(node.node);

            for (Node n : neighbors) {
                int newCost = node.value + n.value;
                if (costs.get(n.node) > newCost) {
                    costs.put(n.node, newCost);
                    notUsed.removeIf(node1 -> node1.node == n.node);
                    notUsed.add(new Node(n.node, newCost));
                    parents.put(n.node, node.node);
                }
            }
        }

        if (costs.get(to) == Integer.MAX_VALUE) {
            out.println("-1");
            return;
        }

        out.println(costs.get(to));


        List<Integer> list = new ArrayList<>();
        list.add(to);
        Integer parent = parents.get(to);
        while(parent != null) {
            list.add(parent);
            parent = parents.get(parent);
        }
        Collections.reverse(list);
        for (Integer integer : list) {
            out.print(integer + " ");
        }
    }

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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
