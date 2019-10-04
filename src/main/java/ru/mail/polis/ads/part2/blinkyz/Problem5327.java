package ru.mail.polis.ads.part2.blinkyz;

import java.util.Scanner;

public class Problem5327 {
    private Problem5327() {
    }

    private static void solve() {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int closers = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (ch == ')') {
                closers++;
            } else {
                if (closers == 0) {
                    System.out.println("NO");
                    return;
                }
                closers--;
            }
        }

        if (closers == 0) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }

    public static void main(String[] args) {
        solve();
    }
}
