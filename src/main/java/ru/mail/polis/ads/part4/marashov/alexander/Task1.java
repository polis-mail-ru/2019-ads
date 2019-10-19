package ru.mail.polis.ads.part4.marashov.alexander;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = in.nextInt();

        final long[] heap = new long[n + 1];
        heap[1] = in.nextInt();
        boolean isHeap = true;

        for (int i = 2; i <= n; ++i) {
            heap[i] = in.nextLong();
            if (isHeap && heap[i] < heap[i / 2]) {
                isHeap = false;
            }
        }
        System.out.println(isHeap ? "YES" : "NO");
    }
}
