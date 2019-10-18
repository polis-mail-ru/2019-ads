package ru.mail.polis.ads.part3.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Integer[] array = new Integer[n];

        for (int i = 0; i < n; ++i) {
            array[i] = in.nextInt();
        } 

        Arrays.sort(array, new TrickySort());

        for (Integer i: array) {
            out.print(i + " ");
        }
    }
    
    static class TrickySort implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            if (a % 10 == b % 10) {
              return a - b;
            } else {
              return a % 10 - b % 10;
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
