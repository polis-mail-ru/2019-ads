package ru.mail.polis.ads.part1.shakirov_aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task4 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);

        int n1 = fs.nextInt();
        int[] sequence1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            sequence1[i] = fs.nextInt();
        }

        int n2 = fs.nextInt();
        int[] sequence2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            sequence2[i] = fs.nextInt();
        }

        int[][] d = new int[n1 + 1][n2 + 1];

        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {

                if (sequence1[i-1] == sequence2[j-1]) {
                    d[i][j] = d[i-1][j-1] + 1;
                } else {
                    d[i][j] = max(d[i - 1][j], d[i][j-1]);
                }
            }
        }

        System.out.println(d[n1][n2]);
    }

    public static int max(int a, int b) {
        if  (a > b) {
            return a;
        } else {
            return b;
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
