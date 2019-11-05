package ru.mail.polis.ads.part4.Kopeyka885;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Main {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int amount = in.nextInt();
        int cows = in.nextInt();
        int[] distance = new int[amount];
        for (int i = 0; i < amount; i++) {
            distance[i] = in.nextInt();
        }
        if (cows == 2) {
            out.print(distance[amount - 1] - distance[0]);
            return;
        }
        int left = 0;
        int right = distance[amount - 1] - distance[0];
        while (left < right) {
            int mid = (left + right) / 2;
            int g = cows - 1;
            int j = 0;
            for (int i = 1; i < amount; i++) {
                if (distance[i] - distance[j] >= mid) {
                    j = i;
                    g--;
                }
            }
            if (g <= 0) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        out.print(left - 1);
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
