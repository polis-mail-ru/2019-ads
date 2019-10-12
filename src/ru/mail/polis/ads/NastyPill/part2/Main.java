package ru.mail.polis.ads.NastyPill.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Main {
    private Main() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5804067
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int m, n;
        n = in.nextInt();
        m = in.nextInt();
        int[][] floor = new int[102][102];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                floor[i][j] = in.nextInt();
            }
        }

        for (int i = 1; i < n; i++)
            floor[i][0] = floor[i][0] + floor[i - 1][0];
        for (int j = 1; j < m; j++)
            floor[0][j] = floor[0][j] + floor[0][j - 1];

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                floor[i][j] += Math.max(floor[i - 1][j], floor[i][j - 1]);
            }
        }
        int y = n - 1, x = m - 1;
        StringBuilder sb = new StringBuilder();
        while (y > 0 || x > 0) {
            if (y > 0 && x > 0) {
                if (floor[y - 1][x] > floor[y][x - 1]) {
                    sb.append("F");
                    y--;
                } else {
                    sb.append("R");
                    x--;
                }
            } else if (y == 0) {
                sb.append("R");
                x--;
            } else if (x == 0) {
                sb.append("F");
                y--;
            }
        }
        out.println(sb.reverse().toString());
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