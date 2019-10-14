package ru.mail.polis.ads.part1.DiscreetDmitriy;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Task4LongestCommonSubsequence {
    private Task4LongestCommonSubsequence() {}


    private static int[] xArr;
    private static int[] yArr;
    private static int[][] twoLastRows;

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);

        final int firstLength = in.nextInt();
        xArr = new int[firstLength + 1];

        IntStream.range(1, firstLength).forEach(i -> xArr[i] = in.nextInt());

        final int secondLength = in.nextInt();
        yArr = new int[secondLength + 1];

        IntStream.range(1, secondLength).forEach(i -> yArr[i] = in.nextInt());

        twoLastRows = new int[firstLength + 1][secondLength + 1];

        for (int i = 0; i < firstLength; i++) {
            Arrays.fill(twoLastRows[i], -1);
        }

        final String result =
                String.valueOf(longestCommonSubsequence(firstLength, secondLength));

        out.println(result);

        out.close();
    }

    private static int longestCommonSubsequence(final int firstLength, final int secondLength) {
        if (firstLength == 0 || secondLength == 0)
            return 0;

        if (twoLastRows[firstLength][secondLength] != -1)
            return twoLastRows[firstLength][secondLength];

        if (xArr[secondLength] == yArr[firstLength])
            return 1 + longestCommonSubsequence(firstLength - 1, secondLength - 1);
        else
            return Math.max(longestCommonSubsequence(firstLength, secondLength -1),
                    longestCommonSubsequence(firstLength - 1, secondLength));
    }
}

//  https://www.e-olymp.com/ru/submissions/5859235