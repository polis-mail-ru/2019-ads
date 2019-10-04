package ru.mail.polis.ads.part1.blinkyz;

import java.util.Scanner;

public class Problem1090 {
    private Problem1090() {

    }

    private static void solve() {
        final Scanner scanner = new Scanner(System.in);
        final String s = scanner.nextLine();
        final int length = s.length();

        if (length == 0) {
            System.out.println();
            return;
        }

        String[][] d = new String[length][length];
        for (int right = 0; right < length; right++) {
            for (int left = right; left >= 0; left--) {
                String curSub = s.substring(left, right + 1);
                if (right - left < 4) {
                    d[left][right] = curSub;
                    continue;
                }

                String minReplace = curSub;

                // left <= k < right, that is we evaluated d[left][k] and d[k+1][right] before
                for (int k = left; k < right; k++) {
                    String curReplace = d[left][k] + d[k + 1][right];
                    if (curReplace.length() < minReplace.length()) {
                        minReplace = curReplace;
                    }
                }

                // check if current substring is periodic
                for (int repeatLen = 1; repeatLen < (curSub.length() / 2) + 1; repeatLen++) {
                    boolean isPeriodic = isPeriodic(repeatLen, left, right, d);
                    if (isPeriodic) {
                        final String curReplace = (curSub.length() / repeatLen) + "(" + d[left][left + repeatLen - 1] + ")";
                        if (curReplace.length() < minReplace.length()) {
                            minReplace = curReplace;
                        }
                    }
                }

                d[left][right] = minReplace;
            }
        }
        System.out.println(d[0][length - 1]);
    }


    private static boolean isPeriodic(final int repeatLen, final int left, final int right, final String[][] d) {
        final int subLen = right - left + 1;
        if (subLen % repeatLen != 0) {
            return false;
        }

        final String repeat = d[left][left + repeatLen - 1];
        boolean isPeriodic = true;
        int from = left;
        while (from + repeatLen - 1 <= right) {
            if (!repeat.equals(d[from][from + repeatLen - 1])) {
                isPeriodic = false;
                break;
            }
            from += repeatLen;
        }
        return isPeriodic;
    }

    public static void main(final String[] arg) {
        solve();
    }
}