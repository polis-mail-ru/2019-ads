package ru.mail.polis.ads.part4.suhova;

import java.io.*;
import java.util.StringTokenizer;

public class Task9016 {
    /*
    Task 6: https://www.e-olymp.com/ru/submissions/5881904
     */

    private static void solve(final Task9016.FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int q = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) {
            int x = in.nextInt();
            if (binarySearch(a, n, x)) out.println("YES");
            else out.println("NO");
        }
        out.flush();
    }

    private static boolean binarySearch(int[] a, int toIndex, int key) {
        int low = 0;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = low + high >>> 1;
            int midVal = a[mid];
            if (midVal < key) {
                low = mid + 1;
            } else {
                if (midVal <= key) {
                    return true;
                }

                high = mid - 1;
            }
        }

        return false;
    }

    public static void main(final String[] arg) {
        final Task9016.FastScanner in = new Task9016.FastScanner(System.in);
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
