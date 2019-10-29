package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4039 {
    private Task4039() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5975447
    }

    final static PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());

    private static void solve(final FastScanner in, final PrintWriter out) {
        int num = in.nextInt();
        for (int i = 0; i < num; i++) {
            int command = in.nextInt();
            if(command == 0) {
                heap.add(in.nextInt());
            }
            if(command == 1) {
                out.println(heap.poll());
            }
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
