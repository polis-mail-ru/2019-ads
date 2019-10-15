package ru.mail.polis.ads.part2.Kondrat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Mouse {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        int[][] floor = new int[m][n];
        for (int i = m - 1; i >= 0; i--)
            for (int j = 0; j < n; j++)
                floor[i][j] = in.nextInt();
        long[][] maxValue = new long[m][n];
        maxValue[0][0] = floor[0][0];
        for (int i = 1; i < n; i++)
            maxValue[0][i] = maxValue[0][i - 1] + floor[0][i];
        for (int i = 1; i < m; i++)
            maxValue[i][0] = maxValue[i - 1][0] + floor[i][0];
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                maxValue[i][j] = Long.max(maxValue[i - 1][j], maxValue[i][j - 1]) + floor[i][j];
            }
        char[] path = new char[n + m - 2];
        int x = n - 1;
        int y = m - 1;
        for (int i = n + m - 3; i >= 0; i--) {
            if (x == 0){
                path[i] = 'F';
                y--;
            }
            else if (y == 0){
                path[i] = 'R';
                x--;
            }
            else if (maxValue[y-1][x] > maxValue[y][x-1]){
                path[i] = 'F';
                y--;
            }
            else{
                path[i] = 'R';
                x--;
            }
        }
        out.write(String.valueOf(path));
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
