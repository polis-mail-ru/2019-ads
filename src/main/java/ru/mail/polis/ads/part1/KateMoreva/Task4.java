package ru.mail.polis.ads.part1.KateMoreva;

import java.io.PrintWriter;
import java.util.Scanner;

//e-olymp problem 1618 "Наибольшая общая подпоследовательность"

public final class Task4 {
    private Task4() {

    }

    private static void solve(final Scanner in, final PrintWriter out) {
        final int sizeN = in.nextInt();
        int[] sequence1 = new int[sizeN];
        for (int i = 0; i < sizeN; ++i) {
            sequence1[i] = in.nextInt();
        }

        final int sizeM = in.nextInt();
        int[] sequence2 = new int[sizeM];
        for (int i = 0; i < sizeM; ++i) {
            sequence2[i] = in.nextInt();
        }

        final int len1 = sequence1.length;
        final int len2 = sequence2.length;
        int[][] lengths = new int[2][len2];
        for (int i = 0; i < len1; ++i) {
            lengths[i % 2][0] = (sequence1[i] == sequence2[0]) ? 1 : 0;
            for (int j = 1; j < len2; ++j) {
                if (sequence1[i] == sequence2[j]) {
                    lengths[i % 2][j] = lengths[(i + 1) % 2][j - 1] + 1;
                } else {
                    lengths[i % 2][j] = Math.max(lengths[(i + 1) % 2][j], lengths[i % 2][j - 1]);
                }
            }
        }

        System.out.println(lengths[(len1 - 1) % 2][len2 - 1]);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}

