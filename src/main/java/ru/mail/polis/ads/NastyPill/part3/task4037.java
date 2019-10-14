package ru.mail.polis.ads.NastyPill.part3;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Problem solution template.
 */
public final class task4037 {
    private task4037() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5847798
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new Pair<>(in.nextInt(), in.nextInt()));
        }
        list.sort(task4037::compare);
        for (int i = 0; i < n; i++) {
            out.println(list.get(i).getKey() + " " + list.get(i).getValue());
        }
    }

    public static int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
        return Integer.compare(p1.getKey(), p2.getKey());
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
