package ru.mail.polis.ads.part4.kokobox7;

import java.io.*;
import java.util.StringTokenizer;

public class Cows {
    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        int k = in.nextInt();
        int[] stalls = new int[n];
        for (int i = 0; i < n; i++) {
            stalls[i] = in.nextInt();
        }
        out.println(binarySearch(stalls, k));
    }


    private static int binarySearch(int[] stalls, int k) {
        int left = 0;
        int right = stalls[stalls.length - 1];
        int median = (left + right) / 2;

        while (right - left > 1) {
            if (areCowsCram(stalls, median, k)) {
                left = median;
            } else {
                right = median;
            }
            median = (left + right) / 2;
        }
        return median;
    }

    private static boolean areCowsCram(int[] stalls, int median, int k) {
        int cowsThatCram = 1;
        int stallWithACow = 0;
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - stalls[stallWithACow] >= median) {
                stallWithACow = i;
                cowsThatCram++;
            }
        }
        return (cowsThatCram >= k);
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
