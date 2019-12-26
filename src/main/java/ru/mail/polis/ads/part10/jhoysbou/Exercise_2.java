package ru.mail.polis.ads.part10.jhoysbou;

import java.io.*;
import java.util.*;

// Submission here https://www.e-olymp.com/ru/submissions/6416846

public class Exercise_2 {
    
    private static class Edge {
        int start;
        int end;
        int weight;

        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    static int [] previous;
    
    public static void main(String[] args){
        final FastScanner in = new FastScanner(System.in);
        final int n = in.nextInt();
        final int m = in.nextInt();
        
        if (m == 0) {
            System.out.println(0);
            System.exit(0);
        }

        previous = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            previous[i] = i;
        }

        Queue<Edge> queue = new PriorityQueue<>(n, Comparator.comparing(edge -> edge.weight));

        for (int i = 1; i <= m; ++i) {
            int start = in.nextInt();
            int end = in.nextInt();
            int weight = in.nextInt();

            queue.add(new Edge(start, end, weight));
        }

        int min = 0;
        int counter = n;

        while (counter != 1) {
            Edge edge = queue.poll();

            if (find(edge.start) != find(edge.end)) {
                union(edge.start, edge.end);
                min += edge.weight;
                counter--;
            }
        }

        System.out.println(min);

    }

    private static int find(int number) {
        if (number == previous[number]) {
            return number;
        }

        return previous[number] = find(previous[number]);
    }

    private static void union(int first, int second){
        first = find(first);
        second = find(second);

        if(first != second) {
            previous[second] = first;
        }
    }

    static class FastScanner {
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