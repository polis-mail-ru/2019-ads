package ru.mail.polis.ads.part.fourth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5973023
 */
public class TaskSeven {

    private static void solve(FastScanner in, PrintWriter out) {
        int[] array = new int[in.nextInt()];
        int n = in.nextInt();
        int left = 0;
        int j = 0;
        int right = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
        right = array[array.length - 1] - array[0];
        if (n == 2) {
            out.println(right);
        } else {
            while (left < right) {
                int cowResidue = n - 1;
                int m = (left + right) / 2;
                for (int i = 1; i < array.length; i++) {
                    if (m <= array[i] - array[j]) {
                        j = i;
                        cowResidue--;
                    }
                }
                if (cowResidue > 0) {
                    right = m;
                } else {
                    left = m + 1;
                }
                j = 0;
            }
            out.println(left - 1);
        }
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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