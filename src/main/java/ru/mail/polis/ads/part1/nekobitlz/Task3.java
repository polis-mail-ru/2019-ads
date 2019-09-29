package ru.mail.polis.ads.part1.nekobitlz;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task3 {

    private static int[][] translations;
    private static int[][] periodicity;
    private static StringBuilder sb;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String str = in.next();
        int n = str.length();

        sb = new StringBuilder();
        periodicity = new int[n][n + 1];
        translations = new int[n][n + 1];
        int[][] dp = new int[n][n + 1]; //length of the largest common subsequence

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String substring = str.substring(i, j);
                periodicity[i][j] = getPeriodicity(substring);
            }
        }

        for (int length = 1; length <= n; length++) {
            for (int i = 0; i + length <= n; i++) {
                int j = i + length;
                int period = periodicity[i][j];
                int periodLength = length / period;
                int inc = 3 + (period >= 10 ? 1 : 0) + (period >= 100 ? 1 : 0);

                dp[i][j] = j - i;

                if (dp[i][j] > inc + dp[i][i + periodLength]) {
                    dp[i][j] = dp[i][i + periodLength] + inc;
                    translations[i][j] = -1;
                }

                for (int k = i + 1; k < j; k++) {
                    int sum = dp[i][k] + dp[k][j];

                    if (sum < dp[i][j]) {
                        dp[i][j] = sum;
                        translations[i][j] = k;
                    }
                }
            }
        }

        foldSymbols(str, 0, n);
        String result = sb.toString();

        out.println(result);
        out.close();
    }

    private static int getPeriodicity(String str) {
        int n = str.length();
        int last = getPrefixArray(str)[n - 1];
        int d = n - last;

        if (n % d == 0) {
            return n / d;
        }

        return 1;
    }

    private static int[] getPrefixArray(String str) {
        int n = str.length();
        int[] prefixes = new int[n];
        int count = 0;

        for (int i = 1; i < n; i++) {
            while (count > 0 && str.charAt(count) != str.charAt(i)) {
                count = prefixes[count - 1];
            }

            if (str.charAt(count) == str.charAt(i)) count++;

            prefixes[i] = count;
        }

        return prefixes;
    }

    private static void foldSymbols(String str, int begin, int end) {
        if (begin == end) {
            return;
        }

        switch (translations[begin][end]) {
            case 0:
                sb.append(str, begin, end);
                break;
            case -1:
                int period = periodicity[begin][end];
                int periodLength = (end - begin) / periodicity[begin][end];

                sb.append(period).append("(");

                foldSymbols(str, begin, begin + periodLength);

                sb.append(')');

                break;
            default:
                int mid = translations[begin][end];

                foldSymbols(str, begin, mid);
                foldSymbols(str, mid, end);

                break;
        }
    }
}
