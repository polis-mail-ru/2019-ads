package ru.mail.polis.ads.part4.KateMoreva;

import java.util.Scanner;
import static java.lang.Math.min;

//e-olymp problem 4261 "Количество инверсий"

public class Task4 {
    private Task4(){
    }
    private static long mergeSort(final int[] array) {
        long counter = 0;
        for (int blockSize = 1; blockSize < array.length; blockSize *= 2) {
            for (int firstBlB = 0; firstBlB < array.length - blockSize; firstBlB = firstBlB + 2 * blockSize) {
                final int secondBlB = firstBlB + blockSize;
                int firstBlI = firstBlB;
                int secondBlI = secondBlB;
                final int secondBlockEnd = min(secondBlB + blockSize, array.length);
                final int offset = firstBlB + secondBlB;

                final int[] mergedArray = new int[secondBlockEnd - firstBlB];
                while (firstBlI != secondBlB && secondBlI != secondBlockEnd) {
                    if (array[firstBlI] <= array[secondBlI]) {
                        mergedArray[firstBlI + secondBlI - offset] = array[firstBlI];
                        ++firstBlI;
                    } else {
                        counter += secondBlB - firstBlI;
                        mergedArray[firstBlI + secondBlI - offset] = array[secondBlI];
                        ++secondBlI;
                    }
                }
                while (firstBlI != secondBlB) {
                    mergedArray[firstBlI + secondBlI - offset] = array[firstBlI];
                    ++firstBlI;
                }
                while (secondBlI != secondBlockEnd) {
                    mergedArray[firstBlI + secondBlI - offset] = array[secondBlI];
                    ++secondBlI;
                }
                System.arraycopy(mergedArray, 0, array, firstBlB, secondBlockEnd - firstBlB);
            }
        }
        return counter;
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = Integer.parseInt(in.next());
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = Integer.parseInt(in.next());
        }
        System.out.println(mergeSort(array));
    }
}

