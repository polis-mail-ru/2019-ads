package ru.mail.polis.ads.part5.bardaev;

import java.util.Scanner;

public class Task264 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n ; i++) {
            a[i]= in.nextInt();
        }
        int res = 1;
        int[] d = new int[n];
        for (int i = 0; i < n; i++) {
            int max = 0;
            for (int j = 0; j <i ; j++) {
                if (a[j] != 0 && a[i] % a[j] == 0 && d[j] > max){
                    max = d[j];
                }
            }
            d[i] = max + 1;
            if (d[i] > res){
                res = d[i];
            }
        }
        System.out.println(res);
    }
}
