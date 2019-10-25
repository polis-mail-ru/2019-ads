package ru.mail.polis.ads.part5.art241111;


import java.io.*;
import java.util.StringTokenizer;


public class PatternAndWord {

    private static boolean analyze(String word, String pattern){
        int n = word.length();
        int m = pattern.length();

        boolean[][] d = new boolean[n + 1][m + 1];
        d[0][0] = true;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (word.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                } else d[i][j] = false;
            }
        }
        return d[n][m];
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String pattern = in.next();
        String word = in.next();

        if (!pattern.contains("*") && !pattern.contains("?")) {
            String helper = pattern;
            pattern = word;
            word = helper;
        }

        if (analyze(word,pattern)) out.println("YES"); else out.println("NO");
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