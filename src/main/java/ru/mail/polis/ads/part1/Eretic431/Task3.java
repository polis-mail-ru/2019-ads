package main.java.ru.mail.polis.ads.part1.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5858270
 */

public class Task3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.next();

        int length = str.length();
        String[][] result = new String[length][length];

        for (int i = 1; i <= length; i++) {
            for (int start1 = 0; start1 + i - 1 < length; start1++) {
                int end1 = start1 + i - 1;
                String min = str.substring(start1, end1 + 1);

                if (i > 4) {
                    for (int end2 = start1; end2 < end1; end2++) {
                        int start2 = end2 + 1;
                        String tmp = result[start1][end2] + result[start2][end1];
                        if (tmp.length() < min.length()) {
                            min = tmp;
                        }
                    }

                    for (int k = 1; k < i; k++) {
                        if (i % k == 0) {
                            boolean repetitive = true;
                            for (int j = start1 + k; j <= end1; j++) {
                                if (str.charAt(j) != str.charAt(j - k)) {
                                    repetitive = false;
                                    break;
                                }
                            }
                            if (repetitive) {
                                String tmp = i / k + "(" + result[start1][start1 + k - 1] + ")";
                                if (tmp.length() < min.length()) {
                                    min = tmp;
                                }
                            }
                        }
                    }
                }
                result[start1][end1] = min;
            }
        }
        System.out.println(result[0][length - 1]);
    }
}
