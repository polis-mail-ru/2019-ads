package ru.mail.polis.ads.part5.vegtamrab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task5 {

    private static void solve(final FastScanner in, final PrintWriter out) {
        int n = in.nextInt();
        StringBuilder builder = new StringBuilder();
        int[] array = new int[n];

        for (int i = 0; i < n; ++i) {
            array[i] = i + 1;
        }

        do {
            out.println(atos(array, builder));
        } while (transpositioning(array));
    }

    private static String atos(int[] array, StringBuilder builder) {
        builder.setLength(0);

        for (int a : array) {
            builder.append(a);
            builder.append(" ");
        }

        return builder.toString();
    }

    public static boolean transpositioning(int[] sequence) {
        int i = sequence.length;
        int j = sequence.length;

        do {
            if (i < 2) {
                return false;
            }
            --i;
        } while (sequence[i - 1] >= sequence[i]);

        while (i < j && sequence[i - 1] >= sequence[--j]);
        swap(sequence, i - 1, j);
        j = sequence.length;

        while (i < --j) {
            swap(sequence, i++, j);
        }
        return true;
    }

    private static void swap(int[] array, int lhs, int rhs) {
        int tmp = array[lhs];
        array[lhs] = array[rhs];
        array[rhs] = tmp;
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
