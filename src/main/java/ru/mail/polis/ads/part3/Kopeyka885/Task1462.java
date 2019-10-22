package ru.mail.polis.ads.part3.Kopeyka885;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1462 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int size = in.nextInt();
        int size_helping = size;
        int max_index = 0;
        int swapper = 0;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = in.nextInt();
        }

        while (size_helping > 1) {
            int max = 0;
            for (int i = 0; i < size_helping; i++) {
                if (arr[i]%10 > max%10){
                    max = arr[i];
                    max_index = i;
                }
                else if (arr[i]%10 == max%10 && arr[i] > max){
                    max = arr[i];
                    max_index = i;
                }
            }
            size_helping--;
            swapper = arr[size_helping];
            arr[size_helping] = max;
            arr[max_index] = swapper;
        }

        for (int i = 0; i < size; i++){
            out.print(arr[i] + " ");
        }
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