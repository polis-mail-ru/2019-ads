package ru.mail.polis.ads.part3.blinkyz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Problem4741 {
    private static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    private static int sort(int[] a, int l, int r) {
        boolean swaped;
        int cnt = 0;
        for (int i = l; i < r; i++) {
            swaped = false;
            for (int j = l; j < r; j++) {
                if (a[j] > a[j + 1]) {
                    swaped = true;
                    swap(a, j, j + 1);
                    ++cnt;
                }
            }
            if (!swaped) {
                break;
            }
        }
        return cnt;
    }

    private static void solve() {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        System.out.println(sort(a, 0, n - 1));
    }

    public static void main(String[] args) {
        solve();
    }
}
