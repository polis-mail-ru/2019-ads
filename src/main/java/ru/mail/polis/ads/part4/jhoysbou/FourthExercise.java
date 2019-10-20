package ru.mail.polis.ads.part4.jhoysbou;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//Submission here https://www.e-olymp.com/ru/submissions/5903674

public class FourthExercise {
    private static int invCounter = 0;

    private static class FastScanner {
        private final BufferedReader reader;
        private StringTokenizer tokenizer;

        FastScanner(final InputStream in) {
            reader = new BufferedReader(new InputStreamReader(in));
        }

        String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return tokenizer.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    public static void main (String[] args) {
        FastScanner scanner = new FastScanner(System.in);
        final int length = scanner.nextInt();
        int[] array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = scanner.nextInt();
        }

        countInv(array);

        System.out.println(invCounter);
    }

    private static int[] countInv(int[] array) {
        if (array.length <= 1) {
            return array;
        }

        int midPoint = array.length / 2;

        int[] left = new int[midPoint];
        int[] right = new int[array.length - midPoint];

        System.arraycopy(array, 0, left, 0, midPoint);
        System.arraycopy(array, midPoint, right, 0, array.length - midPoint);

        left = countInv(left);
        right = countInv(right);

        return countSplitInv(left, right);

    }

    private static int[] countSplitInv(int[] left, int[] right) {
        int[] temp = new int[left.length + right.length];
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                temp[k++] = left[i++];
            }
            else {
                temp[k++] = right[j++];
                invCounter += left.length - i;
            }
        }

        while (i < left.length) {
            temp[k++] = left[i++];
        }

        while (j < right.length) {
            temp[k++] = right[j++];
        }

        return temp;
    }


}
