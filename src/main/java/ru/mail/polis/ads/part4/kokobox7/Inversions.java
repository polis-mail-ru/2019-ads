package ru.mail.polis.ads.part4.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class Inversions {
    private static int inversions = 0;

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        mergeSort(array);
        out.println(inversions);
    }

    private static int[] mergeSort(int[] array) {
        int length = array.length;
        if (length < 2) return array;

        int halfLength = length / 2;
        int[] left = new int[halfLength];
        int[] right = new int[length - halfLength];
        for (int i = 0; i < halfLength; i++) {
            left[i] = array[i];
        }
        for (int i = halfLength; i < length; i++) {
            right[i - halfLength] = array[i];
        }
        left = mergeSort(left);
        right = mergeSort(right);
        array = merge(left, right, halfLength, array.length - halfLength);
        return array;
    }

    private static int[] merge(int[] left, int[] right, int halfLength, int remainingLength) {
        int[] newArray = new int[halfLength + remainingLength];
        int i = 0;
        int l = 0;
        int r = 0;
        while (l < halfLength && r < remainingLength) {
            if (left[l] < right[r])
                newArray[i++] = left[l++];
            else {
                newArray[i++] = right[r++];
                inversions += halfLength - l;
            }
        }
        while (l < halfLength)
            newArray[i++] = left[l++];
        while (r < remainingLength)
            newArray[i++] = right[r++];
        return newArray;
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
