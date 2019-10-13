package ru.mail.polis.ads.part3.marashov.alexander;

import java.io.PrintWriter;

public class Task2 {

    private static int[] array;

    private static boolean comparator(final int a, final int b) {
        return (a % 10 < b % 10) || ((a % 10 == b % 10) && (a < b));
    }

    private static int partition(int left, int right) {
        final int center = array[(left + right) / 2];
        left--;
        right++;
        while (true) {
            do {
                ++left;
            } while (comparator(array[left], center));

            do {
                --right;
            } while (comparator(center, array[right]));

            if (left >= right) {
                return right;
            }
            final int tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
        }
    }

    private static void quickSort(int left, int right) {
        final int length = right - left + 1;
        if (length <= 1) {
            return;
        }
        final int k = partition(left, right);
        quickSort(left, k);
        quickSort(k + 1, right);
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(System.out)) {
            final FastScanner in = new FastScanner(System.in);
            int count = in.nextInt();
            array = new int[count];
            while ((count--) > 0) {
                array[count] = in.nextInt();
            }
            quickSort(0, array.length - 1);
            for (int value : array) {
                out.print(value + " ");
            }
        }
    }
}
