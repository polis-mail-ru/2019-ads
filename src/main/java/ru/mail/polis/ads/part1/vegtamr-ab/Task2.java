package ru.mail.polis.ads.part1.vegtamr-ab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task2 {
    
    private static final char PAREN_OPEN = '(';
    private static final char PAREN_CLOSE = ')';
    private static final char BRCKT_OPEN = '[';
    private static final char BRCKT_CLOSE = ']';

    private static void solve(final FastScanner in, final PrintWriter out) {
        StringBuilder builder = new StringBuilder();
        String str = in.next();
        if (str.isEmpty()) {
            out.println();
            return;
        }

        int size = str.length();
        int[][] matrix = new int[size][size];
        int[][] split = new int[size][size];
        for (int j = 0; j < size; ++j) {
            for (int i = j; i >= 0; --i) {
                if (i == j) {
                    matrix[i][j] = 1;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                int splitMin = -1;
                if (str.charAt(i) == PAREN_OPEN && str.charAt(j) == PAREN_CLOSE ||
                        str.charAt(i) == BRCKT_OPEN && str.charAt(j) == BRCKT_CLOSE) {
                    min = matrix[i + 1][j - 1];
                }
                for (int k = i; k < j; ++k) {
                    if (matrix[i][k] + matrix[k + 1][j] < min) {
                        min = matrix[i][k] + matrix[k + 1][j];
                        splitMin = k;
                    }
                }

                matrix[i][j] = min;
                split[i][j] = splitMin;
            }
        }

        restore(0, size - 1, str, matrix, split, builder);
        out.println(builder.toString());
    }

    private static void restore(int i, int j, String str, int[][] matrix, int[][] split, StringBuilder builder) {
        if (i == j) {
            switch (str.charAt(i)) {
                case PAREN_OPEN:
                case PAREN_CLOSE:
                    builder.append("()");
                    break;
                case BRCKT_OPEN:
                case BRCKT_CLOSE:
                    builder.append("[]");
                    break;
            }
            return;
        }

        if (matrix[i][j] == 0) {
            builder.append(str.substring(i, j + 1));
            return;
        }

        if (split[i][j] == -1) {
            builder.append(str.charAt(i));
            restore(i + 1, j - 1, str, matrix, split, builder);
            builder.append(str.charAt(j));
            return;
        }

        int k = split[i][j];
        restore(i, k, str, matrix, split, builder);
        restore(k + 1, j, str, matrix, split, builder);
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
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
