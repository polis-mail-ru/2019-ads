package ru.mail.polis.ads.part1.DiscreetDmitriy;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task3SymbolsPacking {

    private static int[][] t;
    private static int[][] p;
    private static StringBuilder sb;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        String str = in.next();
        int length = str.length();

        sb = new StringBuilder();
        p = new int[length][length + 1];
        t = new int[length][length + 1];
        int[][] dp = new int[length][length + 1];

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                String substr = str.substring(i, j);

                int length1 = substr.length();
                int length2 = substr.length();
                int[] prefixes = new int[length2];

                int count = 0;
                int result = 1;

                for (int i1 = 1; i1 < length2; i1++) {
                    while (count > 0 && substr.charAt(count) != substr.charAt(i1))
                        count = prefixes[count - 1];

                    if (substr.charAt(count) == substr.charAt(i1)) count++;

                    prefixes[i1] = count;
                }

                int last = prefixes[length1 - 1];
                int d = length1 - last;

                if (length1 % d == 0)
                    result = length1 / d;


                p[i][j] = result;
            }
        }

        for (int l = 1; l <= length; l++) {
            for (int i = 0; i + l <= length; i++) {
                int j = i + l;
                int period = p[i][j];
                int periodLength = l / period;
                int inc = 3 + (period >= 10 ? 1 : 0) + (period >= 100 ? 1 : 0);

                dp[i][j] = j - i;

                if (dp[i][j] > inc + dp[i][i + periodLength]) {
                    dp[i][j] = inc + dp[i][i + periodLength];
                    t[i][j] = -1;
                }

                for (int k = i + 1; k < j; k++) {
                    int sum = dp[i][k] + dp[k][j];

                    if (sum < dp[i][j]) {
                        dp[i][j] = sum;
                        t[i][j] = k;
                    }
                }
            }
        }
        foldSymbols(str, 0, length);
        String result = sb.toString();

        out.println(result);
        out.close();
    }

    private static void foldSymbols(String str, int begin, int end) {
        if (begin == end)
            return;

        switch (t[begin][end]) {
            case 0:
                sb.append(str, begin, end);
                break;
            case -1:
                int period = p[begin][end];
                int periodLength = (end - begin) / p[begin][end];

                sb.append(period).append("(");

                foldSymbols(str, begin, begin + periodLength);

                sb.append(')');

                break;
            default:
                int mid = t[begin][end];

                foldSymbols(str, begin, mid);
                foldSymbols(str, mid, end);

                break;
        }
    }
}

//  https://www.e-olymp.com/ru/submissions/5819363
