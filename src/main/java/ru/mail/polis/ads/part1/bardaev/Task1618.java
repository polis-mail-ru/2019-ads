package ru.mail.polis.ads.part1.bardaev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/**
 * Problem solution template.
 */
public final class Task1618 {
    private Task1618() {
        // Should not be instantiated
    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int length1 = in.nextInt();
        if (length1 < 1 && length1 > 1000) return;
        String[] s1 = in.next().split(" ");
        int length2 = in.nextInt();
        if (length2 < 1 && length2 > 1000) return;
        String[] s2 = in.next().split(" ");
        int[] sequence1 = new int[s1.length + 1];
        int[] sequence2 = new int[s2.length + 1];

        for (int q = 0; q < length1; q++) {
            sequence1[q + 1] = Integer.parseInt(s1[q]);
        }
        for (int w = 0; w < length2; w++) {
            sequence2[w + 1] = Integer.parseInt(s2[w]);
        }

        int[][] result = new int[length1+1][length2+1];

        for (int i = 1; i < length1+1; i++) {
            for (int j = 1; j < length2+1; j++) {
                if (sequence1[i] == sequence2[j]) {
                    result[i][j] = result[i-1][j-1] + 1;
                } else {
                    result[i][j] = Math.max(result[i-1][j], result[i][j-1]);
                }
            }
        }

//        int x_i = length1 - 1, y_i = length2 - 1;
//        ArrayList<Integer> res = new ArrayList<>();
//
//        while (x_i >= 0 && y_i >= 0) {
//            if (sequence1[x_i] == sequence2[y_i]) {
//                res.add(sequence1[x_i]);
//                x_i--;
//                y_i--;
//            } else if (result[x_i-1][y_i] > result[x_i][y_i-1]) {
//                x_i--;
//            } else {
//                y_i--;
//            }
//        }

        out.println(result[length1][length2]);
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
                    tokenizer = new StringTokenizer(reader.readLine(), "\t\r\n");
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
