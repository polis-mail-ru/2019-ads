package ru.mail.polis.ads.part3.blinkyz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Problem1462 {
    Problem1462() {
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }

    private static void sort(int[] a, int l, int r) {
        for (int i = l; i <= r; i++) {
            for (int j = i; j > 0; j--) {
                if (a[j] % 10 < a[j - 1] % 10) {
                    swap(a, j, j - 1);
                    continue;
                } else if ((a[j] % 10 == a[j - 1] % 10) && (a[j] <= a[j - 1])) {
                    swap(a, j, j - 1);
                    continue;
                }
                break;
            }
        }
    }

    private static void solve() {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));

        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }

        sort(a, 0, n - 1);
        for (int e : a) {
            System.out.print(e + " ");
        }
    }

    public static void main(String[] args) {
        solve();
    }
}
