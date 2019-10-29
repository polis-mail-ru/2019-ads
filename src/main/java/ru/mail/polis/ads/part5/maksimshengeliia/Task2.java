package ru.mail.polis.ads.part5.maksimshengeliia;

import java.util.Scanner;

/*
*   https://www.e-olymp.com/ru/submissions/5972043
* */
public class Task2 {

    public static void main(String[] strings) {
        Scanner f = new Scanner(System.in);
        long w = f.nextInt();
        long h = f.nextInt();
        long n = f.nextInt();
        long left = Math.max(w, h);
        long right = n * Math.max(w, h);
        while (left < right) {
            long middle = (left + right) / 2;
            long v = (middle / w) * (middle / h);
            if (n <= v) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        System.out.println(left);
    }
}

