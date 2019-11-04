package ru.mail.polis.ads.part5.kuzo_liza;

import java.util.Scanner;

public class LargestSequentialSubsequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int len = scanner.nextInt();
        int[] a = new int[len];
        int[] d = new int[len];
        d[0] = 1;
        int ans = 1;

        for (int i = 0; i < len ; i++) {
            a[i] = scanner.nextInt();
        }

        for (int i = 0; i < len; i++) {
            int max = 0;
            for (int j = 0; j < i ; j++) {
                if (a[j] != 0 && a[i] % a[j]==0 && d[j] > max){
                    max = d[j];
                }
            }
            d[i] = max + 1;
            if (d[i] > ans){
                ans = d[i];
            }
        }
        System.out.println(ans);
    }
}
