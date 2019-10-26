package ru.mail.polis.ads.lectionWork;

import java.util.Scanner;

public class ThreeInLection {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long w = in.nextInt(), h = in.nextInt(), n = in.nextInt();
        long left = Math.max(w, h);
        long right = n * Math.max(w, h);
        long m = 0;
        while (left < right){
            m = (left + right) / 2;
            long v = (m / w) * (m / h);
            if (v >= n){
                right = m;
            } else {
                left = m + 1;
            }
        }
        System.out.println(left);
    }
}
