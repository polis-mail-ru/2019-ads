package ru.mail.polis.ads.part4.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task5149 {
    private Task5149() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        out.println(findMaxDistance(array, k));
    }

    private static long findMaxDistance(long[] array, int k) {
        long left = 0;
        long right = array[array.length - 1] - array[0];
        long mid;
        int counter;
        int i;
        if (k == 2) {
            return right;
        }
        while (left != right) {
            counter = 1;
            mid = left + (right - left) / 2;
            i = 0;
            for (int j = 1; j < array.length; j++) {
                if (array[j] - array[i] > mid) {
                    i = j;
                    counter++;
                }
            }
            if (counter < k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
