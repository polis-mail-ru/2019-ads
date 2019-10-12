package ru.mail.polis.ads.part1.nastypill;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Problem solution template.
 */
public final class Task1618 {
    private Task1618() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5804738
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(0);
        list1.addAll(readList(in));
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(0);
        list2.addAll(readList(in));
        int[][] d = new int[2][Math.max(list1.size() + 1, list2.size() + 1)];
        for (int i = 1; i < list1.size(); i++) {
            for (int j = 1; j < list2.size(); j++) {
                if(list1.get(i).equals(list2.get(j))) {
                    d[i % 2][j] = d[(i + 1) % 2][j - 1] + 1;
                } else {
                    d[i % 2][j] = Math.max(d[(i + 1) % 2][j], d[i % 2][j - 1]);
                }
            }
        }
        out.println(d[(list1.size() - 1) % 2][list2.size() - 1]);
    }

    private static List<Integer> readList(final FastScanner in) {
        int n  = in.nextInt();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(in.nextInt());
        }
        return list;
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