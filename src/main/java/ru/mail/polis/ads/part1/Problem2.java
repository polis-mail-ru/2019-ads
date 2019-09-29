package ru.mail.polis.ads.part1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5740640
 */
public final class Problem2 {
    private Problem2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        if (str.isEmpty()) {
            return;
        }
        int length = str.length();
        String[][] shortest = new String[length][length];

        for (int len = 1; len <= length; len++) {
            for (int left = 0; left < length - len + 1; left++) {
                int right = left + len - 1;
                if (len == 1) {
                    if (str.charAt(left) == '(' || str.charAt(right) == ')') {
                        shortest[left][right] = "()";
                    } else if (str.charAt(left) == '[' || str.charAt(right) == ']') {
                        shortest[left][right] = "[]";
                    }
                } else if (len == 2) {
                    if (str.charAt(left) == '(' && str.charAt(right) == ')') {
                        shortest[left][right] = "()";
                    } else if (str.charAt(left) == '[' && str.charAt(right) == ']') {
                        shortest[left][right] = "[]";
                    } else {
                        shortest[left][right] = shortest[left][left] + shortest[right][right];
                    }
                } else {
                    String dot = ".";
                    String min = dot.repeat(201);
                    for (int i = left; i < right; i++) {
                        String currentShortest = shortest[left][i] + shortest[i + 1][right];
                        if (min.length() > currentShortest.length()) {
                            min = currentShortest;
                        }
                    }
                    if (
                        str.charAt(left) == '(' && str.charAt(right) == ')'
                            || str.charAt(left) == '[' && str.charAt(right) == ']'
                    ) {
                        String newStr = str.charAt(left) + shortest[left + 1][right - 1] + str.charAt(right);
                        if (newStr.length() < min.length()) {
                            min = newStr;
                        }
                    }
                    shortest[left][right] = min;
                }
            }
        }

        System.out.println(shortest[0][length - 1]);
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