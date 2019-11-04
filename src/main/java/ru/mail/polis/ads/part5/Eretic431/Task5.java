package main.java.ru.mail.polis.ads.part5.Eretic431;

import java.io.*;
import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/6024526
 */

public class Task5 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        final int n = in.nextInt();
        final int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = i + 1;
        }

        do {
            for (int i = 0; i < n; i++) {
                out.print(arr[i] + " ");
            }
            out.println();
        } while (nextPermutation(arr, n));

        out.close();
    }

    private static boolean nextPermutation(int[] arr, int n) {
        int i = n - 2;

        while (i != -1 && arr[i] >= arr[i + 1]) {
            i--;
        }
        if (i == -1) {
            return false;
        }

        int k = n - 1;
        while (arr[i] >= arr[k]) {
            k--;
        }

        swap(arr, i, k);

        int l = i + 1;
        int m = n - 1;
        while (l < m) {
            swap(arr, l++, m--);
        }

        return true;
    }

    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}