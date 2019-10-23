package ru.mail.polis.ads.part5.suhova;

import java.util.Scanner;

public class Task991 {
    /*
    Task 4: https://www.e-olymp.com/ru/submissions/5925349
     */
    private static void solve() {
        Scanner scan = new Scanner(System.in);
        String str = scan.next();
        String p, s;
        if (str.contains("*") || str.contains("?")) {
            p = str;
            s = scan.next();
        } else {
            p = scan.next();
            s = str;
        }
        int n = s.length();
        int m = p.length();
        boolean[][] d = new boolean[n + 1][m + 1];
        d[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '?') {
                    d[i][j] = d[i - 1][j - 1];
                } else if (p.charAt(j - 1) == '*') {
                    d[i][j] = d[i - 1][j] || d[i - 1][j - 1] || d[i][j - 1];
                } else {
                    d[i][j] = false;
                }
            }
        }
        if (d[n][m]) System.out.println("YES");
        else System.out.println("NO");
    }

    public static void main(final String[] arg) {
        solve();
    }
}