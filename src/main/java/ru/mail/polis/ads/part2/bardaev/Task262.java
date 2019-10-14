package ru.mail.polis.ads.part2.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Task262 {
    private Task262() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int stairs = in.nextInt();
        int[] steps_val = new int[stairs+2];
        int max_step;
        int[] cost = new int[stairs+2];
        cost[1] = cost[0] + steps_val[1];


        steps_val[0] = 0;
        steps_val[steps_val.length-1] = 0;

        for (int e = 0; e < stairs; e++) {
            steps_val[e+1] = in.nextInt();
        }

        max_step = in.nextInt();
        int max;
        int j;

        for (int i = 1; i < stairs+2; i++) {
            max = Integer.MIN_VALUE;
            for (j = cuurentJ(i, max_step); j < i; j++) {
                if (cost[j] > max) max = cost[j];
            }
            cost[i] = steps_val[i] + max;
        }
        out.println(cost[stairs+1]);
        out.flush();

    }

    public static int cuurentJ(int i, int max_step) {
        if (max_step > i) return 0;
        else return i - max_step;
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
