package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1457 {
    private Task1457() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/6067825
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int len = in.nextInt();
        int weight = in.nextInt();
        List<Integer> train = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            train.add(in.nextInt());
        }
        for (int i = 1; i < len; i++)
        {
            int j = i;
            int k = train.get(i);
            while (j > 0 && train.get(j - 1) > train.get(j)) {
                if (weight < train.get(j - 1) + k)
                {
                    out.println("No");
                    return;
                }
                train.set(j, train.get(--j));
            }
            train.set(j, k);
        }
        out.println("Yes");
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
