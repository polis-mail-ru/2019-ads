package ru.mail.polis.ads.part9.DiscreetDmitriy;

import java.io.*;
import java.util.*;

public class Task2CyclesInGraph {

    enum Color {
        WHITE, GREY, BLACK, CYCLE
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();

        PriorityQueue<Integer>[] queues = new PriorityQueue[n + 1];
        Stack<Integer> stack = new Stack<>();

        Color[] colors = new Color[n + 1];
        int[] prev = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            queues[i] = new PriorityQueue<>(8);
            colors[i] = Color.WHITE;
            prev[i] = -1;
        }

        final int m = in.nextInt();

        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();

            queues[a].add(b);
            queues[b].add(a);
        }

        int min1 = n + 1;

        for (int i = 1; i < n + 1; i++) {
            if (min1 < i + 1)
                break;

            boolean hasCycle = false;

            if (colors[i] == Color.WHITE)
                stack.push(i);


            while (!stack.isEmpty()) {
                Integer current = stack.peek();

                if (colors[current] == Color.CYCLE) {
                    hasCycle = false;
                    min1 = Math.min(current, min1);
                }

                colors[current] = Color.GREY;

                boolean canContinue = false;

                while (!queues[current].isEmpty()) {

                    if (hasCycle) break;

                    int next = queues[current].poll();

                    if (next == prev[current]) continue;

                    if (colors[next] == Color.GREY) {
                        hasCycle = true;
                        colors[next] = Color.CYCLE;

                        break;
                    }

                    if (colors[next] == Color.WHITE) {
                        prev[next] = current;
                        stack.push(next);
                        canContinue = true;

                        break;
                    }
                }

                if (canContinue) continue;

                if (hasCycle) min1 = Math.min(current, min1);

                if (colors[current] != Color.WHITE) {
                    colors[current] = Color.BLACK;
                    stack.pop();
                }
            }
        }
        int min = min1;

        if (min == n + 1) {
            out.println("No");
        } else {
            out.println("Yes");
            out.println(min);
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
