package ru.mail.polis.ads.part5.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class Pattern {
    private static void solve(final FastScanner in, final PrintWriter out) {
        String input1 = in.next();
        String input2 = in.next();
        String p;
        String w;
        if (input1.contains("*") || input1.contains("?")) {
            p = input1;
            w = input2;
        } else {
            w = input1;
            p = input2;
        }
        boolean[][] d = new boolean[w.length() + 1][p.length() + 1];
        d[0][0] = true;
        for (int i = 1; i < w.length() + 1; i++) {
            for (int j = 1; j < p.length() + 1; j++) {
                if (w.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                } else {
                    d[i][j] = false;
                }
            }
        }
        /*for (int i = 0; i < w.length() + 1; i++) {
            System.out.println(Arrays.toString(d[i]));
        }*/
        System.out.println(d[w.length()][p.length()] ? "YES" : "NO");
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
