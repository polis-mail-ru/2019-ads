package ru.mail.polis.ads.part.first;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5854537
 */
public class TaskThree {

    private static String[][] d;

    private static String packing(String string, String sub, int len, int l, int r) {
        for (int i = 1; i <= len / 2; i++) {
            int count = len / i;
            if (len % i == 0) {
                for (int j = l; j <= r - i; j++) {
                    if (string.charAt(j) != string.charAt(j + i)) {
                        count = 0;
                        break;
                    }
                }
            } else { count = 0; }
            if (count > 1) {
                String s = len / i + "(" + d[l][l + i - 1] + ")";
                if (s.length() < sub.length()) { sub = s; }
            }
        }
        return sub;
    }

    private static void solve(final Scanner in, final PrintWriter out) {
        String string = in.nextLine();
        int len = string.length();
        d = new String[len][len];
        for (int n = 1; n <= len; n++) {
            for (int l = 0; l <= len - n; l++) {
                int r = n + l - 1;
                String sub = string.substring(l, r + 1);
                if (n > 4) {
                    sub = packing(string, sub, n, l, r);
                    for (int part = l; part < r; part++) {
                        String newStr = d[l][part] + d[part + 1][r];
                        if (newStr.length() < sub.length()) {
                            sub = newStr;
                        }
                    }
                }
                d[l][r] = sub;
            }
        }
        out.println(d[0][len - 1]);
    }

    public static void main(final String[] arg) {
        final Scanner in = new Scanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
