package ru.mail.polis.ads.part4.marashov.alexander;

import java.io.PrintWriter;
import java.util.Scanner;

public class Task5 {

    private static boolean contains(final int num, final int[] array) {
        int left = 1;
        int right = array.length - 1;
        while (left != right) {
            final int center = (left + right) / 2;
            if (array[center] == num) {
                return true;
            }
            if (array[center] > num) {
                right = center;
            } else {
                left = center + 1;
            }
        }
        return array[left] == num;
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = Integer.parseInt(in.next());
        final int q = Integer.parseInt(in.next());
        if (n == 0) {
            return;
        }
        final int[] array = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            array[i] = Integer.parseInt(in.next());
        }
        try (final PrintWriter out = new PrintWriter(System.out)) {
            for (int i = 0; i < q; ++i) {
                if (contains(Integer.parseInt(in.next()), array)) {
                    out.println("YES");
                } else {
                    out.println("NO");
                }
            }
        }
    }
}
