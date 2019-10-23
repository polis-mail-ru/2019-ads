package ru.mail.polis.ads.part5.suhova;

import java.util.Scanner;

public class Task2169 {
    /*
   Task 5: https://www.e-olymp.com/ru/submissions/5927785
    */
    private static int n;
    private static boolean[] arr;
    private static int[] num;

    private static void solve() {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        arr = new boolean[n];
        num = new int[n];
        rec(0);
    }

    private static void rec(int count) {
        if (count == n) {
            int x = 0;
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < n; i++) {
                str.append(num[i] + " ");
            }
            System.out.println(str);
        }
        for (int i = 0; i < n; i++) {
            if (!arr[i]) {
                arr[i] = true;
                num[count] = i + 1;
                rec(count + 1);
                arr[i] = false;
            }
        }
    }

    public static void main(final String[] arg) {
        solve();
    }
}
