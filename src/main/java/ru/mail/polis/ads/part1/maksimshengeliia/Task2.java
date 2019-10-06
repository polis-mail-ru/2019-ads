package ru.mail.polis.ads.part1.maksimshengeliia;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * https://www.e-olymp.com/ru/submissions/5784158
 * */
public class Task2 {
    private Task2() {
        // Should not be instantiated
    }
    static StringBuilder finalString = new StringBuilder();
    private static void solve(final FastScanner in, final PrintWriter out) {
        String string = new Scanner(System.in).nextLine();
        int length = string.length();
        if (string.isEmpty()) {
            System.out.println();
            return;
        }
        int[][] dynamic = new int[length][length];
        int[][] split = new int[length][length];
        for (int j = 0; j < length; j++) {
            for(int i = j; i >= 0; i--) {
                if (i == j) {
                    dynamic[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int minSplit = -1;
                if (string.charAt(i) == '(' && string.charAt(j) == ')' ||
                string.charAt(i) == '[' && string.charAt(j) == ']') {
                    min = dynamic[i + 1][j - 1];
                }

                for (int k = i; k < j; k++) {
                    if (dynamic[i][k] + dynamic[k + 1][j] < min) {
                        min = dynamic[i][k] + dynamic[k + 1][j];
                        minSplit = k;
                    }
                    dynamic[i][j] = min;
                    split[i][j] = minSplit;
                }
            }
        }
        //System.out.println(dynamic[0][length - 1]);
        restore(0 , length -1, string, dynamic, split);
        System.out.println(finalString);
    }

    static void restore(int i, int j, String string, int[][] dynamic, int[][] split) {
        if (i == j) {
            switch (string.charAt(i)) {
                case '(':
                case ')':
                    finalString.append("()");
                    break;
                case '[':
                case ']':
                    finalString.append("[]");
                    break;
            }
            return;
        }
        if (dynamic[i][j] == 0) {
            finalString.append(string.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            finalString.append(string.charAt(i));
            restore(i + 1, j - 1, string, dynamic, split);
            finalString.append(string.charAt(j));
            return;
        }
        int k = split[i][j];
        restore(i, k, string, dynamic, split);
        restore(k + 1, j, string, dynamic, split);
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