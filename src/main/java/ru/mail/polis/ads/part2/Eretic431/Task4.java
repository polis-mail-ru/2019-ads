package main.java.ru.mail.polis.ads.part2.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5858920
 */

public class Task4 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final String str = in.nextLine();

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
}
