package ru.mail.polis.ads.part3.shakirov_aa;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Task1 {
    public static void main(String[] args) {
        FastScanner fs = new FastScanner(System.in);
        final int N = fs.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = fs.nextInt();
        }

        Arrays.sort(arr);
        PrintWriter out = new PrintWriter(System.out);

        for (int i = 0; i < N; i++) {
            out.print(arr[i] + " ");
        }
        out.close();
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
