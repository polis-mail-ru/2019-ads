package ru.mail.polis.ads.part5.suhova;

import java.io.IOException;
import java.util.Scanner;

public class Task264 {
    /*
    Task 3: https://www.e-olymp.com/ru/submissions/5924919
     */
    private static void solve() throws IOException {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[] d = new int[n];
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = s.nextInt();
        }
        d[0] = 1;
        for (int i = 1; i < n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (a[j] != 0 && a[i] % a[j] == 0 && d[j] > max) {
                    max = d[j];
                }
            }
            d[i] = max + 1;
        }
        System.out.print(max(d, n));
    }

    private static int max(int[] a, int n) {
        int max = a[0];
        for (int i = 1; i < n; i++) {
            if (a[i] > max) max = a[i];
        }
        return max;
    }

    public static void main(final String[] arg) throws IOException {
        solve();
    }
}
