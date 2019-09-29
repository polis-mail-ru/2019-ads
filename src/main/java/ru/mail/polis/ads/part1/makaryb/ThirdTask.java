package ru.mail.polis.ads.part1.makaryb;

// 1618

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Made by БорискинМА
 * 28.09.19
 * gr. Java-10, Технополис
 * IntelliJ IDEA Ultimate 2019.2 (JetBrains Product Pack for Students)
 * e-olymp 100%: https://www.e-olymp.com/ru/submissions/5736029
 */
public final class ThirdTask {

    private static int[] x;
    private static int[] y;
    private static int[][] last2rows;

    private ThirdTask() {}

    private static void solve(final FastScanner in, final PrintWriter out) {
        int len1 = in.nextInt();
        x = new int[len1 +1];

        for(int i = 1; i <= len1; i++)
            x[i] = in.nextInt();

        int len2 = in.nextInt();
        y = new int[len2 +1];

        for(int i = 1; i <= len2; i++)
            y[i] = in.nextInt();

        last2rows = new int[len1 +1][len2 +1];
        for(int i = 0; i <= len1; i++)
            Arrays.fill(last2rows[i], -1);

        int res = lcs(len1, len2);

        System.out.println(res);

        out.flush();
    }
    // https://www.geeksforgeeks.org/java-program-for-longest-common-subsequence/
    private static int lcs(int len2, int len1) {
        if (len2 == 0 || len1 == 0)
            return 0;
        if (last2rows[len2][len1] != -1) return last2rows[len2][len1];
        if (x[len2] == y[len1])
            return 1 + lcs(len2 - 1, len1 - 1);
        else
            return Math.max(lcs(len2, len1 - 1), lcs(len2 - 1, len1));
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
