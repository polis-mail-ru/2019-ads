package ru.mail.polis.ads.part9.DiscreetDmitriy;

import java.io.*;
import java.util.*;

public class Task5DifferentShortestPath {
    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int m = in.nextInt();
        final int s = in.nextInt();
        final int f = in.nextInt();

        List<Integer>[] nodes = new LinkedList[n + 1];
        int[] dist = new int[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            dist[i] = -1;
            prev[i] = -1;
            nodes[i] = new LinkedList<>();
        }

        for (int i = 1; i <= m; ++i) {
            int first = in.nextInt();
            int second = in.nextInt();
            nodes[first].add(second);
            nodes[second].add(first);
        }

        dist[s] = 0;
        prev[s] = s;

        Queue<Integer> queue = new LinkedList<>() {{
            add(s);
        }};

        queue.add(s);

        while (!queue.isEmpty()) {
            Integer curr = queue.poll();

            if (curr == f)
                break;

            for (Integer i : nodes[curr])
                if (prev[i] == -1) {
                    prev[i] = curr;
                    dist[i] = dist[curr] + 1;
                    queue.add(i);
                }
        }

        out.println(dist[f]);

        if (dist[f] != -1) {
            int curr = f;

            Stack<Integer> stack = new Stack<>();

            while (curr != s) {
                stack.push(prev[curr]);
                curr = prev[curr];
            }

            while (!stack.empty())
                out.print(stack.pop() + " ");

            out.print(f);
            out.flush();
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
