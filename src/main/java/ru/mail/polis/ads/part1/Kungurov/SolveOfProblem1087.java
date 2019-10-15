package ru.mail.polis.ads.part1.Kungurov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * created by kirill.kungurov on 26.09.19
 * https://www.e-olymp.com/ru/submissions/5860355
 */
public final class SolveOfProblem1087 {
    private SolveOfProblem1087() {
        // Should not be instantiated
    }

    private static void solve(final BufferedReader in, final PrintWriter out) throws IOException {
        String sequence = in.readLine();
        int length = sequence.length();

        if (length == 0) {
            System.out.println();
            return;
        }

        int[][] matrix = new int[length][length];
        int[][] split = new int[length][length];

        for (int j = 0; j < length; j++)
            for (int i = j; i >= 0; i--) {
                if (i == j) {
                    matrix[i][j] = 1;
                    continue;
                }

                int min = Integer.MAX_VALUE;
                int splitMin = -1;

                if (sequence.charAt(i) == '(' && sequence.charAt(j) == ')' ||
                        sequence.charAt(i) == '[' && sequence.charAt(j) == ']')
                    min = matrix[i + 1][j - 1];

                for (int k = i; k < j; k++)
                    if (matrix[i][k] + matrix[k + 1][j] < min) {
                        min = matrix[i][k] + matrix[k + 1][j];
                        splitMin = k;
                    }
                matrix[i][j] = min;
                split[i][j] = splitMin;
            }
        restore(0, length - 1, sequence, matrix, split);
    }

    private static void restore(int i, int j, String s, int[][] d, int[][] split) {
        if (i == j) {
            switch (s.charAt(i)) {
                case '(':
                case ')':
                    System.out.print("()");
                    break;
                case '[':
                case ']':
                    System.out.print("[]");
                    break;
            }
            return;
        }
        if (d[i][j] == 0) {
            System.out.print(s.substring(i, j + 1));
            return;
        }
        if (split[i][j] == -1) {
            System.out.print(s.charAt(i));
            restore(i + 1, j - 1, s, d, split);
            System.out.print(s.charAt(j));
            return;
        }
        int k = split[i][j];
        restore(i, k, s, d, split);
        restore(k + 1, j, s, d, split);
    }


    public static void main(final String[] arg) throws IOException {
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
            out.flush();
        }
    }
}
