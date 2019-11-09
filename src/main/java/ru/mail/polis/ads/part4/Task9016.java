package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task9016 {
    private Task9016() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/6067816
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int arrLen = in.nextInt();
        int numberCount = in.nextInt();
        int[] arr = new int[arrLen];
        for (int i = 0; i < arrLen; i++) {
            arr[i] = in.nextInt();
        }
        for (int i = 0; i < numberCount; i++) {
            int num = in.nextInt();
            if (binarySearch(arr, num)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }

    private static boolean binarySearch(int[] arr, int num) {
        Integer i = -1;
            int low = 0, high = arr.length, mid;
            while (low < high) {
                mid = (low + high) >>> 1;
                if (num == arr[mid]) {
                    i = mid;
                    break;
                } else {
                    if (num < arr[mid]) {
                        high = mid;
                    } else {
                        low = mid + 1;
                    }
                }
            }
        return i >= 0;
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
