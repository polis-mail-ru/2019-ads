package ru.mail.polis.ads.part9.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Exercise_4 {

    private static class Edge {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    private static final int NO_PATH = -1;
    private static Edge[] edges;
    private static int n;
    private static int m;
    private static int s;
    private static int f;


    public static void main(String[] args) {
        FastScanner scanner = new FastScanner(System.in);

        n = scanner.nextInt();
        m = scanner.nextInt();

        s = scanner.nextInt();
        f = scanner.nextInt();

        edges = new Edge[m];

        int[] dynamicArray = new int[n + 1];
        Arrays.fill(dynamicArray, NO_PATH);
        dynamicArray[1] = 0;

        for (int i = 0; i < m; i++) {
            int start = scanner.nextInt();
            int end = scanner.nextInt();
            int weight = scanner.nextInt();
            edges[i] = new Edge(start, end, weight);
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
}
