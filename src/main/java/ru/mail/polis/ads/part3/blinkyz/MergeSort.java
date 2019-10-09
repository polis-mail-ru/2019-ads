package ru.mail.polis.ads.part3.blinkyz;

public class MergeSort {
    MergeSort() {
    }

    private static void merge(int[] a, int l, int q, int r) {
        int n1 = q - l + 1;
        int n2 = r - q;
        int i, j, k;

        int[] L = new int[n1 + 1];
        int[] R = new int[n2 + 1];

        for (i = 0; i < n1; i++) {
            L[i] = a[l + i];
        }
        for (i = 0; i < n2; i++) {
            R[i] = a[q + i + 1];
        }

        L[n1] = R[n2] = Integer.MAX_VALUE;
        i = j = 0;
        for (k = l; k <= r; k++) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                ++i;
            } else {
                a[k] = R[j];
                ++j;
            }
        }
    }

    static void sort(int[] a, int l, int r) {
        if (l < r) {
            int q = (l + r) / 2;
            sort(a, l, q);
            sort(a, q + 1, r);
            merge(a, l, q, r);
        }
    }
}
