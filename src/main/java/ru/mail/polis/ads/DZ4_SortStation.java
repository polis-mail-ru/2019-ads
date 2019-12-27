package ru.mail.polis.ads;

import java.io.*;
import java.util.StringTokenizer;

public class DZ4_SortStation {
    public static void main(String[] args) {
        FastScanner scan = new FastScanner(System.in);
        int  n = scan.nextInt();
        int m = scan.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scan.nextInt();
        }
        logic(arr, n, m);
    }

    private static void logic(int[] arr, int n, int m) {
        for (int i = 1; i < n; i++) {
            int j = i;
            int k = arr[i];
            while (j > 0 && arr[j - 1] > arr[j]) {
                if (m < arr[j - 1] + k) {
                    System.out.println("No");
                    return;
                }
                arr[j] = arr[--j];
            }
            arr[j] = k;
        }
        System.out.println("Yes");
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

