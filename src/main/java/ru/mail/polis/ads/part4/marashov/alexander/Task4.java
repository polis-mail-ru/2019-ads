package ru.mail.polis.ads.part4.marashov.alexander;

import java.util.Scanner;

public class Task4 {

    private static long mergeSort(final int[] array) {
        long counter = 0;
        for (int blockSize = 1; blockSize < array.length; blockSize *= 2) {
            for (int firstBlockBegin = 0; firstBlockBegin < array.length - blockSize; firstBlockBegin = firstBlockBegin + 2 * blockSize) {
                final int secondBlockBegin = firstBlockBegin + blockSize;
                int firstBlockIndex = firstBlockBegin;
                int secondBlockIndex = secondBlockBegin;
                final int secondBlockEnd = Math.min(secondBlockBegin + blockSize, array.length);
                final int offset = firstBlockBegin + secondBlockBegin;

                final int[] mergedArray = new int[secondBlockEnd - firstBlockBegin];
                while (firstBlockIndex != secondBlockBegin && secondBlockIndex != secondBlockEnd) {
                    if (array[firstBlockIndex] <= array[secondBlockIndex]) {
                        mergedArray[firstBlockIndex + secondBlockIndex - offset] = array[firstBlockIndex];
                        ++firstBlockIndex;
                    } else {
                        counter += secondBlockBegin - firstBlockIndex;
                        mergedArray[firstBlockIndex + secondBlockIndex - offset] = array[secondBlockIndex];
                        ++secondBlockIndex;
                    }
                }
                while (firstBlockIndex != secondBlockBegin) {
                    mergedArray[firstBlockIndex + secondBlockIndex - offset] = array[firstBlockIndex];
                    ++firstBlockIndex;
                }
                while (secondBlockIndex != secondBlockEnd) {
                    mergedArray[firstBlockIndex + secondBlockIndex - offset] = array[secondBlockIndex];
                    ++secondBlockIndex;
                }
                System.arraycopy(mergedArray, 0, array, firstBlockBegin, secondBlockEnd - firstBlockBegin);
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
