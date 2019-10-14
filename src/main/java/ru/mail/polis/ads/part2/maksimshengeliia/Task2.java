package ru.mail.polis.ads.part2.maksimshengeliia;


import java.io.*;
import java.util.StringTokenizer;

/*
*   https://www.e-olymp.com/ru/submissions/5849903
* */
public class Task2 {
    private Task2() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {

        int n = in.nextInt();

        int[] array = new int[n + 2];
        int[] dynamic = new int[n + 2];

        for (int i = 1; i < n + 1; i++) {
            array[i] = in.nextInt();
        }
        int step = in.nextInt();
        int[] lastVars = new int[step];

        for (int i = 1; i < array.length; i++) {
            if (step <= i) {
                // If we can consider last 'step' variables in array
                int a = 0;
                for (int j = i - step; j < i; j++, a++) {
                    lastVars[a] = dynamic[j];
                }
            } else {
                for (int j = 1; j < i; j++) {
                    lastVars[j] = dynamic[j];
                }
            }
            int max = findMax(lastVars);
            dynamic[i] = max + array[i];
        }

        System.out.println(dynamic[n + 1]);
    }

    private static int findMax(int[] lastVars) {

        int max = lastVars[0];
        for (int k = 1; k < lastVars.length; k++) {
            if (max < lastVars[k]) max = lastVars[k];
        }
        return max;
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