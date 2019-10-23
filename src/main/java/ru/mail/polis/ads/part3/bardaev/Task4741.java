package ru.mail.polis.ads.part3.bardaev;

import java.io.*;
import java.util.StringTokenizer;

public class Task4741 {
    private Task4741() {}

    private static void solve(final FastScanner in, final PrintWriter out) {
        int count = in.nextInt();
        int arr[] = new int[count];
        for (int i = 0; i < count; i++) {
            arr[i] = in.nextInt();
        }

        int counter = 0;
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    counter++;
                }
            }
        }
        out.print(counter);
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
    }

    public static void main(final String[] arg) {
        final FastScanner in = new FastScanner(System.in);
        try (PrintWriter out = new PrintWriter(System.out)) {
            solve(in, out);
        }
    }
}
