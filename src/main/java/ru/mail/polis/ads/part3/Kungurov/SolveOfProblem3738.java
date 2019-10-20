package ru.mail.polis.ads.part3.Kungurov;

import java.io.*;
import java.util.StringTokenizer;

/**
 * created by kirill kungurov on 16.10.19
 * https://www.e-olymp.com/ru/submissions/5908018
 */
public class SolveOfProblem3738 {
    private SolveOfProblem3738() {
    }

    private static void mergeSort(int[] arr, int arrLength) {
        if (arrLength < 2) {
            return;
        }
        int mid = arrLength / 2;
        int[] lArr = new int[mid];
        int[] rArr = new int[arrLength - mid];
        for (int i = 0; i < mid; i++) {
            lArr[i] = arr[i];
        }
        for (int i = mid; i < arrLength; i++) {
            rArr[i - mid] = arr[i];
        }
        mergeSort(lArr, mid);
        mergeSort(rArr, arrLength - mid);

        merge(arr, lArr, rArr, mid, arrLength - mid);
    }

    public static void merge(int[] arr, int[] lArr, int[] rArr, int left, int right) {

        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (lArr[i] <= rArr[j]) {
                arr[k++] = lArr[i++];
            } else {
                arr[k++] = rArr[j++];
            }
        }
        while (i < left) {
            arr[k++] = lArr[i++];
        }
        while (j < right) {
            arr[k++] = rArr[j++];
        }
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        int[] arr = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = in.nextInt();
        }
        mergeSort(arr,count);
        for (int i = 0; i < count; i++) {
            out.print(arr[i] + " ");
        }

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

