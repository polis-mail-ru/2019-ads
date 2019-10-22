package ru.mail.polis.ads.part5.gogun;

import java.io.*;
import java.util.StringTokenizer;

public class Task3 {
    static long[] array;
    static int[] d;
    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final BufferedInputStream in) {
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

    private static void solve(FastScanner input, PrintWriter output) {
        int n = input.nextInt();
        array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = input.nextLong();
        }
        d = new int[n];
        d[0] = 1;
        int ans = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if ((array[j] != 0) && (array[i] % array[j] == 0) && (d[j] > max))
                    max = d[j];
            }
            d[i] = max + 1;
            if (d[i] > ans)
                ans = d[i];
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {
        FastScanner in = new FastScanner(new BufferedInputStream(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out))) {
            solve(in, out);
        }
    }
}
