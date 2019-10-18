package ru.mail.polis.ads.part4.medalexey;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class BinarySearch {

    private static int[] array;

    private BinarySearch() {
    }


    private static void solve(final FastScanner in, final PrintWriter out) {
        final int ARRAY_SIZE = in.nextInt();
        final int NUMBER_OF_REQUESTS = in.nextInt();

        array = new int[ARRAY_SIZE];
        fillArray(in);

        for (int i = 0; i < NUMBER_OF_REQUESTS; i++) {
            out.println(
                    findElement(
                            in.nextInt()
                    ) ? "YES" : "NO"
            );

        }

    }


    private static void fillArray(final FastScanner in) {
        for (int i = 0; i < array.length; i++) {
            array[i] = in.nextInt();
        }
    }


    private static boolean findElement(final int element) {
        int left = 0;
        int right = array.length - 1;
        int median = left + (right-left)/2;

        while (left != right && element != array[median]) {
            if (element > array[median]) {
                left = (median == left) ? right : median;
            } else {
                right = median;
            }

            median = left + (right-left)/2;
        }

        return element == array[median];
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
