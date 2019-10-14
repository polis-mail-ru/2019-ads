package ru.mail.polis.ads.part2.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int arr[] = new int[n + 2];
        for (int i = 1; i < n + 1; ++i) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();
        int[] matrix = new int[n + 2];
        int max = 0;
        for (int i = 1; i < n + 2; ++i) {
            max = Integer.MIN_VALUE;
            for (int j = (k > i) ? 0 : i - k; j < i; ++j) {
                if (matrix[j] > max) {
                    max = matrix[j];
                }
            }
            matrix[i] = max + arr[i];
        }
        out.println(matrix[n + 1]);
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
