package ru.mail.polis.ads.part2.medalexey;

import java.io.*;
import java.util.StringTokenizer;

/**
 *  Задача: Лесенка (https://www.e-olymp.com/ru/problems/262)
 *  Тестирование: https://www.e-olymp.com/ru/submissions/5783643
 */
public class Ladder {

    private Ladder() {

    }

    private static void solve(final FastScanner in, final PrintWriter out) {
        int numberOfStairs = in.nextInt();
        int[] stairs = new int[numberOfStairs + 2];

        fillStairs(stairs, numberOfStairs, in);

        out.println(countBestWayForEachCell(stairs,in.nextInt()));
    }


    private static int countBestWayForEachCell(int[] stairs, final int maxStep) {
        int[] bestWayForEachStair = new int[stairs.length];

        for (int stairIndex = 1; stairIndex < stairs.length; stairIndex++) {
            bestWayForEachStair[stairIndex] = stairs[stairIndex] + bestWayForEachStair[stairIndex - 1];

            for (int step = 2; step <= maxStep; step++) {
                if (stairIndex - step >= 0) {
                    bestWayForEachStair[stairIndex] =
                            Math.max(bestWayForEachStair[stairIndex], stairs[stairIndex]
                                    + bestWayForEachStair[stairIndex - step]);
                }
            }
        }

        return bestWayForEachStair[bestWayForEachStair.length - 1];
    }


    // fill array with input values
    private static void fillStairs(int[] stairs, int numberOfStairs, final FastScanner in) {
        for (int i = 1; i <= numberOfStairs; i++) {
            stairs[i] = in.nextInt();
        }
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
