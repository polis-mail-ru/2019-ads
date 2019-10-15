package ru.mail.polis.ads.part2.DiscreetDmitriy;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task1MouseAndGrains {
    private Task1MouseAndGrains() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m = in.nextInt();
        int n = in.nextInt();

        int[][] field = new int[m + 1][n + 1];

        for (int[] row: field)
            Arrays.fill(row, -1);

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                field[m-i+1][j] = in.nextInt();

        field[0][1] = 0;

        for (int i = 1; i <= m; ++i)
            for (int j = 1; j <= n; ++j)
                field[i][j] = Math.max(field[i-1][j], field[i][j-1]) + field[i][j];

        char[] pos = new char[n * m];
        int index = 0;

        while (m + n > 2) {
            if (field[m-1][n] > field[m][n-1]) {
                pos[index] = 'F';
                m--;
            } else {
                pos[index] = 'R';
                n--;
            }
            index++;
        }

        while(index-- != 0)
            out.print(pos[index]);
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

