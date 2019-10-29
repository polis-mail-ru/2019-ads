package ru.mail.polis.ads.part4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5149 {
    private Task5149() {
        // Should not be instantiated https://www.e-olymp.com/ru/submissions/5976606
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        Integer num, numOfCows, currentPlace;
        Long left, right, top, down;
        num = in.nextInt();
        numOfCows = in.nextInt();
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = in.nextInt();
        }
        if (numOfCows == 2) {
            out.println(array[num - 1] - array[0]);
            return;
        }
        left = 0l;
        right = (long) array[num - 1] - array[0];
        while (!left.equals(right)) {
            top = (left + right) / 2;
            down = 1l;
            currentPlace = 0;
            for (int i = 0; i < num; i++) {
                if (array[i] - array[currentPlace] >= top) {
                    currentPlace = i;
                    down++;
                }
            }
            if (down >= numOfCows) {
                left = top + 1;
            } else {
                right = top;
            }
        }
        out.println(left - 1);
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
