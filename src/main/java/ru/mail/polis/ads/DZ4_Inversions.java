package ru.mail.polis.ads;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DZ4_Inversions {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        System.out.println(getCountOfInversions(arr, 0, n - 1));
    }

    static int getCountOfInversions(int[] arr, int left, int right) {
        if (left >= right) {
            return 0;
        }

        int middle = (left + right) / 2;
        return getCountOfInversions(arr, left, middle)
                + getCountOfInversions(arr, middle + 1, right)
                + getCountOfSplitInversions(arr, left, right, middle);
    }

    static int getCountOfSplitInversions(int[] arr, int left, int right, int middle) {
        int[] l = Arrays.copyOfRange(arr, left, middle + 1);
        int[] r = Arrays.copyOfRange(arr, middle + 1, right + 1);

        int i = 0, j = 0, k = left, count = 0;
        while (i < l.length && j < r.length) {
            if (l[i] < r[j]) {
                arr[k] = l[i];
                i++;
            } else {
                arr[k] = r[j];
                j++;
                count += l.length - i;
            }

            k++;
        }

        while (i < l.length) {
            arr[k] = l[i];
            i++;
            k++;
        }

        while (j < r.length) {
            arr[k] = r[j];
            j++;
            k++;
        }

        return count;
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