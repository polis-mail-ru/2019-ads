package ru.mail.polis.ads.part4.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 *  Название задачи: Количество инверсий  https://www.e-olymp.com/ru/problems/4261
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5910137
 */
public class NumberOfInversions {

    private static int[] arr;
    private static int[] sortedSubArr;
    private static int result;

    private NumberOfInversions() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberOfElements = in.nextInt();
        arr = new int[numberOfElements];
        sortedSubArr = new int[numberOfElements];
        result = 0;

        fillArr(in);

        sort(0, arr.length-1);
        out.println(result);
    }


    private static void fillArr(FastScanner in) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = in.nextInt();
        }
    }


    private static void sort(final int left, final int right) {

        if(left == right) {
            return;
        }

        int middle = (left+right)/2;
        sort(left, middle);
        sort(middle+1, right);

        int leftPointer = left, rightPointer = middle+1, sortedArrPointer = 0;
        while (leftPointer <= middle || rightPointer <= right ) {
            if (leftPointer == middle+1) {
                System.arraycopy(arr, rightPointer, sortedSubArr, sortedArrPointer, right-rightPointer+1);
                rightPointer = right+1;
            } else if (rightPointer == right+1) {
                System.arraycopy(arr, leftPointer, sortedSubArr, sortedArrPointer, middle-leftPointer+1);
                leftPointer = middle+1;
            } else if (arr[rightPointer] < arr[leftPointer]) {
                sortedSubArr[sortedArrPointer] = arr[rightPointer];
                sortedArrPointer++;
                rightPointer++;
                result += (middle - leftPointer +1);
            } else {
                sortedSubArr[sortedArrPointer] = arr[leftPointer];
                sortedArrPointer++;
                leftPointer++;
            }
        }

        // copy sorted part back to main array
        System.arraycopy(sortedSubArr, 0, arr, left, right-left+1);
    }


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


    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
