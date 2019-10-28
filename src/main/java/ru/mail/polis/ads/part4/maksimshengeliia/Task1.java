package ru.mail.polis.ads.part4.maksimshengeliia;

import java.util.Scanner;

/*
*   https://www.e-olymp.com/ru/submissions/5963786
* */
public class Task1 {
    public static void main(final String[] arg) {
        int i, n;
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        long[] m = new long[n + 1];
        for (i = 1; i <= n; i++) {
            m[i] = in.nextLong();
        }


        for (i = 1; i <= n / 2; i++) {
            if (2 * i <= n && m[i] > m[2 * i]) break;
            if (2 * i + 1 <= n && m[i] > m[2 * i + 1]) break;
        }

        if (i <= n / 2) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
    }
}

