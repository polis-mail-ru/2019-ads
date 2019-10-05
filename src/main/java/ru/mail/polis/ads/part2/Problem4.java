package ru.mail.polis.ads.part2;

import java.util.Scanner;

/**
 * submission - https://www.e-olymp.com/ru/submissions/5784960
 */
public class Problem4 {

    private Problem4() {
        // Should not be instantiated
    }

    private static void solve() {
        final Scanner sc = new Scanner(System.in);
        final String str = sc.nextLine();

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            final char c = str.charAt(i);
            if (c == '(') {
                count++;
            }
            if (c == ')') {
                count--;
            }
            if (count < 0) {
                break;
            }
        }

        if (count == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(final String[] arg) {
        solve();
    }
}
