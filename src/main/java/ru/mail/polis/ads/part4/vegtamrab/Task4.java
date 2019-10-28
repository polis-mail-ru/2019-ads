package ru.mail.polis.ads.part4.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task4 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] A = new int[n];

        for (int i = 0; i < n; ++i) {
            A[i] = in.nextInt();
        }

        out.println(countInversions(A, 0, n));
    }

    private static int countInversions(int[] array, int left, int right) {
        if (left + 1 >= right) {
            return 0;
        }

        int mid = left + (right - left) / 2;
        return countInversions(array, left, mid) +
            countInversions(array, mid, right) +
            countSplitInversions(array, left, mid, right);
    }

    private static int countSplitInversions(int[] array, int left, int mid, int right) {
        int leftIterator = 0;
        int midIterator = 0;
        int count = 0;

        int[] res = new int[right - left];

        while ((left + leftIterator < mid) && (mid + midIterator < right)) {
            if (array[left + leftIterator] < array[mid + midIterator]) {
                res[leftIterator + midIterator] = array[left + leftIterator];
                leftIterator++;
            } else {
                res[leftIterator + midIterator] = array[mid + midIterator];
                midIterator++;
                count += mid - left - leftIterator;
            }
        }

        while (left + leftIterator < mid) {
            res[leftIterator + midIterator] = array[left + leftIterator];
            leftIterator++;
        }

        while (mid + midIterator < right) {
            res[leftIterator + midIterator] = array[mid + midIterator];
            midIterator++;
        }

        for (int i = 0; i < leftIterator + midIterator; ++i) {
            array[left + i] = res[i];
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        } catch (Exception ex) {
            System.out.println();
        }
    }
}
