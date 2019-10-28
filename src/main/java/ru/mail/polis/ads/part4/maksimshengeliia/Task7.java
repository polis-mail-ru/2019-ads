package ru.mail.polis.ads.part4.maksimshengeliia;

import java.util.Scanner;

/*
*   https://www.e-olymp.com/ru/submissions/5972582
* */
public class Task7 {

    private static long[] array;
    private static int k;
    private static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        array = new long[n];
        long max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            array[i] = sc.nextLong();
            if (array[i] > max) max = array[i];
            if (array[i] < min) min = array[i];
        }
        System.out.println(maxDistance(max, min));
    }

    private static long maxDistance(long max, long min) {
        long left = 0;
        long right = max - min + 1;
        long middle;
        while (right - left != 1) {
            middle = (left + right) / 2;
            if (isCorrect(middle)) left = middle;
            else right = middle;
        }
        return left;
    }

    private static boolean isCorrect(long x) {
        int cows = 1;
        long lastCow = array[0];
        for (int i = 0; i < n; i++) {
            long c = array[i];
            if (c - lastCow >= x) {
                cows += 1;
                lastCow = c;
            }
        }
        return cows >= k;
    }
}
