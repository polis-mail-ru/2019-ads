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
public final class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long m = in.nextLong();
        long[] array = new long[n];

        for (int i = 0; i < n; ++i) {
            array[i] = in.nextLong();
        }

        out.println(isCorrect(array, 0, n, m) ? "Yes" : "No");
    }

    private static boolean isCorrect(long[] array, int left, int right, long m) {
        if (left + 1 >= right) {
            return true;
        }

        int mid =  left + (right - left) / 2;
        return isCorrect(array, left, mid, m) &&
               isCorrect(array, mid, right, m) &&
               isCorrectSplit(array, left, mid, right, m);
    }

    private static boolean isCorrectSplit(long[] array, int left, int mid, int right, long m) {
        int leftIterator = 0;
        int midIterator = 0;
        long[] res = new long[right - left];

        while ((left + leftIterator < mid) && (mid + midIterator < right)) {
            if (array[left + leftIterator] < array[mid + midIterator]) {
                res[leftIterator + midIterator] = array[left + leftIterator];
                leftIterator++;
            } else {
                if (array[left + leftIterator] + array[mid + midIterator] > m) {
                    return false;
                }
                res[leftIterator + midIterator] = array[mid + midIterator];
                midIterator++;
            }
        }

        while (left + leftIterator < mid) {
            if (array[left + leftIterator] + array[mid + midIterator - 1] > m) {
                return false;
            }
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

        return true;
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
        
        long nextLong() {
            return Long.parseLong(next());
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
