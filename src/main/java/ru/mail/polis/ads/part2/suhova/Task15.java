package ru.mail.polis.ads.part2.suhova;


import java.io.*;
import java.util.StringTokenizer;

public class Task15 {
    /*
    https://www.e-olymp.com/ru/submissions/5795815
     */
    private static int[][] d;
    private static char[][] nav;

    private static void solve(final Task15.FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();
        d = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            d[0][i] = -1;
        }

        nav = new char[m + 1][n + 1];
        for (int i = m; i > 0; i--) {
            for (int j = 1; j <= n; j++) {
                d[i][j] = in.nextInt();
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (d[i][j - 1] > d[i - 1][j]) {
                    d[i][j] += d[i][j - 1];
                    nav[i][j] = 'R';
                } else {
                    nav[i][j] = 'F';
                    d[i][j] += d[i - 1][j];
                }
            }
        }
        StringBuilder res = new StringBuilder();
        int i = m, j = n;
        while (i > 1 || j > 1) {
            res.insert(0, nav[i][j]);
            if (nav[i][j] == 'F') i--;
            else j--;
        }
        if (i == 1) {
            for (int x = 1; x < j; x++) res.insert(0, "R");
        } else {
            for (int x = 1; x < i; x++) res.insert(0, "F");
        }
        out.println(res);
        out.flush();
    }

    public static void main(final String[] arg) {
        final Task15.FastScanner in = new Task15.FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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