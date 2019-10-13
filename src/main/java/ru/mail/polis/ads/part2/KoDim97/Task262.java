package ru.mail.polis.ads.part2.KoDim97;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task262 {
    private Task262() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int arr[]= new int[size + 2];
        int addArr[]= new int[size + 2];
        for (int i = 0; i < size; i++) {
            arr[i+1] = in.nextInt();
            addArr[i+1] = Integer.MIN_VALUE;
        }
        addArr[size + 1] = Integer.MIN_VALUE;
        int k = in.nextInt();
        for (int i = 1; i <= size + 1; i++) {
            for (int j = 0; j < (i < k ? i : k); j++){
                int curVal = addArr[i - 1 - j] + arr[i];
                if(addArr[i] < curVal){
                    addArr[i] = curVal;
                }
            }
        }
        out.print(addArr[size + 1]);
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

