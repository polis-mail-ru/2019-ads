package ru.mail.polis.ads.part4.marashov.alexander;

import java.util.Scanner;

public class Task7 {

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = Integer.parseInt(in.next());
        final int k = Integer.parseInt(in.next());
        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            array[i] = Integer.parseInt(in.next());
        }
        int left = 0;
        int right = array[n] - array[1];
        if (k == 2) {
            System.out.println(right);
            return;
        }
        while (left != right) {
            int dist = (left + right) / 2;
            int count = 1;
            int lastCowIndex = 1;
            for (int i = 2; i <= n; ++i) {
                if (array[i] - array[lastCowIndex] >= dist) {
                    ++count;
                    lastCowIndex = i;
                }
            }
            if (count >= k) {
                left = dist + 1;
            } else {
                right = dist;
            }
        }
        System.out.println(left - 1);
    }
}
