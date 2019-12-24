package ru.mail.polis.ads.part9.maksimshengeliia;

import java.io.*;
import java.util.*;

public class Task4 {

    private static Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
    private static List<Integer> processed = new ArrayList<>();
    private static LinkedList<Integer> queue = new LinkedList<>();

    private static Integer findLowestCostNode(Map<Integer, Integer> costs) {
        Integer lowestCost = Integer.MAX_VALUE;
        Integer lowestCostNode = null;

        // Go through each node
        for (Map.Entry<Integer, Integer> node : costs.entrySet()) {
            Integer cost = node.getValue();
            // If it's the lowest cost so far and hasn't been processed yet...
            if (cost < lowestCost && !processed.contains(node.getKey())) {
                // ... set it as the new lowest-cost node.
                lowestCost = cost;
                lowestCostNode = node.getKey();
            }
        }

        return lowestCostNode;
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int a  = in.nextInt();
        int b  = in.nextInt();

        int from  = in.nextInt();
        int to  = in.nextInt();

        for (int i = 0; i < b; i++) {
            int q = in.nextInt();
            int w = in.nextInt();
            int e = in.nextInt();

            graph.computeIfAbsent(q, k -> new HashMap<>());
            graph.get(q).put(w, e);

            graph.computeIfAbsent(w, k -> new HashMap<>());
            graph.get(w).put(q, e);
        }


        // The costs table
        Map<Integer, Integer> costs = new HashMap<>();

        Map<Integer, Integer> friends = graph.get(from);
        for (Integer s : graph.keySet()) {
            if (s == from) {
                costs.put(s, 0);
                continue;
            }
            Integer friendCost = friends.get(s);
            if (friendCost != null) {
                costs.put(s, friendCost);
                continue;
            }
            costs.put(s, Integer.MAX_VALUE);
        }

        // the parents table
        Map<Integer, Integer> parents = new HashMap<>();

        Integer node = findLowestCostNode(costs);
        while (node != null) {
            Integer cost = costs.get(node);
            // Go through all the neighbors of this node

            Map<Integer, Integer> neighbors = graph.get(node);

            for (Integer n : neighbors.keySet()) {
                int newCost = cost + neighbors.get(n);
                // If it's cheaper to get to this neighbor by going through this node
                if (costs.get(n) > newCost) {
                    // ... update the cost for this node
                    costs.put(n, newCost);
                    // This node becomes the new parent for this neighbor.
                    parents.put(n, node);
                }
            }
            // Mark the node as processed
            processed.add(node);

            // Find the next node to process, and loop
            node = findLowestCostNode(costs);
        }

        //
        queue.offer(to);
        int curr = to;
        do {
            Set<Integer> set = graph.get(curr).keySet();
            for (Integer i : set) {
                int cost = costs.get(i);
                int c = graph.get(i).get(curr);
                if (c + cost == costs.get(curr)) {
                    queue.offer(i);
                    curr = i;
                    break;
                }
            }
        } while (curr != from);
        Collections.reverse(queue);

        out.println(costs.get(to));
        Integer variable;
        while ((variable = queue.poll()) != null) {
            out.print(variable + " ");
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
