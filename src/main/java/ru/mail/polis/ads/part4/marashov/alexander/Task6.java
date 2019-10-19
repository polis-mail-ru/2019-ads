package ru.mail.polis.ads.part4.marashov.alexander;

import java.util.Scanner;

public class Task6 {

    private static boolean mergeSort(final int[] array, final int maxWeight) {
        for (int blockSize = 1; blockSize < array.length; blockSize *= 2) {
            for (int firstBlockBegin = 0; firstBlockBegin < array.length - blockSize; firstBlockBegin = firstBlockBegin + 2 * blockSize) {
                final int secondBlockBegin = firstBlockBegin + blockSize;
                int firstBlockIndex = firstBlockBegin;
                int secondBlockIndex = secondBlockBegin;
                final int secondBlockEnd = Math.min(secondBlockBegin + blockSize, array.length);
                final int offset = firstBlockBegin + secondBlockBegin;

                final int[] mergedArray = new int[secondBlockEnd - firstBlockBegin];
                int maxWeightToSwap = 0;
                while (firstBlockIndex != secondBlockBegin && secondBlockIndex != secondBlockEnd) {
                    if (array[firstBlockIndex] <= array[secondBlockIndex]) {
                        mergedArray[firstBlockIndex + secondBlockIndex - offset] = array[firstBlockIndex];
                        ++firstBlockIndex;
                    } else if (array[firstBlockIndex] + array[secondBlockIndex] > maxWeight) {
                        return false;
                    } else {
                        maxWeightToSwap = Math.max(maxWeightToSwap, array[secondBlockIndex]);
                        mergedArray[firstBlockIndex + secondBlockIndex - offset] = array[secondBlockIndex];
                        ++secondBlockIndex;
                    }
                }
                while (firstBlockIndex != secondBlockBegin) {
                    if (maxWeightToSwap + array[firstBlockIndex] > maxWeight) {
                        return false;
                    }
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
        return true;
    }

    public static void main(String[] args) {
        final Scanner in = new Scanner(System.in);
        final int n = Integer.parseInt(in.next());
        final int m = Integer.parseInt(in.next());
        final int[] array = new int[n];
        for (int i = 0; i < n; ++i) {
            array[i] = Integer.parseInt(in.next());
        }
        if (mergeSort(array, m)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }
}
