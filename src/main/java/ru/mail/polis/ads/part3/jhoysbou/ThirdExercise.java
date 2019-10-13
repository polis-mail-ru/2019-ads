package ru.mail.polis.ads.part3.jhoysbou;

import java.util.Scanner;

// Submission here https://www.e-olymp.com/ru/submissions/5841905

public class ThirdExercise {

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int length = scanner.nextInt();
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }
        
        System.out.println(bubbleSort(array));
        

    }

    private static int bubbleSort(int[] array) {
        int counter = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 1; j < array.length-i; j++) {
                if (array[j-1] > array[j]) {
                    int temp = array[j-1];
                    array[j-1] = array[j];
                    array[j] = temp;
                    counter++;
                }
            }
        }

        return counter;
    }

}
