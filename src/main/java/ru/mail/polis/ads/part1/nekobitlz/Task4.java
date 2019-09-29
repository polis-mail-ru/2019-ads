package ru.mail.polis.ads.part1.nekobitlz;

import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Math.*;

public class Task4 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        int firstLength = in.nextInt();
        int[] firstSequence = new int[firstLength + 1];

        for (int i = 1; i <= firstLength; i++) firstSequence[i] = in.nextInt();

        int secondLength = in.nextInt();
        int[] secondSequence = new int[secondLength + 1];
        int[][] dp = new int[2][secondLength + 1]; //length of the largest common subsequence

        for (int i = 1; i <= secondLength; i++) secondSequence[i] = in.nextInt();

        for (int i = 1; i <= firstLength; i++) {
            for (int j = 1; j <= secondLength; j++) {
                if (firstSequence[i] == secondSequence[j]) {
                    dp[i % 2][j] = 1 + dp[(i - 1) % 2][j - 1];
                } else {
                    dp[i % 2][j] = max(dp[(i - 1) % 2][j], dp[i % 2][j - 1]);
                }
            }
        }

        int result = dp[firstLength % 2][secondLength];
        out.println(result);
        out.close();
    }
}
