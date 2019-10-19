package ru.mail.polis.ads.part4.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task1457 {
    private Task1457() {
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        long m = in.nextLong();
        long[] array = new long[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextLong();
        }
        boolean result = isCorrect(array, 0, n, m);
        out.println(result ? "Yes" : "No");
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
        int iterLeft = 0;
        int iterMid = 0;
        boolean isCorrect = true;
        long[] result = new long[right - left];
        while ((left + iterLeft < mid) && (mid + iterMid < right)) {
            if (array[left + iterLeft] < array[mid + iterMid]) {
                result[iterLeft + iterMid] = array[left + iterLeft];
                iterLeft++;
            } else {
                if (array[left + iterLeft] + array[mid + iterMid] > m) {
                    isCorrect = false;
                }
                result[iterLeft + iterMid] = array[mid + iterMid];
                iterMid++;
            }
        }
        while (left + iterLeft < mid) {
            if (array[left + iterLeft] + array[mid + iterMid - 1] > m) {
                isCorrect = false;
            }
            result[iterLeft + iterMid] = array[left + iterLeft];
            iterLeft++;
        }
        while (mid + iterMid < right) {
            result[iterLeft + iterMid] = array[mid + iterMid];
            iterMid++;
        }
        for (int i = 0; i < iterLeft + iterMid; i++) {
            array[left + i] = result[i];
        }
        return isCorrect;
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
            ex.printStackTrace();
        }
    }
}
