package ru.mail.polis.ads.part5.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task991 {
    private Task991() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String first = in.next();
        String second = in.next();
        char[] pattern;
        char[] word;
        if (first.contains("*") || first.contains("?")) {
            pattern = first.toCharArray();
            word = second.toCharArray();
        } else {
            pattern = second.toCharArray();
            word = first.toCharArray();
        }
        boolean[][] d = new boolean[word.length + 1][pattern.length + 1];
        d[0][0] = true;
        for (int i = 1; i <= word.length; i++) {
            for (int j = 1; j <= pattern.length; j++) {
                if (word[i-1] == pattern[j-1] || pattern[j-1] == '?') {
                    d[i][j] = d[i-1][j-1];
                } else if (pattern[j-1] == '*') {
                    d[i][j] = d[i-1][j] || d[i-1][j-1] || d[i][j-1];
                } else {
                    d[i][j] = false;
                }
            }
        }
        out.println(d[word.length][pattern.length] ? "YES" : "NO");
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
            ex.printStackTrace();
        }
    }
}