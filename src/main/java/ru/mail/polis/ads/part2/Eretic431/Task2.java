package main.java.ru.mail.polis.ads.part2.Eretic431;

import java.util.Scanner;

/**
 * https://www.e-olymp.com/ru/submissions/5858813
 */
public class Task2 {
    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n + 2];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = in.nextInt();
        }
        int k = in.nextInt();

        for (int i = 1; i < n + 2; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = i - k; j < i; j++) {
                if (j < 0) {
                    continue;
                }
                if (arr[j] > max) {
                    max = arr[j];
                }
            }
            arr[i] = max + arr[i];
        }

        System.out.println(arr[n + 1]);
    }
}
