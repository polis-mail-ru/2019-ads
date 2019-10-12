package ru.mail.polis.ads.part1.shakirov_aa;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        System.out.println(result(s));

    }

    public static String result(String s) {
        int n = s.length();
        String[][] d = new String[n][n];

        for (int j = 0; j < n; j++) {
            for (int i = j; i >= 0 ; i--) {
                String min = s.substring(i, j + 1);

                d[i][j] = s.substring(i, j + 1);

                for (int k = 1; k < j; k++) {
                    int currentLength;

                    if (isPeriod(i, j, k, s)) {
                        String substring = s.substring(i, j + 1);
                        currentLength = numberOfDigits(substring.length() / k) + 2 + k;
                        if (currentLength < min.length()) {
                            d[i][j] = substring.length() / k + "(" + d[i][i + k - 1] + ")";
                            min = d[i][j];
                        }
                    }
                }

                for (int k = i; k < j; k++) {
                    if ((d[i][k].length() + d[k+1][j].length()) < min.length()) {
                        min = d[i][k] + d[k+1][j];
                        d[i][j] = d[i][k] + d[k+1][j];
                    }
                }
            }
        }

        return(d[0][n-1]);
    }

    public static boolean isPeriod(int i, int j, int k, String s) {
        if ((j - i + 1) % k != 0) {
            return false;
        }

        if (j - i == 0) {
            return false;
        }

        int p1 = i, p2 = p1 + k;

        while (p2 <= j) {
            if (s.charAt(p1) != s.charAt(p2)) {
                return false;
            }
            p1++;
            p2++;
        }

        return true;
    }

    public static int numberOfDigits(int k) {
        int count = 0;
        while (k > 0) {
            k = k / 10;
            count++;
        }

        return count;
    }
}