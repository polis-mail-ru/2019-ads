package main.java.ru.mail.polis.ads.part5.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/6024647
 */

public class Task4 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final String word = in.next();
        final String pattern = in.next();

        if (word.contains("*") || word.contains("?")) {
            solve(word, pattern);
        } else {
            solve(pattern, word);
        }
    }

    private static void solve(String pattern, String word) {
        final int patternLength = pattern.length();
        final int wordLength = word.length();

        boolean[][] d = new boolean[wordLength + 1][patternLength + 1];
        d[0][0] = true;

        for (int i = 1; i <= wordLength; i++) {
            for (int j = 1; j <= patternLength; j++) {
                if (word.charAt(i - 1) == pattern.charAt(j - 1) || pattern.charAt(j - 1) == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (pattern.charAt(j - 1) == '*') {
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
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
}
