package ru.mail.polis.ads.part5.bogdanmendli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FourthTask {

    private static void solve() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String firstWord = in.readLine();
        String secondWord = in.readLine();
        String word;
        String pattern;
        if (firstWord.contains("*") || firstWord.contains("?")) {
            pattern = firstWord;
            word = secondWord;
        } else {
            pattern = secondWord;
            word = firstWord;
        }
        int n = pattern.length();
        int m = word.length();
        boolean[][] d = new  boolean[n + 1][m + 1];
        d[0][0] = true;

        for (int i= 1; i <= n; i++) {
            final char patternChar = pattern.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                final char wordChar = word.charAt(j - 1);
                if (patternChar == wordChar || patternChar == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (patternChar == '*') {
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                } else {
                    d[i][j] = false;
                }
            }
        }

        if (d[n][m]) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        try {
            solve();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
