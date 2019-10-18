package ru.mail.polis.ads.part4.jhoysbou;

import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5892859

public class FirstExercise {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        long[] array = new long[length + 1];

        for (int i = 1; i < length + 1; i++) {
            array[i] = scanner.nextLong();
        }

        if(isHeap(array)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

    }

    private static boolean isHeap(long[] array) {
        for (int i = 1; 2*i + 1 <= array.length; i++) {
            if (array[2*i] < array[i] && array[2*i+1] < array[i]) {
                return false;
            }
        }
        return true;
    }
}
