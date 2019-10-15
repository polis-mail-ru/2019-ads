package ru.mail.polis.ads.part1.Kungurov;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by kirill.kungurov on 14.10.19
 * https://www.e-olymp.com/ru/submissions/5860724
 */
public final class SolveOfProblem1618 {
    private SolveOfProblem1618() {
        // Should not be instantiated
    }

    private static int solve(int[] firstSequence, int[] secondSequence) {
        int firstCount = firstSequence.length;
        int secondCount = secondSequence.length;

        int[][] matrix = new int[firstCount + 1][secondCount + 1];
        for (int i = 1; i < firstCount + 1; i++) {
            for (int j = 1; j < secondCount + 1; j++) {
                if (firstSequence[i - 1] == secondSequence[j - 1]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = Math.max(matrix[i - 1][j], matrix[i][j - 1]);
                }
            }
        }
        return matrix[firstCount][secondCount];
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
        final PrintWriter out = new PrintWriter(System.out);

        int firstCount = in.nextInt();
        int[] firstSequence = new int[firstCount];
        for (int i = 0; i < firstCount; i++) {
            firstSequence[i] = in.nextInt();
        }

        int secondCount = in.nextInt();
        int[] secondSequence = new int[secondCount];
        for (int i = 0; i < secondCount; i++) {
            secondSequence[i] = in.nextInt();
        }

        out.println(solve(firstSequence, secondSequence));
        out.flush();
    }
}
