package ru.mail.polis.ads.part3.blinkyz;

import static ru.mail.polis.ads.part3.blinkyz.SortUtils.swap;

public class SelectionSort {
    SelectionSort() {
    }

    private static void sort(int[] a, int l, int r) {
        int k;
        for (int i = l; i <= r; i++) {
            k = i;
            for (int j = i + 1; j <= r; j++) {
                if (a[j] < a[k]) {
                    k = j;
                }
            }
            swap(a, i, k);
        }
    }
}
