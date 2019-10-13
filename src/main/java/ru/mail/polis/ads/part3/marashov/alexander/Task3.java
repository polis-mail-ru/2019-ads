package ru.mail.polis.ads.part3.marashov.alexander;

import java.util.Scanner;

public class Task3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int count = in.nextInt();
        int[] array = new int[count];
        for (int i = 0; i < count; ++i) {
            array[i] = in.nextInt();
        }

        long answer = 0;
        for (int i = 1; i < count; ++i) {
            for (int j = 0; j < count - i; ++j) {
                if (array[j] > array[j + 1]) {
                    ++answer;
                    final int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        System.out.println(answer);
    }
}
