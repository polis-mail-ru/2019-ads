package ru.mail.polis.ads.part5.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task5 {

    private static void sort(int left, int right, int[] array) {
        for (int i = left; i <= right; ++i) {
            for (int j = i + 1; j <= right; ++j) {
                if (array[i] > array[j]) {
                    int tmp = array[i];
                    array[i] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = i + 1;
        }
        try (PrintWriter out = new PrintWriter(System.out)) {
            for (int value : array) {
                out.print(value + " ");
            }
            out.println();
            int index = n - 1;
            while (index != -1) {
                int minNumber = 9;
                int ind = index;
                for (int i = index + 1; i < n; ++i) {
                    if (array[i] > array[index] && minNumber > array[i]) {
                        minNumber = array[i];
                        ind = i;
                    }
                }
                if (index == ind) {
                    index--;
                } else {
                    int tmp = array[index];
                    array[index] = array[ind];
                    array[ind] = tmp;
                    sort(index + 1, n - 1, array);
                    index = n - 1;
                    for (int value : array) {
                        out.print(value + " ");
                    }
                    out.println();
                }
            }
        }
    }
}
