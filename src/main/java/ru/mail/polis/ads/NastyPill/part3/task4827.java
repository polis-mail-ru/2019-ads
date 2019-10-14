package ru.mail.polis.ads.NastyPill.part3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.*;

/**
 * Problem solution template.
 */
public final class task4827 {
    private task4827() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5847439
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        Set<BigInteger> set = new TreeSet<>();
        String[] s = in.next().split(" +");
        int size = s.length;
        for (int i = 0; i < size; i++) {
            set.add(new BigInteger(s[i]));
        }
        ArrayList<BigInteger> list = new ArrayList<>(set);
        out.println(list.get(list.size() - n));
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
                    tokenizer = new StringTokenizer(reader.readLine(), "\n");
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
