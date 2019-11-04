package ru.mail.polis.ads.part5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task264{
    private Task264() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/6013654
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int res = 0;
        int count = in.nextInt();
        int[] arr = new int[count];
        int[] d = new int[count];
        d[0] = 1;
        for (int i = 0; i < count; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 1; i < count; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] != 0 &&  arr[i] % arr[j] == 0 && d[j] > max) {
                    max = d[j];
                }
            }
            d[i] = max + 1;
            if(d[i] > res) {
                res = d[i];
            }
        }
        out.println(res);
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
