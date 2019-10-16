package ru.mail.polis.ads.part3.Kungurov;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * created by kirill kungurov on 16.10.19
 * https://www.e-olymp.com/ru/submissions/5873306
 */
public class SolveOfProblem3738 {
    private SolveOfProblem3738() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = in.nextInt();
        }
        Arrays.sort(arr);
        for (int i = 0; i < count; i++) {
            out.print(arr[i] + " ");
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

