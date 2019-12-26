package ru.mail.polis.ads.part9.maksimshengeliia;

import java.io.*;
import java.util.*;

/*
*   https://www.e-olymp.com/ru/submissions/6415175
* */
public class Task5 {

    static int from;
    static int to;
    private static List<List<Integer>> graph = new ArrayList<>();
    private static PriorityQueue<Node> queue = new PriorityQueue<>();
    private static Map<Integer, Integer> parents = new HashMap<>();

    static class Node implements Comparable<Node>{
        int node;
        int dist = Integer.MAX_VALUE;

        public Node(int node, int value) {
            this.node = node;
            this.dist = value;
        }

        @Override
        public int compareTo(Node node) {
            return dist - node.dist;
        }
    }
    private static void solve(final FastScanner in, final PrintWriter out) {
        int a  = in.nextInt();
        int b  = in.nextInt();

        from  = in.nextInt();
        to  = in.nextInt();

        int[] costs = new int[a + 1];
        boolean[] visited = new boolean[a + 1];

        for (int i = 0; i <= a; i++) {
            graph.add(new ArrayList<>());
            costs[i] = Integer.MAX_VALUE;
        }
        for (int i = 0; i < b; i++) {
            int q = in.nextInt();
            int w = in.nextInt();

            graph.get(q).add(w);
            graph.get(w).add(q);
        }


        queue.add(new Node(from, 0));
        costs[from] = 0;

        Node node;
        while ((node = queue.poll()) != null) {
            if (visited[node.node]) continue;
            visited[node.node] = true;
            List<Integer> neighbors = graph.get(node.node);

            for (int n : neighbors) {
                int newDist = node.dist + 1;
                if (costs[n] > newDist) {
                    costs[n] = newDist;
                    queue.add(new Node(n, newDist));
                    parents.put(n, node.node);
                }
            }
        }

        if (costs[to] == Integer.MAX_VALUE) {
            out.println("-1");
            return;
        }

        out.println(costs[to]);


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
