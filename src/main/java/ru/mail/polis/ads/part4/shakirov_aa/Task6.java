package ru.mail.polis.ads.part4.shakirov_aa;

import java.io.*;
import java.util.StringTokenizer;

public class Task6 {
    public static void main(String[] args) {
        FastScanner in = new FastScanner(System.in);
        PrintWriter out = new PrintWriter(System.out);
        int n = in.nextInt();
        int q = in.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            if (binarySearch(array, in.nextInt())) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
        out.close();
    }

    static boolean binarySearch(int[] array, int x) {
        int low = 0, high = array.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (array[mid] > x) {
                high = mid - 1;
            } else if (array[mid] < x) {
                low = mid + 1;
            } else {
                return true;
            }
        }

        return false;
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
