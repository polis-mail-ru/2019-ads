package ru.mail.polis.ads.part3.blinkyz;

import static ru.mail.polis.ads.part3.blinkyz.SortUtils.swap;

public class InsertionSort {
    InsertionSort() {
    }

    private static void sort(int[] a, int l, int r) {
        for (int i = l; i <= r; i++) {
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                swap(a, j, j - 1);
            }
        }
    }
}
