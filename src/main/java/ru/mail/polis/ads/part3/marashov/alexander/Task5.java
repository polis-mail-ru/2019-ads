package ru.mail.polis.ads.part3.marashov.alexander;

import java.io.PrintWriter;

public class Task5 {

    private static class Robot {
        int mainNumber;
        int additionalNumber;
    }

    private static void mergeSort(final Robot[] array) {
        for (int blockSize = 1; blockSize < array.length; blockSize *= 2) {
            for (int firstBlockBegin = 0; firstBlockBegin < array.length - blockSize; firstBlockBegin = firstBlockBegin + 2 * blockSize) {
                final int secondBlockBegin = firstBlockBegin + blockSize;
                int firstBlockIndex = firstBlockBegin;
                int secondBlockIndex = secondBlockBegin;
                final int secondBlockEnd = Math.min(secondBlockBegin + blockSize, array.length);
                final int offset = firstBlockBegin + secondBlockBegin;

                final Robot[] mergedArray = new Robot[secondBlockEnd - firstBlockBegin];
                while (firstBlockIndex != secondBlockBegin && secondBlockIndex != secondBlockEnd) {
                    if (array[firstBlockIndex].mainNumber <= array[secondBlockIndex].mainNumber) {
                        mergedArray[firstBlockIndex + secondBlockIndex - offset] = array[firstBlockIndex];
                        ++firstBlockIndex;
                    } else {
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
    }

    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final int len = in.nextInt();
        final Robot[] array = new Robot[len];
        for (int i = 0; i < len; ++i) {
            array[i] = new Robot();
            array[i].mainNumber = in.nextInt();
            array[i].additionalNumber = in.nextInt();
        }
        mergeSort(array);
        try (PrintWriter out = new PrintWriter(System.out)) {
            for (final Robot robot: array) {
                out.println(robot.mainNumber + " " + robot.additionalNumber);
            }
        }

    }
}
