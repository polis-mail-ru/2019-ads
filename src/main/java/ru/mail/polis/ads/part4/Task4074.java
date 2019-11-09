package ru.mail.polis.ads.part4;

import java.io.*;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4074 {
    private Task4074() {
        // Should not be instantiated
    }

    //create your own heap with comparator

    final static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    final static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    private static void solve(final PrintWriter out) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        maxHeap.add(Integer.MIN_VALUE);
        minHeap.add(Integer.MAX_VALUE);
        int ctr = 0;
        int n = -1;
        while (true) {
            try {
                n = Integer.parseInt(br.readLine().trim());
            } catch (Exception e) {
                return;
            }
            if (n >= minHeap.peek()) {
                minHeap.add(n);
            } else {
                maxHeap.add(n);
            }

            if (maxHeap.size() > minHeap.size()) {
                minHeap.add(maxHeap.poll());
            } else if (maxHeap.size() + 1 < minHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
            if (ctr % 2 == 0) {
                out.println(minHeap.peek());
            } else {
                out.println((minHeap.peek() + maxHeap.peek()) / 2);
            }
            ctr++;
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
            solve(out);
        }
    }
}
