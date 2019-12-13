package ru.mail.polis.ads.part9.DiscreetDmitriy;

import java.io.*;
import java.util.StringTokenizer;

public class Task3FordBellman {

    private static class Node {
        int first;
        int second;
        int weight;

        Node(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        final int n = in.nextInt();
        final int m = in.nextInt();

        int[] answer = new int[n + 1];
        Node[] mass = new Node[m + 1];

        for (int i = 1; i <= n; i++) {
            answer[i] = 30_000;
        }

        answer[1] = 0;

        for (int i = 1; i <= m; i++) {
            mass[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= m; j++) {
                int first = mass[j].first;

                if (answer[first] == 30_000)
                    continue;

                int second = mass[j].second;
                int weight = mass[j].weight;

                answer[second] = Math.min(answer[second], weight + answer[first]);
            }
        }

        for (int i = 1; i <= n; i++) {
            out.print(answer[i] + " ");
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
