package ru.mail.polis.ads.part3.marashov.alexander;

import java.util.Scanner;

public class Task4 {

    private static String[] array;
    private static boolean comparator(final String a, final String b) {
        int lenA = a.length();
        int lenB = b.length();
        if (lenA < lenB) {
            return true;
        }

        if (lenA > lenB) {
            return false;
        }

        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.charAt(i) < b.charAt(i);
            }
        }
        return false;
    }

    private static int partition(int left, int right) {
        final String center = array[(left + right) / 2];
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
            final String tmp = array[left];
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
        final Scanner in = new Scanner(System.in);
        int k = Integer.parseInt(in.nextLine());
        array = in.nextLine().split(" ");
        k = array.length - k;
        quickSort(0, array.length - 1);
        System.out.println(array[k]);
    }
}
