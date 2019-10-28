package ru.mail.polis.ads.part.fourth;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5971035
 */
public class TaskFour {

    private static int count = 0;

    private static void solve(FastScanner in, PrintWriter out) {
        int[] array = new int[in.nextInt()];
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
        mergeSort(array, array.length);
        out.println(count);
    }

    private static int[] mergeSort(int[] array, int size) {
        if (size > 1) {
            int mid = size / 2;
            int[] arrayLeft = Arrays.copyOfRange(array, 0, mid);
            int[] arrayRight = Arrays.copyOfRange(array, mid, size);
            arrayLeft = mergeSort(arrayLeft, mid);
            arrayRight = mergeSort(arrayRight, size - mid);
            array = merge(arrayLeft, arrayRight, mid, size - mid);
        }
        return array;
    }

    private static int[] merge(int arrayLeft[], int arrayRight[], int l, int r) {
        int array[] = new int[l + r];
        int i = 0;
        int m = 0;
        int n = 0;
        while (m < l && n < r) {
            if (arrayLeft[m] < arrayRight[n]) {
                array[i++] = arrayLeft[m++];
            } else {
                array[i++] = arrayRight[n++];
                count += l - m;
            }
        }
        while (m < l) {
            array[i++] = arrayLeft[m++];
        }
        while (n < r) {
            array[i++] = arrayRight[n++];
        }
        return array;
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
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
}
