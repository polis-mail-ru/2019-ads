package ru.mail.polis.ads.part1.kokobox7;

import java.io.PrintWriter;
import java.util.Scanner;

/*
    Упаковка символов
    e-olymp submission: https://www.e-olymp.com/ru/submissions/5796996
    */

public class Task3 {
    private static String[][] dp;

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(System.out);
             Scanner in = new Scanner(System.in)) {
            String string = in.nextLine();
            int n = string.length();
            dp = new String[n][n];
            String substring;

            for (int subLength = 1; subLength <= n; subLength++) {
                for (int left = 0; left <= n - subLength; left++) {
                    int right = left + subLength - 1;
                    substring = string.substring(left, right + 1);
                    /*
                    Упаковывать подстроку длины меньше 4 не имеет смысла
                    Потому что знаков получиться только больше:
                    AA -> 2(A)
                    (четыре знака против двух)
                     */
                    if (subLength > 4) {
                        substring = pack(string, substring, subLength, left, right);
                        for (int part = left; part < right; part++) {
                            String newStr = dp[left][part] + dp[part + 1][right];
                            if (newStr.length() < substring.length()) {
                                substring = newStr;
                            }
                        }
                    }
                    dp[left][right] = substring;
                }
            }
            /*for (String[] s : dp) {
                System.out.println(Arrays.toString(s));
            }*/
            out.println(dp[0][n - 1]);
            out.flush();
        }
    }

    private static String pack(String string, String substring, int subLength, int left, int right) {
        String outSubstring = substring;
        for (int i = 1; i <= subLength / 2; i++) {
            int count = subLength / i;
            if (subLength % i == 0) {
                for (int j = left; j <= right - i; j++) {
                    if (string.charAt(j) != string.charAt(j + i)) {
                        count = 0;
                        break;
                    }
                }
            } else count = 0;
            if (count > 1) {
                String s = subLength / i + "(" + dp[left][left + i - 1] + ")";
                if (s.length() < substring.length()) {
                    outSubstring = s;
                }
            }
        }
        return outSubstring;
    }
}
