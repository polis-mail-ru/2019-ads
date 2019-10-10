package ru.mail.polis.ads.part1.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task1087 {
    private Task1087() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        StringBuilder builder = new StringBuilder();
        String str = in.next();
        if (str.isEmpty()) {
            out.println();
            return;
        }
        int n = str.length();
        int[][] d = new int[n][n];
        int[][] split = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j; i>=0; i--) {
                if (i == j) {
                    d[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if (str.charAt(i) == '(' && str.charAt(j) == ')' ||
                        str.charAt(i) == '[' && str.charAt(j) == ']') {
                    min = d[i+1][j-1];
                }
                for (int k = i; k<j; k++) {
                    if (d[i][k] + d[k+1][j] < min) {
                        min = d[i][k] + d[k+1][j];
                        splitMin = k;
                    }
                }
                d[i][j] = min;
                split[i][j] = splitMin;
            }
        }
        restore(0,n-1, str, d, split, builder);
        out.println(builder.toString());
    }

    private static void restore(int i, int j, String s, int[][] d, int[][] split, StringBuilder builder) {
        if (i == j) {
            switch (s.charAt(i)) {
                case '(':
                case ')':
                    builder.append("()");
                    break;
                case '[':
                case ']':
                    builder.append("[]");
                    break;
            }
            return;
        }
        if (d[i][j] == 0) {
            builder.append(s.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            builder.append(s.charAt(i));
            restore(i+1, j-1, s, d, split, builder);
            builder.append(s.charAt(j));
            return;
        }
        int k = split[i][j];
        restore(i, k, s, d, split, builder);
        restore(k+1, j, s, d, split, builder);
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
