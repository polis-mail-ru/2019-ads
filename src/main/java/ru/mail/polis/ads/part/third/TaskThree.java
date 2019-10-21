package ru.mail.polis.ads.part.third;

import java.io.*;
import java.util.StringTokenizer;


/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5917103
 */
public class TaskThree {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] a = new int[n];
        int x = 0;
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = n - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (a[j] > a[j + 1]) {
                    int k = a[j + 1];
                    a[j + 1] = a[j];
                    a[j] = k;
                    x++;
                }
            }
        }
        out.print(x);
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
