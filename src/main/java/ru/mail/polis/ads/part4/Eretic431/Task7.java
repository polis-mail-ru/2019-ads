package main.java.ru.mail.polis.ads.part4.Eretic431;

import java.io.*;
import java.util.StringTokenizer;

/**
 * https://www.e-olymp.com/ru/submissions/5972498
 */

public class Task7 {
    public static void main(String[] args) {
        final FastScanner in = new FastScanner(System.in);
        final PrintWriter out = new PrintWriter(System.out);
        final int n = in.nextInt();
        final int q = in.nextInt();
        final int[] arr = new int[n];


        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        for (int i = 0; i < q; i++) {
            final int tmp = in.nextInt();
            if (contains(tmp, arr)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
            out.flush();
        }
    }

    private static boolean contains(int n, int[] arr) {
        int floor = 0;
        int ceiling = arr.length - 1;

        while (floor <= ceiling) {
            int mid = (floor + ceiling) / 2;
            int pivot = arr[mid];

            if (pivot == n) {
                return true;
            }

            if (pivot > n) {
                ceiling = mid - 1;
            } else {
                floor = mid + 1;
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
