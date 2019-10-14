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
public final class Task3 {
    
    private static void solve(final FastScanner in, final PrintWriter out) {
        String sequence = in.next();
        int size = sequence.length();
        String[][] matrix = new String[size][size];

        for (int j = 0; j < size; ++j) {
            for (int i = j; i >= 0; --i) {
                packSubsequence(sequence, i, j, matrix);
            }
        }
        out.println(matrix[0][size - 1]);
    }

    private static void packSubsequence(String sequence, int i, int j, String[][] matrix) {
        if (j - i + 1 <= 4) {
            matrix[i][j] = sequence.substring(i, j + 1);
            return;
        }

        String sub = sequence.substring(i, j + 1);
        int minLength = Integer.MAX_VALUE;
        String minSequence = "";

        for (int k = i; k < j; ++k) {
            if ((matrix[i][k].length() + matrix[k + 1][j].length()) <= minLength) {
                minSequence = matrix[i][k] + matrix[k + 1][j];
                minLength = minSequence.length();
            }
        }

        String repeated = getRepeated(sequence, sub, i, j, matrix);
        matrix[i][j] = minSequence.length() < repeated.length() ? minSequence : repeated;
    }

    private static String getRepeated(String sequence, String substring, int begin, int end, String[][] matrix) {
        int counter;
        String packed;
        int minLength = Integer.MAX_VALUE;
        String minSequence = substring;

        for (int j = 1; j <= substring.length() / 2; ++j) {
            counter = substring.length() / j;

            if (substring.length() % j == 0) {
                for (int i = begin; i <= end - j; ++i) {
                    if (sequence.charAt(i) != sequence.charAt(i + j)) {
                        counter = 0;
                        break;
                    }
                }
            } else {
                counter = 0;
            }

            if (counter > 0) {
                packed = getPacking(matrix[begin][begin + j - 1], substring.length() / j);
                if (packed.length() < minLength) {
                    minSequence = packed;
                    minLength = minSequence.length();
                }
            }
        }

        return minSequence;
    }

    private static String getPacking(String str, int strLength) {
        if (strLength == 1) {
            return str;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(strLength);
        builder.append('(');
        builder.append(str);
        builder.append(')');
        return builder.toString();
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
