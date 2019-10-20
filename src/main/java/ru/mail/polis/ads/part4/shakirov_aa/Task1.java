package ru.mail.polis.ads.part4.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int n = fs.nextInt();
        long[] array = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = fs.nextLong();
        }

        boolean isHeap = true;
        for (int i = 1; i < n / 2; i++) {
            if (array[i] > array[2 * i]) {
                isHeap = false;
            }

            if (array[i] > array[2 * i + 1]) {
                isHeap = false;
            }
        }

        if (isHeap) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
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

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
