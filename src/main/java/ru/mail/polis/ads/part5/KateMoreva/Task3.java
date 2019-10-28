package ru.mail.polis.ads.part5.KateMoreva;

import java.util.Scanner;
import static java.lang.Math.max;

//e-olymp problem 264 "Наибольшая последовательнократная подпоследовательность"

public class Task3 {
    private Task3(){
    }
    public static void main(String[] args){
        Scanner input =new Scanner(System.in);
        int n = input.nextInt();
        final long[] array = new long[n];
        for (int i = 0; i < n; ++i) {
            array[i] = input.nextLong();
        }
        final int[] d = new int[n];
        d[0] = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (array[j] != 0 && array[i] % array[j] == 0 && d[j] > d[i]) {
                    d[i] = d[j];
                }
            }
            d[i]++;
        }
        int maxCount = 1;
        for (int i = 1; i < n; ++i) {
            maxCount = max(d[i], maxCount);
        }
        System.out.println(maxCount);
    }
}
