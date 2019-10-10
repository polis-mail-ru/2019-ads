package ru.mail.polis.ads.part3.jhoysbou;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5825476

public class SecondExercise {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        Integer[] array = new Integer[length];

        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array, new myComparator());

        for (int i = 0; i < length; i++) {
            System.out.print(array[i] + " ");
        }
    }

    public static class myComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer firstNumber, Integer secondNumber) {
            if (firstNumber % 10 == secondNumber % 10) {
                return firstNumber - secondNumber;
            } else {
                return firstNumber % 10 -secondNumber % 10;
            }
        }

    }
}

