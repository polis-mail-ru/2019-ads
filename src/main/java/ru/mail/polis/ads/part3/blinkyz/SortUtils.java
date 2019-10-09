package ru.mail.polis.ads.part3.blinkyz;

public class SortUtils {
    private SortUtils() {
    }

    public static void swap(int[] a, int i, int j) {
        int temp = a[j];
        a[j] = a[i];
        a[i] = temp;
    }
}
