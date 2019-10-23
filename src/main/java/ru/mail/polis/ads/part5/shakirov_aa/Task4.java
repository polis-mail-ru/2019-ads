package ru.mail.polis.ads.part5.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task4 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        String s1 = in.next();
        String s2 = in.next();

        if (s1.contains("*") || s1.contains("?")) {
            solve(s1, s2);
        } else {
            solve(s2, s1);
        }
    }

    static void solve(String pattern, String word) {
        int patternLength = pattern.length();
        int wordLength = word.length();

        boolean[][] d = new boolean[wordLength + 1][patternLength + 1];
        d[0][0] = true;

        for (int i = 1; i <= wordLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                if (word.charAt(i-1) == pattern.charAt(j-1) || pattern.charAt(j-1) == '?') {
                    d[i][j] = d[i-1][j-1];
                } else if (pattern.charAt(j-1) == '*') {
                    d[i][j] = d[i-1][j] || d[i-1][j-1] || d[i][j-1];
                } else {
                    d[i][j] = false;
                }
            }
        }

        if (d[wordLength][patternLength]) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
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
}
