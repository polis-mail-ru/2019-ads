package ru.mail.polis.ads.part4.stasmilke;

import java.io.*;
import java.util.StringTokenizer;

public class Task4261 {
    private Task4261() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int[] array = new int[n];
        for (int i = 0; i < n; i++) {
            array[i] = in.nextInt();
        }
        int result = countInv(array, 0, n);
        out.println(result);
    }

    private static int countInv(int[] array, int left, int right) {
        if (left + 1 >= right) {
            return 0;
        }
        int mid =  left + (right - left) / 2;
        return countInv(array, left, mid) + countInv(array, mid, right) + countSplitInv(array, left, mid, right);
    }

    private static int countSplitInv(int[] array, int left, int mid, int right) {
        int iterLeft = 0;
        int iterMid = 0;
        int counter = 0;
        int[] result = new int[right - left];
        while ((left + iterLeft < mid) && (mid + iterMid < right)) {
            if (array[left + iterLeft] < array[mid + iterMid]) {
                result[iterLeft + iterMid] = array[left + iterLeft];
                iterLeft++;
            } else {
                result[iterLeft + iterMid] = array[mid + iterMid];
                iterMid++;
                counter += mid - left - iterLeft;
            }
        }
        while (left + iterLeft < mid) {
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
        return counter;
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
