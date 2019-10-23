package ru.mail.polis.ads.part5.DiscreetDmitriy;

import java.io.*;
import java.util.StringTokenizer;

public class Task4PatternAndWord {
    private Task4PatternAndWord() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        String s1 = in.next();
        String s2 = in.next();

        String w;
        String p;

        if (s1.contains("*") || s1.contains("?")) {
            w = s2;
            p = s1;
        } else {
            w = s1;
            p = s2;
        }

        int n = w.length();
        int m = p.length();

        boolean[][] d = new boolean[n + 1][m + 1];
        d[0][0] = true;

        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= m; j++)
                if (w.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?')
                    d[i][j] = d[i - 1][j - 1];
                else if (p.charAt(j - 1) == '*')
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                else
                    d[i][j] = false;

        String ans;

        if (d[n][m]) ans = "YES";
        else ans = "NO";

        out.println(ans);
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

