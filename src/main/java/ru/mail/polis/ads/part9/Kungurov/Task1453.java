package ru.mail.polis.ads.part9.Kungurov;


import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/6419645
 */
public class Task1453 {

    private static class Link {
        int from;
        int to;
        int weight;

        public Link(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static final int MAX = 30000;


    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();

        int[] distance = new int[n + 1];
        Arrays.fill(distance, MAX);
        distance[1] = 0;
        Link[] links = new Link[m];
        for (int i = 0; i < m; i++) {
            int from = scanner.nextInt();
            int to = scanner.nextInt();
            int weight = scanner.nextInt();
            links[i] = new Link(from, to, weight);
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int from = links[j].from;
                int to = links[j].to;
                int weight = links[j].weight;
                if (distance[from] != MAX) {
                    int min = Math.min(distance[to], distance[from] + weight);
                    distance[to] = min;
                }
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (distance[links[j].to] != MAX) {
                    int min = Math.min(distance[links[j].to], distance[links[j].from] + links[j].weight);
                    distance[links[j].to] = min;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(distance[i] + " ");
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
