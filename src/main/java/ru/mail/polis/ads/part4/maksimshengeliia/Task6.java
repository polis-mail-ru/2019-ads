package ru.mail.polis.ads.part4.maksimshengeliia;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5969737
* */
public class Task6 {
    private Task6() {
        // Should not be instantiated
    }

    static int[] array;
    private static void solve(final FastScanner in, final PrintWriter out) {
        int length = in.nextInt();
        int queries = in.nextInt();

        array = new int[length];

        for (int i = 0; i < length; i++) {
            array[i] = in.nextInt();
        }

        for (int i = 0; i < queries; i++) {
            int q = in.nextInt();
            out.println(binarySearch(q) ? "YES" : "NO");
        }
    }



    private static boolean binarySearch(int item) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            int guess = array[mid];
            if (guess == item) {
                return true;
            }
            if (guess > item) {
                high = mid - 1;
            } else {
                low = mid + 1;
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

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
