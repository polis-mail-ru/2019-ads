package ru.mail.polis.ads.part3.blinkyz;

import static ru.mail.polis.ads.part3.blinkyz.SortUtils.swap;

public class BubbleSort {
    private BubbleSort() {
    }

    private static void sort(int[] a, int l, int r) {
        boolean swaped;
        for (int i = l; i < r; i++) {
            swaped = false;
            for (int j = l; j < r; j++) {
                if (a[j] > a[j + 1]) {
                    swaped = true;
                    swap(a, j, j + 1);
                }
            }
            if (!swaped) {
                break;
            }
        }
    }
}
