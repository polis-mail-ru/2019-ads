package ru.mail.polis.ads.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        int m = fs.nextInt();
        int n = fs.nextInt();
        int[][] floor = new int[m][n];
        int[][] value = new int[m][n];
        String[][] way = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                floor[i][j] = fs.nextInt();
            }
        }


        value[m-1][0] = floor[m-1][0];
        way[m-1][0] = "";
        for (int i = m - 2; i >= 0; i--) {
            value[i][0] = value[i+1][0] + floor[i][0];
            way[i][0] = way[i+1][0] + "F";
        }
        for (int i = 1; i < n; i++) {
            value[m-1][i] = value[m-1][i-1] + floor[m-1][i];
            way[m-1][i] = way[m-1][i-1] + "R";
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = 1; j < n; j++) {
                if (value[i+1][j] >= value[i][j-1]) {
                    value[i][j] = value[i+1][j] + floor[i][j];
                    way[i][j] = way[i+1][j] + "F";
                } else {
                    value[i][j] = value[i][j-1] + floor[i][j];
                    way[i][j] = way[i][j-1] + "R";
                }
            }
        }

        System.out.println(way[0][n-1]);
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
