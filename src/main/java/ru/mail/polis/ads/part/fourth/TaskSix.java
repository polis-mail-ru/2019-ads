package ru.mail.polis.ads.part.fourth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * @author v.ivlev
 * https://www.e-olymp.com/ru/submissions/5971268
 */
public class TaskSix {

    private static void solve(FastScanner in, PrintWriter out) {
        int[] array = new int[in.nextInt()];
        int n = in.nextInt();
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (binarySearch(array, array.length, x)) {
                out.println("YES");
            } else {
                out.println("NO");
            }
        }
    }

    private static boolean binarySearch(int[] array, int i, int key) {
        int low = 0;
        int high = i - 1;
        while (low <= high) {
            int mid = low + high >>> 1;
            int midValue = array[mid];
            if (midValue < key) {
                low = mid + 1;
            } else {
                if (midValue <= key) {
                    return true;
                }
                high = mid - 1;
            }
        }
        return false;
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