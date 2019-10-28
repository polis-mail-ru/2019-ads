package ru.mail.polis.ads.part4.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task7 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        long[] array = new long[n];

        for (int i = 0; i < n; ++i) {
            array[i] = in.nextLong();
        }

        long maxDistance = array[array.length - 1] - array[0];

        if (k == 2) {
            out.println(maxDistance);

            return;
        }

        long minDistance = 0;
        long mid = 0;
        int counter = 0;
        int i = 0;

        while (minDistance != maxDistance) {
            counter = 1;
            mid = minDistance + (maxDistance - minDistance) / 2;
            i = 0;

            for (int j = 1; j < array.length; ++j) {
                if (array[j] - array[i] > mid) {
                    i = j;
                    counter++;
                }
            }

            if (counter < k) {
                maxDistance = mid;
            } else {
                minDistance = mid + 1;
            }
        }

        out.println(maxDistance);
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
