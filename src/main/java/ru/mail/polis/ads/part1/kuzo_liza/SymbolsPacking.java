package ru.mail.polis.ads.part1.kuzo_liza;

import java.util.Scanner;

public class SymbolsPacking {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        int n = input.length();
        String[][] string = new String[n][n];

        for (int len = 1; len <= input.length(); len++) {
            for (int left = 0; left + len - 1 < n; left++) {

                int right = left + len - 1;
                String substring = input.substring(left, right + 1);

                // N(?) - 4 символа
                if (len > 4) {
                    for (int right1 = left; right1 < right; right1++) {
                        int left2 = right1 + 1;
                        String t = string[left][right1] + string[left2][right];

                        if (t.length() < substring.length()) {
                            substring = t;
                        }
                    }

                    for (int period = 1; period < len; period++) {
                        if (len % period == 0) {
                            boolean isPeriodic = true;
                            for (int i = left + period; i <= right; i++) {
                                if (input.charAt(i) != input.charAt(i - period)) {
                                    isPeriodic = false;
                                    break;
                                }
                            }
                            if (isPeriodic) {
                                String t = len / period + "(" + string[left][left + period - 1] + ")";
                                if (t.length() < substring.length()) {
                                    substring = t;
                                }
                            }
                        }
                    }
                }
                string[left][right] = substring;
            }

        }
        System.out.println(string[0][n-1]);
    }
}
