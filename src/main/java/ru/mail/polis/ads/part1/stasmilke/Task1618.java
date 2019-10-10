package ru.mail.polis.ads.part1.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task1618 {
    private Task1618() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int nFirst = in.nextInt();
        int[] arrFirst = new int[nFirst];
        for (int i = 0; i < nFirst; i++) {
            arrFirst[i] = in.nextInt();
        }
        int nSecond = in.nextInt();
        int[] arrSecond = new int[nSecond];
        for (int i = 0; i < nSecond; i++) {
            arrSecond[i] = in.nextInt();
        }
        int[][] d = new int[nFirst + 1][nSecond + 1];
        for (int i = 1; i <= nFirst; i++) {
            for (int j = 1; j <= nSecond; j++) {
                if (arrFirst[i - 1] == arrSecond[j - 1]) {
                    d[i][j] = d[i - 1][j - 1] + 1;
                } else {
                    d[i][j] = Math.max(d[i][j - 1], d[i - 1][j]);
                }
            }
        }
        out.println(d[nFirst][nSecond]);
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
