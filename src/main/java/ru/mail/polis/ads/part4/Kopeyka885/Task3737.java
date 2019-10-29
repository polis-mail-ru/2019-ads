package ru.mail.polis.ads.part4.Kopeyka885;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task3737 {

    private static void solve(final FastScanner in, final PrintWriter out){
        int size = in.nextInt();
        int arr_size = size+1;
        long[] arr = new long[size + 1];
        for (int i = 1; i < arr_size; i++) {
            arr[i] = in.nextLong();
        }

        for (int i = 1; i < arr_size; i++) {
            if ((2 * i) < arr_size){
                if (arr[i] > arr[2 * i]) {
                    out.println("NO");
                    out.flush();
                    return;
                }
            }
           if ((2 * i + 1) < arr_size) {
                if (arr[i] > arr[2 * i + 1]) {
                    out.println("NO");
                    out.flush();
                    return;
                }
            }
        }
        out.println("YES");
        out.flush();
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

    public static void main(final String[] arg){
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}