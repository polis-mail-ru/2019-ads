package main.java.ru.mail.polis.ads.part1.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5858336
 */

public class Task4 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);

        final int length1 = in.nextInt();
        int[] string1 = new int[length1 + 1];
        for (int i = 1; i <= length1; ++i) {
            string1[i] = in.nextInt();
        }

        final int length2 = in.nextInt();
        int[] string2 = new int[length2 + 1];
        for (int i = 1; i <= length2; ++i) {
            string2[i] = in.nextInt();
        }

        int[][] result = new int[2][length2 + 1];

        for (int i = 1; i <= length1; ++i) {
            for (int j = 1; j <= length2; ++j) {
                if (string1[i] == string2[j]) {
                    result[i % 2][j] = 1 + result[(i - 1) % 2][j - 1];
                } else {
                    result[i % 2][j] = Math.max(result[(i - 1) % 2][j], result[i % 2][j - 1]);
                }
            }
        }
        System.out.println(result[length1 % 2][length2]);
    }
}
