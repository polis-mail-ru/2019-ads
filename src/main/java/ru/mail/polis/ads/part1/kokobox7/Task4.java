package ru.mail.polis.ads.part1.kokobox7;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

/*
    Длина наибольшей общей подпоследовательности
    e-olymp submission: https://www.e-olymp.com/ru/submissions/5793032
    */


public class Task4 {
    private static int[][] d;
    private static int[] firstSequence;
    private static int[] secondSequence;

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(System.out);
             Scanner in = new Scanner(System.in)) {
            int length1 = Integer.parseInt(in.nextLine().trim());
            firstSequence = new int[length1];
            for (int i = 0; i < length1; i++) {
                firstSequence[i] = in.nextInt();
            }
            in.nextLine();
            int length2 = Integer.parseInt(in.nextLine().trim());
            secondSequence = new int[length2];
            for (int i = 0; i < length2; i++) {
                secondSequence[i] = in.nextInt();
            }
            in.nextLine();


            d = new int[firstSequence.length + 1][secondSequence.length + 1];
            for (int[] ints : d) {
                Arrays.fill(ints, -1);
            }
            out.println(lcs(length1, length2));
        }
    }

    private static int lcs(int n, int m) {
        if (n == 0 || m == 0)
            return d[n][m] = 0;
        if (d[n][m] != -1) {
            return d[n][m];
        } else if (firstSequence[n - 1] == secondSequence[m - 1]) {
            return d[n][m] = 1 + lcs(n - 1, m - 1);
        } else {
            return d[n][m] = Math.max(lcs(n - 1, m), lcs(n, m - 1));
        }
    }
}
