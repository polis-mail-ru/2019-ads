package ru.mail.polis.ads.part5.jhoysbou;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise_5 {

    private static int[] array;
    private static PrintWriter out = new PrintWriter(new BufferedOutputStream(System.out));
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        final int number = scanner.nextInt();

        array = new int[number];

        for (int i = 1; i<= number; i++) {
            array[i-1] = i;
        }
        show(array);

        while (nextPair(array, number)) {
            show(array);
        }
        out.flush();
        out.close();

    }

    private static boolean nextPair(int[] array, int number) {
        int j = number - 2;

        while (j != -1 && array[j] >= array[j+1]) {
            j--;
        }

        if (j == -1) {
            return false;
        }

        int k = number - 1;

        while (array[j] >= array[k]) {
            k--;
        }

        swap(array, j, k);

        int left = j + 1, right = number - 1;

        while (left < right) {
            swap(array, left++, right--);
        }
        return true;

    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void show(int[] array) {
        for (int i = 0; i < array.length; i++) {
            out.print(array[i] + " ");
        }
        out.println();
    }

}
